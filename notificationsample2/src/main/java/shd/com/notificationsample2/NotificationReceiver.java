package shd.com.notificationsample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    /*接收notification送出的intent
    送出一個broadcast給BroadcastReceiver,讓他在背景做事而不會中斷目前程式的動作*/
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
