package com.example.perforatormobile.app.fragments.authorization

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            viewModel.onLoginButtonClicked()
            //findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
        }

        binding.notRegisteredButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }

        binding.editLogin.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserNameChanged(text.toString())
        }
        binding.editPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordChanged(text.toString())
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.editUserNameHelper
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { messageRes ->
                    binding.editLoginLayout.helperText = messageRes?.let { it -> getText(it) }
                }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.editPasswordHelper
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { messageRes ->
                    binding.editPasswordLayout.helperText = messageRes?.let { it -> getText(it) }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.response
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { response ->
                    val loginResponseData = response?.body()
                    if (loginResponseData?.status == "ok") {
                        findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                    }
                }
        }

        return binding.root
    }
}