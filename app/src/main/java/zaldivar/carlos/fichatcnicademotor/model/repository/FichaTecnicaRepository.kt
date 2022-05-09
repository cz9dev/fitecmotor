package zaldivar.carlos.fichatcnicademotor.model.repository

import androidx.lifecycle.LiveData
import czaldivarp.fitecmotor.model.daos.FichaTecnicaDao
import czaldivarp.fitecmotor.model.daos.LargoHierroDao
import czaldivarp.fitecmotor.model.daos.MarcaDao
import czaldivarp.fitecmotor.model.daos.ModeloDao
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import czaldivarp.fitecmotor.model.entities.LargoHierro
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo
import zaldivar.carlos.fichatcnicademotor.model.FichaTecnicaDetail

class FichaTecnicaRepository(private val fichaTecnicaDao: FichaTecnicaDao) {

    val getFichaTecnica: LiveData<List<FichaTecnicaDetail>> = fichaTecnicaDao.getAll()

    fun addFichaTecnica(fichaTecnica: FichaTecnica): Long {
        return fichaTecnicaDao.insert(fichaTecnica)
    }

    suspend fun updateFichaTecnica(fichaTecnica: FichaTecnica) {
        fichaTecnicaDao.update(fichaTecnica)
    }

    suspend fun updateFavoryte(idFichaTecnica: Int, favoryte: Boolean) {
        fichaTecnicaDao.updateFavoryte(idFichaTecnica, favoryte)
    }

    suspend fun deleteFichaTecnica(fichaTecnica: FichaTecnica) {
        fichaTecnicaDao.delete(fichaTecnica)
    }
}