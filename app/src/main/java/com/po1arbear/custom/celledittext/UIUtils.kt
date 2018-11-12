package com.po1arbear.custom.celledittext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.EditText

class UIUtils {

    companion object {

        //获取状态栏高度
        fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        //沉浸式状态栏
        fun translucentBar(activity: Activity, statusColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val localLayoutParams = activity.window.attributes
                //设置状态栏透明，activity全屏显示
                localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        // SYSTEM_UI_FLAG_LAYOUT_STABLE:防止系统栏隐藏时内容区域大小发生变化
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                setStatusBarColor(activity, statusColor)
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        fun setStatusBarColor(activity: Activity, statusColor: Int) {
            activity.window.statusBarColor = statusColor
        }

        //设置虚拟按键透明
        fun translucentNavigation(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val localLayoutParams = activity.window.attributes
                localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION or
                        localLayoutParams.flags
            }
        }

        //显示或隐藏状态栏
        fun isShowStatusBar(activity: Activity, show: Boolean) {
            //        显示和隐藏方法最好对应使用，这些方法都是不同版本时期使用的，优先级可能不同，不对号使用可能不会产生作用
            if (show) {
                //            如果是动态且频繁的操作状态栏，建议使用方法1，SYSTEM_UI_FLAG_LAYOUT_STABLE标签能防止内容区域大小发生变化引起画面晃动
                activity.window
                        .decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //            方法2
                //            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
                //            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //            activity.getWindow().setAttributes(attr);
                //如果不注释下面这句话，状态栏将把界面挤下去
                /*getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/
                //            方法3
                //下面方法也能实现显示状态栏效果，但是过度不是很自然
                //            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                //                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            } else {
                activity.window
                        .decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                //该属性可是实现沉浸式的状态栏，点击状态栏，状态栏出现后一段时间后会自动消失，常用于视频和游戏
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                //            方法2  该方法会使虚拟按键也变成透明
                //            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                //            //隐藏状态栏
                //            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                //            activity.getWindow().setAttributes(lp);
                //            activity.getWindow().addFlags(
                //                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                //方法3
                //下面方法也能实现显示状态栏效果，但是过度不是很自然
                //            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                //                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }

        fun limitChineseInET(editText: EditText) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {

                }
            })
        }

        @JvmOverloads
        fun dipsToPix(dps: Float, context: Context? = null): Int {
            val resources: Resources
            if (null == context) {
                resources = Resources.getSystem()
            } else {
                resources = context.resources
            }
            return Math.round(
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, resources.displayMetrics)
            )
        }

        fun dp2px(context: Context, dp: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        fun px2dp(context: Context, px: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (px / scale + 0.5f).toInt()
        }


        fun stringtoBitmap(string: String): Bitmap? {
            //将字符串转换成Bitmap类型
            var bitmap: Bitmap? = null
            try {
                val bitmapArray: ByteArray
                bitmapArray = Base64.decode(string, Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return bitmap
        }

        fun isInstalled(context: Context, packageName: String): Boolean {
            val packageManager = context.packageManager
            val pinfo = packageManager.getInstalledPackages(0)
            for (i in pinfo.indices) {
                if (pinfo[i].packageName
                                .equals(packageName, ignoreCase = true)
                )
                    return true
            }
            return false
        }

        fun startApp(context: Context, packageName: String) {
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)!!
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}

