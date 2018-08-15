package inakavr.app.birth.h3.inakavr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.system.Os.shutdown
import inakavr.app.birth.h3.inakavr.adapter.PanoramaRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // リサイクルビューの設定
        val panorama = resources.getStringArray(R.array.panorama).toMutableList()
        rv_parorama.adapter = PanoramaRecyclerAdapter(this, panorama)
        rv_parorama.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        button.setOnClickListener{
         supportFragmentManager.beginTransaction()
                .add(R.id.root_view, VRPanoramaFragment())
                .commit()
        }

    }

}
