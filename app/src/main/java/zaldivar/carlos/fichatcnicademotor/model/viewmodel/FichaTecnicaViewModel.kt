package zaldivar.carlos.fichatcnicademotor.model.viewmodel

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.FichaTecnicaDatabase
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zaldivar.carlos.fichatcnicademotor.model.repository.FichaTecnicaRepository
import zaldivar.carlos.fichatcnicademotor.utils.ImageControler

class FichaTecnicaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FichaTecnicaRepository
    private val fichaTecnica: LiveData<List<FichaTecnica>>

    init {
        val fichaTecnicaDao = FichaTecnicaDatabase.getInstance(application).FichaTecnicaDao()
        repository = FichaTecnicaRepository(fichaTecnicaDao)
        fichaTecnica = repository.getFichaTecnica
    }

    fun getFichaTecnica(): LiveData<List<FichaTecnica>> {
        return fichaTecnica
    }

    fun addFichaTecnica(
        nombreMotor: String,
        corrienteNominal: Double,
        potencia: Double,
        ip: Int,
        tencionNominal: Double,
        diametroSuccion: Double,
        diametroDescarga: Double,
        tipoCapacitor: String,
        capacidadCapacitor: String,
        datosEnrollado: String,
        fav: Boolean,
        idMarca: Int,
        idModelo: Int,
        idLargoHierro: Int,
        imageUri: Uri? = null,
        context: Context
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            var idFicha = repository.addFichaTecnica(
                FichaTecnica(
                    0,
                    nombreMotor,
                    corrienteNominal,
                    potencia,
                    ip,
                    tencionNominal,
                    diametroSuccion,
                    diametroDescarga,
                    tipoCapacitor,
                    capacidadCapacitor,
                    datosEnrollado,
                    fav,
                    idMarca,
                    idModelo,
                    idLargoHierro
                )
            )
            println("Este es el id de la foto: $idFicha")
            imageUri?.let { ImageControler.saveImage(context, idFicha.toInt(), it) }
        }
    }

    fun editFichaTecnica(
        idFichaTecnica: Int,
        nombreMotor: String,
        corrienteNominal: Double,
        potencia: Double,
        ip: Int,
        tencionNominal: Double,
        diametroSuccion: Double,
        diametroDescarga: Double,
        tipoCapacitor: String,
        capacidadCapacitor: String,
        datosEnrollado: String,
        fav: Boolean,
        idMarca: Int,
        idModelo: Int,
        idLargoHierro: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateFichaTecnica(
                FichaTecnica(
                    idFichaTecnica,
                    nombreMotor,
                    corrienteNominal,
                    potencia,
                    ip,
                    tencionNominal,
                    diametroSuccion,
                    diametroDescarga,
                    tipoCapacitor,
                    capacidadCapacitor,
                    datosEnrollado,
                    fav,
                    idMarca,
                    idModelo,
                    idLargoHierro
                )
            )
        }
    }

    fun editFavoryte(idFichaTecnica: Int, favoryte: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateFavoryte(idFichaTecnica, favoryte)
        }
    }

    fun deleteFichaTecnica(context: Context, idFichaTecnica: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFichaTecnica(
                FichaTecnica(
                    idFichaTecnica,
                    "",
                    0.0,
                    0.0,
                    0,
                    0.0,
                    0.0,
                    0.0,
                    "",
                    "",
                    "",
                    true,
                    0,
                    0,
                    0
                )
            )
            ImageControler.deleteImage(context, idFichaTecnica)
        }
    }


}