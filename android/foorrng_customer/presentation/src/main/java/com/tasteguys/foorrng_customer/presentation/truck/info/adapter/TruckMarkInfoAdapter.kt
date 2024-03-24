package com.tasteguys.foorrng_customer.presentation.truck.info.adapter

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseAdapter
import com.tasteguys.foorrng_customer.presentation.base.BaseHolder
import com.tasteguys.foorrng_customer.presentation.base.collapse
import com.tasteguys.foorrng_customer.presentation.base.expand
import com.tasteguys.foorrng_customer.presentation.databinding.ItemMarkInfoBinding
import com.tasteguys.foorrng_customer.presentation.model.TruckMark

private const val TAG = "TruckMarkInfoAdapter"
class TruckMarkInfoAdapter: BaseAdapter<TruckMark>() {

    class MarkInfoHolder(private val binding: ItemMarkInfoBinding) : BaseHolder<TruckMark>(binding){
        private val checkedDay = mapOf(
            "월" to 0, "화" to 1, "수" to 2, "목" to 3, "금" to 4, "토" to 5, "일" to 6
        )
        override fun bindInfo(data: TruckMark) {
            with(binding) {
                tvRoadInfo.text = data.address
                val span = SpannableStringBuilder(tvDayInfo.text)
                for (day in data.operationInfo) {
                    val dayIdx = checkedDay[day.day]!!
                    span.setSpan(
                        ForegroundColorSpan(root.resources.getColor(R.color.foorrng_orange_dark)),
                        dayIdx,
                        dayIdx + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                tvDayInfo.text = span
                rvDayInfo.apply {
                    adapter = TruckOperationInfoAdapter().apply {
                        submitList(data.operationInfo)
                    }
                }

//                root.setOnClickListener {
//                    Log.d(TAG, "ViewExpand bindInfo: ${llExpand.visibility}")
////                    if(rvDayInfo.visibility == View.GONE) rvDayInfo.visibility = View.VISIBLE else rvDayInfo.visibility = View.GONE
//                    if(llExpand.visibility == View.GONE) {
//                        ToggleAnimation.expand(llExpand) {
//                            rvDayInfo.visibility = View.VISIBLE
//                        }
//
////                        llExpand.expand()
//                    } else{
//                        rvDayInfo.visibility = View.GONE
//                        ToggleAnimation.collapse(llExpand)
////                        llExpand.collapse()
//                    }
////                    if(llExpand.visibility == View.GONE) llExpand.visibility = View.VISIBLE else llExpand.visibility = View.GONE
//
//                }

                btnExpandToggle.setOnClickListener {
                    if (rvDayInfo.visibility == View.GONE){
//                        rvDayInfo.visibility = View.VISIBLE
//                        Log.d(TAG, "bindInfo: ${rvDayInfo.visibility}")
                        rvDayInfo.expand()
                    } else if (rvDayInfo.visibility == View.VISIBLE) {
//                        rvDayInfo.visibility = View.GONE
//                        Log.d(TAG, "bindInfo: ${rvDayInfo.visibility}")
                        rvDayInfo.collapse()
                    }
                }

            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<TruckMark> {
        return MarkInfoHolder(ItemMarkInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply { setOnItemClickListener(clickListener) }
    }
}