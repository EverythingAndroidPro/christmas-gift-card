package com.example.coursework;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "personal_notifications";
    public static final int NOTIFICATION_ID = 101;
    public static final String EXTRA_MESSAGE ="com.example.CourseWork.MESSAGE" ;

private Button button2;
Button informationaboutchristmas;
private Button button3;
private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        informationaboutchristmas = (Button)findViewById(R.id.informationaboutchristmas);
        informationaboutchristmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                 startActivity(intent);

            }
        });
        Button button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        Button button1 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startService(new Intent(MainActivity.this, MyService.class));
                                       }
                                   });
Button button2 = (Button) findViewById(R.id.button5);
            button2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }

        });
        {
        }
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Do you want to open musical greeting from friend?");
        alt_bld.setCancelable(false);
        alt_bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        alt_bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
             finish();
            }

        });
        AlertDialog alert = alt_bld.create();
        alert.setTitle("Title");
        alert.show();
    }

    public void displayNotification(View view) {

        createNotificationChannel();
        Intent landingIntent = new Intent(this, LandingActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIntent = PendingIntent.getActivity(this, 0, landingIntent, PendingIntent.FLAG_ONE_SHOT);
        // PendingIntent loadingPendingIntent = PendingIntent.getActivities(this, 0, landingIntent, PendingIntent.FLAG_ONE_SHOT);
        // PendingIntent YesIntent  = new Intent(this,YesActivity.class);

        //YesIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // PendingIntent YesPendinng = PendingIntent
        Intent YesIntent = new Intent(this, YesActivity.class);
        YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent YesPendingIntent = PendingIntent.getActivity(this, 0, YesIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent NoIntent = new Intent(this, NoActivity.class);
        NoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent NoPendingIntent = PendingIntent.getActivity(this, 0, NoIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Ivan");
        builder.setContentText("Merry Christmas!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(landingPendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Personal Notifications";
                String description = "include all personal notification";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

                notificationChannel.setDescription((description));
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(notificationChannel);
            }

    }
}
