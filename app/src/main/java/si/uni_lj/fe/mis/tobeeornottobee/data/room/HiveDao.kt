package si.uni_lj.fe.mis.tobeeornottobee.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity


@Dao
interface HiveDao {


    @Query("SELECT * FROM HiveDbEntity")
    fun getHives(): LiveData<List<HiveDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHive(hive: HiveDbEntity)

    @Update
    suspend fun updateHive(hive: HiveDbEntity)
}