package zaldivar.carlos.fichatcnicademotor

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import zaldivar.carlos.fichatcnicademotor.fichatecnica.FichaTecnicaFragment
import zaldivar.carlos.fichatcnicademotor.largohierro.LargoHierroFragment
import zaldivar.carlos.fichatcnicademotor.marca.MarcaFragment
import zaldivar.carlos.fichatcnicademotor.modelo.ModeloFragment
import zaldivar.carlos.fichatcnicademotor.splash.SplashFragment
import zaldivar.carlos.fichatcnicademotor.utils.MyPreferences
import zaldivar.carlos.fichatcnicademotor.utils.PaidCheked

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var progressBar: ProgressBar? = null
    private var i = 0
    private val handler = Handler()
    private var countExit = 1

    //private var PACKAGE_ID: String = "zaldivar.calos.fichatcnicademotor"
    private var PACKAGE_ID: String = "zaldivar.carlos.calcelect"
    var theme = "blanco"

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var tongle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI
        mDrawerLayout = findViewById(R.id.drawer_layout)
        var toolbar: Toolbar? = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        tongle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(tongle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        mNavigationView = findViewById(R.id.nav_view)
        mNavigationView.setNavigationItemSelectedListener(this)
        mNavigationView.setCheckedItem(R.id.nav_ficha_tecnica) // Para seleccionar el item ficha técnica

        //PACKAGE_ID = packageName
        PACKAGE_ID = "zaldivar.carlos.calcelect"
        // find progressbar by its id
        progressBar = findViewById(R.id.progressBar)


        if (MyPreferences(this).darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Iniciar el fragment Splash (pacotilla de la app jeje)
        addFragmentToActivity(SplashFragment())

        //i = progressBar?.progress!!
        Thread(Runnable {
            // this loop will run until the value of i becomes 99
            while (i < 100) {
                i += 1
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    progressBar?.progress = i
                })
                try {
                    Thread.sleep(30)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            makeDecision()

        }).start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.conf_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (tongle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.action_modo_oscuro -> {
                changeTheme()
                mNavigationView.setCheckedItem(R.id.nav_ficha_tecnica)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            if (countExit == 0) {
                super.onBackPressed()
            }
            else{
                Toast.makeText(this,"Haz clic una vez más para salir", Toast.LENGTH_LONG).show()
                countExit--
            }
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_ficha_tecnica -> addFragmentToActivityBack(FichaTecnicaFragment())
            R.id.nav_largo_hierro -> addFragmentToActivityBack(LargoHierroFragment())
            R.id.nav_marca -> addFragmentToActivityBack(MarcaFragment())
            R.id.nav_modelo -> addFragmentToActivityBack(ModeloFragment())
            R.id.nav_ayuda -> Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()
            R.id.nav_acerca_de -> showDialogAbout()
        }
        //setTitle(item.title)
        countExit = 1 // Reiniciar a 1 el contador de toques para salir de la app
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        tongle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        tongle.onConfigurationChanged(newConfig)
    }

    fun makeDecision() {
        //addFragmentToActivity(RequerimentFragment())
        if (MyPreferences(this).Paid) {
            addFragmentToActivity(FichaTecnicaFragment())
        } else {
            if (PaidCheked().isPurchased(this, PACKAGE_ID) == "num04") {
                MyPreferences(this).Paid = true
                addFragmentToActivity(FichaTecnicaFragment())
            } else {
                MyPreferences(this).Paid = false
                addFragmentToActivity(RequerimentFragment.newInstance(theme, ""))
            }
        }
    }

    fun addFragmentToActivity(fragment: Fragment?) {
        if (fragment == null) return
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.container, fragment)
        tr.commitAllowingStateLoss()
    }

    fun addFragmentToActivityBack(fragment: Fragment?) {
        if (fragment == null) return
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.container, fragment)
        tr.addToBackStack(null)
        tr.commitAllowingStateLoss()
    }

    /**
     * MOSTRAR DIALOGO DE ACERCA DE
     */
    private fun showDialogAbout() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_about)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.tv_version) as TextView).text =
            "Version 1.0"
        (dialog.findViewById(R.id.bt_getcode) as View).setOnClickListener { /*
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
                    startActivity(i);
                    */
            rateAction(this)
        }
        (dialog.findViewById<View>(R.id.bt_close) as ImageButton).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById<View>(R.id.bt_rate) as Button).setOnClickListener {
            rateAction(this)
        }
        dialog.show()
        dialog.window!!.attributes = lp
    }

    /**
     * FUNCION PARA REENVIAR A APKLIS A VALORAR APP
     */
    fun rateAction(activity: Activity) {
        val uri = Uri.parse("market://details?id=" + activity.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            activity.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.apklis.cu/application/" + activity.packageName)
                )
            )
        }
    }


    /**
     * FUNCION PARA CAMBIAR EL TEMA DE LA APP
     */
    fun changeTheme() {
        if (MyPreferences(this).darkMode) {
            MyPreferences(this).darkMode = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            MyPreferences(this).darkMode = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}