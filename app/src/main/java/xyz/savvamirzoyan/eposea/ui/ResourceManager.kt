package xyz.savvamirzoyan.eposea.ui

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringId: Int): String
    fun getString(@StringRes stringId: Int, vararg params: Any): String

    class Base(private val context: Context) : ResourceManager {

        override fun getString(stringId: Int) = context.getString(stringId)
        override fun getString(stringId: Int, vararg params: Any) = context.getString(stringId, *params)
    }
}