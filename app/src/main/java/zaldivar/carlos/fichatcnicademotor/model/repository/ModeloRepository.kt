package zaldivar.carlos.fichatcnicademotor.model.repository

import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.daos.ModeloDao
import czaldivarp.fitecmotor.model.entities.Modelo

class ModeloRepository(private val modeloDao: ModeloDao) {

    val getModelo: LiveData<List<Modelo>> = modeloDao.getAll()

    suspend fun addModelo(modelo: Modelo){
        modeloDao.insert(modelo)
    }

    suspend fun updateModelo(modelo: Modelo){
        modeloDao.update(modelo)
    }

    suspend fun deleteModelo(modelo: Modelo){
        modeloDao.delete(modelo)
    }
}