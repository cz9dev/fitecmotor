package zaldivar.carlos.fichatcnicademotor.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

object ImageControler {

    /**
     * Función para seleccionar la imagen desde la galería
     */
    fun selectPhotoFromGalery(activity: Activity, code: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    /**
     * Función para salvar la imagen en el almacenamiento de la APP
     */
    fun saveImage(context: Context, id: Int, uri: Uri) {
        val file = File(context.filesDir, id.toString())

        val byte = context.contentResolver.openInputStream(uri)?.readBytes()!!

        file.writeBytes(byte)
    }

    /**
     * Función para debolver el Uri de la imagen
     */
    fun getImageUri(context: Context, id: Int): Uri {
        val file = File(context.filesDir, id.toString())

        return if (file.exists()) Uri.fromFile(file)
        else Uri.parse("android.resource://zaldivar.carlos.fichatcnicademotor/drawable/ic_motor")
    }

    /**
     * Función para eliminar una imagen del almacenamiento
     */
    fun deleteImage(context: Context, id: Int) {
        val file = File(context.filesDir, id.toString())
        file.delete()
    }
}