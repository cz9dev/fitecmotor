package zaldivar.carlos.fichatcnicademotor.modelo

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.extensions.hideKeyboard
import zaldivar.carlos.fichatcnicademotor.marca.MarcaFragment
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.MarcaViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.ModeloViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_MODELO = "id_modelo"
private const val MODELO = "modelo"

/**
 * A simple [Fragment] subclass.
 * Use the [ModeloNuevoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModeloNuevoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id_modelo: Int? = null
    private var modelo: String? = null

    private var toolbar: Toolbar? = null
    private lateinit var etModelo: TextInputLayout
    private lateinit var mModeloViewModel: ModeloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_modelo = it.getInt(ID_MODELO)
            modelo = it.getString(MODELO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.done_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_done) {

            val txtModelo = etModelo.editText!!.text.toString()

            if (txtModelo != "") {
                if (id_modelo != null) {
                    mModeloViewModel.editModelo(id_modelo!!, txtModelo)
                    Toast.makeText(context, "Modelo actualizado", Toast.LENGTH_LONG).show()
                } else {
                    //Agregar a la base de datos la marca
                    mModeloViewModel.addModelo(txtModelo)
                    Toast.makeText(context, "Modelo agregada", Toast.LENGTH_LONG).show()
                }
                hideKeyboard()
                addFragmentToFragment(ModeloFragment())
            } else {
                etModelo.error = getString(R.string.campo_requerido)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_modelo_nuevo, container, false)
        // Agregando Toolbar
        setToolbar(view)
        // Trabajo con etModelo
        etModelo = view.findViewById(R.id.etModelo)
        // Trabajando con modeloViewModel
        mModeloViewModel = ViewModelProvider(this).get(ModeloViewModel::class.java)
        // Limpiar error en caso de cambiar el texto
        etModelo.editText?.doOnTextChanged { _, _, _, _ -> etModelo.error = null }

        // Entrando datos al formulario si accion es actualizar
        if (id_modelo != null) {
            etModelo.editText?.setText(modelo)
        }

        // retornando el view ya conformado
        return view
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ModeloNuevoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(id_modelo: Int, modelo: String) =
            ModeloNuevoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_MODELO, id_modelo)
                    putString(MODELO, modelo)
                }
            }
    }
}