package com.tasteguys.foorrng_customer.presentation.profile.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemFavoriteTruckBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckWithFavorite

private const val TAG = "FavoriteTruckAdapter"
class FavoriteTruckAdapter(
    override var clickListener: BaseHolder.ItemClickListener = object :
        BaseHolder.ItemClickListener {
        // default 매개 변수
        override fun onClick(position: Int) {}
    }
) : BaseAdapter<TruckWithFavorite>(clickListener) {

    class TruckListHolder(private val binding: ItemFavoriteTruckBinding) :
        BaseHolder<TruckWithFavorite>(binding) {

        interface ButtonClickListener {
            fun onToggleClick(icChecked: Boolean)
            fun onButtonClick()

        }

        private lateinit var buttonClickListener: ButtonClickListener

        fun setOnButtonClickListener(clickListener: ButtonClickListener) {
            buttonClickListener = clickListener
        }

        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckWithFavorite) {
            binding.root.setOnClickListener {
                clickListener.onClick(layoutPosition)
            }
            binding.btnFavorite.setOnCheckedChangeListener { _, b ->
                buttonClickListener.onToggleClick(b)
            }

            binding.btnPathfinder.setOnClickListener {
                buttonClickListener.onButtonClick()
            }

            Glide.with(binding.root.context)
                .load(data.picture)
                .error(R.drawable.logo_truck)
                .into(binding.civTruck)

            binding.tvTruckName.text = data.name

            binding.tvTruckTags.text = data.category.fold(""){ res, it->
                "$res #$it"
            }

            binding.tvReviewCnt.text = "${data.numOfReview}개"
            binding.tvDistance.text = "${data.distance}m"

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<TruckWithFavorite> {
        return TruckListHolder(ItemFavoriteTruckBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            setOnItemClickListener(clickListener)
            setOnButtonClickListener(buttonClickListener)
        }
    }

    private lateinit var buttonClickListener: TruckListHolder.ButtonClickListener

    fun setOnButtonClickListener(listener: TruckListHolder.ButtonClickListener){
        buttonClickListener = listener
    }

}