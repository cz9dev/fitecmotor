package czaldivarp.fitecmotor.model.daos

import androidx.room.*
import czaldivarp.fitecmotor.model.entities.FichaTecnica

@Dao
interface FichaTecnicaDao {

    @Query("SELECT * FROM FichaTecnica")
    fun getAll(): List<FichaTecnica>

    @Query("SELECT * FROM FichaTecnica WHERE idFichaTecnica = :idFichaTecnica")
    fun findById(idFichaTecnica: Int): List<FichaTecnica>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertALL(fichaTecnica: List<FichaTecnica>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fichaTecnica: FichaTecnica)

    @Update
    fun update(fichaTecnica: FichaTecnica)

    @Delete
    fun delete(fichaTecnica: FichaTecnica)
}