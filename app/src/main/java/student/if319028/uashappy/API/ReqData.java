package student.if319028.uashappy.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import student.if319028.uashappy.Model.ResponseModel;



public interface ReqData {
    @GET("/UasHappy/retrieve.php/check")
    Call<ResponseModel> reqRetriveData();

    //encode url terlebih dahulu
    @FormUrlEncoded
    @POST("/UasHappy/retrieve.php/insertSoal")
    Call<ResponseModel> ardInsertData(
            @Field("judulsoal") String judulsoal,
            @Field("deskripsi") String deskripsi,
            @Field("opsia") String opsia,
            @Field("opsib") String opsib,
            @Field("opsic") String opsic,
            @Field("opsid") String opsid
    );

    @FormUrlEncoded
    @POST("/UasHappy/retrieve.php/deleteSoal")
    Call<ResponseModel> ardDeleteData(
           @Field("id_soal") Integer id_soal
    );
}
