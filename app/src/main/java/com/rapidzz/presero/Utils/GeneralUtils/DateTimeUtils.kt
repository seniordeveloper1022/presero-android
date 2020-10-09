package com.rapidzz.presero.Utils.GeneralUtils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
object DateTimeUtils {
    fun changeDateTimeFormat(date: String): String {
//        val oldFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
        val oldFormat = "yyyy-MM-dd"
        val newFormat = "MM/dd/yyyy"
        val dateFormat = SimpleDateFormat(oldFormat)
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeFormat = SimpleDateFormat(newFormat)
//        Log.e("timeformat",timeFormat.format(myDate))
        return timeFormat.format(myDate)
    }
    fun returnMonth(date: String): String {
        val oldFormat = "dd MMM yyyy"
        val newFormat = "MMMM"
        val dateFormat = SimpleDateFormat(oldFormat)
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeFormat = SimpleDateFormat(newFormat)
        return timeFormat.format(myDate)
    }
    fun returnDateOfMonth(date: String): String {
        val oldFormat = "dd MMM yyyy"
        val newFormat = "dd"
        val dateFormat = SimpleDateFormat(oldFormat)
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeFormat = SimpleDateFormat(newFormat)
        return timeFormat.format(myDate)
    }
    fun returnDateAndMonth(date: String): String {
        val oldFormat = "dd MMM yyyy"
        val newFormat = "MMMM dd"
        val dateFormat = SimpleDateFormat(oldFormat)
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeFormat = SimpleDateFormat(newFormat)
        return timeFormat.format(myDate)
    }
    fun returnDateMonth(date: String): String {
        val oldFormat = "yyyy-MM-dd"
        val newFormat = "MMM dd, yyyy"
        val dateFormat = SimpleDateFormat(oldFormat)
        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeFormat = SimpleDateFormat(newFormat)
        return timeFormat.format(myDate)
    }
    fun calculateDuration(startTime:String,endTime:String):Int{
        val simpleDateFormat = SimpleDateFormat("hh:mm a")
        val date1 = simpleDateFormat.parse(startTime);
        val date2 = simpleDateFormat.parse(endTime);
        val difference: Long = date2.getTime() - date1.getTime()
        val days = (difference / (1000 * 60 * 60 * 24)).toInt()
        var hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
//        val min = (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours) as Int / (1000 * 60)
        hours = if (hours < 0) -hours else hours
        Log.i("======= Hours", " :: $hours")
        return hours
    }
}