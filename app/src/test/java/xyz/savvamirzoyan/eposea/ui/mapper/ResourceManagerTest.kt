package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.ui.ResourceManager

class ResourceManagerTest : ResourceManager {
    override fun getString(stringId: Int): String = when (stringId) {
        R.string.error_api -> "Server is unavailable\nPlease check connection with Internet or try again"
        R.string.error -> "Error"
        R.string.no_error_message -> "No error message"
        R.string.error_other -> "Something bad happened with app\nPlease try again later"
        R.string.description -> "Description"
        else -> stringId.toString()
    }

    override fun getString(stringId: Int, vararg params: Any): String = getString(stringId)
}