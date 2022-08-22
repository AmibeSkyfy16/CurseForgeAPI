@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ch.skyfy.curseforgeapi.db

import org.ktorm.schema.*

open class Assets(alias: String?) : Table<Asset>("asset", alias) {
    companion object : Assets(null)
    override fun aliased(alias: String) = Assets(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val iconUrl = varchar("icon_url").bindTo { it.iconUrl }
    val tileUrl = varchar("tile_url").bindTo { it.tileUrl }
    val coverUrl = varchar("cover_url").bindTo { it.coverUrl }
}

open class Games(alias: String?) : Table<Game>("game", alias) {
    companion object : Games(null)
    override fun aliased(alias: String) = Games(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val slug = varchar("slug").bindTo { it.slug }
    val dateModified = timestamp("date_modified").bindTo { it.dateModified }
    val status = int("status").bindTo { it.status }
    val apiStatus = int("api_status").bindTo { it.apiStatus }

    val assetId = int("asset_id").references(Assets) { it.asset }
    val asset get() = assetId.referenceTable as Assets
}

open class VersionTypes(alias: String?) : Table<VersionType>("version_type", alias) {
    companion object : VersionTypes(null)
    override fun aliased(alias: String) = VersionTypes(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val slug = varchar("slug").bindTo { it.slug }

    val gameId = int("game_id").references(Games) { it.game }
    val game get() = gameId.referenceTable as Games
}

open class Versions(alias: String?) : Table<Version>("version", alias) {
    companion object : Versions(null)
    override fun aliased(alias: String) = Versions(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }

    val versionTypeId = int("version_type_id").references(VersionTypes) { it.versionType }
    val versionType get() = versionTypeId.referenceTable as VersionTypes
}

open class Categories(alias: String?) : Table<Category>("category", alias) {
    companion object : Categories(null)
    override fun aliased(alias: String) = Categories(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val classId = int("class_id").bindTo { it.classId }
    val name = varchar("name").bindTo { it.name }
    val slug = varchar("slug").bindTo { it.slug }
    val url = varchar("url").bindTo { it.url }
    val iconUrl = varchar("icon_url").bindTo { it.iconUrl }
    val dateModified = timestamp("date_modified").bindTo { it.dateModified }
    val isClass = boolean("is_class").bindTo { it.isClass }
    val displayIndex = int("display_index").bindTo { it.displayIndex }

    val parentCategoryId = int("parent_category_id").references(Categories) { it.parentCategory }
    val parentCategory get() = parentCategoryId.referenceTable as Categories
}

open class Links(alias: String?) : Table<Link>("link", alias) {
    companion object : Links(null)
    override fun aliased(alias: String) = Links(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val websiteUrl = varchar("website_url").bindTo { it.websiteUrl }
    val wikiUrl = varchar("wiki_url").bindTo { it.wikiUrl }
    val issuesUrl = varchar("issues_url").bindTo { it.issuesUrl }
    val sourceUrl = varchar("source_url").bindTo { it.sourceUrl }
}

open class Authors(alias: String?) : Table<Author>("author", alias) {
    companion object : Authors(null)
    override fun aliased(alias: String) = Authors(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val url = varchar("url").bindTo { it.url }
}

open class Logos(alias: String?) : Table<Logo>("logo", alias) {
    companion object : Logos(null)
    override fun aliased(alias: String) = Logos(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val title = varchar("title").bindTo { it.title }
    val description = varchar("description").bindTo { it.description }
    val thumbnailUrl = varchar("thumbnail_url").bindTo { it.thumbnailUrl }
    val url = varchar("url").bindTo { it.url }
}

open class Screenshots(alias: String?) : Table<Screenshot>("screenshot", alias) {
    companion object : Screenshots(null)
    override fun aliased(alias: String) = Screenshots(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val title = varchar("title").bindTo { it.title }
    val description = varchar("description").bindTo { it.description }
    val thumbnailUrl = varchar("thumbnail_url").bindTo { it.thumbnailUrl }
    val url = varchar("url").bindTo { it.url }
}

open class Hashes(alias: String?) : Table<Hash>("hash", alias) {
    companion object : Hashes(null)
    override fun aliased(alias: String) = Hashes(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val value = varchar("value").bindTo { it.value }
    val algo = int("algo").bindTo { it.algo }
}

open class Modules(alias: String?) : Table<Module>("module", alias) {
    companion object : Modules(null)
    override fun aliased(alias: String) = Modules(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val fingerprint = int("fingerprint").bindTo { it.fingerprint }
}

open class Mods(alias: String?) : Table<Mod>("mod", alias) {
    companion object : Mods(null)
    override fun aliased(alias: String) = Mods(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val classId = int("class_id").bindTo { it.classId }
    val name = varchar("name").bindTo { it.name }
    val slug = varchar("slug").bindTo { it.slug }
    val summary = varchar("summary").bindTo { it.slug }
    val status = int("status").bindTo { it.status }
    val downloadCount = int("download_count").bindTo { it.downloadCount }
    val isFeatured = boolean("is_featured").bindTo { it.isFeatured }
    val dateCreated = timestamp("date_created").bindTo { it.dateCreated }
    val dateModified = timestamp("date_modified").bindTo { it.dateModified }
    val dateReleased = timestamp("date_released").bindTo { it.dateReleased }
    val allowModDistribution = boolean("allow_mod_distribution").bindTo { it.allowModDistribution }
    val gamePopularityRank = int("game_popularity_rank").bindTo { it.gamePopularityRank }
    val isAvailable = boolean("is_available").bindTo { it.isAvailable }
    val thumbsUpCount = int("thumbs_up_count").bindTo { it.thumbsUpCount }

    val gameId = int("game_id").references(Games) { it.game }
    val game get() = gameId.referenceTable as Games

    val logoId = int("logo_id").references(Logos) { it.logo }
    val logo get() = logoId.referenceTable as Logos

    val linkId = int("link_id").references(Links) { it.link }
    val link get() = linkId.referenceTable as Links

    val primaryCategoryId = int("primary_category_id").references(Categories) { it.primaryCategory }
    val primaryCategory get() = primaryCategoryId.referenceTable as Categories
}

open class Files(alias: String?) : Table<File>("file", alias) {
    companion object : Files(null)
    override fun aliased(alias: String) = Files(alias)

