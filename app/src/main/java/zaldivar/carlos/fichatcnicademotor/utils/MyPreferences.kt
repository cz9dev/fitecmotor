package zaldivar.carlos.fichatcnicademotor.utils

import android.content.Context
import android.preference.PreferenceManager

class MyPreferences(context: Context?) {
    companion object {
        private const val DARK_MODE = "DARK_MODE"
        private const val USER_OBEJECT = "USER_OBEJECT"
        private const val PAID = "PAID"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getBoolean(DARK_MODE, false)
        set(value) = preferences.edit().putBoolean(DARK_MODE, value).apply()

    var userObject = preferences.getString(USER_OBEJECT, "")
        set(value) = preferences.edit().putString(USER_OBEJECT, value).apply()

    var Paid = preferences.getBoolean(PAID, false)
        set(value) = preferences.edit().putBoolean(PAID, value).apply()
}