package com.zebar.stefan_shan.commonlib.util

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 加密工具类
 */
object EncryptionUtil {
    //MD5加密
    fun getMD5Str(input: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            val bDigests = md.digest(input.toByteArray(charset("UTF-8")))
            return byte2hex(bDigests)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return null
    }

    //SHA256加密
    fun getSHA256Str(input: String): String? {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            val bDigests = md.digest(input.toByteArray(charset("UTF-8")))
            return byte2hex(bDigests)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return null
    }

    private fun byte2hex(b: ByteArray): String {
        val hs = StringBuilder()
        var tmp: String
        for (aB in b) {
            tmp = Integer.toHexString(aB.toInt() and 0XFF)
            if (tmp.length == 1)
                hs.append("0").append(tmp)
            else
                hs.append(tmp)
        }

        return hs.toString().toLowerCase()
    }
}