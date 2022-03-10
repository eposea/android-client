package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

data class EditTextStatusDomain(
    @StringRes val helperText: Int? = null,
    @StringRes val error: Int? = null
) : Model.Domain
