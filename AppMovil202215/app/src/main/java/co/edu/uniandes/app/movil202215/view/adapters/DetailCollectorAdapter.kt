package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailCollectorItemBinding
import co.edu.uniandes.app.movil202215.models.Collector

class DetailCollectorAdapter : RecyclerView.Adapter<DetailCollectorAdapter.CommentViewHolder>(){

    var detailCollector :List<Collector> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: DetailCollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false)
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collectorDetail = detailCollector[position]
        }
        val currentItem = detailCollector[position]
    }

    override fun getItemCount(): Int {
        return detailCollector.size
    }


    class CommentViewHolder(val viewDataBinding: DetailCollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_collector_item
        }

    }


}
