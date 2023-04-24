package com.example.baseapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File


object ApkInstaller {
    fun installApk(context: Context, apkFile: File?) {
        apkFile?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            val apkUriOldWay: Uri = Uri.fromFile(apkFile)
            val apkUri: Uri = FileProvider.getUriForFile(context,context.packageName+".fileprovider",apkFile)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.startActivity(intent)
        }
    }
}