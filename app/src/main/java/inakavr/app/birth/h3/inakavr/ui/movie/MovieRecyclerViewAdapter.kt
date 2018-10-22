package inakavr.app.birth.h3.inakavr.ui.movie

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import inakavr.app.birth.h3.inakavr.model.entity.Items
import inakavr.app.birth.h3.inakavr.model.entity.YoutubeDataAPI
import inakavr.app.birth.h3.inakavr.ui.base.BaseRecyclerViewAdapter
import kotlin.math.max

class MovieRecyclerViewAdapter: RecyclerView.Adapter<MovieRecyclerViewHolder>() {
    lateinit var listener: OnItemClickListener
    private val items: MutableList<Items> = mutableListOf()

    fun setItems(items: List<Items>) {
        val previousSize = itemCount
        this.items.clear()
        this.items.addAll(items)

        notifyItemRangeChanged(0, max(previousSize, itemCount))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
        // アイテムクリック処理
        holder.itemView.setOnClickListener {
            listener.onClick(it, items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerViewHolder {
        return MovieRecyclerViewHolder.create(parent)
    }

    /**
     * クリックリスナーのインターフェース
     */
    interface OnItemClickListener {
        fun onClick(view: View, items: Items)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}