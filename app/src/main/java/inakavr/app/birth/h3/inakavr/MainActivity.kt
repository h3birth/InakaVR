package inakavr.app.birth.h3.inakavr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.system.Os.shutdown
import inakavr.app.birth.h3.inakavr.adapter.PanoramaRecyclerAdapter
import inakavr.app.birth.h3.inakavr.viewpager.PanoramaSelectAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 下記のページアダプターを設定
        val pager : ViewPager = this.findViewById(R.id.vp_panorama_select)
        val PanoramaSelectPagerManager : android.support.v4.app.FragmentManager? = supportFragmentManager
        val panoramaSelectAdapter = PanoramaSelectAdapter(PanoramaSelectPagerManager)
        pager.adapter = panoramaSelectAdapter
//        pager.offscreenPageLimit = 3

//        button.setOnClickListener{
//         supportFragmentManager.beginTransaction()
//                .add(R.id.root_view, VRPanoramaFragment())
//                .commit()
//        }

    }

}
