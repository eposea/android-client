package xyz.savvamirzoyan.eposea.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.R

@ExperimentalSerializationApi
class MainActivity : CoreActivity() {

    private val navController by lazy { findNavController(R.id.main_nav_host_fragment) }
    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(
            findViewById<NavigationView>(R.id.navigation_view),
            navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}