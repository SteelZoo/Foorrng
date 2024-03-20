package com.tasteguys.foorrng_owner.presentation.foodtruck

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.tasteguys.foorrng_owner.presentation.databinding.ItemMenuCategoryBinding

class MenuCategoryAdapter(
    private val menuCategoryList: List<String>
) : RecyclerView.Adapter<MenuCategoryAdapter.MenuCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        return MenuCategoryViewHolder(
            ItemMenuCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuCategoryViewHolder, position: Int) {
        holder.binding(menuCategoryList[position])
    }

    override fun getItemCount(): Int {
        return menuCategoryList.size
    }

    class MenuCategoryViewHolder(
        private val binding: ItemMenuCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var isChecked = false
        fun binding(item: String){
            binding.chipCategory.text = item
            binding.chipCategory.isChecked = isChecked
            binding.chipCategory.setOnClickListener {
                isChecked = (it as Chip).isChecked
            }
        }
    }
}