package zaldivar.carlos.fichatcnicademotor.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.FichaTecnicaDatabase
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zaldivar.carlos.fichatcnicademotor.model.repository.MarcaRepository
import zaldivar.carlos.fichatcnicademotor.model.repository.ModeloRepository

class ModeloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ModeloRepository
    private val modelo: LiveData<List<Modelo>>

    init {
        val modeloDao = FichaTecnicaDatabase.getInstance(application).ModeloDao()
        repository = ModeloRepository(modeloDao)
        modelo = repository.getModelo
    }

    fun getModelo(): LiveData<List<Modelo>>{
        return modelo
    }

    fun addModelo(modeloText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addModelo(Modelo(0, modeloText))
        }
    }

    fun editModelo(modeloID: Int, modeloText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateModelo(Modelo(modeloID,modeloText))
        }
    }

    fun deleteModelo(modeloID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteModelo(Modelo(modeloID,""))
        }
    }



}