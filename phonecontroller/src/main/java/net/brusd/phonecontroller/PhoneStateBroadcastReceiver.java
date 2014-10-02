package net.brusd.phonecontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by BruSD on 5/31/2014.
 */
public class PhoneStateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo networkInfo = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (networkInfo.isConnected()) {

//                AudioManager am =  (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//
//                am.setStreamVolume(AudioManager.STREAM_ALARM, 0,  AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
//                am.setStreamVolume(AudioManager.STREAM_RING , 0,  AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
//                am.setStreamVolume(AudioManager.STREAM_MUSIC, 0,  AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
//                am.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0,  AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
                Toast.makeText(context, "Receive Connection" , Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(context, "Receive Disconnection" , Toast.LENGTH_LONG).show();
            }


        }

    }
}
