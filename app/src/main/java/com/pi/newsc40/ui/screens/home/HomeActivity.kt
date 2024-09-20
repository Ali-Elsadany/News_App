package com.pi.newsc40.ui.screens.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.pi.newsc40.R
import com.pi.newsc40.data.utils.InternetConnectionChecker
import com.pi.newsc40.databinding.ActivityHomeBinding
import com.pi.newsc40.ui.base.BaseActivity
import com.pi.newsc40.ui.screens.home.fragments.categories.CategoriesFragment


class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    val categoriesFragment = CategoriesFragment()

    //    {
//        showFragment(NewsFragment())
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(binding!!.root)
        showFragment(categoriesFragment)
        initDrawer()

    }

    private fun initDrawer() {
        binding!!.appBarLayout.icDrawer.setOnClickListener {
            if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding!!.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding!!.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        val toggle = ActionBarDrawerToggle(
            this,
            binding!!.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding!!.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding!!.drawerNavigationView.setNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.categoriesMenuItem) {
                showFragment(categoriesFragment)
                return@setNavigationItemSelectedListener true
            } else if (item.itemId == com.pi.newsc40.R.id.settingsMenuItem) {
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
                return@setNavigationItemSelectedListener false
            }
            return@setNavigationItemSelectedListener true
        }
    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//    }

    //show menu icon and back icon while drawer open
    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    //  override fun getLayoutId(): Int = R.layout.activity_home
}