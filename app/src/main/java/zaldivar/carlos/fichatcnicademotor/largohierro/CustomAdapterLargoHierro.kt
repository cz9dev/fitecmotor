package zaldivar.carlos.fichatcnicademotor.largohierro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import czaldivarp.fitecmotor.model.entities.LargoHierro
import czaldivarp.fitecmotor.model.entities.Marca
import zaldivar.carlos.fichatcnicademotor.R

class CustomAdapterLargoHierro(private val fragment: LargoHierroFragment) :
    RecyclerView.Adapter<CustomAdapterLargoHierro.ViewHolder>() {
    var mLargoHierroList = emptyList<LargoHierro>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_largo_hierro, viewGroup, false)
        return ViewHolder(v)
    }

    //tengo que arreglar esta funcion
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemLargoHierro.text = mLargoHierroList[i].largo.toString()
        viewHolder.itemDiametroInterior.text = mLargoHierroList[i].diametroInterior.toString()
        viewHolder.itemDiametroExterior.text = mLargoHierroList[i].diametroExterior.toString()
        viewHolder.itemEditLargoHierro.setOnClickListener {
            //fragment.raiseDialog(mLargoHierroList[i].idLargoHierro)
            // Para editar
            fragment.addFragmentToFragment(
                LargoHierroNuevoFragment.newInstance(
                    mLargoHierroList[i].idLargoHierro,
                    mLargoHierroList[i].largo,
                    mLargoHierroList[i].diametroInterior,
                    mLargoHierroList[i].diametroExterior
                )
            )
        }
        viewHolder.itemDeleteLargoHierro.setOnClickListener {
            fragment.mLargoHierroViewModel.deleteLargoHierro(mLargoHierroList[i].idLargoHierro)
        }
    }

    override fun getItemCount(): Int {
        return if (mLargoHierroList == null) 0 else mLargoHierroList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemLargoHierro: TextView
        var itemDiametroInterior: TextView
        var itemDiametroExterior: TextView
        var itemEditLargoHierro: ImageButton
        var itemDeleteLargoHierro: ImageButton

        init {
            itemLargoHierro = itemView.findViewById(R.id.item_largo_h)
            itemDiametroInterior = itemView.findViewById(R.id.item_diametro_i)
            itemDiametroExterior = itemView.findViewById(R.id.item_diametro_e)
            itemEditLargoHierro = itemView.findViewById(R.id.ibEditLargoHierro)
            itemDeleteLargoHierro = itemView.findViewById(R.id.ibDeleteLargoHierro)
        }
    }

    fun update(largoHierro: List<LargoHierro>) {
        println("UPDATING DATA")
        this.mLargoHierroList = largoHierro
        notifyDataSetChanged()
    }
}