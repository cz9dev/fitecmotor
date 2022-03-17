package zaldivar.carlos.fichatcnicademotor.largohierro

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import czaldivarp.fitecmotor.model.entities.LargoHierro
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.extensions.hideKeyboard
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.LargoHierroViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.ModeloViewModel
import zaldivar.carlos.fichatcnicademotor.modelo.ModeloFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_LARGO_HIERRO = "id_largo_hierro"
private const val LARGO_HIERRO = "largo_hierro"
private const val DIAMETRO_INTERIOR = "diametro_interior"
private const val DIAMETRO_EXTERIOR = "diametro_exterior"


/**
 * A simple [Fragment] subclass.
 * Use the [LargoHierroNuevoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LargoHierroNuevoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id_largo_hierro: Int? = null
    private var largo_hierro: Double? = null
    private var diametro_interior: Double? = null
    private var diametro_exterior: Double? = null

    private var toolbar: Toolbar? = null
    private lateinit var etLargoHierro: TextInputLayout
    private lateinit var etDiametroInterior: TextInputLayout
    private lateinit var etDiametroExterior: TextInputLayout
    private lateinit var mLargoHierroViewModel: LargoHierroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_largo_hierro = it.getInt(ID_LARGO_HIERRO)
            largo_hierro = it.getDouble(LARGO_HIERRO)
            diametro_interior = it.getDouble(DIAMETRO_INTERIOR)
            diametro_exterior = it.getDouble(DIAMETRO_EXTERIOR)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.done_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var good = true // Variable para el control de validacion
        val id = item.itemId
        if (id == R.id.action_done) {

            val txtLargoHierro = etLargoHierro.editText!!.text.toString()
            val txtDiametroInterior = etDiametroInterior.editText!!.text.toString()
            val txtDiametroExterior = etDiametroExterior.editText!!.text.toString()

            if (txtLargoHierro == "") {
                etLargoHierro.error = getString(R.string.campo_requerido)
                good = false
            }
            if (txtDiametroInterior == "") {
                etDiametroInterior.error = getString(R.string.campo_requerido)
                good = false
            }
            if (txtDiametroExterior == "") {
                etDiametroExterior.error = getString(R.string.campo_requerido)
                good = false
            }

            if (good) {
                if (id_largo_hierro != null){
                    mLargoHierroViewModel.editLargoHierro(
                        id_largo_hierro!!,
                        txtLargoHierro.toDouble(),
                        txtDiametroInterior.toDouble(),
                        txtDiametroExterior.toDouble()
                    )
                    Toast.makeText(context, "Largo de hierro actualizado", Toast.LENGTH_LONG).show()
                }else {
                    mLargoHierroViewModel.addLargoHierro(
                        txtLargoHierro.toDouble(),
                        txtDiametroInterior.toDouble(),
                        txtDiametroExterior.toDouble()
                    )
                    Toast.makeText(context, "Largo de hierro agregado", Toast.LENGTH_LONG).show()
                }
                hideKeyboard()
                addFragmentToFragment(LargoHierroFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_largo_hierro_nuevo, container, false)
        // Agregando Toolbar
        setToolbar(view)

        etLargoHierro = view.findViewById(R.id.etLargoHierro)
        etDiametroInterior = view.findViewById(R.id.etDiametroInterior)
        etDiametroExterior = view.findViewById(R.id.etDiametroExterior)

        // Trabajando con modeloViewModel
        mLargoHierroViewModel = ViewModelProvider(this).get(LargoHierroViewModel::class.java)

        // Limpiar error en caso de cambiar el texto
        etLargoHierro.editText?.doOnTextChanged { _, _, _, _ -> etLargoHierro.error = null }
        etDiametroInterior.editText?.doOnTextChanged { _, _, _, _ ->
            etDiametroInterior.error = null
        }
        etDiametroExterior.editText?.doOnTextChanged { _, _, _, _ ->
            etDiametroExterior.error = null
        }

        if (id_largo_hierro != null) {
            etLargoHierro.editText?.setText(largo_hierro.toString())
            etDiametroInterior.editText?.setText(diametro_interior.toString())
            etDiametroExterior.editText?.setText(diametro_exterior.toString())
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LargoHierroNuevoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            id_largo_hierro: Int,
            largo_hierro: Double,
            diametro_interior: Double,
            diametro_exterior: Double
        ) =
            LargoHierroNuevoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_LARGO_HIERRO, id_largo_hierro)
                    putDouble(LARGO_HIERRO, largo_hierro)
                    putDouble(DIAMETRO_INTERIOR, diametro_interior)
                    putDouble(DIAMETRO_EXTERIOR, diametro_exterior)
                }
            }
    }

    /**
     * Este método inicializa el toolbar y le da opciones de menú
     */
    private fun setToolbar(view: View) {
        setHasOptionsMenu(true)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar?.inflateMenu(R.menu.done_menu)
    }

    /**
     * Este método es para la transicion acia otro fragment pero sin recordar este (no retorna a este)
     */
    private fun addFragmentToFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.disallowAddToBackStack()
        //transaction?.addToBackStack(null)
        transaction?.commit()
    }
}