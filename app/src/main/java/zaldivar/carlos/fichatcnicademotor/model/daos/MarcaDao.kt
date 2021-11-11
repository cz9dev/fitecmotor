package czaldivarp.fitecmotor.model.daos

import android.view.Display
import androidx.lifecycle.LiveData
import androidx.room.*
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo

@Dao
interface MarcaDao {

    @Query("SELECT * FROM Marca")
    fun getAll(): LiveData<List<Marca>>

    @Query("SELECT * FROM Marca WHERE idMarca = :idMarca")
    fun findById(idMarca: Int): List<Marca>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(marca: List<Marca>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(marca: Marca)

    @Update
    fun update(marca: Marca)

    @Delete
    fun delete(marca: Marca)
}