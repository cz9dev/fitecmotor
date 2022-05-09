package zaldivar.carlos.fichatcnicademotor.model

import androidx.room.DatabaseView

@DatabaseView(value = "SELECT \n" +
        "       FichaTecnica.idFichaTecnica, \n" +
        "       FichaTecnica.nombreMotor, \n" +
        "       FichaTecnica.corrienteNominal, \n" +
        "       FichaTecnica.potencia, \n" +
        "       FichaTecnica.ip, \n" +
        "       FichaTecnica.tensionNominal, \n" +
        "       FichaTecnica.diametroSuccion, \n" +
        "       FichaTecnica.diametroDescarga, \n" +
        "       FichaTecnica.capacidadCapacitor, \n" +
        "       FichaTecnica.tipoCapacitor, \n" +
        "       FichaTecnica.datosEnrrollado, \n" +
        "       FichaTecnica.fav, \n" +
        "       FichaTecnica.idMarca, \n" +
        "       FichaTecnica.idModelo, \n" +
        "       FichaTecnica.idLargoHierro, \n" +
        "       LargoHierro.largo, \n" +
        "       LargoHierro.diametroInterior, \n" +
        "       LargoHierro.diametroExterior, \n" +
        "       Marca.nombreMarca, \n" +
        "       Modelo.nombreModelo\n" +
        "FROM   FichaTecnica\n" +
        "       LEFT JOIN LargoHierro ON LargoHierro.idLargoHierro = FichaTecnica.idLargoHierro\n" +
        "       LEFT JOIN Marca ON Marca.idMarca = FichaTecnica.idMarca\n" +
        "       LEFT JOIN Modelo ON Modelo.idModelo = FichaTecnica.idModelo;",
    viewName = "v_ficha_tecnica_all"
)
data class FichaTecnicaDetail(
    val idFichaTecnica: Int,
    val nombreMotor: String?,
    val corrienteNominal: Double?,
    val potencia: Double?,
    val ip: Int?,
    val tensionNominal: Double?,
    val diametroSuccion: Double?,
    val diametroDescarga: Double?,
    val tipoCapacitor: String?,
    val capacidadCapacitor: String?, // Permanente o Arranque
    val datosEnrrollado: String?,
    val fav: Boolean?,
    val idMarca: Int,
    val idModelo: Int,
    val idLargoHierro: Int,
    val nombreMarca:String,
    val nombreModelo:String
)