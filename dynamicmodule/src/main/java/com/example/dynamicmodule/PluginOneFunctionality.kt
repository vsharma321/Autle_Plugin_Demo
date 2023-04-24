package com.example.dynamicmodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class PluginOneFunctionality : Fragment() {
    abstract fun setView(inflater: LayoutInflater,
                         container: ViewGroup?):View
}