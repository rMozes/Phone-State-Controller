package net.brusd.phonecontroller.AppDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.brusd.phonecontroller.utils.SharedPreferences;

/**
 * Created by BruSD on 6/10/2014.
 */
public class AppDB {
    private Context context;
    private SQLiteDatabase appDB;
    private AppOpenHelper appOpenHelper;

    private static AppDB instance = null;

    public static AppDB getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDB.class) {
                if (instance == null) {
                    instance = new AppDB(context);
                }
            }
        }
        return instance;
    }
    private AppDB(Context context) {
        this.context = context;
        this.appOpenHelper = new AppOpenHelper(context);
        appDB = appOpenHelper.getWritableDatabase();
    }
    public void addWiFiModeRelation(int modeID, String wiFiName) {
        if (!appDB.isOpen())
            open();

        wiFiName = wiFiName.replace("'", "''");


        Cursor check = appDB.query(AppOpenHelper.TABLE_MODES_WIFI_RELATION,
                new String[]{AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name},
                AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name + " = '" + wiFiName + "'",
                null,
                null,
                null,
                null);

        int count = check.getCount();
        check.close();

        if (count == 0) {

            ContentValues values = new ContentValues();
            values.put(AppOpenHelper.TABLE_MODES_WIFI_RELATION_mode_id, modeID);
            values.put(AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name, wiFiName);

            appDB.insert(AppOpenHelper.TABLE_MODES_WIFI_RELATION, null, values);
        }
    }
    public int isWiFiRelatedToMode(String wifiName){
        Cursor cursorAllWifi = null;
        int related;
        if (!appDB.isOpen())
            open();

        cursorAllWifi = appDB.query(AppOpenHelper.TABLE_MODES_WIFI_RELATION,
                new String[]{ AppOpenHelper.TABLE_MODES_WIFI_RELATION_mode_id,  AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name},
                AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name + " = "+wifiName ,
                null,
                null,
                null,
                null);
        if(cursorAllWifi.moveToFirst()){
            related = cursorAllWifi.getInt(0);
        }else {
            related = getDefoultModeID();
        }

        return related;
    }

    private int getDefoultModeID() {
        return SharedPreferences.getGlobalVolumeMode(context);
    }

    public Cursor getAllWifiByModeID(int modeID){
        Cursor cursorAllWifi = null;
        if (!appDB.isOpen())
            open();

        cursorAllWifi = appDB.query(AppOpenHelper.TABLE_MODES_WIFI_RELATION,
                new String[]{AppOpenHelper.TABLE_MODES_WIFI_RELATION_id, AppOpenHelper.TABLE_MODES_WIFI_RELATION_mode_id, AppOpenHelper.TABLE_MODES_WIFI_RELATION_wifi_name, },
                AppOpenHelper.TABLE_MODES_WIFI_RELATION_mode_id + " = "+modeID ,
                null,
                null,
                null,
                null);

        return cursorAllWifi;
    }


    public void open(){
        appDB = appOpenHelper.getWritableDatabase();
    }

    public void close(){
        appDB.close();
    }


}
