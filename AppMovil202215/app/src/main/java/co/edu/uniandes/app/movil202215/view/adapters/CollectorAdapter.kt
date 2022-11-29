package co.edu.uniandes.app.movil202215.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.CollectorItemBinding
import co.edu.uniandes.app.movil202215.models.Collector
import co.edu.uniandes.app.movil202215.view.CollectorFragmentDirections

class CollectorAdapter : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder> (){

    var collectors :List<Collector> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

        val currentItem = collectors[position]

        holder.viewDataBinding.root.setOnClickListener {
            val action =
                CollectorFragmentDirections.actionCollectorFragmentNavToDetailCollectorFragment(
                    collectors[position].id
                )
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }

}