package com.ldc.android.travelchecker.ui.login.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.ldc.android.travelchecker.MainActivity
import com.ldc.android.travelchecker.R
import com.ldc.android.travelchecker.data.API.RetrofitBuilder
import com.ldc.android.travelchecker.data.API.SessionManager
import com.ldc.android.travelchecker.data.API.TravelAPIHelper
import com.ldc.android.travelchecker.data.repositories.LoginRepository
import com.ldc.android.travelchecker.databinding.FragmentLoginBinding
import java.io.Serializable


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        setHasOptionsMenu(false)
        sessionManager = SessionManager(this.requireContext())
        return binding.root
    }
    fun allFieldsFilledIn(): Boolean {

        if (binding.username.text.isNullOrBlank())
            return false
        if (binding.password.text.isNullOrBlank())
            return false
        return true
    }
    fun promptLogin() {
        val intent = Intent(this.activity, MainActivity::class.java)
        loginViewModel.login(binding.username.text.toString(),binding.password.text.toString())

        loginViewModel.loggedInUser.observe(viewLifecycleOwner, Observer { it ->
            it.token.let {
                sessionManager.saveAuthToken(it)
                intent.putExtra("loggedIn",loginViewModel.loggedInUser.value as Serializable)
                this.requireActivity().startActivity(intent);
            }
        })
        loginViewModel.loginFailed.observe(this.viewLifecycleOwner, Observer {
            it.let {
                Snackbar.make(
                    binding.root,
                    "Could not log in, please try again",
                    Snackbar.LENGTH_SHORT
                )
                    .setBackgroundTint(Color.rgb(92, 5, 18))
                    .setActionTextColor(Color.BLACK)
                    .show()
            }
        })

        val spinner = binding.loading
        spinner.showContextMenu()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(LoginRepository(
            TravelAPIHelper(RetrofitBuilder.apiService)
        )))
            .get(LoginViewModel::class.java)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            if (allFieldsFilledIn()) {
                promptLogin()
            } else
                Toast.makeText(this.context, "Fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}