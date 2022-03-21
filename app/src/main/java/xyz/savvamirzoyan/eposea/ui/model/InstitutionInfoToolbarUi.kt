package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionInfoToolbarUi(
    val title: String,
    val imageUrl: String = ""
) : Model.Ui {

    class Base(title: String, imageUrl: String) : InstitutionInfoToolbarUi(title, imageUrl)
    class Error(title: String) : InstitutionInfoToolbarUi(title)
    class Loading(title: String) : InstitutionInfoToolbarUi(title)

    override fun equals(other: Any?): Boolean {
        if (other is InstitutionInfoToolbarUi)
            return this.title == other.title && this.imageUrl == other.imageUrl
        return false
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + imageUrl.hashCode()
        return result
    }
}