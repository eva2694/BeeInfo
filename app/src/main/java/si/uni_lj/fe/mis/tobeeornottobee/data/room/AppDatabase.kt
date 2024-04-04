package si.uni_lj.fe.mis.tobeeornottobee.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity

@Database(
    version = 1,
    entities = [HiveDbEntity::class]
)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?  = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "kasa_app.db")
                    .fallbackToDestructiveMigration()
                    .build()
                instance
            }
        }
    }
    abstract fun getHiveDao(): HiveDao

}