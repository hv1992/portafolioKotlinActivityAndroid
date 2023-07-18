package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatImageRecyclerModel

class ListCatImageRecyclerViewAdapter(private val dataSet: ArrayList<CatImageRecyclerModel>)
    : RecyclerView.Adapter<ListCatImageRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageCat : ImageView
        val informationCat : TextView
        var constraintLayoutMain : ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            informationCat = view.findViewById(R.id.textViewInformationCat)
            imageCat = view.findViewById(R.id.imageViewCat)
            constraintLayoutMain = view.findViewById(R.id.constraintLayoutMainCatImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_image_cat_row_recycler, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        //Aqui es donde configuramos los elementos de la fila

        //Agregamos el texto
        if (dataSet[position].informationCat == "null") {
            viewHolder.informationCat.text = ""
        } else {
            viewHolder.informationCat.text = dataSet[position].informationCat
        }

        //Agregamos la imagen
        //Establecemos el radio de la imagen
        val cornerRadius = RoundedCorners(16)
        //Aplicamos el cornerRadius, y cargamos la imagen en el ImageView, con Glide.
        Glide.with(viewHolder.imageCat.context)
            .load(dataSet[position].imageCatBitmap)
            .apply(RequestOptions.bitmapTransform(cornerRadius))
            .into(viewHolder.imageCat)

        //Establecemos el tamaño de la fila del recyclerView
        val bitMapHeight = dataSet[position].imageCatBitmap.height //Tamaño del ImageView
        var heightText : Int = 100 //Tamaño del TextView
        val totalHeight = bitMapHeight + heightText //Total de altura

        //Establecemos el tamaño de la fila
        val layoutParam = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,totalHeight)
        viewHolder.constraintLayoutMain.layoutParams = layoutParam
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}