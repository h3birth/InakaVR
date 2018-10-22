package inakavr.app.birth.h3.inakavr.ui.photo

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import inakavr.app.birth.h3.inakavr.InakaVRApp
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.di.PhotoModule
import inakavr.app.birth.h3.inakavr.model.entity.Photo
import inakavr.app.birth.h3.inakavr.ui.panorama.ParoramaActivity
import inakavr.app.birth.h3.inakavr.ui.photo.PhotoRecyclerViewAdapter
import inakavr.app.birth.h3.inakavr.ui.photo.PhotoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_item_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PhotoFragment.OnListFragmentInteractionListener] interface.
 */
class PhotoFragment : Fragment() {

    @Inject
    lateinit var viewModel: PhotoViewModel

    private var adapter = PhotoRecyclerViewAdapter()

    private val disposable = CompositeDisposable()

    /**
     * 依存性処理
     */
    private fun inject() {
        (context?.applicationContext as InakaVRApp)
                .component
                .plus(PhotoModule())
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()

        adapter?.setOnItemClickListener(object : PhotoRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(view: View, items: Photo) {
                Log.d("Photo", "item:"+items.title)
                val intent = Intent(context, ParoramaActivity::class.java)
                intent.putExtra("PANORAMA_IMAGE", items.imageName)
                startActivity(intent)
            }
        })
        photo_list.adapter = adapter
        photo_list.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        photo_list.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
        disposable.addAll(
                viewModel.fetchItems()
                        .subscribe(),
                viewModel.observeItems()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    Log.d("Photo", "success! : ")
                                    adapter.setItems(it)
                                },
                                onError = {
                                    Log.d("Photo", "error : " + it.message)
                                }
                        )
        )
    }

    override fun onDetach() {
        super.onDetach()
        disposable.dispose()
    }

}
