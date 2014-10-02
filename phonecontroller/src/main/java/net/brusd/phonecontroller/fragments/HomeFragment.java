package net.brusd.phonecontroller.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.startad.lib.SADView;


import net.brusd.phonecontroller.R;

import java.util.Locale;

/**
 * Created by BruSD on 6/5/2014.
 */
public class HomeFragment extends Fragment {
    private View rootView;
    private SADView sadView;
    private Activity parentActivity;
    private static final String APPLICAITON_ID = "542d3fc223a7211400000000";
    private String ssid = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        parentActivity = getActivity();


        initAdView();
        initNetworkData();


        return rootView;
    }


    private void initAdView(){
        sadView = new SADView(parentActivity, APPLICAITON_ID);
        LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.sad_linear_layout);

        // Add the adView to it
        layout.addView(sadView);

        if(Locale.getDefault().getLanguage().equals("ru") ||Locale.getDefault().getLanguage().equals("ua")){
            this.sadView.loadAd(SADView.LANGUAGE_RU);
        }else {
            this.sadView.loadAd(SADView.LANGUAGE_EN);
        }
    }

    private void initNetworkData() {

        ConnectivityManager connManager = (ConnectivityManager) parentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) parentActivity.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !(connectionInfo.getSSID().equals(""))) {
                //if (connectionInfo != null && !StringUtil.isBlank(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        } else{
            ssid = parentActivity.getString(R.string.no_wifi_connection_string);
        }


    }

    @Override
    public void onDestroy() {
        if (this.sadView != null) {
            this.sadView.destroy();
        }
        super.onDestroy();
    }
}
