package zaldivar.carlos.fichatcnicademotor.utils

import android.content.Context
import android.net.Uri
import android.os.RemoteException
import android.util.Log

class PaidCheked {
    companion object {

        private const val APKLIS_PROVIDER = "content://cu.uci.android.apklis.payment.provider/app/"
        private const val APKLIS_PAID = "paid"
        private const val APKLIS_USER_NAME = "user_name"
        private const val TAG = "user_name"
    }
    fun isPurchased(context: Context, packageId: String): String {
        val providerURI: Uri = Uri.parse("$APKLIS_PROVIDER$packageId")
        try {
            val contentResolver =
                context.contentResolver.acquireContentProviderClient(providerURI)
                    ?: return "num00" // No cuenta con Apklis instalada
            val cursor =
                contentResolver.query(providerURI, null, null, null, null)
                    ?: return "num01"  //
            cursor?.let {
                if (it.moveToFirst()) {
                    Log.d(TAG, "Cheked: " + it.getString(it.getColumnIndex(APKLIS_USER_NAME)))
                    if (it.getString(it.getColumnIndex(APKLIS_USER_NAME)) == null) {
                        return "num02" // Usuario no autenticado en Apklis
                    }
                    return if (it.getInt(it.getColumnIndex(APKLIS_PAID)) > 0) {
                        "num04" // Aplicación comprada
                    } else {
                        "num03" // Aplicación no comprada
                    }
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                contentResolver?.close()
            } else {
                contentResolver?.release()
            }
            cursor?.close()
        } catch (e: RemoteException) {
            e.printStackTrace()
            return "num05" //
        }
        return "num05" //
    }
}