package com.tasteguys.foorrng_customer.presentation.truck.info

import android.os.Bundle
import android.view.View
import com.tasteguys.foorrng_customer.presentation.Dummy
import com.tasteguys.foorrng_customer.presentation.R
import com.tasteguys.foorrng_customer.presentation.base.BaseFragment
import com.tasteguys.foorrng_customer.presentation.databinding.FragmentTruckReviewBinding
import com.tasteguys.foorrng_customer.presentation.truck.info.adapter.TruckReviewAdapter


object ReviewMap{
    private const val DELICIOUS = "음식이 맛있어요"
    private const val SPECIAL = "특별한 메뉴가 있어요"
    private const val CHEAP = "가성비가 좋아요"
    private const val FAST = "음식이 빨리 나와요"
    private const val COOL = "푸드트럭이 멋져요"
    private const val CLEAN = "매장이 청결해요"
    private const val KIND = "사장님이 친절해요"

    val reviewMap = mapOf(
        DELICIOUS to R.drawable.img_delicious,
        SPECIAL to R.drawable.img_special,
        CHEAP to R.drawable.img_cheap,
        FAST to R.drawable.img_fast,
        COOL to R.drawable.img_cool,
        CLEAN to R.drawable.img_clean,
        KIND to R.drawable.img_kind
    )
}

class TruckReviewFragment : BaseFragment<FragmentTruckReviewBinding>(
    { FragmentTruckReviewBinding.bind(it)}, R.layout.fragment_truck_review
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        with(binding){
            tvTotalCnt.text = Dummy.truckInfo.review.total.toString()
            rvReview.apply {
                adapter = TruckReviewAdapter().apply {
                    submitList(Dummy.truckInfo.review.reviews)
                }
            }
            btnWriteReview.setOnClickListener {
                requireParentFragment().parentFragmentManager.beginTransaction()
                    .replace(R.id.fcv_container, TruckWriteReviewFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

}