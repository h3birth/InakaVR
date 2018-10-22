package inakavr.app.birth.h3.inakavr.ui.movie

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.BR
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.model.entity.Items
import inakavr.app.birth.h3.inakavr.model.entity.YoutubeDataAPI

class MovieRecyclerViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(items: Items) {
        binding.setVariable(BR.movie, items)
        binding.setVariable(BR.viewHolder, this)
    }

    companion object {
        fun create(parent: ViewGroup) = MovieRecyclerViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.fragment_item2, parent,
                        false
                )
        )
    }
}