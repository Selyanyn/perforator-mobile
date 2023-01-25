package com.example.perforatormobile.app.fragments.authorization

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.io.File


@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private val viewModel: RegisterViewModel by viewModels()

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            viewModel.userPhotoURI.value = data!!.data!!

            val imageprojection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor = requireContext().contentResolver.query(
                viewModel.userPhotoURI.value!!,
                imageprojection,
                null,
                null,
                null
            )!!

            cursor.moveToFirst()
            val indexImage: Int = cursor.getColumnIndex(imageprojection[0])
            viewModel.userPhotoFilePath = cursor.getString(indexImage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            val binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        }
        catch (e: Exception) {
            Log.e("tag", "tag", e)
            throw e
        }
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userPhotoURI
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect{ uri ->
                    if (uri !== null) {
                        binding.userImageView.setImageURI(uri)
                    }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isUserSuccessfullyRegistered
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { value ->
                    if (value) {
                        findNavController().navigate(R.id.action_navigation_registration_to_navigation_home)
                    }
                }
        }

        binding.userImageView.setOnClickListener {
            openImageChooser()
        }

        binding.registerButton.setOnClickListener {
            /*val cursor = requireContext().contentResolver.query(
                viewModel.userPhotoURI.value!!,
                arrayOf(android.provider.MediaStore.Images.ImageColumns.DATA),
                null,
                null
            )!!
            cursor.moveToFirst()
            viewModel.userPhotoFilePath = cursor.getString(0)
            cursor.close()*/
            val image = File(viewModel.userPhotoFilePath)
            val stream = requireContext().contentResolver.openInputStream(
                viewModel.userPhotoURI.value!!
            )
            val ext = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                requireContext().contentResolver.getType(viewModel.userPhotoURI.value!!)
            )
            viewModel.onRegisterButtonClicked(stream!!, "images/jpeg")
        }

        binding.alreadyRegisteredButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("images/jpeg"))
        resultLauncher.launch(intent)
    }
}