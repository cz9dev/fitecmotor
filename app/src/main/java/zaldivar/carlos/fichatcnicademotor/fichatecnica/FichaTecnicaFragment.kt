package zaldivar.carlos.fichatcnicademotor.fichatecnica

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.FichaTecnicaViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const val TAG = "FichaTecnicaFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [FichaTecnicaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FichaTecnicaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var fab: FloatingActionButton
    lateinit var mFichaTecnicaViewModel: FichaTecnicaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_ficha_tecnica, container, false)
        setupList(view)

        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            addFragmentToFragment(FichaTecnicaNuevaFragment())
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
         * @return A new instance of fragment FichaTecnicaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FichaTecnicaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     * Funcion para llenar datos del recyclerView
     * @param view
     * @author carlos.zaldivar
     */
    private fun setupList(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        var adapter = CustomAdapterFichaTecnica(this)

        mFichaTecnicaViewModel = ViewModelProvider(this).get(FichaTecnicaViewModel::class.java)
        mFichaTecnicaViewModel.getFichaTecnica().observe(viewLifecycleOwner, { fichaTecnica ->
            adapter.update(fichaTecnica)
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    /**
     * Función para transicion entre fragmentos
     * @param fragment
     * @author carlos.zaldivar
     */
    fun addFragmentToFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        //transaction?.disallowAddToBackStack()
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}