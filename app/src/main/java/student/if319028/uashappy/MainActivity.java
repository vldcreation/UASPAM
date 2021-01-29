package student.if319028.uashappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nama,username,password;
    Button daftar;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Assign obj dengan ID
        setContentView(R.layout.activity_main);
        nama = findViewById(R.id.nama);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        daftar = findViewById(R.id.daftar);
        login = findViewById(R.id.login);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User Entity
                final AkunEntity ae = new AkunEntity();
                ae.setNama(nama.getText().toString());
                ae.setUsername(username.getText().toString());
                ae.setPassword(password.getText().toString());
                if(ValidasiForm(ae)){
                    //Form telah diisi
                    AkunDatabase akundatabase = AkunDatabase.getAkunDatabase(getApplicationContext());
                    final AkunDao akundao = akundatabase.akunDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() { // Daftarkan user menggunakan dao
                            akundao.DaftarAkun(ae);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() { // menjalankan UI Thank di UI Thread
                                    Toast.makeText(getApplicationContext(),"Berhasil di daftarkan , Silahkan masuk!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }else{
                    Toast.makeText(getApplicationContext(),"Isi semua field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });
    }

    private boolean ValidasiForm(AkunEntity ae){
        //return false
        if(ae.getNama().isEmpty() || ae.getUsername().isEmpty() || ae.getPassword().isEmpty() ){
            return false;
        }
        return true;
    }
}