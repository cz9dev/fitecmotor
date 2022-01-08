package zaldivar.carlos.fichatcnicademotor.model.repository

import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.daos.LargoHierroDao
import czaldivarp.fitecmotor.model.daos.MarcaDao
import czaldivarp.fitecmotor.model.daos.ModeloDao
import czaldivarp.fitecmotor.model.entities.LargoHierro
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo

class LargoHierroRepository(private val largoHierroDao: LargoHierroDao) {

    val getLargoHierro: LiveData<List<LargoHierro>> = largoHierroDao.getAll()

    suspend fun addLargoHierro(largoHierro: LargoHierro){
        largoHierroDao.insert(largoHierro)
    }

    suspend fun updateLargoHierro(largoHierro: LargoHierro){
        largoHierroDao.update(largoHierro)
    }

    suspend fun deleteLargoHierro(largoHierro: LargoHierro){
        largoHierroDao.delete(largoHierro)
    }
}