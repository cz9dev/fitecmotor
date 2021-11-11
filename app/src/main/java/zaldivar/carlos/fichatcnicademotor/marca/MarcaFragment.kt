package zaldivar.carlos.fichatcnicademotor.marca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.MarcaViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MarcaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarcaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fab: FloatingActionButton

    lateinit var mMarcaViewModel: MarcaViewModel

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

        var view: View = inflater.inflate(R.layout.fragment_marca, container, false)

        /* Esta parte es para utilizarla como guia para saber como funciona

        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMarca)
        var adapter = CustomAdapterMarca()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
         */
        setupList(view)

        fab = view.findViewById(R.id.fabMarca)
        fab.setOnClickListener {
            addFragmentToFragment(MarcaNuevaFragment())
        }

        return view
    }

    private fun setupList(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMarca)
        var adapter = CustomAdapterMarca()
        mMarcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        mMarcaViewModel.getMarcas().observe(viewLifecycleOwner, { marcas ->
            adapter.update(marcas)
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun addFragmentToFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        //transaction?.disallowAddToBackStack()
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MarcaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarcaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}