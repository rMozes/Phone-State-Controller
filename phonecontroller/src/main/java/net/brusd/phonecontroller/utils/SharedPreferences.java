package net.brusd.phonecontroller.utils;

import android.app.Activity;
import android.content.Context;

import net.brusd.phonecontroller.Constant;

/**
 * Created by BruSD on 6/16/2014.
 */
public class SharedPreferences {

    public static final String PREFS_NAME = Constant.PROJECT_NAME + "StateSettings";
    public static final String GLOBAL_VOLUME_MODE = Constant.PROJECT_NAME + "StateSettings";

    private static android.content.SharedPreferences settings;
    private static Context context = null;

    public static android.content.SharedPreferences getSP(Context _context) {
        context = _context;
        android.content.SharedPreferences settingsTemp = context.getSharedPreferences(PREFS_NAME, 0);
        return settingsTemp;
    }

    public static int getVolumeValue(Activity activity, String prefName) {
        settings = getSP(activity);
        return settings.getInt(Constant.PROJECT_NAME + prefName, 0);
    }

    public static int getGlobalVolumeMode(Context context) {
        settings = getSP(context);
        return settings.getInt(GLOBAL_VOLUME_MODE, 0);
    }

    public static void setGlobalVolumeMode(Context context, int modeID) {
        settings = getSP(context);

        android.content.SharedPreferences.Editor editor = settings.edit();
        editor.putLong(GLOBAL_VOLUME_MODE, modeID);
        // Commit the edits!
        editor.commit();
    }
}
