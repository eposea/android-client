package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

data class EditTextStatusUi(
    val helperText: String? = null,
    val errorText: String? = null
) : Model.Ui
