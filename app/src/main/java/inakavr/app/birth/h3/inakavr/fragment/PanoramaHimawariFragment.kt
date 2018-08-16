package inakavr.app.birth.h3.inakavr.fragment


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inakavr.app.birth.h3.inakavr.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PanoramaHimawariFragment : Fragment() {

    lateinit var activity : Activity
    var panorama_Title: String = "ひまわり"
    var panorama_Image_Resouce: Int = R.drawable.himawari_top

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_panorama_himawari, container, false)
        return view
    }
}