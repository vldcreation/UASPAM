package student.if319028.uashappy.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.if319028.uashappy.API.ReqData;
import student.if319028.uashappy.API.RetServer;
import student.if319028.uashappy.APIManageSoal;
import student.if319028.uashappy.CRUD.Tambah;
import student.if319028.uashappy.Model.ResponseModel;
import student.if319028.uashappy.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import student.if319028.uashappy.Model.DataModel;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context context;
    private List<DataModel> listdatasoal;

    public AdapterData(Context context, List<DataModel> listdatasoal) {
        this.context = context;
        this.listdatasoal = listdatasoal;
    }


    @NonNull
    @Override
//    CardItem ke Recycler view
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_managesoal,parent,false);
        HolderData hd = new HolderData(layout);
        return hd;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listdatasoal.get(position);   // set data berdasarkan posisi yang di get
        holder.tv_id.setText( String.valueOf(dm.getId_soal()));
        holder.tvJudul.setText(String.valueOf(position+1)+" "+dm.getJudulSoal());
        holder.tvDeskripsi.setText(dm.getDeskripsi());
        holder.opsia.setText(dm.getOpsia());
        holder.opsib.setText(dm.getOpsib());
        holder.opsic.setText(dm.getOpsic());
        holder.opsid.setText(dm.getOpsid());
    }

    @Override
    public int getItemCount() {
        return listdatasoal.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tv_id, tvJudul,tvDeskripsi;
        RadioGroup rgOpsi;
        RadioButton opsia,opsib,opsic,opsid;
        private Integer idSoal;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.idsoal);
            tvJudul = itemView.findViewById(R.id.tvjudul);
            tvDeskripsi = itemView.findViewById(R.id.tvdeskripsi);
            opsia = itemView.findViewById(R.id.opsiA);
            opsib = itemView.findViewById(R.id.opsiB);
            opsic = itemView.findViewById(R.id.opsiC);
            opsid = itemView.findViewById(R.id.opsiD);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    idSoal = Integer.parseInt(tv_id.getText().toString());

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setMessage("Pilih Opsi :");
                    alertDialog.setCancelable(true);

                    alertDialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((APIManageSoal)context).retriefData();
                        }
                    });

                    alertDialog.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            context.startActivity(new Intent(context, Tambah.class));
                        }
                    });

                    alertDialog.show();

                    return false;
                }
            });
        }

        public void deleteData(){
            ReqData ardData = RetServer.hubRetrofit().create(ReqData.class);

            Call<ResponseModel> deleteData = ardData.ardDeleteData(idSoal);

            deleteData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    boolean error = response.body().isError();
                    String feedback = response.body().getFeedback();

                    Toast.makeText(context,"error => "+error+" \nFeedback =>"+feedback,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(context,"Gagal Menghubungkan ke Server"+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
