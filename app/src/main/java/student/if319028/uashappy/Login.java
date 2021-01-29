package student.if319028.uashappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // buat validasi
                final String inpUsername = username.getText().toString();
                final String inpPassword = password.getText().toString();
                if(inpUsername.isEmpty() || inpPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Isi Field yang kosong",Toast.LENGTH_SHORT).show();
                }else{
                    // jika data cocok
                    // jalankan query
                    AkunDatabase akunDatabase = AkunDatabase.getAkunDatabase(getApplicationContext());
                    final AkunDao akunDao = akunDatabase.akunDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AkunEntity ae = akunDao.login(inpUsername,inpPassword);
                            // Check entity
                            if(ae == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Data yang dimasukan salah!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                String curName = ae.nama;
                                startActivity(new Intent(Login.this,Welcome.class)
                                        .putExtra("txtnama",curName));
                            }
                        }
                    }).start();
                }
            }
        });
    }
}