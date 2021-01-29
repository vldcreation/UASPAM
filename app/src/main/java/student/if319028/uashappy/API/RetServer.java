package student.if319028.uashappy.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetServer {
    private static final String baseUrl = "http://192.168.43.192:80";
    private static Retrofit retro;

    //method untuk menghubungkan retrofit
    public static Retrofit hubRetrofit(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }



}
