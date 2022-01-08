package zaldivar.carlos.fichatcnicademotor.modelo

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.marca.CustomAdapterMarca
import zaldivar.carlos.fichatcnicademotor.marca.MarcaNuevaFragment
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.MarcaViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.ModeloViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ModeloFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModeloFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var fab: FloatingActionButton
    lateinit var mModeloViewModel: ModeloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_modelo, container, false)

        setupList(view)

        fab = view.findViewById(R.id.fabModelo)
        fab.setOnClickListener {
            addFragmentToFragment(ModeloNuevoFragment())
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
         * @return A new instance of fragment ModeloFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ModeloFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupList(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewModelo)
        var adapter = CustomAdapterModelo(this)
        mModeloViewModel = ViewModelProvider(this).get(ModeloViewModel::class.java)
        mModeloViewModel.getModelo().observe(viewLifecycleOwner, { modelo ->
            adapter.update(modelo)
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun addFragmentToFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        //transaction?.disallowAddToBackStack()
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val updatedNote = EditText(requireContext())
        updatedNote.hint = "Entre el nuevo texto"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Guardar", DialogInterface.OnClickListener {
                // The setPositiveButton method takes in two arguments
                // More info here: https://developer.android.com/reference/kotlin/android/app/AlertDialog.Builder#setpositivebutton
                // Use underscores when lambda arguments are not used
                    _,_ -> mModeloViewModel.editModelo(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Actualizar Modelo")
        alert.setView(updatedNote)
        alert.show()
    }
}