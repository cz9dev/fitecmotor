package czaldivarp.fitecmotor.model.entities

import android.icu.number.FormattedNumberRange
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys =
    [
        ForeignKey(
            entity = Marca::class,
            parentColumns = arrayOf("idMarca"),
            childColumns = arrayOf("idMarca"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Modelo::class,
            parentColumns = arrayOf("idModelo"),
            childColumns = arrayOf("idModelo"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LargoHierro::class,
            parentColumns = arrayOf("idLargoHierro"),
            childColumns = arrayOf("idLargoHierro"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices =
    [
        Index(value = arrayOf("idMarca")),
        Index(value = arrayOf("idModelo")),
        Index(value = arrayOf("idLargoHierro"))
    ]
)
data class FichaTecnica(
    @PrimaryKey(autoGenerate = true) val idFichaTecnica: Int,
    val nombreMotor: String,
    val corrienteNominal: Double,
    val potencia: Double,
    val ip: Int,
    val tensionNominal: Double,
    val diametroSuccion: Double,
    val diametroDescarga: Double,
    val tipoCapacitor: String,
    val capacidadCapacitor: String, // Permanente o Arranque
    val datosEnrrollado: String,
    val direccionFoto: String,
    val idMarca: Int,
    val idModelo: Int,
    val idLargoHierro: Int
)
