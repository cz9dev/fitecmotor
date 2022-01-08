package zaldivar.carlos.fichatcnicademotor.largohierro

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
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.LargoHierroViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.ModeloViewModel
import zaldivar.carlos.fichatcnicademotor.modelo.CustomAdapterModelo
import zaldivar.carlos.fichatcnicademotor.modelo.ModeloNuevoFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LargoHierroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LargoHierroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var fab: FloatingActionButton
    lateinit var mLargoHierroViewModel: LargoHierroViewModel

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
        var view: View = inflater.inflate(R.layout.fragment_largo_hierro, container, false)
        setupList(view)

        fab = view.findViewById(R.id.fabLargoHierro)
        fab.setOnClickListener {
            addFragmentToFragment(LargoHierroNuevoFragment())
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
         * @return A new instance of fragment LagoHierroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LargoHierroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupList(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLargoHierro)
        var adapter = CustomAdapterLargoHierro(this)
        mLargoHierroViewModel = ViewModelProvider(this).get(LargoHierroViewModel::class.java)
        mLargoHierroViewModel.getLargoHierro().observe(viewLifecycleOwner, { largoHierro ->
            adapter.update(largoHierro)
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

    fun raiseDialog(id: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val updatedLargo = EditText(requireContext())
        val updatedDiametroI = EditText(requireContext())
        val updatedDiametroE = EditText(requireContext())
        updatedLargo.hint = "Entre el nuevo largo"
        updatedDiametroI.hint = "Entre el nuevo diametro interior"
        updatedDiametroE.hint = "Entre el nuevo diametro exterior"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Guardar", DialogInterface.OnClickListener {
                // The setPositiveButton method takes in two arguments
                // More info here: https://developer.android.com/reference/kotlin/android/app/AlertDialog.Builder#setpositivebutton
                // Use underscores when lambda arguments are not used
                    _, _ ->
                mLargoHierroViewModel.editLargoHierro(id, updatedLargo.text.toString().toDouble(),updatedDiametroI.text.toString().toDouble(),updatedDiametroE.text.toString().toDouble())
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Actualizar Largo del Hierro")
        alert.setView(updatedLargo)
        alert.setView(updatedDiametroI)
        alert.setView(updatedDiametroE)
        alert.show()
    }
}