package com.desuzed.testusersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.desuzed.testusersapp.User
import com.desuzed.testusersapp.databinding.FragmentDetailedInfoBinding

class DetailedInfoFragment : Fragment() {
    private lateinit var binding: FragmentDetailedInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent()
    }

    private fun setContent (){
        val user = arguments?.getParcelable<User>(USER_KEY) as User
        if (user!=null){
            binding.firstNameDetailTextView.text = user.firstName
            binding.secondNameDetailTextView.text = user.lastName
            binding.emailDetailTextView.text = user.email
            Glide
                .with(requireContext())
                .load(user.avatar)
                .into(binding.avatarDetailImageView)
        }
    }

    companion object{
        const val USER_KEY = "user"
    }
}