package student.if319028.uashappy;

import retrofit2.Callback;
import retrofit2.Response;
import student.if319028.uashappy.API.ReqData;
import student.if319028.uashappy.Adapter.AdapterData;
import student.if319028.uashappy.CRUD.Tambah;
import student.if319028.uashappy.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import student.if319028.uashappy.API.RetServer;
import student.if319028.uashappy.Model.DataModel;
import student.if319028.uashappy.Model.ResponseModel;

public class APIManageSoal extends AppCompatActivity {
    private RecyclerView rvdata;
    private RecyclerView.Adapter addata;
    private RecyclerView.LayoutManager lmdata;
    private List<DataModel> listdata = new ArrayList<>();
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_i_manage_soal);

        rvdata = findViewById(R.id.rvlistdata);
        fabAdd = findViewById(R.id.fab_add);
        lmdata = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvdata.setLayoutManager(lmdata);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(APIManageSoal.this, Tambah.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        retriefData();
    }

    public void retriefData(){
        ReqData ars = RetServer.hubRetrofit().create(ReqData.class);
        Call<ResponseModel> viewdata = ars.reqRetriveData();

        viewdata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean error = response.body().isError();
                String feedback = response.body().getFeedback();

                Toast.makeText(APIManageSoal.this," isError : "+error+"  \n Feedback : "+feedback,Toast.LENGTH_SHORT).show();

                listdata = response.body().getHasil();

                addata = new AdapterData(APIManageSoal.this,listdata);

                rvdata.setAdapter(addata);
                addata.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(APIManageSoal.this,"Gagal Menghubungkan ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}