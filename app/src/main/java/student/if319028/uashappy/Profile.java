package student.if319028.uashappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView backtoHome,VTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backtoHome = findViewById(R.id.btnBacktoHome);
        VTN = findViewById(R.id.valueTextNama);
        String curNama = getIntent().getStringExtra("txtnama");
        VTN.setText(curNama);
        backtoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Welcome.class));
            }
        });
    }
}