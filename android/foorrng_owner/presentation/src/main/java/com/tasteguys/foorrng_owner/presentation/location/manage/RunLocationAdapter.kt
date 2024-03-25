package com.tasteguys.foorrng_owner.presentation.location.manage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasteguys.foorrng_owner.presentation.databinding.ItemLocationInfoBinding
import com.tasteguys.foorrng_owner.presentation.model.location.RunInfo
import com.tasteguys.foorrng_owner.presentation.model.location.RunLocationInfo

class RunLocationAdapter(
    private val runLocationList: List<RunLocationInfo>
) : RecyclerView.Adapter<RunLocationAdapter.RunLocationViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunLocationViewHolder {
        return RunLocationViewHolder(
            ItemLocationInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RunLocationViewHolder, position: Int) {
        holder.bind(runLocationList[position])
    }

    override fun getItemCount(): Int {
        return runLocationList.size
    }

    class RunLocationViewHolder(
        private val binding: ItemLocationInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var runInfoAdapter: RunInfoAdapter? = null

        fun bind(item: RunLocationInfo) {
            if (binding.rvLocationDays.adapter == null){
                setRunInfoAdapter(item.runInfoList)
            }

            binding.tvLocationAddress.text = item.address

        }

        private fun setRunInfoAdapter(runInfoList: List<RunInfo>) {
            if (runInfoAdapter == null){
                runInfoAdapter = RunInfoAdapter(runInfoList)
            }
            binding.rvLocationDays.adapter = runInfoAdapter
        }
    }
}