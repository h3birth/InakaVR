package inakavr.app.birth.h3.inakavr

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import android.util.Log
import inakavr.app.birth.h3.inakavr.fragment.PhotoFragment
import inakavr.app.birth.h3.inakavr.fragment.MovieFragment
import inakavr.app.birth.h3.inakavr.model.Panorama
import inakavr.app.birth.h3.inakavr.viewpager.PanoramaSelectAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.nifcloud.mbaas.core.NCMB

class MainActivity : AppCompatActivity(),
        PhotoFragment.OnListFragmentInteractionListener,
        MovieFragment.OnListFragmentInteractionListener{

    override fun onListFragmentInteraction(panorama: Panorama) {
        Log.d("OnCclick" , "item = "+panorama.key)
        if( panorama.type == 1 ){
            val intent = Intent(this, ParoramaActivity::class.java)
            intent.putExtra("Panorama_Key", panorama.key)
            startActivity(intent)
        }else if (panorama.type == 2){
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=" + panorama.key))
            startActivity(intent)
        }
    }

    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //NCMB.initialize(this.getApplicationContext(), getString(R.string.nifty_application_key),getString(R.string.nifty_client_key))

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

        MobileAds.initialize(this, getString(R.string.admob_appid))

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_license -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                return true
            }
            R.id.action_version -> {
                AlertDialog.Builder(this)
                        .setTitle(R.string.menu_version)
                        .setMessage(BuildConfig.VERSION_NAME)
                        .setPositiveButton("ok"){ dialog, which ->
                        }.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
