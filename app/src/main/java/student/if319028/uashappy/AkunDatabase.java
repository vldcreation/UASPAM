package student.if319028.uashappy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AkunEntity.class},version = 3,exportSchema = false)
public abstract class AkunDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "akun";
    private static AkunDatabase DATABASE_USER;

    public static synchronized AkunDatabase getAkunDatabase(Context context){

        // jika tidak ada USerDatabase yang di assign
        if(DATABASE_USER == null){
            DATABASE_USER = Room.databaseBuilder(context,AkunDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return DATABASE_USER;
    }

    public abstract AkunDao akunDao();


}
