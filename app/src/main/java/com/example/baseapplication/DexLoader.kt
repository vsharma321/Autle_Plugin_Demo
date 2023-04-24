package com.example.baseapplication

import androidx.fragment.app.Fragment
import dalvik.system.DexClassLoader

class DexLoader(
    dexPath: String?,
    optimizedDirectory: String?,
    librarySearchPath: String?,
    parent: ClassLoader?
) : DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, parent) {
    override fun findClass(name: String?): Class<*> {
//        if ("androidx.fragment.app.Fragment" == name)
//            return Fragment::class.java
        return super.findClass(name)
    }
}