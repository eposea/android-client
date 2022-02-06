package xyz.savvamirzoyan.eposea.data.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorData(exception: Exception) : Error(exception)
