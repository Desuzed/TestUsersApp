package com.desuzed.testusersapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.desuzed.testusersapp.App
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.databinding.FragmentEditUserBinding
import com.desuzed.testusersapp.ui.ViewModelFactory
import com.desuzed.testusersapp.ui.detail.DetailInfoViewModel

class EditUserDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentEditUserBinding

    private val detailInfoViewModel: DetailInfoViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelFactory(App.instance.getRepo())
        )
            .get(DetailInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun collect() {
        lifecycleScope.launchWhenStarted {
            detailInfoViewModel.currentUserStateFlow.collect { user ->
                binding.firstNameEditText.setText(user.firstName)
                binding.lastNameEditText.setText(user.lastName)
                binding.emailEditText.setText(user.email)
                binding.avatarEditText.setText(user.avatar)
                applyButtonClickListener(user)
            }
        }
    }

    private fun applyButtonClickListener(user: User) {
        binding.applyChangesButton.setOnClickListener {
            detailInfoViewModel.updateUser(
                User(
                    user.id,
                    binding.firstNameEditText.text.toString(),
                    binding.lastNameEditText.text.toString(),
                    binding.emailEditText.text.toString(),
                    binding.avatarEditText.text.toString()
                )
            )
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        collect()
    }


}