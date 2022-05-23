package com.example.final2.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final2.R
import kotlinx.android.synthetic.main.resource_item.view.*

class ResourceItemAdapter(
    var items: List<ResourceItem>,
    private val viewModel: ResourceViewModel
): RecyclerView.Adapter<ResourceItemAdapter.ResourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resource_item, parent, false)
        return ResourceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val curResourceItem = items[position]

        holder.itemView.tvName.text = curResourceItem.name
        holder.itemView.tvAmount.text = "${curResourceItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curResourceItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            curResourceItem.amount++
            viewModel.upsert(curResourceItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(curResourceItem.amount > 0) {
                curResourceItem.amount--
                viewModel.upsert(curResourceItem)
            }
        }
    }

    inner class ResourceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}