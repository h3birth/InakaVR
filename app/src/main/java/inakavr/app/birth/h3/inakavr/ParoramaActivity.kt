package inakavr.app.birth.h3.inakavr

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.content.Intent



class ParoramaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parorama)

        val intent = intent
        val Panorama_Key = intent.getStringExtra("Panorama_Key")

        // データを渡す為のBundleを生成し、渡すデータを内包させる
        val bundle = Bundle()

        bundle.putString("PAMORAMA_KEY", Panorama_Key)
        //値を書き込む
//        bundle.putString("URL", "http://hogehoge.com")

// Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
        val fragment : Fragment = VRPanoramaFragment()
        fragment.setArguments(bundle)

// FragmentをFragmentManagerにセットする
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.panorama_container, fragment)
        transaction.commit()
    }
}
