package com.example.series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.series.fragments.ActorsFragment
import com.example.series.fragments.InformationFragment
import com.example.series.fragments.SeasonsFragment
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(InformationFragment(), "Información")
        adapter.addFragment(ActorsFragment(), "Actores")
        adapter.addFragment(SeasonsFragment(), "Temporadas")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    class MyViewPagerAdapter (manager : FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment (fragment: Fragment, title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }
}
