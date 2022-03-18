package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.core.CoreTextWatcher
import xyz.savvamirzoyan.eposea.databinding.FragmentRegisterBinding
import xyz.savvamirzoyan.eposea.extension.snackbar
import xyz.savvamirzoyan.eposea.ui.activity.AuthActivity
import xyz.savvamirzoyan.eposea.ui.viewmodel.RegisterViewModel

class RegisterFragment : CoreFragment<AuthActivity, RegisterViewModel>() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = app.registerViewModel

        setEmailChangeListener()
        setPasswordChangeListener()
        setPasswordRepeatChangeListener()
        setEmailCodeChangeListener()
        setSignUpClickListener()
        setSendCodeClickListener()

        launchCoroutine { setIsSignUpButtonEnabledStateListener() }
        launchCoroutine { isSendConfirmationCodeButtonVisibleStateListener() }
        launchCoroutine { setEmailEditTextStateListener() }
        launchCoroutine { setPasswordEditTextStateListener() }
        launchCoroutine { setPasswordRepeatEditTextStateListener() }
        launchCoroutine { setConfirmationCodeBlockVisibility() }
        launchCoroutine { setIsSignUpButtonVisibleStateListener() }
        launchCoroutine { setIsSendCodeButtonEnabledStateListener() }
        launchCoroutine { setIsProgressSignUpVisibleStateListener() }
        launchCoroutine { setErrorMessageStateListener() }

        return binding.root
    }

    private suspend fun setIsSendCodeButtonEnabledStateListener() {
        viewModel.isSendCodeButtonEnabledFlow.collect {
            binding.buttonSendConfirmationCode.isEnabled = it
        }
    }

    private fun setSendCodeClickListener() {
        binding.buttonSendConfirmationCode.setOnClickListener {
            viewModel.onSendCodeClick()
        }
    }

    private fun setPasswordChangeListener() {
        binding.editTextPasswordRepeat.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onPasswordRepeatChange(p0?.toString() ?: "")
            }
        })
    }

    private fun setPasswordRepeatChangeListener() {
        binding.editTextPassword.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onPasswordChange(p0?.toString() ?: "")
            }
        })
    }

    private fun setEmailChangeListener() {
        binding.editTextEmail.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                val text = p0?.toString() ?: ""
                viewModel.onEmailChange(text)
            }
        })
    }

    private fun setSignUpClickListener() {
        binding.buttonSignUp.setOnClickListener {
            viewModel.onSignUpClick()
        }
    }

    private fun setEmailCodeChangeListener() {
        binding.editTextEmailCode.addTextChangedListener(object : CoreTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                val text = p0?.toString() ?: ""
                viewModel.onCodeFromEmailChange(text)
            }
        })
    }

    private suspend fun isSendConfirmationCodeButtonVisibleStateListener() {
        viewModel.isSendConfirmationCodeButtonVisible.collect { isVisible ->
            binding.buttonSendConfirmationCode.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private suspend fun setEmailEditTextStateListener() {
        viewModel.emailEditTextStateFlow.collect {
            binding.textInputLayoutEmail.isErrorEnabled = it.errorText != null
            binding.textInputLayoutEmail.error = it.errorText
        }
    }

    private suspend fun setPasswordEditTextStateListener() {
        viewModel.passwordEditTextStateFlow.collect {
            binding.textInputLayoutPassword.isErrorEnabled = it.errorText != null
            binding.textInputLayoutPassword.error = it.errorText
        }
    }

    private suspend fun setPasswordRepeatEditTextStateListener() {
        viewModel.passwordRepeatEditTextStateFlow.collect {
            binding.textInputLayoutPasswordRepeat.isErrorEnabled = it.errorText != null
            binding.textInputLayoutPasswordRepeat.error = it.errorText
        }
    }

    private suspend fun setIsSignUpButtonEnabledStateListener() {
        viewModel.isSignUpButtonEnabled.collect {
            binding.buttonSignUp.isEnabled = it
        }
    }

    private suspend fun setConfirmationCodeBlockVisibility() {
        viewModel.isConfirmationCodeBlockVisible.collect {
            binding.textInputLayoutCodeFromEmail.visibility = it
            binding.buttonSendConfirmationCode.visibility = it
        }
    }

    private suspend fun setIsSignUpButtonVisibleStateListener() {
        viewModel.isSignUpButtonVisible.collect {
            binding.buttonSignUp.visibility = it
        }
    }

    private suspend fun setIsProgressSignUpVisibleStateListener() {
        viewModel.isProgressSignUpVisibleFlow.collect {
            binding.progressSignUp.visibility = it
        }
    }

    private suspend fun setErrorMessageStateListener() {
        viewModel.errorMessageFlow.collect {
            snackbar(it)
        }
    }
}