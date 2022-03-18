package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.core.CoreTextWatcher
import xyz.savvamirzoyan.eposea.databinding.FragmentLoginBinding
import xyz.savvamirzoyan.eposea.ui.activity.AuthActivity
import xyz.savvamirzoyan.eposea.ui.viewmodel.LoginViewModel

class LoginFragment : CoreFragment<AuthActivity, LoginViewModel>() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = app.loginViewModel

        setSignUpClickListener()
        setEmailChangeListener()
        setPasswordChangeListener()
        setLoginClickListener()

        launchCoroutine { setEmailEditTextStateListener() }
        launchCoroutine { setPasswordEditTextStateListener() }
        launchCoroutine { setLoginButtonStateListener() }

        return binding.root
    }

    private fun openRegisterScreen() {
        findNavController().navigate(LoginFragmentDirections.toRegisterFragment())
    }

    private fun setSignUpClickListener() {
        binding.textViewSignUp.setOnClickListener { openRegisterScreen() }
    }

    private fun setEmailChangeListener() {
        binding.editTextEmail.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onEmailChange(p0?.toString() ?: "")
            }
        })
    }

    private fun setPasswordChangeListener() {
        binding.editTextPassword.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onPasswordChange(p0?.toString() ?: "")
            }
        })
    }

    private fun setLoginClickListener() {
        binding.buttonLogin.setOnClickListener {
            viewModel.onLoginClick()
        }
    }

    private suspend fun setEmailEditTextStateListener() {
        viewModel.emailEditTextFlow.collect {
            binding.textInputLayoutEmail.apply {
                helperText = it.helperText
                error = it.errorText
                isErrorEnabled = it.errorText != null
            }
        }
    }

    private suspend fun setPasswordEditTextStateListener() {
        viewModel.passwordEditTextFlow.collect {
            binding.textInputLayoutPassword.apply {
                helperText = it.helperText
                error = it.errorText
                isErrorEnabled = it.errorText != null
            }
        }
    }

    private suspend fun setLoginButtonStateListener() {
        viewModel.isLoginButtonEnabledFlow.collect {
            binding.buttonLogin.isEnabled = it
        }
    }
}