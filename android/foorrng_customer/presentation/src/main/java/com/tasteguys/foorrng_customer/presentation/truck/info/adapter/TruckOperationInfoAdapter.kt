package com.tasteguys.foorrng_customer.presentation.truck.info.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.databinding.ItemOperationDailyDetailBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckOperationInfo

class TruckOperationInfoAdapter: BaseAdapter<TruckOperationInfo>() {

    class OperationInfoHolder(private val binding: ItemOperationDailyDetailBinding): BaseHolder<TruckOperationInfo>(binding){
        @SuppressLint("SetTextI18n")
        override fun bindInfo(data: TruckOperationInfo) {
            with(binding){
                tvDay.text = "${data.day}요일"
                tvTime.text="${data.startTime}~${data.endTime}"
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<TruckOperationInfo> {
        return OperationInfoHolder(ItemOperationDailyDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}