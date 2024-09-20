package com.pi.newsc40.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pi.newsc40.R

abstract class BaseFragment<Binding>: Fragment() {
    var binding: Binding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, getLayoutId(),)
//        setContentView(binding!!.root)
    }


    //abstract fun getLayoutId(): Int
}