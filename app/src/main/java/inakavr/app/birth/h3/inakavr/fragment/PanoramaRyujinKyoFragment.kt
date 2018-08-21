package inakavr.app.birth.h3.inakavr.fragment


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.R
import kotlinx.android.synthetic.main.fragment_panorama_himawari.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PanoramaRyujinKyoFragment : Fragment() {

    lateinit var activity : Activity
    var panorama_Title: String = "竜神狭"
    var panorama_Image_Resouce: Int = R.drawable.ryujinkyo_top

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_panorama_ryujinkyo, container, false)
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as FragmentActivity

        btn_view_start.setOnClickListener{
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            val fragment = VRPanoramaFragment()
            val bundle = Bundle()
            bundle.putInt("PAMORAMA_KEY", 1)
            //値を書き込む
            fragment.setArguments(bundle)
            fragmentTransaction.addToBackStack("")
            fragmentTransaction.add(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }

}