    val id = int("id").primaryKey().bindTo { it.id }
    val isAvailable = boolean("is_available").bindTo { it.isAvailable }
    val displayName = varchar("display_name").bindTo { it.displayName }
    val fileName = varchar("file_name").bindTo { it.fileName }
    val releaseType = int("release_type").bindTo { it.releaseType }
    val fileStatus = int("file_status").bindTo { it.fileStatus }
    val fileDate = timestamp("file_date").bindTo { it.fileDate }
    val fileLength = int("file_length").bindTo { it.fileLength }
    val downloadCount = int("download_count").bindTo { it.downloadCount }
    val downloadUrl = varchar("download_url").bindTo { it.downloadUrl }
    val exposeAsAlternative = boolean("expose_as_alternative").bindTo { it.exposeAsAlternative }
    val isServerPack = boolean("is_server_pack").bindTo { it.isServerPack }
    val fileFingerPrint = int("file_finger_print").bindTo { it.fileFingerPrint }


    val gameId = int("game_id").references(Games) { it.game }
    val game get() = gameId.referenceTable as Games

    val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods

    val parentProjectFileId = int("parent_project_file_id").references(Files) { it.parentProjectFile }
    val parentProjectFile get() = parentProjectFileId.referenceTable as Files

    val alternateFileId = int("alternate_file_id").references(Files) { it.alternateFile }
    val alternateFile get() = alternateFileId.referenceTable as Files
}

open class FileModules(alias: String?) : Table<FileModule>("file_module", alias) {
    companion object : FileModules(null)
    override fun aliased(alias: String) = FileModules(alias)

    val fileId = int("file_id").references(Files) { it.file }
    val file get() = fileId.referenceTable as Files

    val moduleId = int("module_id").references(Modules) { it.module }
    val module get() = moduleId.referenceTable as Modules
}

open class FileGameVersions(alias: String?) : Table<FileGameVersion>("file_game_version", alias) {
    companion object : FileGameVersions(null)
    override fun aliased(alias: String) = FileGameVersions(alias)

    val fileId = int("file_id").references(Files) { it.file }
    val file get() = fileId.referenceTable as Files

    val versionId = int("version_id").references(Versions) { it.version }
    val version get() = versionId.referenceTable as Versions
}

open class FileHashes(alias: String?) : Table<FileHash>("file_hash", alias) {
    companion object : FileHashes(null)
    override fun aliased(alias: String) = FileHashes(alias)

    val fileId = int("file_id").references(Files) { it.file }
    val file get() = fileId.referenceTable as Files

    val hashId = int("hash_id").references(Hashes) { it.hash }
    val hash get() = hashId.referenceTable as Hashes
}

open class Dependencies(alias: String?) : Table<Dependency>("dependency", alias) {
    companion object : Dependencies(null)
    override fun aliased(alias: String) = Dependencies(alias)

    val relationType = int("relation_type").bindTo { it.relationType }

    val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods
}

open class FileDependencies(alias: String?) : Table<FileDependency>("file_dependency", alias) {
    companion object : FileDependencies(null)
    override fun aliased(alias: String) = FileDependencies(alias)

    val fileId = int("file_id").references(Files) { it.file }
    val file get() = fileId.referenceTable as Files

    val dependencyId = int("dependency_id").references(Dependencies) { it.dependency }
    val dependency get() = dependencyId.referenceTable as Dependencies
}

open class ModAuthors(alias: String?) : Table<ModAuthor>("mod_author", alias) {
    companion object : ModAuthors(null)
    override fun aliased(alias: String) = ModAuthors(alias)

    val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods

    val authorId = int("author_id").references(Authors) { it.author }
    val author get() = authorId.referenceTable as Authors
}

open class ModScreenshots(alias: String?) : Table<ModScreenshot>("mod_screenshot", alias) {
    companion object : ModScreenshots(null)
    override fun aliased(alias: String) = ModScreenshots(alias)

    val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods

    val screenshotId = int("screenshot_id").references(Screenshots) { it.screenshot }
    val screenshot get() = screenshotId.referenceTable as Screenshots
}

open class ModCategories(alias: String?) : Table<ModCategory>("mod_screenshot", alias) {
    companion object : ModCategories(null)
    override fun aliased(alias: String) = ModCategories(alias)

    val gameId = int("game_id").references(Games) { it.game }
    val game get() = gameId.referenceTable as Games

    val modId = int("mod_id").references(Mods) { it.mod }
    val mod get() = modId.referenceTable as Mods

    val categoryId = int("category_id").references(Categories) { it.category }
    val category get() = categoryId.referenceTable as Categories
}