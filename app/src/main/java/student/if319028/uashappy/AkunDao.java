package student.if319028.uashappy;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

@Dao
public interface AkunDao {
    @Insert
    void DaftarAkun(AkunEntity akunEntity);

    @Query("Select * from akun where username=(:username) and password=(:password)")
    AkunEntity login(String username,String password); // abstract method dao
}
