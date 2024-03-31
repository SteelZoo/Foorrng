package com.tasteguys.foorrng_customer.presentation.profile.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.contains
import com.bumptech.glide.Glide
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemTruckBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress

private const val TAG = "FavoriteTruckAdapter"

class TruckAdapter : BaseAdapter<TruckDataWithAddress>() {

    private val favoriteStatus = SparseBooleanArray()

    class TruckListHolder(
        private val binding: ItemTruckBinding,
        private val truckAdapter: TruckAdapter
    ) :
        BaseHolder<TruckDataWithAddress>(binding) {
        interface ButtonClickListener {
            fun onToggleClick(position: Int)
            fun onButtonClick(position: Int)

        }

        private lateinit var buttonClickListener: ButtonClickListener

        fun setOnButtonClickListener(clickListener: ButtonClickListener) {
            buttonClickListener = clickListener
        }

        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckDataWithAddress) {
            Log.d(TAG, "bindInfo: ${data.name} ${data.type} ${data.markId} ${data.truckId}")
            with(binding) {
                root.setOnClickListener {
                    clickListener.onClick(layoutPosition)
                }
                btnFavorite.setOnClickListener {
                    buttonClickListener.onToggleClick(layoutPosition)
                    truckAdapter.favoriteStatus.put(
                        data.truckId.toInt(),
                        !truckAdapter.favoriteStatus.get(data.truckId.toInt())
                    )
                    btnFavorite.isChecked = truckAdapter.favoriteStatus.get(data.truckId.toInt())
                }
                btnPathfinder.setOnClickListener {
                    buttonClickListener.onButtonClick(layoutPosition)
                }
                Glide.with(root.context)
                    .load(data.picture)
                    .error(R.drawable.logo_truck)
                    .into(civTruck)
                tvTruckName.text = data.name
                tvTruckTags.text = data.category.fold("") { res, it ->
                    "$res #$it"
                }
                tvReviewCnt.text = "${data.numOfReview}개"
                if (data.distance < 0) {
                    llDistanceView.visibility = View.GONE
                }
                binding.tvDistance.text = "${data.distance}m"
                if(!truckAdapter.favoriteStatus.contains(data.truckId.toInt())){
                    truckAdapter.favoriteStatus.put(data.truckId.toInt(), data.isFavorite)
                }
                btnFavorite.isChecked = truckAdapter.favoriteStatus.get(data.truckId.toInt())
//                Log.d(TAG, "bindInfofs: ${truckAdapter.favoriteStatus}")
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<TruckDataWithAddress> {
        return TruckListHolder(
            ItemTruckBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), this
        ).apply {
            setOnItemClickListener(clickListener)
            setOnButtonClickListener(buttonClickListener)
        }
    }

    private lateinit var buttonClickListener: TruckListHolder.ButtonClickListener

    fun setOnButtonClickListener(listener: TruckListHolder.ButtonClickListener) {
        buttonClickListener = listener
    }

}