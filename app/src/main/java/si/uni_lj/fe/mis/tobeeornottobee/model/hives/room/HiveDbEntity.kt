package si.uni_lj.fe.mis.tobeeornottobee.model.hives.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HiveDbEntity (
    @PrimaryKey
        val id: Int,
        val name: String
)