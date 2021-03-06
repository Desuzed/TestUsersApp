package com.desuzed.testusersapp.ui.users

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.desuzed.testusersapp.App
import com.desuzed.testusersapp.R
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.databinding.FragmentUsersBinding
import com.desuzed.testusersapp.ui.ViewModelFactory
import com.desuzed.testusersapp.ui.adapters.OnItemClickListener
import com.desuzed.testusersapp.ui.adapters.UserAdapter
import com.desuzed.testusersapp.ui.detail.DetailedInfoFragment
import com.desuzed.testusersapp.ui.navigate

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
        setHasOptionsMenu(true)
        val recyclerView = binding.usersRecyclerView
        recyclerView.adapter = userAdapter
        collect()
    }

    private fun toggleVisibility(state: Boolean) {
        if (state) {
            binding.usersRecyclerView.visibility = View.VISIBLE
            binding.noUsersTextView.visibility = View.GONE
        } else {
            binding.usersRecyclerView.visibility = View.GONE
            binding.noUsersTextView.visibility = View.VISIBLE
        }
    }

    private fun collect() {
        lifecycleScope.launchWhenStarted {
            usersViewModel.usersStateFlow.collect {
                if (it.isEmpty()) {
                    toggleVisibility(false)
                } else {
                    toggleVisibility(true)
                    userAdapter.submitList(it)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            usersViewModel.errorStateFlow.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(user: User) {
        val bundle = bundleOf(DetailedInfoFragment.USER_ID to user.id)
        navigate(R.id.action_usersFragment_to_detailedInfoFragment, bundle)
    }

    override fun onLongClick(user: User) {
        usersViewModel.deleteUser(user)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_users_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refreshMenuItem -> {
                usersViewModel.fetchUsers()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}