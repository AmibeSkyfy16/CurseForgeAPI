@file:Suppress("unused")

package ch.skyfy.curseforgeapi.db

import org.ktorm.schema.*

open class Versions(alias: String?) : Table<Version>("version", alias) {
    companion object : Versions(null)
    override fun aliased(alias: String) = Versions(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val downloadUrl = text("download_Url").bindTo { it.downloadUrl }

    private val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods
}

open class Tags(alias: String?) : Table<Tag>("tag", alias) {
    companion object : Tags(null)
    override fun aliased(alias: String) = Tags(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val description = varchar("description").bindTo { it.description }

    private val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods
}

open class Tutorials(alias: String?) : Table<Tutorial>("tutorial", alias) {
    companion object : Tutorials(null)
    override fun aliased(alias: String) = Tutorials(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val description = text("description").bindTo { it.description }
    val url = varchar("url").bindTo { it.url }

    private val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods
}

open class Mods(alias: String?) : Table<Mod>("mod", alias) {
    companion object : Mods(null)
    override fun aliased(alias: String) = Mods(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val curseforgeIdentifier = int("curseforge_identifier").primaryKey().bindTo { it.curseforgeIdentifier }
    val name = varchar("name").bindTo { it.name }
    val description = text("description").bindTo { it.description }
    val websiteUrl = varchar("website_url").bindTo { it.websiteUrl }
    val sourceCodeUrl = varchar("source_code_url").bindTo { it.sourceCodeUrl }
    val gameVersion = varchar("game_version").bindTo { it.gameVersion }
    val side = enum<Side>("side").bindTo { it.side }
    val bugsSpeculation = int("bugs_speculation").bindTo { it.bugsSpeculation }
    val pregenerationRequired = boolean("pregeneration_required").bindTo { it.pregenerationRequired }
    val worksInSingleplayer = boolean("works_in_singleplayer").bindTo { it.worksInSingleplayer }
//    val addedPresentation = boolean("added_presentation").bindTo { it.addedPresentation }
//    val ready = boolean("ready").bindTo { it.ready }
//    val comment = text("comment").bindTo { it.comment }

//    val versionId = int("mod_version_id").references(ModVersions) { it.modVersion }
//    val version get() = versionId.referenceTable as Versions
}

//open class ModVersions(alias: String?) : Table<ModVersion>("mod_version", alias) {
//    companion object : ModVersions(null)
//    override fun aliased(alias: String) = ModVersions(alias)
//
//    val id = int("id").primaryKey().bindTo { it.id }
//
//    private val modId = int("mod_id").references(Mods){it.mod}
//    val mod get() = modId.referenceTable as Mods
//
//    private val versionId = int("version_id").references(Versions){it.version}
//    val version get() = versionId.referenceTable as Versions
//}


//open class ModTags(alias: String?) : Table<ModTag>("mod_tag", alias) {
//    companion object : ModTags(null)
//    override fun aliased(alias: String) = ModTags(alias)
//
//    val id = int("id").primaryKey().bindTo { it.id }
//
//    private val modId = int("mod_id").references(Mods){it.mod}
//    val mod get() = modId.referenceTable as Mods
//
//    private val tagId = int("tag_id").references(Tags){it.tag}
//    val tag get() = tagId.referenceTable as Tags
//}

//open class ModTutorials(alias: String?) : Table<ModTutorial>("mod_tutorial", alias) {
//    companion object : ModTutorials(null)
//    override fun aliased(alias: String) = ModTutorials(alias)
//
//    val id = int("id").primaryKey().bindTo { it.id }
//
//    private val modId = int("mod_id").references(Mods){it.mod}
//    val mod get() = modId.referenceTable as Mods
//
//    private val tutorialId = int("tutorial_id").references(Tutorials){it.tutorial}
//    val tutorial get() = tutorialId.referenceTable as Tutorials
//}

open class ModAlternatives(alias: String?) : Table<ModAlternative>("mod_alternative", alias) {
    companion object : ModAlternatives(null)
    override fun aliased(alias: String) = ModAlternatives(alias)

    val id = int("id").primaryKey().bindTo { it.id }

    private val modId = int("mod_id").references(Mods){it.mod}
    val mod get() = modId.referenceTable as Mods

    private val modAlternativeId = int("mod_alternative_id").references(Mods){it.mod}
    val modAlternative get() = modAlternativeId.referenceTable as Mods
}

open class ModDependencies(alias: String?) : Table<ModDependency>("mod_dependency", alias) {
    companion object : ModDependencies(null)
    override fun aliased(alias: String) = ModDependencies(alias)

    val id = int("id").primaryKey().bindTo { it.id }

    private val modId = int("mod_id").references(Mods){it.mod}
    val mod get() = modId.referenceTable as Mods

    private val modDependencyVersionId = int("dependency_id").references(Versions){it.modDependencyVersion}
    val modDependencyVersion get() = modDependencyVersionId.referenceTable as Versions

//    private val dependencyId = int("dependency_id").references(ModVersions){it.modDependencyVersion}
//    val dependency get() = dependencyId.referenceTable as ModVersions
}