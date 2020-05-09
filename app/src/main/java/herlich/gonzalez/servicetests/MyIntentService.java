package herlich.gonzalez.servicetests;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {

    private static final String TAG = MyBackgroundService.class.getSimpleName();


    //ESTO ES IMPORTANTE PQ AQUI SE LE DA EL NOMBRE A NUESTRO BACKGROUND THREAD
    public MyIntentService() {
        super("myIntentServiceLocottus");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"task Execution Starts ",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onCreate Thread Name: " + Thread.currentThread().getName());

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //aqui escribimos nuestro codigo
        Log.i(TAG,"OnHandleIntent Thread Name: " + Thread.currentThread().getName());
        int duracion = intent.getIntExtra("tiempo",0);
        int i = 1;
        while(i < duracion)
        {
            //AQUI HACEMOS LAS TAREAS QUE QUEREMOS HACER!!!
            Log.i(TAG,"Time elapsed: " + String.valueOf(i));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
        }

        //esto envia el resultado del servicio de vuelta
        Intent localIntent = new Intent("my.own.broadcast");
        localIntent.putExtra("result",i);

        //sendBroadcast(localIntent);//esta forma envia el broadcast a tod o el telefono.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);//esta forma envia el resultado unicamente en el APP.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy Thread Name: " + Thread.currentThread().getName());
        Toast.makeText(this,"task Execution ends ",Toast.LENGTH_LONG).show();
    }





}
