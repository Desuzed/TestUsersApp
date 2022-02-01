package com.desuzed.testusersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.desuzed.testusersapp.App
import com.desuzed.testusersapp.R
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.databinding.FragmentUsersBinding
import com.desuzed.testusersapp.ui.adapters.OnItemClickListener
import com.desuzed.testusersapp.ui.adapters.UserAdapter

class UsersFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentUsersBinding
    private val userAdapter = UserAdapter(this)

    private val usersViewModel: UsersViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelFactory(App.instance.getRepo())
        )
            .get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.usersRecyclerView
        recyclerView.adapter = userAdapter
        collect()
        usersViewModel.fetchUsers()
    }

    private fun collect() {
        lifecycleScope.launchWhenStarted {
            usersViewModel.usersStateFlow.collect {
                userAdapter.submitList(it)
            }
        }
    }

    override fun onClick(user: User) {
        val bundle = bundleOf(DetailedInfoFragment.USER_KEY to user)
        navigate(R.id.action_usersFragment_to_detailedInfoFragment, bundle)
    }

}