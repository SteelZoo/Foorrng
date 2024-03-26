package com.tasteguys.foorrng_customer.presentation.profile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemTruckBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress

private const val TAG = "FavoriteTruckAdapter"
class TruckAdapter: BaseAdapter<TruckDataWithAddress>() {

    class TruckListHolder(private val binding: ItemTruckBinding) :
        BaseHolder<TruckDataWithAddress>(binding) {

        interface ButtonClickListener {
            fun onToggleClick(icChecked: Boolean)
            fun onButtonClick()

        }

        private lateinit var buttonClickListener: ButtonClickListener

        fun setOnButtonClickListener(clickListener: ButtonClickListener) {
            buttonClickListener = clickListener
        }

        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckDataWithAddress) {
            with(binding){
                root.setOnClickListener {
                    clickListener.onClick(layoutPosition)
                }
                btnFavorite.setOnCheckedChangeListener { _, b ->
                    buttonClickListener.onToggleClick(b)
                }
                btnPathfinder.setOnClickListener {
                    buttonClickListener.onButtonClick()
                }
                Glide.with(root.context)
                    .load(data.picture)
                    .error(R.drawable.logo_truck)
                    .into(civTruck)
                tvTruckName.text = data.name
                tvTruckTags.text = data.category.fold(""){ res, it->
                    "$res #$it"
                }
                tvReviewCnt.text = "${data.numOfReview}개"
                if(data.distance<0){
                    llDistanceView.visibility = View.GONE
                }
                binding.tvDistance.text = "${data.distance}m"
                if(data.isFavorite){
                    btnFavorite.isChecked = true
                }
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<TruckDataWithAddress> {
        return TruckListHolder(ItemTruckBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            setOnItemClickListener(clickListener)
            setOnButtonClickListener(buttonClickListener)
        }
    }

    private lateinit var buttonClickListener: TruckListHolder.ButtonClickListener

    fun setOnButtonClickListener(listener: TruckListHolder.ButtonClickListener){
        buttonClickListener = listener
    }

}