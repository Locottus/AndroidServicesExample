package herlich.gonzalez.servicetests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvResult = findViewById(R.id.txvResults);

    }

    public void startBackgroundService(View view){
        Intent intent = new Intent(this,MyBackgroundService.class);
        startService(intent);
    }

    public void stopBackgroundService(View view){
        Intent intent = new Intent(this, MyBackgroundService.class);
        stopService(intent);
    }

    public void startIntentService(View view){

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("tiempo",25);
        startService(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("my.own.broadcast");
        //registerReceiver(myLocalBroadcastReceiver,intentFilter);//esta forma escucha el broadcast a tod o el telefono.
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver,intentFilter);//esta forma recibe el resultado unicamente en el APP.

    }

    private BroadcastReceiver myLocalBroadcastReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            int resultado = intent.getIntExtra("result",0);
            txvResult.setText("Task executed in " + resultado + " seconds");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadcastReceiver);
    }

    public void startJobService(View view) {
        Intent i = new Intent(this, MyJobIntentService.class);
        i.putExtra("tiempo", 25);
        MyJobIntentService.enqueuedWork(this,i);

    }
}
