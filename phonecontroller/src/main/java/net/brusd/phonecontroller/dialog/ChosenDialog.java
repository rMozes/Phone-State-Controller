package net.brusd.phonecontroller.dialog;

import android.app.Dialog;
import android.content.Context;

import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.brusd.phonecontroller.AppDataBase.AppDB;
import net.brusd.phonecontroller.Constant;
import net.brusd.phonecontroller.R;

/**
 * Created by BruSD on 10/2/2014.
 */
public class ChosenDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Context context;
    private RadioGroup radiogroup;
    private static AppDB appDB = null;
    private String wifi;
    private Button yes, no;
    private int modeID;

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {

                case R.id.radio_full_mode:
                    modeID = Constant.MODE_FULL;
                    break;
                case R.id.radio_medium_mode:
                    modeID = Constant.MODE_MEDIUM;
                    break;
                case R.id.radio_silent_mode:
                    modeID = Constant.MODE_SILENT;
                    break;
            }
        }
    };


    public ChosenDialog(Context context, String wifiName) {
        super(context);
        this.context = context;
        this.wifi = wifiName;
        appDB = AppDB.getInstance(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choisen);

        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        radiogroup = (RadioGroup) findViewById(R.id.radio_group);

        radiogroup.setOnCheckedChangeListener(onCheckedChangeListener);
        initChosenRB();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    private void initChosenRB() {
        modeID = appDB.isWiFiRelatedToMode(wifi);
        switch (modeID) {
            case Constant.MODE_FULL:
                RadioButton radioFullButton = (RadioButton) findViewById(R.id.radio_full_mode);
                radioFullButton.setChecked(true);
                break;
            case Constant.MODE_MEDIUM:
                RadioButton radioMediumButton = (RadioButton) findViewById(R.id.radio_medium_mode);
                radioMediumButton.setChecked(true);
                break;
            case Constant.MODE_SILENT:
                RadioButton radioSilentButton = (RadioButton) findViewById(R.id.radio_silent_mode);
                radioSilentButton.setChecked(true);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                appDB.addWiFiModeRelation(modeID, wifi);
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
        }
    }
}
