package inakavr.app.birth.h3.inakavr.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import inakavr.app.birth.h3.inakavr.fragment.PanoramaHimawariFragment
import inakavr.app.birth.h3.inakavr.fragment.PanoramaOoaraiFragment
import inakavr.app.birth.h3.inakavr.fragment.PanoramaRyujinKyoFragment
import inakavr.app.birth.h3.inakavr.fragment.PanoramaSuidenFragment

// ページ数やページの情報を設定するFragmentPagerAdapter
class PanoramaSelectAdapter(fm: android.support.v4.app.FragmentManager?) : FragmentPagerAdapter(fm) {


    // 各ページの情報
    override fun getItem(position: Int): Fragment {

        val fragment =
        when(position){
            0 -> PanoramaHimawariFragment()
            1 -> PanoramaRyujinKyoFragment()
            2 -> PanoramaSuidenFragment()
            3 -> PanoramaOoaraiFragment()
            else -> PanoramaHimawariFragment()
        }

        // 各ページに渡すテキスト情報を設定
        val bundle : Bundle = Bundle()
        bundle.putInt("panorama_index", position)
        fragment.arguments = bundle

        return fragment
    }


    // ページ数
    override fun getCount(): Int {
        return 4
    }
}