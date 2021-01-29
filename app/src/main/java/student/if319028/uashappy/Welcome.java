package student.if319028.uashappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Welcome extends AppCompatActivity {

    TextView curName;
    RelativeLayout rellay_Profile,rellay_manageSoal,rellay_ReadSoal,rellayAbout;   //assign Relay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        curName = findViewById(R.id.txtnama);
        String nama = getIntent().getStringExtra("txtnama");
        curName.setText(nama);
        rellay_Profile = findViewById(R.id.rellay_Profile);
        rellay_manageSoal = findViewById(R.id.rellay_manageSoal);
        rellay_ReadSoal = findViewById(R.id.rellay_ReadSoal);
        rellayAbout = findViewById(R.id.rellayAbout);

        rellay_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Profile.class)
                        .putExtra("txtnama",nama);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        rellay_manageSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,APIManageSoal.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        rellay_ReadSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Profile.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        rellayAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Profile.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}