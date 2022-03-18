package xyz.savvamirzoyan.eposea.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
@OptIn(ExperimentalSerializationApi::class)
class SplashActivity : CoreActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (application as App).splashViewModel

        launchCoroutine(Lifecycle.State.CREATED) {
            viewModel.isLoggedInFlow.collect { isLogged ->
                isLogged?.let {
                    val intent = if (isLogged) {
                        Intent(this@SplashActivity, MainActivity::class.java)
                    } else {
                        Intent(this@SplashActivity, AuthActivity::class.java)
                    }

                    startActivity(intent)
                    finishAffinity()
                }
            }
        }
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
}