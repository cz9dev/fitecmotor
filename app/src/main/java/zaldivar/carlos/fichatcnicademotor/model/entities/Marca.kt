package czaldivarp.fitecmotor.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Marca(
    @PrimaryKey(autoGenerate = true)
    val idMarca: Int,
    val nombreMarca: String
)
