package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.viewmodel.SplashViewModel

class SplashFragment : CoreFragment<SplashViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ((activity as CoreActivity).application as App).splashViewModel

        launchCoroutine {
            viewModel.isLoggedInSharedFlow.collect { isLogged ->
                isLogged?.let {
                    if (isLogged) {
                        findNavController().navigate(SplashFragmentDirections.toMain())
                    } else {
                        findNavController().navigate(SplashFragmentDirections.toLogin())
                    }
                }
            }
        }

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}