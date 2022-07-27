package ch.skyfy.curseforgeapi

//@kotlinx.serialization.Serializable
//data class Version(val type: Int, val versions: List<String>)

data class VersionType(val id: Int, val name: String, val slug: String, val versions: List<String>)

data class Game(
    val id: Int,
    val name: String,
    val slug: String,
    val dateModified: String,
    val assets: List<String?>,
    val status: Int,
    val apiStatus: Int,
    val versions: List<VersionType>
)