package inakavr.app.birth.h3.inakavr.ui.photo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.model.entity.Photo
import kotlin.math.max

class PhotoRecyclerViewAdapter: RecyclerView.Adapter<PhotoRecyclerViewHolder>() {
    lateinit var listener: OnItemClickListener
    private val items: MutableList<Photo> = mutableListOf()

    fun setItems(items: List<Photo>) {
        val previousSize = itemCount
        this.items.clear()
        this.items.addAll(items)

        notifyItemRangeChanged(0, max(previousSize, itemCount))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PhotoRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
        // アイテムクリック処理
        holder.itemView.setOnClickListener {
            listener.onClick(it, items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoRecyclerViewHolder {
        return PhotoRecyclerViewHolder.create(parent)
    }

    /**
     * クリックリスナーのインターフェース
     */
    interface OnItemClickListener {
        fun onClick(view: View, items: Photo)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}