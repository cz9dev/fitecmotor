package zaldivar.carlos.fichatcnicademotor.modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import czaldivarp.fitecmotor.model.entities.Marca
import czaldivarp.fitecmotor.model.entities.Modelo
import zaldivar.carlos.fichatcnicademotor.R

class CustomAdapterModelo(private val fragment: ModeloFragment) : RecyclerView.Adapter<CustomAdapterModelo.ViewHolder>() {
    var mModeloLists = emptyList<Modelo>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_modelo, viewGroup, false)
        return ViewHolder(v)
    }

    //tengo que arreglar esta funcion
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemModelo.text = mModeloLists[i].nombreModelo
        viewHolder.itemEditModelo.setOnClickListener {
            //fragment.raiseDialog(mModeloLists[i].idModelo)
            fragment.addFragmentToFragment(ModeloNuevoFragment.newInstance(mModeloLists[i].idModelo,mModeloLists[i].nombreModelo))
        }
        viewHolder.itemDeleteModelo.setOnClickListener {
            fragment.mModeloViewModel.deleteModelo(mModeloLists[i].idModelo)
        }
    }

    override fun getItemCount(): Int {
        return if (mModeloLists == null) 0 else mModeloLists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemModelo: TextView
        var itemEditModelo: ImageButton
        var itemDeleteModelo: ImageButton

        init {
            itemModelo = itemView.findViewById(R.id.item_modelo_modelo)
            itemEditModelo = itemView.findViewById(R.id.ibEditModelo)
            itemDeleteModelo = itemView.findViewById(R.id.ibDeleteModelo)
        }
    }

    fun update(modelo: List<Modelo>) {
        println("UPDATING DATA")
        this.mModeloLists = modelo
        notifyDataSetChanged()
    }
}