package inakavr.app.birth.h3.inakavr.ui.panorama


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import inakavr.app.birth.h3.inakavr.ui.ImageLoaderTask
import inakavr.app.birth.h3.inakavr.R


/**
 * A simple [Fragment] subclass.
 *
 */
class VRPanoramaFragment : Fragment() {

    public var TAG = "VRPANORAMA"
    private lateinit var panoWidgetView: VrPanoramaView
    private var backgroundImageLoaderTask: ImageLoaderTask? = null
    lateinit var activity : Activity
    var paranomaImage : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        paranomaImage = arguments!!.getString("PANORAMA_IMAGE")
        Log.i("MyFragment : ", "渡された値 = " + paranomaImage)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vrpanorama, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as FragmentActivity

        panoWidgetView = activity.findViewById(R.id.vr_view)

        loadPanoImage()
    }

    override fun onPause() {
        panoWidgetView.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        panoWidgetView.resumeRendering()
        super.onResume()
    }

    override fun onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown()
        super.onDestroy()
    }

    @Synchronized
    private fun loadPanoImage() {
        var task = backgroundImageLoaderTask
        if (task != null && !task.isCancelled) {
            // Cancel any task from a previous loading.
            task.cancel(true)
        }

        // pass in the name of the image to load from assets.
        val viewOptions = VrPanoramaView.Options()
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO

        // create the task passing the widget view and call execute to start.
        task = ImageLoaderTask(this.panoWidgetView, viewOptions, paranomaImage)
        task.execute(activity.assets)
        backgroundImageLoaderTask = task
    }
}
