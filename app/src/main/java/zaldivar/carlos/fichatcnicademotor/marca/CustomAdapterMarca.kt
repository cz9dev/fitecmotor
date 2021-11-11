package zaldivar.carlos.fichatcnicademotor.marca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import czaldivarp.fitecmotor.model.entities.Marca
import zaldivar.carlos.fichatcnicademotor.R

class CustomAdapterMarca() : RecyclerView.Adapter<CustomAdapterMarca.ViewHolder>() {
    var mMarcaLists = emptyList<Marca>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_marca, viewGroup, false)
        return ViewHolder(v)
    }

    //tengo que arreglar esta funcion
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemMarca.text = mMarcaLists.get(i).nombreMarca
    }

    override fun getItemCount(): Int {
        //return marca.size
        return if (mMarcaLists == null) 0 else mMarcaLists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemMarca: TextView

        init {
            itemMarca = itemView.findViewById(R.id.item_marca_marca)
        }
    }

    fun update(marcas: List<Marca>) {
        println("UPDATING DATA")
        this.mMarcaLists = marcas
        notifyDataSetChanged()
    }
}