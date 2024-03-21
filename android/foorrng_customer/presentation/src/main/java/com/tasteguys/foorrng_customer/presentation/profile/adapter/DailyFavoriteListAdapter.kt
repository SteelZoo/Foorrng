package com.tasteguys.foorrng_customer.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemFavoriteCategoryBinding

class DailyFavoriteListAdapter: BaseAdapter<String>() {

    class FavoriteHolder(private val binding: ItemFavoriteCategoryBinding) : BaseHolder<String>(binding) {
        override fun bindInfo(data: String) {
            binding.chipFavoriteCategory.text = data
            binding.root.setOnClickListener {
                clickListener.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<String> {
       return FavoriteHolder(ItemFavoriteCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
           setOnItemClickListener(clickListener)
       }
    }
}