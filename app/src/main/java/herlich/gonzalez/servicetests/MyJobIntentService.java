package herlich.gonzalez.servicetests;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyJobIntentService extends JobIntentService {
    private static final String TAG = MyJobIntentService.class.getSimpleName();


    public static void enqueuedWork(Context context, Intent intent){
        enqueueWork(context,MyJobIntentService.class,91,intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate myJobIntentService Thread Name: " + Thread.currentThread().getName());
        Toast.makeText(this,"task Execution starts ",Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onHandleWork( Intent intent) {
        //aqui escribimos nuestro codigo
        Log.i(TAG, "OnHandleWork Thread Name: " + Thread.currentThread().getName());
        int duracion = intent.getIntExtra("tiempo", 0);
        int i = 1;
        while (i < duracion) {
            //AQUI HACEMOS LAS TAREAS QUE QUEREMOS HACER!!!
            Log.i(TAG, "Time elapsed: " + String.valueOf(i));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy myJobIntentService Thread Name: " + Thread.currentThread().getName());
        Toast.makeText(this,"task Execution ends ",Toast.LENGTH_LONG).show();

    }

}
