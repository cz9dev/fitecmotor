package zaldivar.carlos.fichatcnicademotor.fichatecnica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.RecyclerView
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import czaldivarp.fitecmotor.model.entities.LargoHierro
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.largohierro.LargoHierroFragment
import zaldivar.carlos.fichatcnicademotor.largohierro.LargoHierroNuevoFragment
import zaldivar.carlos.fichatcnicademotor.utils.ImageControler

class CustomAdapterFichaTecnica(private val fragment: FichaTecnicaFragment) :
    RecyclerView.Adapter<CustomAdapterFichaTecnica.ViewHolder>() {
    var mFichaTecnicaList = emptyList<FichaTecnica>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_ficha_tecnica, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemImage.setImageURI(
            ImageControler.getImageUri(
                this.fragment.requireContext(),
                mFichaTecnicaList[i].idFichaTecnica
            )
        )
        viewHolder.itemNombreMotor.text = "Nombre: " + mFichaTecnicaList[i].nombreMotor
        viewHolder.itemMarcaMotor.text = "Marca: " + mFichaTecnicaList[i].idMarca.toString()
        viewHolder.itemModeloMotor.text = "Modelo: " + mFichaTecnicaList[i].idModelo.toString()
        viewHolder.itemDescriptionMotor.text =
            "Datos enrrollado: " + mFichaTecnicaList[i].datosEnrrollado

        if (mFichaTecnicaList[i].fav) viewHolder.itemFavoryte.setImageResource(R.drawable.ic_favorite)
        else viewHolder.itemFavoryte.setImageResource(R.drawable.ic_favorite_border)

        viewHolder.itemEditFichaTecnica.setOnClickListener {
            fragment.addFragmentToFragment(
                FichaTecnicaNuevaFragment.newInstance(
                    mFichaTecnicaList[i].idFichaTecnica,
                    mFichaTecnicaList[i].nombreMotor,
                    mFichaTecnicaList[i].corrienteNominal,
                    mFichaTecnicaList[i].potencia,
                    mFichaTecnicaList[i].ip,
                    mFichaTecnicaList[i].tensionNominal,
                    mFichaTecnicaList[i].diametroSuccion,
                    mFichaTecnicaList[i].diametroDescarga,
                    mFichaTecnicaList[i].tipoCapacitor,
                    mFichaTecnicaList[i].capacidadCapacitor, // Permanente o Arranque
                    mFichaTecnicaList[i].datosEnrrollado,
                    mFichaTecnicaList[i].fav, // esto es direccion de favorito
                    mFichaTecnicaList[i].idMarca,
                    mFichaTecnicaList[i].idModelo,
                    mFichaTecnicaList[i].idLargoHierro
                )
            )
        }
        // Evento onClick para eliminar una ficha tecnica
        viewHolder.itemDeleteFichaTecnica.setOnClickListener {
            fragment.mFichaTecnicaViewModel.deleteFichaTecnica(this.fragment.requireContext(),mFichaTecnicaList[i].idFichaTecnica)
        }
        // Evento onClick para cambiar estado de favorito
        viewHolder.itemFavoryte.setOnClickListener {
            var stateFav: Boolean

            if (mFichaTecnicaList[i].fav) stateFav = false
            else stateFav = true

            fragment.mFichaTecnicaViewModel.editFavoryte(mFichaTecnicaList[i].idFichaTecnica, stateFav)
        }
    }

    override fun getItemCount(): Int {
        return if (mFichaTecnicaList == null) 0 else mFichaTecnicaList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemNombreMotor: TextView
        var itemMarcaMotor: TextView
        var itemModeloMotor: TextView
        var itemDescriptionMotor: TextView
        var itemFavoryte: ImageView
        var itemEditFichaTecnica: ImageButton
        var itemDeleteFichaTecnica: ImageButton

        init {
            itemImage = itemView.findViewById(R.id.item_imagen)
            itemNombreMotor = itemView.findViewById(R.id.item_nombre_motor)
            itemMarcaMotor = itemView.findViewById(R.id.item_marca)
            itemModeloMotor = itemView.findViewById(R.id.item_modelo)
            itemDescriptionMotor = itemView.findViewById(R.id.item_descripcion)
            itemFavoryte = itemView.findViewById(R.id.item_favorito)
            itemEditFichaTecnica = itemView.findViewById(R.id.itemEditFichaTecnica)
            itemDeleteFichaTecnica = itemView.findViewById(R.id.itemDeleteFichaTecnica)
        }
    }

    fun update(FichaTecnica: List<FichaTecnica>) {
        println("UPDATING DATA")
        this.mFichaTecnicaList = FichaTecnica
        notifyDataSetChanged()
    }

}