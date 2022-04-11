package xyz.savvamirzoyan.eposea.core

fun interface Clicker<T> {
    fun onClick(item: T)
}