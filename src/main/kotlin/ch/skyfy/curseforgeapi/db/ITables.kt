package ch.skyfy.curseforgeapi.db

import org.ktorm.entity.Entity

enum class Side{ CLIENT, BOTH, SERVER }

interface Version : Entity<Version> {
    companion object : Entity.Factory<Version>()

    val id: Int
    var name: String
    var downloadUrl: String

    var mod: Mod
}

interface Tag : Entity<Tag> {
    companion object : Entity.Factory<Tag>()

    val id: Int
    var name: String
    var description: String

    var mod: Mod
}

/**
 * A tutorial contains a short description explaining what kind
 * of tutorial the url leads to (showcase, complete tutorial about the mod, etc.)
 *
 * A mod can contain many tutorials.
 */
interface Tutorial : Entity<Tutorial> {
    companion object : Entity.Factory<Tutorial>()

    val id: Int
    var description: String
    var url: String

    var mod: Mod
}

interface Mod : Entity<Mod> {
    companion object : Entity.Factory<Mod>()

    val id: Int
    val curseforgeIdentifier: Int
    var name: String
    var description: String
    var websiteUrl: String
    var sourceCodeUrl: String
    var gameVersion: String
    var side: Side
    var bugsSpeculation: Int
    var pregenerationRequired: Boolean
    var worksInSingleplayer: Boolean
//    var addedPresentation: Boolean
//    var ready: Boolean
//    var comment: String

//    val modVersion: ModVersion
}

//interface ModVersion : Entity<ModVersion> {
//    companion object : Entity.Factory<ModVersion>()
//
//    val id: Int
//
//    var mod: Mod
//    var version: Version
//}

//interface ModTag : Entity<ModTag> {
//    companion object : Entity.Factory<ModTag>()
//
//    val id: Int
//
//    var mod: Mod
//    var tag: Tag
//}

interface ModAlternative : Entity<ModAlternative> {
    companion object : Entity.Factory<ModAlternative>()

    val id: Int

    var mod: Mod
    var modAlternative: Mod
}

interface ModDependency : Entity<ModDependency> {
    companion object : Entity.Factory<ModDependency>()

    val id: Int

    var mod: Mod
    var modDependencyVersion: Version
}