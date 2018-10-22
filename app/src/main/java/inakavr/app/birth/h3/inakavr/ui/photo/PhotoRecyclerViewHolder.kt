package inakavr.app.birth.h3.inakavr.ui.photo

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.BR
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.model.entity.Photo

class PhotoRecyclerViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(items: Photo) {
        binding.setVariable(BR.photo, items)
        binding.setVariable(BR.viewHolder, this)
    }

    companion object {
        fun create(parent: ViewGroup) = PhotoRecyclerViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.fragment_item, parent,
                        false
                )
        )
    }
}