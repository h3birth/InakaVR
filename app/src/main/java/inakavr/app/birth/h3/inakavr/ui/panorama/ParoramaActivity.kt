package inakavr.app.birth.h3.inakavr.ui.panorama

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.ui.panorama.VRPanoramaFragment


class ParoramaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parorama)

        val intent = intent
        val panoramaImage = intent.getStringExtra("PANORAMA_IMAGE")

        // データを渡す為のBundleを生成し、渡すデータを内包させる
        val bundle = Bundle()

        bundle.putString("PANORAMA_IMAGE", panoramaImage)


        // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
        val fragment : Fragment = VRPanoramaFragment()
        fragment.setArguments(bundle)

        // FragmentをFragmentManagerにセットする
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.panorama_container, fragment)
        transaction.commit()
    }
}
