package com.tasteguys.foorrng_owner.presentation.location.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasteguys.foorrng_owner.presentation.databinding.ItemRecommendLocationBinding
import com.tasteguys.foorrng_owner.presentation.model.location.RecommendLocation

class RecommendLocationAdapter(
    private val recommendLocationList: List<RecommendLocation>
) : RecyclerView.Adapter<RecommendLocationAdapter.RecommendLocationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendLocationViewHolder {
        return RecommendLocationViewHolder(
            ItemRecommendLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecommendLocationViewHolder, position: Int) {
        holder.bind(recommendLocationList[position])
    }

    override fun getItemCount(): Int {
        return recommendLocationList.size
    }

    class RecommendLocationViewHolder(
        private val binding: ItemRecommendLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendLocation) {
            binding.tvRecommendLocationAddress.text = item.address
            binding.tvRecommendLocationComment.text = item.comment
        }
    }
}