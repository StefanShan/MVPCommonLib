package com.zebar.stefan_shan.commonlib.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 图片处理类
 */
object BitmapUtil {

    //压缩图片
    fun compressImage(bitmap: Bitmap, maxSize: Int): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        var quality = 100
        while (bos.toByteArray().size / 1024 > maxSize) {
            quality -= 10
            if (quality == 0) {
                break
            }

            bos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        }

        return bos.toByteArray()
    }

    //缩放图片
    fun zoomImage(bgimage: Bitmap, newWidth: Double, newHeight: Double): Bitmap {
        // 获取这个图片的宽和高
        val width = bgimage.width.toFloat()
        val height = bgimage.height.toFloat()
        // 创建操作图片用的matrix对象
        val matrix = Matrix()
        // 计算宽高缩放率
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bgimage, 0, 0, width.toInt(),
            height.toInt(), matrix, true)
    }

    //保存图片
    fun saveImage(context: Context?, name: String, bitmap: Bitmap): String? {
        val appDir = File(Environment.getExternalStorageDirectory().path)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = "$name.png"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            if (context != null) {
                //图片加到相册中
                MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, name, null)
                // 发送广播，通知刷新图库的显示
                context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$fileName")))
            }
            return file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}
