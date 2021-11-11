package czaldivarp.fitecmotor.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LargoHierro(
    @PrimaryKey(autoGenerate = true)
    val idLargoHierro: Int,
    val largo: Double,
    val diametroInterior: Double,
    val diametroExterior: Double
)
