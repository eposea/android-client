package xyz.savvamirzoyan.eposea.extension

import android.view.View
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

fun StateFlow<Boolean>.mapVisibility() = this.map { if (it) View.VISIBLE else View.GONE }