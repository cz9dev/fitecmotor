package czaldivarp.fitecmotor.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Modelo(
    @PrimaryKey(autoGenerate = true)
    val idModelo: Int,
    val nombreModelo: String
)
