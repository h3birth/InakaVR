package inakavr.app.birth.h3.inakavr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import inakavr.app.birth.h3.inakavr.ui.PanoramaSelectAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.app.AlertDialog
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.nifcloud.mbaas.core.NCMB
import inakavr.app.birth.h3.inakavr.ui.ImageLoaderTask

class MainActivity : AppCompatActivity(){

    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null
    lateinit var mAdView : AdView
    var info: ApplicationInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)

        NCMB.initialize(this.getApplicationContext(), getEnvKey("nifty_appkey"), getEnvKey("nifty_clientkey"))

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

        MobileAds.initialize(this,  getEnvKey("admob_appid"))

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

    fun getEnvKey(key: String): String {
        return info!!.metaData.getString(key)
    }
}
