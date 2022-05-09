package zaldivar.carlos.fichatcnicademotor.fichatecnica

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.collection.arrayMapOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import zaldivar.carlos.fichatcnicademotor.R
import zaldivar.carlos.fichatcnicademotor.extensions.hideKeyboard
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.FichaTecnicaViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.LargoHierroViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.MarcaViewModel
import zaldivar.carlos.fichatcnicademotor.model.viewmodel.ModeloViewModel
import zaldivar.carlos.fichatcnicademotor.utils.ImageControler
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM00 = "idFichaTecnica"
private const val ARG_PARAM01 = "nombreMotor"
private const val ARG_PARAM02 = "corrienteNominal"
private const val ARG_PARAM03 = "potencia"
private const val ARG_PARAM04 = "ip"
private const val ARG_PARAM05 = "tensionNominal"
private const val ARG_PARAM06 = "diametroSuccion"
private const val ARG_PARAM07 = "diametroDescarga"
private const val ARG_PARAM08 = "tipoCapacitor"
private const val ARG_PARAM09 = "capacidadCapacitor"
private const val ARG_PARAM10 = "datosEnrrollado"
private const val ARG_PARAM12 = "fav"
private const val ARG_PARAM13 = "idMarca"
private const val ARG_PARAM14 = "idModelo"
private const val ARG_PARAM15 = "fidLargoHierro"

