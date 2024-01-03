package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.dao

import androidx.room.*
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.models.ContactEntity
import kotlinx.coroutines.flow.Flow

/*
    El Dao es una interface en donde establecer los controladores de persistencias de datos, que en este caso es para la tabla de Contactos
    Aqui es donde definis las funciones para insertar, eliminar, actualizar, y tambien para ejecutar querys, que seria con @Query
    El Flow es para manejar valores que se obtiene de manera asincrona, es decir, al declarar como Flow, dices de que el mismo lo obtendras desde un segundo plano, y en un tiempo indeterminado.
 */
@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contactEntity: ContactEntity)

    @Update
    suspend fun update(contactEntity: ContactEntity)

    @Delete
    suspend fun delete(contactEntity: ContactEntity)

    @Query("SELECT * FROM `contact_table`")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM `contact_table` WHERE id = :id")
    fun getContactsById(id: Int): Flow<ContactEntity>

}