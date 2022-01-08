package czaldivarp.fitecmotor.model.daos

import android.view.Display
import androidx.lifecycle.LiveData
import androidx.room.*
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo

@Dao
interface ModeloDao {

    @Query("SELECT * FROM Modelo")
    fun getAll(): LiveData<List<Modelo>>

    @Query("SELECT * FROM Modelo WHERE idModelo = :idModelo")
    fun findById(idModelo: Int): List<Modelo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(modelo: List<Modelo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(modelo: Modelo)

    @Update
    fun update(modelo: Modelo)

    @Delete
    fun delete(modelo: Modelo)
}