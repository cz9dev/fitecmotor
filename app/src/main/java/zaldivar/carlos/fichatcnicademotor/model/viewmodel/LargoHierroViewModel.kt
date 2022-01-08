package zaldivar.carlos.fichatcnicademotor.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.FichaTecnicaDatabase
import czaldivarp.fitecmotor.model.entities.LargoHierro
import czaldivarp.fitecmotor.model.entities.Marca
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zaldivar.carlos.fichatcnicademotor.model.repository.LargoHierroRepository
import zaldivar.carlos.fichatcnicademotor.model.repository.MarcaRepository

class LargoHierroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LargoHierroRepository
    private val largoHierro: LiveData<List<LargoHierro>>

    init {
        val largoHierroDao = FichaTecnicaDatabase.getInstance(application).LargoHierroDao()
        repository = LargoHierroRepository(largoHierroDao)
        largoHierro = repository.getLargoHierro
    }

    fun getLargoHierro(): LiveData<List<LargoHierro>>{
        return largoHierro
    }

    fun addLargoHierro(largo: Double, diametroInterior: Double, diametroExterior: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addLargoHierro(LargoHierro(0, largo, diametroInterior, diametroExterior))
        }
    }

    fun editLargoHierro(largoHierroID: Int, largo: Double, diametroInterior: Double, diametroExterior: Double){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateLargoHierro(LargoHierro(largoHierroID, largo, diametroInterior, diametroExterior))
        }
    }

    fun deleteLargoHierro(largoHierroID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteLargoHierro(LargoHierro(largoHierroID,0.00, 0.00, 0.00))
        }
    }



}