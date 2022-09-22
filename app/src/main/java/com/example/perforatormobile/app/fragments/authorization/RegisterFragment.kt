package com.example.perforatormobile.app.fragments.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.perforatormobile.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RegisterFragment: Fragment() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.editLogin.doOnTextChanged { text, _, _, _ ->
            viewModel.onUserNameChanged(text.toString())
        }
        binding.editPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordChanged(text.toString())
        }
        binding.editPhone.doOnTextChanged { text, _, _, _ ->
            viewModel.onPhoneChanged(text.toString())
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
            viewModel.editPhoneHelper
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { messageRes ->
                    binding.editPhoneLayout.helperText = messageRes?.let { it -> getText(it) }
                }
        }

        binding.registerButton.setOnClickListener {
            viewModel.on
        }

        return binding.root
    }
}