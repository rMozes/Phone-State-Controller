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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.startad.lib.SADView;

import net.brusd.phonecontroller.AppDataBase.AppDB;
import net.brusd.phonecontroller.Constant;
import net.brusd.phonecontroller.R;
import net.brusd.phonecontroller.dialog.ChosenDialog;

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

    private TextView wifiNameTextView;
    private ImageView modeTypeImageView, editModeAssocieteImageView;
    private static AppDB appDB = null;
    private ChosenDialog chosenDialog;

    private View.OnClickListener onClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showChosenDialog();

        }
    };

    private void showChosenDialog() {
        chosenDialog =  new ChosenDialog(parentActivity, ssid);
        chosenDialog.setCancelable(false);
        chosenDialog.show();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        parentActivity = getActivity();
        wifiNameTextView = (TextView) rootView.findViewById(R.id.current_connected_wifi_text_view);
        modeTypeImageView = (ImageView) rootView.findViewById(R.id.mode_type_image_view);
        editModeAssocieteImageView = (ImageView)rootView.findViewById(R.id.edit_mode_image_view);

        initAdView();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initNetworkData();
    }

    @Override
    public void onDestroy() {
        if (this.sadView != null) {
            this.sadView.destroy();
        }
        super.onDestroy();
    }

    private void initAdView() {
        sadView = new SADView(parentActivity, APPLICAITON_ID);
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.sad_linear_layout);

        // Add the adView to it
        layout.addView(sadView);

        if (Locale.getDefault().getLanguage().equals("ru") || Locale.getDefault().getLanguage().equals("ua")) {
            this.sadView.loadAd(SADView.LANGUAGE_RU);
        } else {
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
                appDB = AppDB.getInstance(parentActivity);
                ssid = connectionInfo.getSSID();
                initModeTypeImageView(appDB.isWiFiRelatedToMode(ssid));
                editModeAssocieteImageView.setOnClickListener(onClickListener);
            }
        } else {
            ssid = parentActivity.getString(R.string.no_wifi_connection_string);
        }
        wifiNameTextView.setText(ssid);
    }

    private void initModeTypeImageView(int modeID) {

        switch (modeID) {
            case Constant.MODE_FULL:
                modeTypeImageView.setBackgroundColor(getResources().getColor(R.color.full_mode));
                break;
            case Constant.MODE_MEDIUM:
                modeTypeImageView.setBackgroundColor(getResources().getColor(R.color.medium_mode));
                break;
            case Constant.MODE_SILENT:
                modeTypeImageView.setBackgroundColor(getResources().getColor(R.color.silent_mode));
                break;

        }


    }

}
