package mx.com.lgonzalez.pruebatecnica.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)