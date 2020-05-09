package herlich.gonzalez.servicetests;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate Thread Name: " + Thread.currentThread().getName());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand Thread Name: " + Thread.currentThread().getName());
        //return super.onStartCommand(intent, flags, startId);

        //AQUI HACEMOS LAS TAREAS QUE QUEREMOS HACER!!!
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new MyAsyncTask().execute();

        return START_STICKY;
    }

    //@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind Thread Name: " + Thread.currentThread().getName());
        return null;
    }


    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy Thread Name: " + Thread.currentThread().getName());
        super.onDestroy();
    }


    class MyAsyncTask extends AsyncTask<Void,String,Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG,"onPreExecute Thread Name: " + Thread.currentThread().getName());
            super.onPreExecute();
        }

        @Override //performs task in BAckground or worker thread
        protected Void doInBackground(Void... voids) {
            Log.i(TAG,"doInBackground Thread Name: " + Thread.currentThread().getName());
            int i = 1;
            while(i < 50)
            {
                //AQUI HACEMOS LAS TAREAS QUE QUEREMOS HACER!!!
                publishProgress("Time elapsed: " + String.valueOf(i));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.i(TAG,"onProgressUpdate Thread Name: " + Thread.currentThread().getName());
            Toast.makeText(MyBackgroundService.this,values[0],Toast.LENGTH_LONG);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i(TAG,"onPostExecute Thread Name: " + Thread.currentThread().getName());
            super.onPostExecute(aVoid);
            stopSelf();//esto detiene el servicio (destruye el servicio automaticamente cuando tod o acaba)
        }


    }

}
