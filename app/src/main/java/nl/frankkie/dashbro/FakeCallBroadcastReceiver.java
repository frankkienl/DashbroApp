package nl.frankkie.dashbro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by fbouwens on 24-12-2015.
 */
public class FakeCallBroadcastReceiver extends BroadcastReceiver  {

    public static final String[] numbers = new String[]{
            "277535225", //Applejack
            "3588837749", //Fluttershy
            "746543743", //Pinkie Pie
            "72462693274", //Rainbow Dash
            "727489", //Rarity
            "89454448", //Twilight
            "894544487727553" //Twilight Sparkle
    };


    @Override
    public void onReceive(Context context, Intent intent) {
        //http://android-developers.blogspot.fr/2013/05/handling-phone-call-requests-right-way.html
        // Extract phone number reformatted by previous receivers
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        //Check if its one of our numbers, then intercept.
        for (String n : numbers) {
            if (phoneNumber.equals(n)) {
                // My app will bring up the call, so cancel the broadcast
                setResultData(null);
                Intent i = new Intent(context, FakeCallActivity.class);
                i.putExtra("number",phoneNumber);
                context.startActivity(i);
            }
        }
    }
}
