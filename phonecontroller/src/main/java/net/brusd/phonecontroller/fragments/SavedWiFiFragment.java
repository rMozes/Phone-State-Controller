package net.brusd.phonecontroller.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.brusd.phonecontroller.AppDataBase.AppDB;
import net.brusd.phonecontroller.Constant;
import net.brusd.phonecontroller.R;
import net.brusd.phonecontroller.utils.NetworkSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BruSD on 6/7/2014.
 */
public class SavedWiFiFragment extends Fragment {
    private String TAG = "PhoneStateController";
    private View rootView;
    private Activity parentActivity;

    private ArrayList<HashMap<String, ?>> networkList =  new ArrayList<>() ;
    private NetworkSimpleAdapter networkSimpleAdapter ;
    private ListView listView;

    private static AppDB appDB = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = getActivity();
        rootView = inflater.inflate(R.layout.fragment_saved_wifi, container, false);
        listView = (ListView)rootView.findViewById(R.id.network_list);

        initNetworkList();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initNetworkList(){
        ConnectivityManager connManager = (ConnectivityManager) parentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        TextView notificationTextView = (TextView)rootView.findViewById(R.id.no_connection_notification_text_view);
        if (networkInfo.isConnected()) {
            notificationTextView.setVisibility(View.GONE);
            appDB = AppDB.getInstance(parentActivity);

            final WifiManager wifiManager = (WifiManager) parentActivity.getSystemService(Context.WIFI_SERVICE);
            List<WifiConfiguration> configs = wifiManager.getConfiguredNetworks();
            for (WifiConfiguration config : configs) {

                int modeID = appDB.isWiFiRelatedToMode(config.SSID);
                HashMap<String, String> temp = new HashMap<>();
                temp.put(Constant.WIFI_NAME, config.SSID);
                temp.put(Constant.MODE_NAME, String.valueOf(modeID));

                networkList.add(temp);
            }
            networkSimpleAdapter = new NetworkSimpleAdapter(parentActivity, networkList, 0, null, null);
            listView.setAdapter(networkSimpleAdapter);



        }else {
            notificationTextView.setVisibility(View.VISIBLE);
            notificationTextView.setText(parentActivity.getString(R.string.no_wifi_connection_string));
        }

    }

}
