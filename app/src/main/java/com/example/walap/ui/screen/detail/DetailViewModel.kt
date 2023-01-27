package com.example.walap.ui.screen.detail

import android.app.Application
import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.walap.utils.DetailState
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private val context = application

    private var _downloadState: MutableLiveData<DetailState<String>> = MutableLiveData()
    val downloadState get() = _downloadState

    fun downloadWallpapers(url: String) {

        downloadState.postValue(DetailState.Loading())
        var mImage: Bitmap?
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())

        try {
            myExecutor.execute {
                mImage = mLoad(url)
                myHandler.post {
                    if (mImage != null) {
                        mSaveMediaToStorage(mImage)
                        downloadState.postValue(DetailState.Success("Download success"))
                    }
                }
            }
        } catch (e: Exception) {
            downloadState.postValue(DetailState.Error("Download error ${e.message}"))
        }
    }

    fun setWallpapers(url: String) {

        downloadState.postValue(DetailState.Loading())
        var mImage: Bitmap?
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())
        myExecutor.execute {
            mImage = mLoad(url)
            myHandler.post {
                if (mImage != null) {
                    mSaveMediaToStorage(mImage)
                    mImage?.let { it1 -> setWallpaper(it1) }
                }
            }
        }
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }


    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
//            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            downloadState.postValue(DetailState.Error("Download error ${e.message}"))
        }
        return null
    }

    private fun mSaveMediaToStorage(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context?.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
//            Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setWallpaper(bitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        wallpaperManager.setBitmap(bitmap)
//        Toast.makeText(context, "Wallpaper set!", Toast.LENGTH_SHORT).show()
    }
}