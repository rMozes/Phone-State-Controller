package net.brusd.phonecontroller.AppDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.brusd.phonecontroller.Constant;

/**
 * Created by BruSD on 6/10/2014.
 */
public class AppOpenHelper extends SQLiteOpenHelper {
    // DB
    public static final String DATABASE_NAME  = Constant.PROJECT_NAME + ".PhoneController";
    public static final int DATABASE_VERSION = 1;

    //Modes Wifi Relation Table
    public static final String TABLE_MODES_WIFI_RELATION = "Modes_Wifi_Relation_Table";
    public static final String TABLE_MODES_WIFI_RELATION_id = "id_relation";
    public static final String TABLE_MODES_WIFI_RELATION_mode_id = "mode_id";
    public static final String TABLE_MODES_WIFI_RELATION_wifi_name = "wifi_name";

    private static final String TABLE_MODES_WIFI_RELATION_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_MODES_WIFI_RELATION
            + " ("
            + TABLE_MODES_WIFI_RELATION_id + " INTEGER PRIMARY KEY, "
            + TABLE_MODES_WIFI_RELATION_mode_id + " INTEGER NOT NULL, "
            + TABLE_MODES_WIFI_RELATION_wifi_name + " TEXT NOT NULL"
            + ");";

    private static final String TABLE_MODES_WIFI_RELATION_DROP = "DROP TABLE IF EXISTS " + TABLE_MODES_WIFI_RELATION;

    public AppOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_MODES_WIFI_RELATION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL(TABLE_MODES_WIFI_RELATION_DROP);
        onCreate(db);

    }
}
