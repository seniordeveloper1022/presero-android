package com.rapidzz.presero.Views.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.loadImage
import de.hdodenhof.circleimageview.CircleImageView



class OrderRatingDialog(var userName:String,var userImage:String,var onDataFechCallback:OnFetchRatingCallback) :  DialogFragment() {


    var ratingBar:RatingBar ?= null
    var ivUserImage:CircleImageView ?= null
    var tvUserName:TextView ?= null
    var btnSubmit:TextView ?= null
    var ivClose:ImageView ?= null



    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.GeneralDialogStyle);
        val builder = android.app.AlertDialog.Builder(activity!!, R.style.GeneralDialogStyle)
        val inflater = activity!!.layoutInflater

        val dialog = inflater.inflate(R.layout.rating_dialog_layout, null)
        ratingBar = dialog.findViewById<View>(R.id.rbRating) as RatingBar
        ivUserImage=dialog.findViewById<View>(R.id.ivUserImage) as CircleImageView
        tvUserName=dialog.findViewById<View>(R.id.tvUserName) as TextView
        btnSubmit=dialog.findViewById<View>(R.id.btnSubmit) as TextView
        ivClose=dialog.findViewById<View>(R.id.ivClose) as ImageView
        tvUserName?.text=userName
        ivUserImage?.loadImage(userImage)


        btnSubmit?.setOnClickListener {
            if(ratingBar?.rating!=null && ratingBar?.rating==0f )
            {
               Toast.makeText(context,"Please select rating",Toast.LENGTH_SHORT).show()
            }
            else
            {
               onDataFechCallback.onFetchRating(ratingBar?.rating!!.toInt())
                this.dismiss()
            }
        }

        ivClose?.setOnClickListener {
            this.dismiss()
        }
        builder.setView(dialog)
        builder.setCancelable(false)
        return builder.create()
    }



    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = 0.0f
        window?.attributes = windowParams
    }


    interface OnFetchRatingCallback {
        fun onFetchRating(data: Int)


    }


}

