package ch.skyfy.curseforgeapi

import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.test.Test


class Tests {

    @Test
    fun test2(){
        for (game in Loader.ALL.games) {
            println("name: ${game.name}")
        }
    }

}