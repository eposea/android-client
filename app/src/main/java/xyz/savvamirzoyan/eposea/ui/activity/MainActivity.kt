package xyz.savvamirzoyan.eposea.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.R

@ExperimentalSerializationApi
class MainActivity : CoreActivity() {

    private val navController by lazy { findNavController(R.id.main_nav_host_fragment) }
    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }
    private val bottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.navigation_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onDestroy() {
        super.onDestroy()

        finishAffinity()
    }

    fun startLogin() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}