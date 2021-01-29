package student.if319028.uashappy.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.if319028.uashappy.API.ReqData;
import student.if319028.uashappy.API.RetServer;
import student.if319028.uashappy.APIManageSoal;
import student.if319028.uashappy.Model.ResponseModel;
import student.if319028.uashappy.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tambah extends AppCompatActivity {

    private EditText etjudulsoal,etdeskipsi,etopsia,etopsib,etopsic,etopsid;
    private Button btnSave;
    private String tmpJudul,tmpDeskripsi,tmpOpsiA,tmpOpsiB,tmpOpsiC,tmpOpsiD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etjudulsoal = findViewById(R.id.idJudul);
        etdeskipsi = findViewById(R.id.ideskripsi);
        etopsia = findViewById(R.id.idopsiA);
        etopsib = findViewById(R.id.idopsiB);
        etopsic = findViewById(R.id.idopsiC);
        etopsid = findViewById(R.id.idopsiD);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmpJudul = etjudulsoal.getText().toString();
                tmpDeskripsi = etdeskipsi.getText().toString();
                tmpOpsiA = etopsia.getText().toString();
                tmpOpsiB = etopsib.getText().toString();
                tmpOpsiC = etopsic.getText().toString();
                tmpOpsiD = etopsid.getText().toString();

                if(tmpJudul.trim().equals("")){
                    etjudulsoal.setError("Judul Soal harus diisi!");
                }
                else if(tmpDeskripsi.trim().equals("")){
                    etdeskipsi.setError("Deskripsi Soal harus diisi!");
                }else if(tmpOpsiA.trim().equals("")){
                    etopsia.setError("Opsi A Soal harus diisi!");
                }else if(tmpOpsiB.trim().equals("")){
                    etopsib.setError("Opsi B Soal harus diisi!");
                }else if(tmpOpsiC.trim().equals("")){
                    etopsic.setError("Opsi C Soal harus diisi!");
                }else if(tmpOpsiD.trim().equals("")){
                    etopsid.setError("Opsi D Soal harus diisi!");
                }
                else
                    InsertData();
            }
        });
    }

    public void InsertData(){
        ReqData ars = RetServer.hubRetrofit().create(ReqData.class);
        Call<ResponseModel> insertData = ars.ardInsertData(tmpJudul,tmpDeskripsi,tmpOpsiA,tmpOpsiB,tmpOpsiC,tmpOpsiD);

        insertData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean error = response.body().isError();
                String feedback = response.body().getFeedback();

                Toast.makeText(Tambah.this," isError : "+error+"  \n Feedback : "+feedback,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Tambah.this," Gagal Menghubungi Server"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}