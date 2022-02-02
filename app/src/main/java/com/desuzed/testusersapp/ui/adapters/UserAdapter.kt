package com.desuzed.testusersapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

    class UserViewHolder(binding: UserRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val firstNameTextView: TextView = binding.firstNameTextView
        private val secondNameTextView: TextView = binding.secondNameTextView
        private val avatarImageView: ImageView = binding.avatarImageView

        fun bind(current: User, onItemClickListener: OnItemClickListener) {
            firstNameTextView.text = current.firstName
            secondNameTextView.text = current.lastName
            Glide
                .with(itemView.context)
                .load(current.avatar)
                .into(avatarImageView)
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