package com.rapidzz.presero.Utils.GeneralUtils

import android.graphics.drawable.Drawable

class Singleton {


    companion object{

        var imageDrawable : Drawable? = null

        fun setDrawable(image: Drawable) {
            imageDrawable = image
        }
        fun getDrawable() : Drawable?{
            return imageDrawable
        }
    }
}