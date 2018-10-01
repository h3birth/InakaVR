package inakavr.app.birth.h3.inakavr.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.content.res.TypedArray


import inakavr.app.birth.h3.inakavr.fragment.PhotoFragment.OnListFragmentInteractionListener
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.model.Panorama

import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PhotoItemRecyclerViewAdapter(
        private val mValues: List<String>,
        private val mImages: TypedArray,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<PhotoItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val listenerType : Int = 1

    init {
        mOnClickListener = View.OnClickListener{  v ->
            val item = v.tag as String
            val panorama = Panorama(listenerType,item)
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(panorama)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item
        Glide.with(holder.mView).load(mImages.getResourceId(position, 0)).into(holder.ivPanoramaTop)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content_movie
        val ivPanoramaTop: ImageView = mView.iv_panorama_movie_top

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}

