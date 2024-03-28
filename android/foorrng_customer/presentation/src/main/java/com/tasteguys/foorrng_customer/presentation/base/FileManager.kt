package com.tasteguys.foorrng_customer.presentation.base

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.File

private const val TAG = "FileManager"
fun Uri.toFile(context: Context): File {
    val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
    val c: Cursor? = context.contentResolver.query(this, proj, null, null, null)
    val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    c?.moveToFirst()
    val result = c?.getString(index!!)
    return File(result!!)
}

class FileManager {
    companion object{

    }
}