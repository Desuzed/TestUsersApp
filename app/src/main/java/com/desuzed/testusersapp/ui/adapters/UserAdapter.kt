package com.desuzed.testusersapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desuzed.testusersapp.data.model.User
import com.desuzed.testusersapp.databinding.UserRecyclerViewItemBinding

class UserAdapter(
    private val onItemClickListener: OnItemClickListener,
) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val current = getItem(position)
        holder.bind(current, onItemClickListener)
    }

    class UserViewHolder(private val binding: UserRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: User, onItemClickListener: OnItemClickListener) {
            binding.firstNameTextView.text = current.firstName
            binding.secondNameTextView.text = current.lastName
            binding.emailTextView.text = current.email
            Glide
                .with(itemView.context)
                .load(current.avatar)
                .into(binding.avatarImageView)
            itemView.setOnClickListener {
                onItemClickListener.onClick(current)
            }
            itemView.setOnLongClickListener {
                onItemClickListener.onLongClick(current)
                return@setOnLongClickListener true
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                return UserViewHolder(
                    UserRecyclerViewItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }
    }

}

interface OnItemClickListener {
    fun onClick(user: User)
    fun onLongClick(user: User)
}