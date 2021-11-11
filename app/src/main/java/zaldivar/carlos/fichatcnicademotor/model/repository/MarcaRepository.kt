package zaldivar.carlos.fichatcnicademotor.model.repository

import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.daos.MarcaDao
import czaldivarp.fitecmotor.model.entities.Marca

class MarcaRepository(private val marcaDao: MarcaDao) {

    val getMarcas: LiveData<List<Marca>> = marcaDao.getAll()

    suspend fun addMarca(marca: Marca){
        marcaDao.insert(marca)
    }

    suspend fun updateMarca(marca: Marca){
        marcaDao.update(marca)
    }

    suspend fun deleteMarca(marca: Marca){
        marcaDao.delete(marca)
    }
}