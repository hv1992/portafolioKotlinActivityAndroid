package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.dataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.dao.ContactDao
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.models.ContactEntity

/**
 * Aqui es donde creamos y configuramos la base de datos para guardar nuestro contactos.
 * Aqui tambien vamos a trabajar con clases abstractas.
 * Las clases abstractas son clases que no pueden ser instanciados, pero puedes heredar subclases de ellas.
 * Se usa como herencia, en donde el hijo establece las acciones heredadas mediante el override.
 * Se coloca el numero de version para establecer de que va a haber actualizacion en la base de datos.
 * Si va a haber nuevas tablas o mas cosas, se debe actualizar el numero de version.
 * Cada que vez que hay una nueva version, lo que hace es migrar de la vieja a la nueva version.
 * Para nuestro caso, si no encuentra una migracion adecuada, borra y reemplaza todas las tablas existentes por medio del fallbackToDestructiveMigration.
 */

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactDao

    companion object {

        //Con esto establecemos de que, cuando se haga el getInstance, no se inicialice la base de datos de forma automatica, provocando que asi no tengamos problemas de rendimiento.
        //@Volatile es una anotación de Kotlin que se usa para marcar el campo de respaldo de JVM de la propiedad anotada como volátil, lo que significa que las escrituras en este campo se hacen inmediatamente visibles para otros hilos
        @Volatile
        private var INSTANCE : ContactDatabase? = null

        fun getInstance(context: Context) : ContactDatabase {

            //El synchronized en Kotlin es una palabra clave que se usa para sincronizar el acceso a recursos compartidos y asegurar que solo un hilo pueda acceder a una sección crítica a la vez. Es decir, es como el famoso DispatchQueue.main.async
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    //Aqui se crea la base de datos, pero lo que hace es volver a crear uno nuevo
                    instance = Room.databaseBuilder(context.applicationContext,ContactDatabase::class.java,"contact_book_database").fallbackToDestructiveMigration().build()
                    //El fallbackToDestructiveMigration es un método que se puede usar al construir una base de datos con Room. Este método le indica a Room que borre y reemplace todas las tablas existentes si no encuentra una migración adecuada entre las versiones de la base de datos¹. Esto puede ser útil si no quieres escribir una lógica de migración compleja o si quieres empezar de cero con una nueva versión de la base de datos². Sin embargo, ten en cuenta que este método eliminará todos los datos de los usuarios, por lo que solo debes usarlo con precaución

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}