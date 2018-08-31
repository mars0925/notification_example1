package shd.com.notificationsamlpe5;

import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static shd.com.notificationsamlpe5.App.CHANNEL_2_ID;

/**再通知裡建立program bar*/
public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
    }

    public void sendOnChannel2(View v) {
        final int progressMax = 100;

        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)//使用者無法用clear all來移除通知
                .setOnlyAlertOnce(true)//設定提醒只執行一次
                /*setProgress(max, progress, false). The first parameter is what the "complete" value is (such as 100);
                the second is how much is currently complete,
                and the last indicates this is a determinate progress bar.
                最後一個參數設false表示bar會找進度跑,設true表示bar會一直跑,直到結束*/
                .setProgress(progressMax, 0, false);//設定progress bar

        notificationManager.notify(2, notification.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for (int progress = 0; progress <= progressMax; progress += 20) {
                    /*持續跟新進度的地方,如果不想依照進度顯示的話,可以不寫*/
                    notification.setProgress(progressMax, progress, false);
                    notificationManager.notify(2, notification.build());
                    SystemClock.sleep(1000);
                }
                notification.setContentText("Download finished")
                        .setProgress(0, 0, false)//進度完成後將進度條移除,不寫會留下進度條
                        .setOngoing(false);//進度條完成後就可以用clear all來移除通知
                notificationManager.notify(2, notification.build());
            }
        }).start();
    }
}
