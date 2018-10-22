package inakavr.app.birth.h3.inakavr.ui.base

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import inakavr.app.birth.h3.inakavr.R
import kotlinx.android.synthetic.main.fragment_item.view.*

open class BaseRecyclerViewAdapter(private val items: MutableList<Any>): RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: BaseRecyclerViewAdapter.ViewHolder, p1: Int) {
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView)
}