package ch.skyfy.curseforgeapi

object Utils {
    fun areNull(field: List<Any?>) : Boolean{
        return field.stream().anyMatch { it == null }
    }
}