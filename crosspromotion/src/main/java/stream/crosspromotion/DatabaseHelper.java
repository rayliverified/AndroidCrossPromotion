package stream.crosspromotion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DBVersion = 1;
    public static final String DBName = "CrossPromotionDB";

    public static final String TABLE_ADS = "ads";
    public static final String KEY_ID = "_id";
    public static final String KEY_ORIGINALID = "originalid";
    public static final String KEY_FROMID = "fromid";
    public static final String KEY_ADTYPE = "adtype";
    public static final String KEY_SEGMENT = "segment";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_DEVICEVERSION = "deviceversion";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SUBTITLE = "subtitle";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DESCRIPTIONSHORT = "descriptionshort";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_RATING = "rating";
    public static final String KEY_INSTALLS= "installs";
    public static final String KEY_VERSION = "version";
    public static final String KEY_DEVELOPER = "developer";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_WEBSITE = "website";
    public static final String KEY_LINKURL = "linkurl";
    public static final String KEY_PACKAGENAME = "packagename";
    public static final String KEY_PREVIEWIMAGEURL = "previewimageurl";
    public static final String KEY_IMAGEURL = "imageurl";
    public static final String KEY_PREVIEWVIDEOIMAGEURL = "previewvideoimageurl";
    public static final String KEY_VIDEOURL = "videourl";
    public static final String KEY_TEXT1 = "text1";
    public static final String KEY_TEXT2 = "text2";
    public static final String KEY_TEXT3 = "text3";
    public static final String KEY_NUMBER1 = "number1";
    public static final String KEY_NUMBER2 = "number2";
    public static final String KEY_NUMBER3 = "number3";
    public static final String KEY_CREATEAT = "createat";
    public static final String KEY_UPDATEAT = "updateat";
    public static final String KEY_STARTAT = "startat";
    public static final String KEY_ENDAT = "endat";
    public static final String KEY_REMOVEAT = "removeat";

    public static final String TABLE_ANALYTICS = "analytics";
    //    public static final String KEY_ID = "_id";
    public static final String KEY_ANALYTICSTYPE = "analyticstype";
    public static final String KEY_STATID = "statid";
    public static final String KEY_STATINT = "statint";
    public static final String KEY_STATTEXT = "stattext";
    //    public static final String KEY_CREATEAT = "createat";
    public static final String KEY_UPLOADED = "uploaded";

    public final String mActivity = this.getClass().getSimpleName();

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Query.
        String adsTable = "CREATE TABLE IF NOT EXISTS ads (_id INTEGER PRIMARY KEY AUTOINCREMENT, originalid INTEGER, fromid INTEGER, adtype INTEGER, segment TEXT, location TEXT, deviceversion INTEGER, " +
                "weight INTEGER, title TEXT, subtitle TEXT, description TEXT, descriptionshort TEXT, category TEXT, rating INTEGER, installs INTEGER, " +
                "version TEXT, developer TEXT, email TEXT, address TEXT, website TEXT, linkurl TEXT, packagename TEXT, previewimageurl TEXT, imageurl TEXT, previewvideoimageurl TEXT, " +
                "videourl TEXT, text1 TEXT, text2 TEXT, text3 TEXT, number1 INTEGER, number2 INTEGER, number3 INTEGER, " +
                "createat INTEGER DEFAULT 0, updateat INTEGER DEFAULT 0, startat INTEGER DEFAULT 0, endat INTEGER DEFAULT 0, removeat INTEGER DEFAULT 0);";
        String analyticsTable = "CREATE TABLE IF NOT EXISTS analytics (_id INTEGER PRIMARY KEY AUTOINCREMENT, analyticstype INTEGER, statid INTEGER, statint INTEGER, stattext TEXT, createat INTEGER DEFAULT 0, uploaded INTEGER DEFAULT 0);";

        //Execute Query
        db.execSQL(adsTable);
        Log.d("SQLite", "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADS);
        onCreate(db);
    }

    public void ResetDatabase() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADS);
        onCreate(db);

        db.close();
    }

    public void InitializeDatabase() {
        ResetDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
        db.close();
    }
}

