package czaldivarp.fitecmotor.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import zaldivar.carlos.fichatcnicademotor.model.FichaTecnicaDetail

@Dao
interface FichaTecnicaDao {

    @Query("SELECT * FROM v_ficha_tecnica_all")
    fun getAll(): LiveData<List<FichaTecnicaDetail>>

    @Query("SELECT * FROM v_ficha_tecnica_all WHERE idFichaTecnica = :idFichaTecnica")
    fun findById(idFichaTecnica: Int): List<FichaTecnica>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertALL(fichaTecnica: List<FichaTecnica>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fichaTecnica: FichaTecnica): Long

    @Update
    fun update(fichaTecnica: FichaTecnica)

    @Query("UPDATE FichaTecnica SET fav = :favoryte WHERE idFichaTecnica = :idFichaTecnica")
    fun updateFavoryte(idFichaTecnica: Int, favoryte: Boolean)

    @Delete
    fun delete(fichaTecnica: FichaTecnica)
}