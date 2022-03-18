package xyz.savvamirzoyan.eposea.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.R

class AuthActivity : CoreActivity() {
    private val navController by lazy { findNavController(R.id.auth_nav_host_fragment) }
    private val navigationView by lazy {
        findViewById<NavigationView>(R.id.auth_navigation_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    @ExperimentalSerializationApi
    fun startApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}