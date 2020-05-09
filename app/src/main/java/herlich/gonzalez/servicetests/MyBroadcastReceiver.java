package herlich.gonzalez.servicetests;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //here we write our code
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            //ESTO ES PARA EL EJEMPLO DE BROADCAST RECEIVER SIN QUE SEA JOB
//            Intent i = new Intent(context, MyIntentService.class);
//            i.putExtra("tiempo", 25);
//            context.startService(i);

            //ESTO ES PARA EL EJEMPLO DE BROADCAST RECEIVER para un JOB
            Intent i = new Intent(context, MyJobIntentService.class);
            i.putExtra("tiempo", 25);
            MyJobIntentService.enqueuedWork(context,i);

        }

    }

}
