package com.tasteguys.foorrng_customer.presentation.truck.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemMenuBinding
import com.tasteguys.foorrng_customer.presentation.databinding.ItemMenuSimpleBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckMenu

class TruckMenuAdapter(
    override var clickListener: BaseHolder.ItemClickListener = object: BaseHolder.ItemClickListener{
        // default 매개 변수
        override fun onClick(position: Int) {}}, private var isSimple : Boolean = true
) : BaseAdapter<TruckMenu>(clickListener) {

    class SimpleMenuHolder(private val binding: ItemMenuSimpleBinding) : BaseHolder<TruckMenu>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckMenu) {
            binding.tvMenuName.text = data.name
            binding.tvMenuPrice.text = "${data.price}원"
        }
    }

    class MenuHolder(private val binding: ItemMenuBinding) : BaseHolder<TruckMenu>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckMenu) {
            Glide.with(binding.root)
                .load(data.img)
                .error(R.drawable.logo_truck)
                .into(binding.ivMenuImg)
            binding.tvMenuName.text = data.name
            binding.tvMenuPrice.text = "${data.price}원"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<TruckMenu> {
        return if(isSimple){
            SimpleMenuHolder(
                ItemMenuSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }else{
            MenuHolder(
                ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}