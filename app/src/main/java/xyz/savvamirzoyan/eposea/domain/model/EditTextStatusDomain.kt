package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

data class EditTextStatusDomain(
    @StringRes val helperText: Int? = null,
    @StringRes val error: Int? = null
) : Model.Domain

//isEnabled = status.value.isEnabled
//                    error = status.value.error?.let { getString(it) }
//                    helperText = getString(status.value.helperText)
//                    visibility = status.value.visibility