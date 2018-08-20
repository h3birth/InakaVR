package inakavr.app.birth.h3.inakavr

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import h3.birth.app.kusegemater.builder.httpBuilder
import h3.birth.app.kusegemater.service.YoutubeDataApiService
import inakavr.app.birth.h3.inakavr.R.layout.fragment_item2

import inakavr.app.birth.h3.inakavr.model.Panorama
import inakavr.app.birth.h3.inakavr.model.YoutubeDataAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_item_list2.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment2.OnListFragmentInteractionListener] interface.
 */
class ItemFragment2 : Fragment() {

    // TODO: Customize parameters
    lateinit var retrofit: Retrofit
    lateinit var activity : Activity

    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list2, container, false)

        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = MyItemRecyclerViewAdapter2(resources.getStringArray(R.array.panorama_movie_list).toMutableList(), listener)
//            }
//        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as FragmentActivity

        apicall()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Panorama)
    }

    fun apicall() {
        try {
            val gson = GsonBuilder()
                    .serializeNulls()
                    .create()

            // Observer
            // create retrofit
            retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(getString(R.string.youtube_data_api_url)) // Put your base URL
                    .client(httpBuilder.build())
                    .build()

            retrofit.create(YoutubeDataApiService::class.java)
                    .youtubeMovie(getString(R.string.youtube_data_api_key), getString(R.string.youtube_data_api_part),getString(R.string.youtube_data_api_channel_id))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({apiresponse ->
                                Log.d("retrofit", "succcess")
                                // Set the adapter
                                movie_list.adapter = MyItemRecyclerViewAdapter2(apiresponse.items.toMutableList(), listener)
                                movie_list.layoutManager = LinearLayoutManager(activity)
                            },
                            {throwable -> throwable.printStackTrace() })

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
