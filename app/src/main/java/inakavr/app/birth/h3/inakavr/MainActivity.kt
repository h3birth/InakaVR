package inakavr.app.birth.h3.inakavr

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.system.Os.shutdown
import android.util.Log
import android.view.Window
import android.view.WindowManager
import inakavr.app.birth.h3.inakavr.adapter.PanoramaRecyclerAdapter
import inakavr.app.birth.h3.inakavr.dummy.DummyContent
import inakavr.app.birth.h3.inakavr.model.Panorama
import inakavr.app.birth.h3.inakavr.viewpager.PanoramaSelectAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
        ItemFragment.OnListFragmentInteractionListener,
        ItemFragment2.OnListFragmentInteractionListener{

    override fun onListFragmentInteraction(panorama: Panorama) {
        Log.d("OnCclick" , "item = "+panorama.key)
        if( panorama.type == 1 ){
            val intent = Intent(this, ParoramaActivity::class.java)
            intent.putExtra("Panorama_Key", panorama.key)
            startActivity(intent)
        }else if (panorama.type == 2){
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCcPQ1-EFJPeheyFL_tNWLtg?view_as=subscriber"))
            startActivity(intent)
        }
    }

    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        // 下記のページアダプターを設定
        val pager : ViewPager = this.findViewById(R.id.vp_panorama_select)
        val PanoramaSelectPagerManager : android.support.v4.app.FragmentManager? = supportFragmentManager
        val panoramaSelectAdapter = PanoramaSelectAdapter(PanoramaSelectPagerManager)
        pager.adapter = panoramaSelectAdapter

        val tabLayout : TabLayout = this.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }

}
