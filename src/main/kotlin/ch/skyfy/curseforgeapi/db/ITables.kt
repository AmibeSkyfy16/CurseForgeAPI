package ch.skyfy.curseforgeapi.db

import org.ktorm.entity.Entity
import java.time.LocalDateTime

interface Asset : Entity<Asset> {
    companion object : Entity.Factory<Asset>()

    val id: Int
    val iconUrl: String
    val tileUrl: String
    val coverUrl: String
}

interface Game : Entity<Game> {
    companion object : Entity.Factory<Game>()

    val id: Int
    val name: String
    val slug: String
    val dateModified: LocalDateTime
    val status: Int
    val apiStatus: Int

    val asset: Asset
}

interface VersionType : Entity<VersionType> {
    companion object : Entity.Factory<VersionType>()

    val id: Int
    val name: String
    val slug: String

    val game: Game
}

interface Version : Entity<Version> {
    companion object : Entity.Factory<Version>()

    val id: Int
    val name: String

    val versionType: VersionType
}

interface Category : Entity<Category> {
    companion object : Entity.Factory<Category>()

    val id: Int
    val classId: Int
    val name: String
    val slug: String
    val url: String
    val iconUrl: String
    val dateModified: LocalDateTime
    val isClass: Boolean
    val displayIndex: Int

    val parentCategory: Category
}

interface Link : Entity<Link> {
    companion object : Entity.Factory<Link>()

    val id: Int
    val websiteUrl: String
    val wikiUrl: String
    val issuesUrl: String
    val sourceUrl: String
}

interface Author : Entity<Author> {
    companion object : Entity.Factory<Author>()

    val id: Int
    val name: String
    val url: String
}

interface Logo : Entity<Logo> {
    companion object : Entity.Factory<Logo>()

    val id: Int
    val title: String
    val description: String
    val thumbnailUrl: String
    val url: String
}

interface Screenshot : Entity<Screenshot> {
    companion object : Entity.Factory<Screenshot>()

    val id: Int
    val title: String
    val description: String
    val thumbnailUrl: String
    val url: String
}

interface Hash : Entity<Hash> {
    companion object : Entity.Factory<Hash>()

    val id: Int
    val value: String
    val algo: Int
}

interface Module : Entity<Module> {
    companion object : Entity.Factory<Module>()

    val id: Int
    val name: String
    val fingerprint: Int
}

interface Mod : Entity<Mod> {
    companion object : Entity.Factory<Mod>()

    val id: Int
    val classId: Int
    val name: String
    val slug: String
    val summary: String
    val status: Int
    val downloadCount: Int
    val isFeatured: Boolean
    val dateCreated: LocalDateTime
    val dateModified: LocalDateTime
    val dateReleased: LocalDateTime
    val allowModDistribution: Boolean
    val gamePopularityRank: Int
    val isAvailable: Boolean
    val thumbsUpCount: Int

    val game: Game
    val logo: Logo
    val link: Link
    val primaryCategory: Category
}

interface File : Entity<File> {
    companion object : Entity.Factory<File>()

    val id: Int
    val isAvailable: Boolean
    val displayName: String
    val fileName: String
    val releaseType: Int
    val fileStatus: Int
    val fileDate: LocalDateTime
    val fileLength: Int
    val downloadCount: Int
    val downloadUrl: String
    val exposeAsAlternative: Boolean
    val isServerPack: Boolean
    val fileFingerPrint: Int

    val game: Game
    val mod: Mod
    val parentProjectFile: File
    val alternateFile: File
}

interface FileModule : Entity<FileModule> {
    companion object : Entity.Factory<FileModule>()

    val id: Int

    val file: File
    val module: Module
}

interface FileGameVersion : Entity<FileGameVersion> {
    companion object : Entity.Factory<FileGameVersion>()

    val id: Int

    val file: File
    val version: Version
}

interface FileHash : Entity<FileHash> {
    companion object : Entity.Factory<FileHash>()

    val id: Int

    val file: File
    val hash: Hash
}

interface Dependency : Entity<Dependency> {
    companion object : Entity.Factory<Dependency>()

    val id: Int
    val relationType: Int

    val mod: Mod
}

interface FileDependency : Entity<FileDependency> {
    companion object : Entity.Factory<FileDependency>()

    val id: Int

    val file: File
    val dependency: Dependency
}

interface ModAuthor : Entity<ModAuthor> {
    companion object : Entity.Factory<ModAuthor>()

    val id: Int

    val mod: Mod
    val author: Author
}

interface ModScreenshot : Entity<ModScreenshot> {
    companion object : Entity.Factory<ModScreenshot>()

    val id: Int

    val mod: Mod
    val screenshot: Screenshot
}

interface ModCategory : Entity<ModCategory> {
    companion object : Entity.Factory<ModCategory>()

    val id: Int

    val game: Game
    val mod: Mod
    val category: Category
}