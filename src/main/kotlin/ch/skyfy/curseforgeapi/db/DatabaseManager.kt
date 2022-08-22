package ch.skyfy.curseforgeapi.db

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.like
import org.ktorm.entity.*

val Database.versions get() = this.sequenceOf(Versions)
val Database.tags get() = this.sequenceOf(Tags)
val Database.tutorials get() = this.sequenceOf(Tutorials)
val Database.mods get() = this.sequenceOf(Mods)
//val Database.modVersions get() = this.sequenceOf(ModVersions)
//val Database.modTags get() = this.sequenceOf(ModTags)
//val Database.modTutorials get() = this.sequenceOf(ModTutorials)
val Database.modDependencies get() = this.sequenceOf(ModDependencies)

class DatabaseManager {

    private val db: Database

    init {
        createDatabase()
        db = Database.connect("jdbc:mariadb://localhost:3307/automaticmodpack2", "org.mariadb.jdbc.Driver", "root", "Pa\$\$w0rd")
        initDatabase()

    }

    @Suppress("SqlNoDataSourceInspection", "SqlDialectInspection")
    private fun createDatabase() {
        val database = Database.connect("jdbc:mariadb://localhost:3307", "org.mariadb.jdbc.Driver", "root", "Pa\$\$w0rd")
        database.useConnection { conn ->
            val sql = "create database if not exists `automaticmodpack2`;"
            conn.prepareStatement(sql).use { statement ->
                statement.executeQuery()
            }
        }
    }

    private fun initDatabase() {
        val stream = javaClass.classLoader.getResourceAsStream("assets/curseforgeapi/sql/init.sql")!!
        db.useConnection { connection ->
            connection.createStatement().use { statement ->
                stream.bufferedReader().use { reader ->
                    for (sql in reader.readText().split(';'))
                        if (sql.any { it.isLetterOrDigit() }) statement.executeUpdate(sql)
                }
            }
        }

        populateDatabase()
    }

    private fun populateDatabase() {



    }


}