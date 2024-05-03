package si.uni_lj.fe.mis.tobeeornottobee.model.hives.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["latitude", "longitude"],
    unique = true)])
data class HiveDbEntity (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo
        val name: String="",
        @ColumnInfo(name = "image_url")
        val imageURL: String = "",
        @ColumnInfo
        val latitude: Double= 0.0,
        @ColumnInfo
        val longitude: Double=0.0,
        @ColumnInfo
        val humidity: Float=0f,
        @ColumnInfo
        val temperature: Float =0f,
        @ColumnInfo(name = "bee_count")
        val beeCount: Int =0 ,
        @ColumnInfo
        val voc: Int = 0,
        @ColumnInfo(name = "is_collect")
        val isCollect: Boolean = false

)