package xyz.savvamirzoyan.eposea.core

abstract class Error(val exception: Exception, val errorMessage: String) : Exception()