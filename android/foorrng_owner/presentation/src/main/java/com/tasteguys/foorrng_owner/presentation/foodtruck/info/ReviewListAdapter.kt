package com.tasteguys.foorrng_owner.presentation.foodtruck.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasteguys.foorrng_owner.presentation.databinding.ItemReviewBinding
import com.tasteguys.foorrng_owner.presentation.model.Review
import com.tasteguys.foorrng_owner.presentation.model.ReviewSet

class ReviewListAdapter(
    private val review: ReviewSet
) : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(review.totalCount,review.reviewList[position])
    }

    override fun getItemCount(): Int = review.reviewList.size

    class ReviewViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(totalCount: Int, review: Review) {
            with(binding) {
                tvReviewTitle.text = review.name
                tvReviewCount.text = review.count.toString()+"개"
                progressBar.progress = review.count
                progressBar.max = totalCount
             }
        }
    }
}
