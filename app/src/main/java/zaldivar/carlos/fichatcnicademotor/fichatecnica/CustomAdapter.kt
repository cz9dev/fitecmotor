package zaldivar.carlos.fichatcnicademotor.fichatecnica

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import czaldivarp.fitecmotor.model.entities.FichaTecnica
import zaldivar.carlos.fichatcnicademotor.R

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    val images = intArrayOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
    )
    val motor = arrayOf(
        "Motor 1",
        "Motor 2",
        "Motor 3",
        "Motor 4",
        "Motor 5",
    )
    val marca = arrayOf(
        "Baldor",
        "Weg",
        "Siemens",
        "Sumitomo",
        "Emerson",
    )
    val modelo = arrayOf(
        "Modelo 1",
        "Modelo 2",
        "Modelo 1",
        "Modelo 4",
        "AHDK_67",
    )
    val descripcion = arrayOf(
        "Motor trifasico, de IP 55, potencia 7,5 CV, Tensión 220V",
        "Motor trifasico, de IP 55, potencia 7,5 CV, Tensión 220V",
        "Motor trifasico, de IP 55, potencia 7,5 CV, Tensión 220V",
        "Motor trifasico, de IP 55, potencia 7,5 CV, Tensión 220V",
        "Motor trifasico, de IP 55, potencia 7,5 CV, Tensión 220V",
    )
    val fav = intArrayOf(
        R.drawable.ic_favorite_border,
        R.drawable.ic_favorite,
        R.drawable.ic_favorite_border,
        R.drawable.ic_favorite_border,
        R.drawable.ic_favorite,
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ficha_tecnica,viewGroup,false)
        return ViewHolder(v)
    }

    //tengo que arreglar esta funcion
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemNombreMotor.text = motor[i]
        viewHolder.itemMarcaMotor.text = marca[i]
        viewHolder.itemModeloMotor.text = modelo[i]
        viewHolder.itemDescriptionMotor.text = descripcion[i]
        viewHolder.itemFavoryte.setImageResource(fav[i])
    }

    override fun getItemCount(): Int {
        return motor.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemNombreMotor: TextView
        var itemMarcaMotor: TextView
        var itemModeloMotor: TextView
        var itemDescriptionMotor: TextView
        var itemFavoryte: ImageView

        init {
            itemImage = itemView.findViewById(R.id.item_imagen)
            itemNombreMotor = itemView.findViewById(R.id.item_nombre_motor)
            itemMarcaMotor = itemView.findViewById(R.id.item_marca)
            itemModeloMotor = itemView.findViewById(R.id.item_modelo)
            itemDescriptionMotor = itemView.findViewById(R.id.item_descripcion)
            itemFavoryte = itemView.findViewById(R.id.item_favorito)
        }
    }


}