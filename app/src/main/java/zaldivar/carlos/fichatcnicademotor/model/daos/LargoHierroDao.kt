package czaldivarp.fitecmotor.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import czaldivarp.fitecmotor.model.entities.LargoHierro

@Dao
interface LargoHierroDao {

    @Query("SELECT * FROM LargoHierro")
    fun getAll(): LiveData<List<LargoHierro>>

    @Query("SELECT * FROM LargoHierro WHERE idLargoHierro = :idLargoHierro")
    fun findById(idLargoHierro: Int): List<LargoHierro>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertALL(largoHierro: List<LargoHierro>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(largoHierro: LargoHierro)

    @Update
    fun update(largoHierro: LargoHierro)

    @Delete
    fun delete(largoHierro: LargoHierro)
}