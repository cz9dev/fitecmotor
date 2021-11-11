package zaldivar.carlos.fichatcnicademotor.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.FichaTecnicaDatabase
import czaldivarp.fitecmotor.model.entities.Marca
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zaldivar.carlos.fichatcnicademotor.model.repository.MarcaRepository

class MarcaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MarcaRepository
    private val marcas: LiveData<List<Marca>>

    init {
        val marcaDao = FichaTecnicaDatabase.getInstance(application).MarcaDao()
        repository = MarcaRepository(marcaDao)
        marcas = repository.getMarcas
    }

    fun getMarcas(): LiveData<List<Marca>>{
        return marcas
    }

    fun addMarca(marcaText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addMarca(Marca(0, marcaText))
        }
    }

    fun editMarca(marcaID: Int, marcaText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateMarca(Marca(marcaID,marcaText))
        }
    }

    fun deleteMarca(marcaID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteMarca(Marca(marcaID,""))
        }
    }



}