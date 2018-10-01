package inakavr.app.birth.h3.inakavr.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import inakavr.app.birth.h3.inakavr.fragment.PhotoFragment
import inakavr.app.birth.h3.inakavr.fragment.MovieFragment

// ページ数やページの情報を設定するFragmentPagerAdapter
class PanoramaSelectAdapter(fm: android.support.v4.app.FragmentManager?) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf<CharSequence>("静止画", "動画")

    // 各ページの情報
    override fun getItem(position: Int): Fragment {

        val fragment =
        when(position){
            0 -> PhotoFragment()
            else -> MovieFragment()
        }

        // 各ページに渡すテキスト情報を設定
        val bundle : Bundle = Bundle()
        bundle.putInt("panorama_index", position)
        fragment.arguments = bundle

        return fragment
    }

    override fun getPageTitle(position: Int) : CharSequence {
        //positionごとのタブ名をreturn
        return tabTitles[position]
    }

    // ページ数
    override fun getCount(): Int {
        return 2
    }
}