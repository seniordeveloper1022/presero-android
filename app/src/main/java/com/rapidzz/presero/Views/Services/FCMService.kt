//package com.acclivousbyte.gobblecustomer.view.Services
//
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//import com.rapidzz.presero.Models.Source.Repository.DataRepository
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//
//class FCMService : FirebaseMessagingService() {
//
//    var TAG: String = "FCMToken"
//    var message: String = ""
//    var title: String = ""
//    var requestID = System.currentTimeMillis().toInt()
//   // var eventItem=EventItem()
//
//
//
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//
//        requestID = System.currentTimeMillis().toInt()
//        remoteMessage?.let {
//            remoteMessage.notification?.let {
//                title=remoteMessage.notification!!.title!!
//                message=remoteMessage.notification!!.body!!
//            }
//            if(remoteMessage.data!=null)
//            {
//                TAG=remoteMessage.data["type"]!!
//             /*   eventItem= EventItem(TAG!!)
//                handleNotificationData()*/
//
//            }
//
//        }
//
//    }
//
//
///*
//    fun handleNotificationData() {
//
//        if ((applicationContext as App).getCurrentActivity() != null) {
//
//            when (TAG) {
//                NEW_ORDER_TYPE -> {
//                        if (((applicationContext as App).getCurrentActivity() as BaseActivity).getCurrentFragment() is HomeFragment) {
//                            EventBus.getDefault().post(EventItem(TAG))
//                        } else {
//                            sendNotification()
//                        }
//                }
//
//                ORDER_CANCEL->
//                {
//                    if (((applicationContext as App).getCurrentActivity() as BaseActivity).getCurrentFragment() is BidsHistoryFragment) {
//                        EventBus.getDefault().post(EventItem(TAG))
//                    } else {
//                        sendNotification()
//                    }
//                }
//
//                ORDER_APPROVE->
//                {
//                    if (((applicationContext as App).getCurrentActivity() as BaseActivity).getCurrentFragment() is BidsHistoryFragment) {
//                       AppInstance.getInstance().accepted=true
//                        EventBus.getDefault().post(EventItem(TAG))
//                    } else {
//                        sendNotification()
//                    }
//                }
//                else ->
//                {
//                    sendNotification()
//                }
//
//            }
//        }
//
//    }
//
//
//
//    fun sendNotification()
//    {
//
//        val notificationHelper = NotificationHelper(this)
//        var notificationIntent = Intent(applicationContext, SplashActivity::class.java)
//        notificationIntent.putExtra("data",eventItem)
//        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        var contentIntent= PendingIntent.getActivity(this, requestID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        val builder = notificationHelper.createNotificationBuilder(
//            title ,
//            message, true, contentIntent
//        )
//        notificationHelper.makeNotification(builder, requestID)
//
//    }*/
//
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        GlobalScope.launch {
//            DataRepository(this@FCMService).updateFCMToken(token)
//        }
//    }
//
//
//
//}
