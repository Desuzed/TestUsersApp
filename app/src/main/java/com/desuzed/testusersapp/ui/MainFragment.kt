package com.desuzed.testusersapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.desuzed.testusersapp.R
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.databinding.FragmentMainBinding
import com.desuzed.testusersapp.ui.adapters.OnItemClickListener
import com.desuzed.testusersapp.ui.adapters.UserAdapter

class MainFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.usersRecyclerView
        val userAdapter = UserAdapter(this)
        val mockUserList = ArrayList<User>()
        recyclerView.adapter = userAdapter
        mockUserList.add(User(1, "First name", "sec name", " email", "https://reqres.in/img/faces/12-image.jpg"))
        mockUserList.add(User(2, "First name", "sec name", " email", "https://reqres.in/img/faces/7-image.jpg"))
        userAdapter.submitList(mockUserList)
    }

    override fun onClick(user: User) {
        val bundle = bundleOf(DetailedInfoFragment.USER_KEY to user)
        navigate(R.id.action_mainFragment_to_detailedInfoFragment, bundle)
    }

}