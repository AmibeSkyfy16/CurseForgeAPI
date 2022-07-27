package ch.skyfy.curseforgeapi

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request

object Loader {

    data class All(val games: List<Game>)

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        isLenient = true
        allowStructuredMapKeys = true
        prettyPrint = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        explicitNulls = true
    }

    private val client = OkHttpClient()

    val ALL: All

    init {
        ALL = All(getAllGames())
    }

    private fun getAllGames(): List<Game> {

        val games: MutableList<Game> = mutableListOf()

        val request: Request = Request.Builder()
            .url("https://api.curseforge.com/v1/games")
            .header("x-api-key", "\$2a\$10\$95lNJc48L3s.J0x/OkH7lOwZ85Zr95OTMjcuijXxTCUIGhB9GQjju")
            .build()

        val response = client.newCall(request).execute()

        try {

            val el: JsonElement = json.parseToJsonElement(response.body.string())

            val data = el.jsonObject["data"]!!

            data.jsonArray.forEach {
                val id = it.jsonObject["id"]?.jsonPrimitive?.content!!
                val name = it.jsonObject["name"]?.jsonPrimitive?.content!!
                val slug = it.jsonObject["slug"]?.jsonPrimitive?.content!!
                val dateModified = it.jsonObject["dateModified"]?.jsonPrimitive?.content!!

                val iconUrl = it.jsonObject["assets"]?.jsonObject?.get("iconUrl")?.jsonPrimitive?.content
                val tileUrl = it.jsonObject["assets"]?.jsonObject?.get("tileUrl")?.jsonPrimitive?.content
                val coverUrl = it.jsonObject["assets"]?.jsonObject?.get("coverUrl")?.jsonPrimitive?.content

                val status = it.jsonObject["status"]?.jsonPrimitive?.content!!
                val apiStatus = it.jsonObject["apiStatus"]?.jsonPrimitive?.content!!

                val game = Game(
                    id = id.toInt(),
                    name = name,
                    slug = slug,
                    dateModified = dateModified,
                    assets = listOf(iconUrl, tileUrl, coverUrl),
                    status = status.toInt(),
                    apiStatus = apiStatus.toInt(),
                    getAllVersionTypes(id.toInt())
                )

                games.add(game)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return games.toList()
    }

    private fun getAllVersionTypes(gameId: Int): List<VersionType> {
        val versionTypes: MutableList<VersionType> = mutableListOf()

        val request: Request = Request.Builder()
            .url("https://api.curseforge.com/v1/games/$gameId/version-types")
            .header("x-api-key", "\$2a\$10\$95lNJc48L3s.J0x/OkH7lOwZ85Zr95OTMjcuijXxTCUIGhB9GQjju")
            .build()

        val response = client.newCall(request).execute()
        try {
            val el: JsonElement = json.parseToJsonElement(response.body.string())
            el.jsonObject["data"]?.jsonArray?.forEach {
                val id = it.jsonObject["id"]?.jsonPrimitive?.content?.toInt()!!
                val name = it.jsonObject["name"]?.jsonPrimitive?.content!!
                val slug = it.jsonObject["slug"]?.jsonPrimitive?.content!!
                versionTypes.add(VersionType(id, name, slug, getAllVersions(gameId, id)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return versionTypes.toList()
    }

    private fun getAllVersions(gameId: Int, typeId: Int): List<String> {
        val versions: MutableList<String> = mutableListOf()

        val request: Request = Request.Builder()
            .url("https://api.curseforge.com/v1/games/$gameId/versions")
            .header("x-api-key", "\$2a\$10\$95lNJc48L3s.J0x/OkH7lOwZ85Zr95OTMjcuijXxTCUIGhB9GQjju")
            .build()

        val response = client.newCall(request).execute()
        try {
            val el: JsonElement = json.parseToJsonElement(response.body.string())

            el.jsonObject["data"]?.jsonArray?.forEach {
                val type = it.jsonObject["type"]?.jsonPrimitive?.content
                if (type?.toInt()!! == typeId) {
                    it.jsonObject["versions"]?.jsonArray?.forEach { it2 ->
                        versions.add(it2.jsonPrimitive.content)
                    }
                    return@forEach
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return versions.toList()
    }

}