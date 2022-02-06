package xyz.savvamirzoyan.eposea.domain.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorDomain(exception: Exception) : Error(exception)
