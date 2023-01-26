package com.example.walap.ui.screen.detail

import android.app.WallpaperManager
import android.content.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.walap.R
import com.example.walap.base.BaseFragment
import com.example.walap.databinding.FragmentDetailBinding
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class DetailFragment :
    BaseFragment<FragmentDetailBinding>
        (FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mainImageView.load(args.photo.url) {
                listener(onStart = {
                    binding.progressBar.isVisible = true
                }, onSuccess = { _, _ ->
                    binding.progressBar.isVisible = false
                })
                placeholder(R.drawable.ic_random)
            }
            upBar.imageButtonBack.setOnClickListener { mainNavController.popBackStack() }

            var mImage: Bitmap?
            val myExecutor = Executors.newSingleThreadExecutor()
            val myHandler = Handler(Looper.getMainLooper())

            imgButtonDownload.setOnClickListener {

                myExecutor.execute {
                    mImage = mLoad(args.photo.urlDownload)
                    myHandler.post {
                        if (mImage != null) {
                            mSaveMediaToStorage(mImage)
                        }
                    }
                }
            }

            imgButtonSetWallpaper.setOnClickListener {
                myExecutor.execute {
                    mImage = mLoad(args.photo.urlDownload)
                    myHandler.post {
                        if (mImage != null) {
                            mSaveMediaToStorage(mImage)
                            mImage?.let { it1 -> setWallpaper(it1) }
                        }
                    }
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
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
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
            Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setWallpaper(bitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        wallpaperManager.setBitmap(bitmap)
        Toast.makeText(context ,"Wallpaper set!", Toast.LENGTH_SHORT).show()
    }
}