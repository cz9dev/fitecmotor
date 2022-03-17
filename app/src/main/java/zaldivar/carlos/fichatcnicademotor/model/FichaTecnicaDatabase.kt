package czaldivarp.fitecmotor.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import czaldivarp.fitecmotor.model.daos.FichaTecnicaDao
import czaldivarp.fitecmotor.model.daos.LargoHierroDao
import czaldivarp.fitecmotor.model.daos.MarcaDao
import czaldivarp.fitecmotor.model.daos.ModeloDao
import czaldivarp.fitecmotor.model.entities.LargoHierro
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo

@Database(
    entities = [(FichaTecnica::class), (Modelo::class), (Marca::class), (LargoHierro::class)],
    version = 4,
    exportSchema = false
)
abstract class FichaTecnicaDatabase : RoomDatabase() {

    abstract fun FichaTecnicaDao(): FichaTecnicaDao

    abstract fun ModeloDao(): ModeloDao

    abstract fun MarcaDao(): MarcaDao

    abstract fun LargoHierroDao(): LargoHierroDao

    companion object {

        /**
         * The only instance
         */
        private var isInstance: FichaTecnicaDatabase? = null

        /**
         * Gets the singleton instance of appDatabase.
         *
         * @param context The context.
         * @return The singleton instance of appDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): FichaTecnicaDatabase {
            if (isInstance == null) {
                isInstance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        FichaTecnicaDatabase::class.java,
                        "ficha_tecnica"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            synchronized(this){  // protection from concurrent execution on multiple threads
                val isInstance = Room.databaseBuilder(
                    context.applicationContext,
                    FichaTecnicaDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
            }
            return isInstance!!
        }
    }
}