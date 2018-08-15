package inakavr.app.birth.h3.inakavr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.viewholder.PanoramaRecyclerViewHolder
import android.support.v7.widget.RecyclerView

class PanoramaRecyclerAdapter(private val context: Context, private val itemList:List<String>) : RecyclerView.Adapter<PanoramaRecyclerViewHolder>() {

    private var mRecyclerView : RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null

    }

    override fun onBindViewHolder(holder: PanoramaRecyclerViewHolder, position: Int) {
        holder?.let {
            it.itemTvTitle.text = itemList.get(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanoramaRecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.item_panorama, parent, false)


        return PanoramaRecyclerViewHolder(mView)
    }

}