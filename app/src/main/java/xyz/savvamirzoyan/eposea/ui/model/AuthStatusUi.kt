package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class AuthStatusUi : Model.Ui {
    object Success : AuthStatusUi()
    data class Fail(val message: String) : AuthStatusUi()
}
