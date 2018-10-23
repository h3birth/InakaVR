package inakavr.app.birth.h3.inakavr.ui.movie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import inakavr.app.birth.h3.inakavr.InakaVRApp
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.di.MovieModule
import inakavr.app.birth.h3.inakavr.model.entity.Items
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_item_list2.*
import javax.inject.Inject
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.support.customtabs.CustomTabsClient.getPackageName


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MovieFragment.OnListFragmentInteractionListener] interface.
 */
class MovieFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieViewModel

    private var adapter = MovieRecyclerViewAdapter()

    private val disposable = CompositeDisposable()

    var activity: Activity? = null

    /**
     * 依存性処理
     */
    private fun inject() {
        (context?.applicationContext as InakaVRApp)
                .component
                .plus(MovieModule())
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_list2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()
        // リサイクルビューアダプター設定
        adapter?.setOnItemClickListener(object : MovieRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(view: View, items: Items) {
                val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=" + items.id.videoId))
                startActivity(intent)
            }
        })
        movie_list.adapter = adapter
        movie_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        activity = getActivity() as FragmentActivity

        val info = activity!!.packageManager.getApplicationInfo(activity!!.packageName, PackageManager.GET_META_DATA)
        viewModel.youtubeKey = info.metaData.getString("youtube_apikey")
        if( isNetworkOn() ){
            apicall()
        }else{
            movie_list.visibility = View.GONE
            layout_networkerror.visibility = View.VISIBLE
            button_reload.setOnClickListener { apicall() }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.dispose()
    }

    fun apicall(){
        disposable.addAll(
                viewModel.fetchItems()
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(),
                viewModel.observeItems()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    adapter.setItems(it.items)
                                },
                                onError = {
                                    Log.d("Movie", "error : " + it.message)
                                }
                        )
        )
    }

    fun isNetworkOn(): Boolean {
        val connMgr = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return (networkInfo != null && networkInfo.isConnected())
    }

}
