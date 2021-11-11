package zaldivar.carlos.fichatcnicademotor

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.baoyachi.stepview.VerticalStepView
import zaldivar.carlos.fichatcnicademotor.utils.PaidCheked


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RequerimentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequerimentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val TAG = "LoginFragment"

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
        var view: View = inflater.inflate(R.layout.fragment_requeriment, container, false)

        //
        var bIniciarApp: Button = view.findViewById(R.id.bIniciarApp)

        // Recargar fragment para verificar estado de compra de la app
        bIniciarApp.setOnClickListener {
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var option = 0
        val list0: MutableList<String> = ArrayList()
        //val appId = "zaldivar.carlos.fichatcnicademotor"
        val appId = "zaldivar.carlos.calcelect"
        var step_view: VerticalStepView = view.findViewById(R.id.step_view)
        var a: String = PaidCheked().isPurchased(requireContext(), appId)
        when {
            PaidCheked().isPurchased(requireContext(), appId) == "num00" -> {
                option = 0
                list0.add("Debe instalar APKLIS en su móvil")
                list0.add("Iniciar sección en APKLIS")
                list0.add("Realizar la compra en APKLIS")
                list0.add("Compra verificada")
            }

            PaidCheked().isPurchased(requireContext(), appId) == "num02" -> {
                option = 1
                list0.add("APKLIS instalada")
                list0.add("Usted debe iniciar sección en APKLIS")
                list0.add("Realizar la compra en APKLIS")
                list0.add("Compra verificada")
            }
            PaidCheked().isPurchased(requireContext(), appId) == "num03" -> {
                option = 2
                list0.add("APKLIS instalada")
                list0.add("Sección iniciada en APKLIS")
                list0.add("Realizar la compra en APKLIS")
                list0.add("Compra verificada")
            }
            PaidCheked().isPurchased(requireContext(), appId) == "num04" -> {
                option = 4
                list0.add("APKLIS instalada")
                list0.add("Sección iniciada en APKLIS")
                list0.add("Compra realizada en APKLIS")
                list0.add("Compra verificada")
            }
        }

        if (param1.equals("blanco")) {
            step_view.setStepsViewIndicatorComplectingPosition(option)
                .reverseDraw(false)
                .setTextSize(16)
                .setStepViewTexts(list0)
                .setStepsViewIndicatorCompletedLineColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primaryTextColor
                    )
                )
                .setStepsViewIndicatorUnCompletedLineColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primaryTextColor
                    )
                )
                .setStepViewComplectedTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                .setStepViewUnComplectedTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                .setStepsViewIndicatorCompleteIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check_white
                    )
                )
                .setStepsViewIndicatorDefaultIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check_un_white
                    )
                )
                .setStepsViewIndicatorAttentionIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_proccess
                    )
                )
        } else {
            step_view.setStepsViewIndicatorComplectingPosition(option)
                .reverseDraw(false)
                .setTextSize(16)
                .setStepViewTexts(list0)
                .setStepsViewIndicatorCompletedLineColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primaryTextColor
                    )
                )
                .setStepsViewIndicatorUnCompletedLineColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primaryTextColor
                    )
                )
                .setStepViewComplectedTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                .setStepViewUnComplectedTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                .setStepsViewIndicatorCompleteIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check_white
                    )
                )
                .setStepsViewIndicatorDefaultIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check_un_white
                    )
                )
                .setStepsViewIndicatorAttentionIcon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.attention
                    )
                )
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RequerimentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RequerimentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}