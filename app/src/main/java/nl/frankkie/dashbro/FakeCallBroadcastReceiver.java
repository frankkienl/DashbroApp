package nl.frankkie.dashbro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by fbouwens on 24-12-2015.
 */
public class FakeCallBroadcastReceiver extends BroadcastReceiver  {

    public static final String NUMBER_TO_INTERCEPT = "72462693274"; //RAINBOWDASH in T9

    @Override
    public void onReceive(Context context, Intent intent) {
        //http://android-developers.blogspot.fr/2013/05/handling-phone-call-requests-right-way.html
        // Extract phone number reformatted by previous receivers
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        if (phoneNumber.contains(NUMBER_TO_INTERCEPT)) {
            // My app will bring up the call, so cancel the broadcast
            setResultData(null);
            Toast.makeText(context, "Calling Rainbow Dash", Toast.LENGTH_LONG).show();

        }
        Log.v("FakeCall", "Break point here");
    }
}