/**
 * A simple [Fragment] subclass.
 * Use the [FichaTecnicaNuevaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FichaTecnicaNuevaFragment : Fragment() {
    private var idFichaTecnica: Int? = null
    private var nombreMotor: String? = null
    private var corrienteNominal: Double? = null
    private var potencia: Double? = null
    private var ip: Int? = null
    private var tensionNominal: Double? = null
    private var diametroSuccion: Double? = null
    private var diametroDescarga: Double? = null
    private var tipoCapacitor: String? = null
    private var capacidadCapacitor: String? = null
    private var datosEnrrollado: String? = null
    private var fav: Boolean? = null
    private var idMarca: Int? = null
    private var idModelo: Int? = null
    private var idLargoHierro: Int? = null

    private var toolbar: Toolbar? = null
    private lateinit var fabPhoto: FloatingActionButton
    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null

    private lateinit var etNombreMotor: TextInputLayout
    private lateinit var etCorrienteNominal: TextInputLayout
    private lateinit var etPotencia: TextInputLayout
    private lateinit var etIp: TextInputLayout
    private lateinit var etTensionNominal: TextInputLayout
    private lateinit var etDiametroSuccion: TextInputLayout
    private lateinit var etDiametroDescarga: TextInputLayout
    private lateinit var etTipoCapacitor: TextInputLayout
    private lateinit var etCapacidadCapacitor: TextInputLayout
    private lateinit var etDatosEnrrollado: TextInputLayout
    private lateinit var etFoto: ImageView
    private lateinit var etMarca: TextInputLayout
    private lateinit var etModelo: TextInputLayout
    private lateinit var etLargoHierro: TextInputLayout

    private lateinit var mFichaTecnicaViewModel: FichaTecnicaViewModel
    lateinit var modeloViewModel: ModeloViewModel
    lateinit var marcaViewModel: MarcaViewModel
    lateinit var largoHierroViewModel: LargoHierroViewModel

    lateinit var itemsMarca: MutableList<String>
    lateinit var itemsMarcaId: MutableList<Int>
    lateinit var itemsModel: MutableList<String>
    lateinit var itemsModelId: MutableList<Int>
    lateinit var itemsLargoHierro: MutableList<String>
    lateinit var itemsLargoHierroId: MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idFichaTecnica = it.getInt(ARG_PARAM00)
            nombreMotor = it.getString(ARG_PARAM01)
            corrienteNominal = it.getDouble(ARG_PARAM02)
            potencia = it.getDouble(ARG_PARAM03)
            ip = it.getInt(ARG_PARAM04)
            tensionNominal = it.getDouble(ARG_PARAM05)
            diametroSuccion = it.getDouble(ARG_PARAM06)
            diametroDescarga = it.getDouble(ARG_PARAM07)
            tipoCapacitor = it.getString(ARG_PARAM08)
            capacidadCapacitor = it.getString(ARG_PARAM09)
            datosEnrrollado = it.getString(ARG_PARAM10)
            fav = it.getBoolean(ARG_PARAM12)
            idMarca = it.getInt(ARG_PARAM13)
            idModelo = it.getInt(ARG_PARAM14)
            idLargoHierro = it.getInt(ARG_PARAM15)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_ficha_tecnica_nueva, container, false)
        // Agregando Toolbar
        setToolbar(view)

        etNombreMotor = view.findViewById(R.id.etNombreMotor)
        etCorrienteNominal = view.findViewById(R.id.etCorrienteNominal)
        etPotencia = view.findViewById(R.id.etPotencia)
        etIp = view.findViewById(R.id.etIP)
        etTensionNominal = view.findViewById(R.id.etTencionNominal)
        etDiametroSuccion = view.findViewById(R.id.etDiametroSuccion)
        etDiametroDescarga = view.findViewById(R.id.etDiametroDescarga)
        etTipoCapacitor = view.findViewById(R.id.etTipoCapacitor)
        etCapacidadCapacitor = view.findViewById(R.id.etCapacidadCapacitor)
        etDatosEnrrollado = view.findViewById(R.id.etDatosEnrrollado)
        etFoto = view.findViewById(R.id.ivFotoMotor)
        etMarca = view.findViewById(R.id.etFTMarca)
        etModelo = view.findViewById(R.id.etFTModelo)
        etLargoHierro = view.findViewById(R.id.etFTLargoHierro)

        limpiarErrores()

        var itemTipoCapacitor = listOf("Permanente", "Arranque")
        itemsMarca = mutableListOf<String>()
        itemsMarcaId = mutableListOf<Int>()
        itemsModel = mutableListOf<String>()
        itemsModelId = mutableListOf<Int>()
        itemsLargoHierro = mutableListOf<String>()
        itemsLargoHierroId = mutableListOf<Int>()

        // Tipo Capacitor (LLenar menu)
        var tC = (etTipoCapacitor.editText as? AutoCompleteTextView)
        var adapterTC = ArrayAdapter(requireContext(), R.layout.list_item, itemTipoCapacitor)
        if (idFichaTecnica != null) {
            if (tipoCapacitor.equals("Permanente")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    tC?.setText("Permanente", false)
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    tC?.setText("Arranque", false)
                }
            }
        }

        tC?.setAdapter(adapterTC)

        // Largo del Hierro
        var lH = (etLargoHierro.editText as? AutoCompleteTextView)
        largoHierroViewModel = ViewModelProvider(this).get(LargoHierroViewModel::class.java)
        largoHierroViewModel.getLargoHierro().observe(viewLifecycleOwner, {
            for (item in it) {
                itemsLargoHierroId.add(item.idLargoHierro)
                itemsLargoHierro.add("largo: ${item.largo}, ø int: ${item.diametroInterior}, ø ext: ${item.diametroExterior}")

                // Mostrar seleccionado el cargo del hierro en caso de que estemos editando la ficha tecnica
                if (idFichaTecnica != null && item.idLargoHierro == idLargoHierro) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        lH?.setText(
                            "largo: ${item.largo}, ø int: ${item.diametroInterior}, ø ext: ${item.diametroExterior}",
                            false
                        )
                    }
                }

            }
        })

        val adapterLargoHierro =
            ArrayAdapter(requireContext(), R.layout.list_item, itemsLargoHierro)

        lH?.setAdapter(adapterLargoHierro)
        lH?.setOnItemClickListener { parent, view, position, id ->
            onItemClickLargoHierro(
                parent,
                view,
                position,
                id
            )
        }

        // Marcas
        var mA = (etMarca.editText as? AutoCompleteTextView)
        marcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        marcaViewModel.getMarcas().observe(viewLifecycleOwner, {
            for (item in it) {
                itemsMarcaId.add(item.idMarca)
                itemsMarca.add(item.nombreMarca)

                // Mostrar seleccionado la marca en caso de que estemos editando la ficha tecnica
                if (idFichaTecnica != null && item.idMarca == idMarca) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        mA?.setText(item.nombreMarca, false)
                    }
                }

            }
        })

        val adapterMarca = ArrayAdapter(requireContext(), R.layout.list_item, itemsMarca)

        mA?.setAdapter(adapterMarca)
        mA?.setOnItemClickListener { parent, view, position, id ->
            onItemClickMarca(
                parent,
                view,
                position,
                id
            )
        }

        // Modelos
        var mO = (etModelo.editText as? AutoCompleteTextView)
        modeloViewModel = ViewModelProvider(this).get(ModeloViewModel::class.java)
        modeloViewModel.getModelo().observe(viewLifecycleOwner, {
            for (item in it) {
                itemsModelId.add(item.idModelo)
                itemsModel.add(item.nombreModelo)

                // Mostrar seleccionado el modelo en caso de que estemos editando la ficha tecnica
                if (idFichaTecnica != null && item.idModelo == idModelo) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        mO?.setText(item.nombreModelo, false)
                    }
                }

            }
        })

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemsModel)

        mO?.setAdapter(adapter)
        mO?.setOnItemClickListener { parent, view, position, id ->
            onItemClickModelo(
                parent,
                view,
                position,
                id
            )
        }

        /**
         * Programación de evento OnClick para buscar imagen de Motor
         */
        fabPhoto = view.findViewById(R.id.fab_foto)
        fabPhoto.setOnClickListener {
            ImageControler.selectPhotoFromGalery(requireActivity(), SELECT_ACTIVITY)
        }
        etFoto.setOnClickListener {
            ImageControler.selectPhotoFromGalery(requireActivity(), SELECT_ACTIVITY)
        }

        // Trabajando con ViewModel
        mFichaTecnicaViewModel = ViewModelProvider(this).get(FichaTecnicaViewModel::class.java)

        /**
         * Cargar datos en formulario si accion es editar
         */
        if (idFichaTecnica != null) {
            etNombreMotor.editText!!.setText(nombreMotor.toString())
            etCorrienteNominal.editText!!.setText(corrienteNominal.toString())
            etPotencia.editText!!.setText(potencia.toString())
            etIp.editText!!.setText(ip.toString())
            etTensionNominal.editText!!.setText(tensionNominal.toString())
            etDiametroSuccion.editText!!.setText(diametroSuccion.toString())
            etDiametroDescarga.editText!!.setText(diametroDescarga.toString())
            etCapacidadCapacitor.editText!!.setText(capacidadCapacitor.toString())
            etDatosEnrrollado.editText!!.setText(datosEnrrollado.toString())
            etFoto.setImageURI(ImageControler.getImageUri(this.requireContext(), idFichaTecnica!!))
        }

        return view
    }

    /**
     * Funcion para implementar el evento inItemClick para el Largo del Hierro
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    fun onItemClickLargoHierro(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idLargoHierro = itemsLargoHierroId[position]
    }

    /**
     * Funcion para implementar el evento inItemClick para la Marca
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    fun onItemClickMarca(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idMarca = itemsMarcaId[position]
    }

    /**
     * Funcion para implementar el evento inItemClick para el Modelo
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    fun onItemClickModelo(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idModelo = itemsModelId[position]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.done_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var good = true // Variable para el control de validacion
        val id = item.itemId

        if (id == R.id.action_done) {
            val txtNombreMotor = etNombreMotor.editText!!.text.toString()
            val txtCorrienteNominal = etCorrienteNominal.editText!!.text.toString()
            val txtPotencia = etPotencia.editText!!.text.toString()
            val txtIP = etIp.editText!!.text.toString()
            val txtTencionNominal = etTensionNominal.editText!!.text.toString()
            val txtDiametroSuccion = etDiametroSuccion.editText!!.text.toString()
            val txtDiametroDescarga = etDiametroDescarga.editText!!.text.toString()
            val txtTipoCapacitor = etTipoCapacitor.editText!!.text.toString()
            val txtCapacidadCapacitor = etCapacidadCapacitor.editText!!.text.toString()
            val txtDatosEnrrollado = etDatosEnrrollado.editText!!.text.toString()
            val txtMarca = etMarca.editText!!.text.toString()
            val txtModelo = etModelo.editText!!.text.toString()
            val txtLargoHierro = etLargoHierro.editText!!.text.toString()

            var cualTomaFoco = mutableListOf<TextInputLayout>()
            if (txtNombreMotor == "") {
                etNombreMotor.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etNombreMotor)
                good = false
            }
            if (txtLargoHierro == "") {
                etLargoHierro.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etLargoHierro)
                good = false
            }
            if (txtMarca == "") {
                etMarca.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etMarca)
                good = false
            }
            if (txtModelo == "") {
                etModelo.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etModelo)
                good = false
            }
            if (txtCorrienteNominal == "") {
                etCorrienteNominal.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etCorrienteNominal)
                good = false
            }
            if (txtPotencia == "") {
                etPotencia.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etPotencia)
                good = false
            }
            if (txtIP == "") {
                etIp.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etIp)
                good = false
            }
            if (txtTencionNominal == "") {
                etTensionNominal.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etTensionNominal)
                good = false
            }
            if (txtDiametroSuccion == "") {
                etDiametroSuccion.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etDiametroSuccion)
                good = false
            }
            if (txtDiametroDescarga == "") {
                etDiametroDescarga.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etDiametroDescarga)
                good = false
            }
            if (txtTipoCapacitor == "") {
                etTipoCapacitor.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etTipoCapacitor)
                good = false
            }
            if (txtCapacidadCapacitor == "") {
                etCapacidadCapacitor.error = getString(R.string.campo_requerido)
                cualTomaFoco.add(etCapacidadCapacitor)
                good = false
            }

            if (good) {
                // Comprueba si el idFichaTecnica no es nulo es que es para modificar
                // la ficha tecnica, de lo contrario es para agregar la ficha tecnica
                if (idFichaTecnica != null) {
                    mFichaTecnicaViewModel.editFichaTecnica(
                        idFichaTecnica!!,
                        txtNombreMotor,
                        txtCorrienteNominal.toDouble(),
                        txtPotencia.toDouble(),
                        txtIP.toInt(),
                        txtTencionNominal.toDouble(),
                        txtDiametroSuccion.toDouble(),
                        txtDiametroDescarga.toDouble(),
                        txtTipoCapacitor,
                        txtCapacidadCapacitor,
                        txtDatosEnrrollado,
                        fav!!,
                        idMarca!!,
                        idModelo!!,
                        idLargoHierro!!
                    )
                    imageUri?.let {
                        ImageControler.saveImage(requireContext(), idFichaTecnica!!, it)
                    }
                } else {
                    mFichaTecnicaViewModel.addFichaTecnica(
                        txtNombreMotor.toString(),
                        txtCorrienteNominal.toDouble(),
                        txtPotencia.toDouble(),
                        txtIP.toInt(),
                        txtTencionNominal.toDouble(),
                        txtDiametroSuccion.toDouble(),
                        txtDiametroDescarga.toDouble(),
                        txtTipoCapacitor.toString(),
                        txtCapacidadCapacitor.toString(),
                        txtDatosEnrrollado.toString()!!,
                        fav = false,
                        idMarca!!,
                        idModelo!!,
                        idLargoHierro!!,
                        imageUri,
                        requireContext()
                    )
                }
                hideKeyboard()
                addFragmentToFragment(FichaTecnicaFragment())
            }
            else{
                cualTomaFoco[0].requestFocus()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Compruebo si se cumple para asignar a la foto la que traigo de la galería
        when {
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data

                etFoto.setImageURI(imageUri)
                println("La dirección de la foto es: $imageUri")
            }
        }
    }

    /**
     * Función para limpiar errores en el formulario
     */
    fun limpiarErrores() {
        // Limpiar error en caso de cambiar el texto
        etNombreMotor.editText?.doOnTextChanged { _, _, _, _ -> etNombreMotor.error = null }
        etCorrienteNominal.editText?.doOnTextChanged { _, _, _, _ ->
            etCorrienteNominal.error = null
        }
        etPotencia.editText?.doOnTextChanged { _, _, _, _ -> etPotencia.error = null }
        etIp.editText?.doOnTextChanged { _, _, _, _ -> etIp.error = null }
        etTensionNominal.editText?.doOnTextChanged { _, _, _, _ -> etTensionNominal.error = null }
        etDiametroSuccion.editText?.doOnTextChanged { _, _, _, _ -> etDiametroSuccion.error = null }
        etDiametroDescarga.editText?.doOnTextChanged { _, _, _, _ ->
            etDiametroDescarga.error = null
        }
        etTipoCapacitor.editText?.doOnTextChanged { _, _, _, _ -> etTipoCapacitor.error = null }
        etCapacidadCapacitor.editText?.doOnTextChanged { _, _, _, _ ->
            etCapacidadCapacitor.error = null
        }
        etDatosEnrrollado.editText?.doOnTextChanged { _, _, _, _ -> etDatosEnrrollado.error = null }
        etMarca.editText?.doOnTextChanged { _, _, _, _ -> etMarca.error = null }
        etModelo.editText?.doOnTextChanged { _, _, _, _ -> etModelo.error = null }
        etLargoHierro.editText?.doOnTextChanged { _, _, _, _ -> etLargoHierro.error = null }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FichaTecnicaNuevaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            idFichaTecnica: Int,
            nombreMotor: String,
            corrienteNominal: Double,
            potencia: Double,
            ip: Int,
            tensionNominal: Double,
            diametroSuccion: Double,
            diametroDescarga: Double,
            tipoCapacitor: String,
            capacidadCapacitor: String, // Permanente o Arranque
            datosEnrrollado: String,
            fav: Boolean, // esto es direccion de favorito
            idMarca: Int,
            idModelo: Int,
            idLargoHierro: Int
        ) =
            FichaTecnicaNuevaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM00, idFichaTecnica)
                    putString(ARG_PARAM01, nombreMotor)
                    putDouble(ARG_PARAM02, corrienteNominal)
                    putDouble(ARG_PARAM03, potencia)
                    putInt(ARG_PARAM04, ip)
                    putDouble(ARG_PARAM05, tensionNominal)
                    putDouble(ARG_PARAM06, diametroSuccion)
                    putDouble(ARG_PARAM07, diametroDescarga)
                    putString(ARG_PARAM08, tipoCapacitor)
                    putString(ARG_PARAM09, capacidadCapacitor)
                    putString(ARG_PARAM10, datosEnrrollado)
                    putBoolean(ARG_PARAM12, fav)
                    putInt(ARG_PARAM13, idMarca)
                    putInt(ARG_PARAM14, idModelo)
                    putInt(ARG_PARAM15, idLargoHierro)
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