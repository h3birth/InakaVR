package inakavr.app.birth.h3.inakavr.viewholder

import android.view.View
import android.widget.TextView
import inakavr.app.birth.h3.inakavr.R
import android.support.v7.widget.RecyclerView

class PanoramaRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
//    interface ItemClickListener {
//        fun onItemClick(view: View, position: Int)
//    }

    val itemTvTitle: TextView = view.findViewById(R.id.tv_title)

    init {
        // layoutの初期設定するときはココ
    }

}