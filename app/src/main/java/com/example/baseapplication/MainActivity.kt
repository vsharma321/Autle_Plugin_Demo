package com.example.baseapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
//            requestPermissionsToRead()
            loadFromPrivateStorage()
        }

    }

    private fun loadFromPrivateStorage() {
        val filepath: String = filesDir.absolutePath + "/dynamicmodule-debug.apk"
        val fragmentClassName = "com.example.dynamicmodule.TestLoadFragment"
        val methodToInvoke = "add"

        val optimizedDexOutputPath: File = getDir("dex", Context.MODE_PRIVATE)

        val dLoader = DexLoader(
            filepath, optimizedDexOutputPath.absolutePath,
            null, ClassLoader.getSystemClassLoader().parent)

        try {
            val loadedClass = dLoader.loadClass(fragmentClassName)
            val obj = loadedClass.newInstance() as Any
            try {
                val viewClass = obj as Fragment
                viewClass.onCreateView(layoutInflater,findViewById(android.R.id.content),null)
                if (!viewClass.isAdded && !viewClass.isDetached) {
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.fragmentContainerView3, viewClass)
                    fragmentTransaction.commit()

                }
            } catch (e: java.lang.ClassCastException) {
                e.printStackTrace()
                Log.e(TAG, "loadFromPrivateStorage: ${e.message}")
            }
            val x = 5
            val y = 6
            val m: Method = loadedClass.getMethod(
                methodToInvoke,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            val z = m.invoke(obj, y, x) as Int
            println("The sum of $x and y=$z")
        } catch (e: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: InstantiationException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: java.lang.IllegalArgumentException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    private fun requestPermissionsToRead() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val apkFile =
                Environment.getExternalStorageDirectory().absolutePath + "/DroneAlbum/dynamicmodule-debug.apk"
            val className =
                "com.example.dynamicmodule.DynamicMainActivity"
            val methodToInvoke = "add"

            val optimizedDexOutputPath: File = getDir("dex", Context.MODE_PRIVATE)

            val dLoader = DexClassLoader(
                apkFile, optimizedDexOutputPath.absolutePath,
                null, ClassLoader.getSystemClassLoader().parent
            )

            try {
                dLoader.javaClass
                val loadedClass = dLoader.loadClass(className)
                val obj = loadedClass.newInstance() as Any
                val x = 5
                val y = 6
                val m: Method = loadedClass.getMethod(
                    methodToInvoke,
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                val z = m.invoke(obj, y, x) as Int
                Toast.makeText(applicationContext, "$z", Toast.LENGTH_SHORT).show()
                println("The sum of $x and y=$z")
            } catch (e: ClassNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: InstantiationException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: java.lang.IllegalArgumentException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                111
            )
        }
    }
}