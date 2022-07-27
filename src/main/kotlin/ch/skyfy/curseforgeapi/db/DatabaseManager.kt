package ch.skyfy.curseforgeapi.db

import org.ktorm.database.Database

class DatabaseManager {

    private val db: Database

    init {
        createDatabase()
        db = Database.connect("jdbc:mariadb://localhost:3307/curseforge", "org.mariadb.jdbc.Driver", "root", "Pa\$\$w0rd")
        initDatabase()

        println("done !")
    }

    @Suppress("SqlNoDataSourceInspection", "SqlDialectInspection")
    private fun createDatabase(){
        val database = Database.connect("jdbc:mariadb://localhost:3307", "org.mariadb.jdbc.Driver", "root", "Pa\$\$w0rd")
        database.useConnection { conn ->
            val sql = "create database if not exists `curseforge`;"
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
                        if (sql.any { it.isLetterOrDigit() })
                            statement.executeUpdate(sql)
                }
            }
        }

//        populateDatabase()
    }

}