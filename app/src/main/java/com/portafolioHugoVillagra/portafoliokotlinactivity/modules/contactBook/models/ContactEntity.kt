package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
- El @ColumnInfo sirve para configurar la columna de la tabla, como por ejemplo, el campo name es para colocar un nombre diferente al de tu variable
- @PrimaryKey sirve para establecer el indice de tu elemento, y de esta manera, aunque haya informaciones iguales, sea una fila diferente.
*/
@Entity(tableName = "contact_table") //Se establece el nombre de la tabla
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var name : String = "",
    var lastName : String = "",
    var phone : String = "",
    var email : String = ""
)
