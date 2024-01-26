package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.models.ContactEntity

class ListContactRecyclerViewAdapter (private val dataSet: ArrayList<ContactEntity>)
    : RecyclerView.Adapter<ListContactRecyclerViewAdapter.ViewHolder>(){

    /*
        Un ViewHolder en Android es un contenedor de una vista que almacena un diseño que representa un elemento de una lista. Se usa junto con un Adapter y un RecyclerView para mostrar los datos de una forma eficiente y personalizada. Un ViewHolder evita que se tenga que buscar e inflar las vistas cada vez que se desplaza la lista, lo que mejora el rendimiento de la aplicación
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstTextLeft : TextView
        val secondTextLeft : TextView
        val firstTextRight : TextView
        val secondTextRight : TextView

        init {
            firstTextLeft = view.findViewById(R.id.firstTextLeftContact)
            secondTextLeft = view.findViewById(R.id.secondTextLeftContact)
            firstTextRight = view.findViewById(R.id.firstTextRightContact)
            secondTextRight = view.findViewById(R.id.secondTextRightContact)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_contact_row_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val name = dataSet[position].name
        val lastName = dataSet[position].lastName
        val phone = dataSet[position].phone
        val email = dataSet[position].email

        holder.firstTextLeft.text = name
        holder.secondTextLeft.text = lastName
        holder.firstTextRight.text = phone
        holder.secondTextRight.text = email
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }
}