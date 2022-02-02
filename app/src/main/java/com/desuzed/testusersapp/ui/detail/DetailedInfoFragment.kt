package com.desuzed.testusersapp.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.desuzed.testusersapp.App
import com.desuzed.testusersapp.R
import com.desuzed.testusersapp.databinding.FragmentDetailedInfoBinding
import com.desuzed.testusersapp.ui.ViewModelFactory
import com.desuzed.testusersapp.ui.edit.EditUserDialogFragment

class DetailedInfoFragment : Fragment() {
    private lateinit var binding: FragmentDetailedInfoBinding
    private val detailInfoViewModel: DetailInfoViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelFactory(App.instance.getRepo())
        )
            .get(DetailInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        retrieveArgument()
    }


    private fun collect() {
        lifecycleScope.launchWhenResumed {
            detailInfoViewModel.currentUserStateFlow.collect { user ->
                binding.firstNameDetailTextView.text = user.firstName
                binding.secondNameDetailTextView.text = user.lastName
                binding.emailDetailTextView.text = user.email
                Glide
                    .with(requireContext())
                    .load(user.avatar)
                    .into(binding.avatarDetailImageView)
            }
        }
    }

    private fun retrieveArgument() {
        val userId = arguments?.getInt(USER_ID)
        if (userId != null) {
            detailInfoViewModel.updateCurrentUserStateFlow(userId)
        }
    }

    override fun onResume() {
        super.onResume()
        collect()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detailed_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editUserItem -> {
                EditUserDialogFragment().show(childFragmentManager, "EditFragment")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {
        const val USER_ID = "user"
    }
}