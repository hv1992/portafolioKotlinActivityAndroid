package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        init {
            // Define click listener for the ViewHolder's View.
            informationCat = view.findViewById(R.id.textViewInformationCat)
            imageCat = view.findViewById(R.id.imageViewCat)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.image_cat_row_recycler_view, viewGroup, false)
        val height: Int = viewGroup.measuredHeight / dataSet.size
        view.minimumHeight = height

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.informationCat.text = dataSet[position].informationCat
        viewHolder.imageCat.setImageBitmap(dataSet[position].imageCatBitmap)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}