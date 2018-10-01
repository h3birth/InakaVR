package inakavr.app.birth.h3.inakavr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inakavr.app.birth.h3.inakavr.fragment.VRPanoramaFragment


class ParoramaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parorama)

        val intent = intent
        val Panorama_Key = intent.getStringExtra("Panorama_Key")

        // データを渡す為のBundleを生成し、渡すデータを内包させる
        val bundle = Bundle()

        bundle.putString("PAMORAMA_KEY", Panorama_Key)


        // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
        val fragment : Fragment = VRPanoramaFragment()
        fragment.setArguments(bundle)

        // FragmentをFragmentManagerにセットする
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.panorama_container, fragment)
        transaction.commit()
    }
}
