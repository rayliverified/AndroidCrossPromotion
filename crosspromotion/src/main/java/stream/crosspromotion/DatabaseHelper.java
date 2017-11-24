package stream.crosspromotion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DBVersion = 1;
    public static final String DBName = "CrossPromotionDB";

    public static final String TABLE_ADS = "ads";
    public static final String KEY_ID = "_id";
    public static final String KEY_ORIGINALID = "originalid";
    public static final String KEY_FROMID = "fromid";
    public static final String KEY_ADTYPE = "adtype";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SEGMENT = "segment";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_DEVICEVERSION = "deviceversion";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_PRICE = "price";
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

    private static DatabaseHelper mInstance = null;
    public final String mActivity = this.getClass().getSimpleName();

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    public static DatabaseHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Query.
        String adsTable = "CREATE TABLE IF NOT EXISTS ads (_id INTEGER PRIMARY KEY AUTOINCREMENT, originalid INTEGER, fromid INTEGER, adtype INTEGER, status INTEGER, " +
                "segment TEXT, location TEXT, deviceversion INTEGER, weight INTEGER, price INTEGER, title TEXT, subtitle TEXT, description TEXT, descriptionshort TEXT, " +
                "category TEXT, rating INTEGER, installs INTEGER, version TEXT, developer TEXT, email TEXT, address TEXT, website TEXT, linkurl TEXT, packagename TEXT, " +
                "previewimageurl TEXT, imageurl TEXT, previewvideoimageurl TEXT, videourl TEXT, text1 TEXT, text2 TEXT, text3 TEXT, number1 INTEGER, number2 INTEGER, " +
                "number3 INTEGER, createat INTEGER DEFAULT 0, updateat INTEGER DEFAULT 0, startat INTEGER DEFAULT 0, endat INTEGER DEFAULT 0, removeat INTEGER DEFAULT 0);";
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

    public ArrayList<Ad> GetAds(Integer limit) {
        ArrayList<Ad> itemList = new ArrayList<>();
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime() / 1000;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_ADS, null, KEY_REMOVEAT + "=0 AND " + KEY_STARTAT + "<? AND " + KEY_ENDAT + ">?", new String[]{Long.toString(currentTime), Long.toString(currentTime)}, null, null, KEY_WEIGHT + " DESC", Integer.toString(limit));
        if (c.moveToFirst()) {
            do {
                Ad item = new Ad();
                item = GetAdItem(item, c);
                itemList.add(item);
            } while (c.moveToNext());
        }
        c.close();
        return itemList;
    }

    public long GetMaxAdID() {
        long max = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_ADS, new String[]{"MAX(" + KEY_ORIGINALID + ")"}, null, null, null, null, null);
        if (c.moveToFirst()) {
            max = c.getLong(0);
        }
        c.close();
        return max;
    }

    public int GetAdsLatestUpdate(Long itemid, Integer limit) {
        int latestUpdate = 0;
        String selectQuery = "SELECT MAX(" + KEY_UPDATEAT + ") FROM ( SELECT " + KEY_UPDATEAT + " FROM " + TABLE_ADS
                + " WHERE " + KEY_ORIGINALID + " < " + itemid + " ORDER BY " + KEY_ORIGINALID + " DESC LIMIT " + limit + " )";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            latestUpdate = c.getInt(0);
        }
        c.close();
        return latestUpdate;
    }

    public void AddAdsBatch(JSONArray jsonArray) {
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime() / 1000;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                ContentValues values = new ContentValues();
                values.put(KEY_ORIGINALID, jsonObject.getLong("id"));
                values.put(KEY_FROMID, jsonObject.getLong("fromUserId"));
                values.put(KEY_ADTYPE, jsonObject.getInt("adType"));
                values.put(KEY_STATUS, jsonObject.getInt("status"));
                values.put(KEY_SEGMENT, jsonObject.getString("segment"));
                values.put(KEY_LOCATION, jsonObject.getString("location"));
                values.put(KEY_DEVICEVERSION, jsonObject.getInt("deviceVersion"));
                values.put(KEY_WEIGHT, jsonObject.getString("weight"));
                values.put(KEY_PRICE, jsonObject.getString("price"));
                values.put(KEY_TITLE, jsonObject.getString("title"));
                values.put(KEY_SUBTITLE, jsonObject.getString("subtitle"));
                values.put(KEY_DESCRIPTION, jsonObject.getString("description"));
                values.put(KEY_DESCRIPTIONSHORT, jsonObject.getString("descriptionShort"));
                values.put(KEY_CATEGORY, jsonObject.getString("category"));
                values.put(KEY_RATING, jsonObject.getInt("rating"));
                values.put(KEY_INSTALLS, jsonObject.getInt("installs"));
                values.put(KEY_VERSION, jsonObject.getString("version"));
                values.put(KEY_DEVELOPER, jsonObject.getString("developer"));
                values.put(KEY_EMAIL, jsonObject.getString("email"));
                values.put(KEY_ADDRESS, jsonObject.getString("address"));
                values.put(KEY_WEBSITE, jsonObject.getString("website"));
                values.put(KEY_LINKURL, jsonObject.getString("linkUrl"));
                values.put(KEY_PACKAGENAME, jsonObject.getString("packageName"));
                values.put(KEY_PREVIEWIMAGEURL, jsonObject.getString("previewImgUrl"));
                values.put(KEY_IMAGEURL, jsonObject.getString("imgUrl"));
                values.put(KEY_PREVIEWVIDEOIMAGEURL, jsonObject.getString("previewVideoImgUrl"));
                values.put(KEY_VIDEOURL, jsonObject.getString("videoUrl"));
                values.put(KEY_TEXT1, jsonObject.getString("text1"));
                values.put(KEY_TEXT2, jsonObject.getString("text2"));
                values.put(KEY_TEXT3, jsonObject.getString("text3"));
                values.put(KEY_NUMBER1, jsonObject.getInt("number1"));
                values.put(KEY_NUMBER2, jsonObject.getInt("number2"));
                values.put(KEY_NUMBER3, jsonObject.getInt("number3"));
                //Check dates are smaller than current time. Sometimes, dates are incorrect on server. Prevent permanent errors.
                if (jsonObject.getInt("createAt") > currentTime) {
                    values.put(KEY_CREATEAT, currentTime);
                } else {
                    values.put(KEY_CREATEAT, jsonObject.getInt("createAt"));
                }
                if (jsonObject.getInt("updateAt") > currentTime) {
                    values.put(KEY_UPDATEAT, currentTime);
                } else {
                    values.put(KEY_UPDATEAT, jsonObject.getInt("updateAt"));
                }
                values.put(KEY_STARTAT, jsonObject.getInt("startAt"));
                values.put(KEY_ENDAT, jsonObject.getInt("endAt"));
                values.put(KEY_REMOVEAT, jsonObject.getInt("removeAt"));

                //Attempt to update posts. If update fails, post is new and insert instead.
                long id = db.update(TABLE_ADS, values, KEY_ORIGINALID + "=?", new String[]{String.valueOf(jsonObject.getLong("id"))});
                if (id == 0) {
                    id = db.insert(TABLE_ADS, null, values);
                }

            } catch (Throwable t) {

                Log.e(mActivity, "Error JSON: " + t.toString());

            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public Ad GetAdItem(Ad item, Cursor c) {

        item.setId(c.getLong(c.getColumnIndexOrThrow(KEY_ORIGINALID)));
        item.setFromUserId(c.getLong(c.getColumnIndexOrThrow(KEY_FROMID)));
        item.setAdType(c.getInt(c.getColumnIndexOrThrow(KEY_ADTYPE)));
        item.setStatus(c.getInt(c.getColumnIndexOrThrow(KEY_STATUS)));
        item.setSegment(c.getString(c.getColumnIndexOrThrow(KEY_SEGMENT)));
        item.setLocation(c.getString(c.getColumnIndexOrThrow(KEY_LOCATION)));
        item.setDeviceVersion(c.getInt(c.getColumnIndexOrThrow(KEY_DEVICEVERSION)));
        item.setWeight(c.getInt(c.getColumnIndexOrThrow(KEY_WEIGHT)));
        item.setPrice(c.getInt(c.getColumnIndexOrThrow(KEY_PRICE)));
        item.setTitle(c.getString(c.getColumnIndexOrThrow(KEY_TITLE)));
        item.setSubTitle(c.getString(c.getColumnIndexOrThrow(KEY_SUBTITLE)));
        item.setDescription(c.getString(c.getColumnIndexOrThrow(KEY_DESCRIPTION)));
        item.setDescriptionShort(c.getString(c.getColumnIndexOrThrow(KEY_DESCRIPTIONSHORT)));
        item.setCategory(c.getString(c.getColumnIndexOrThrow(KEY_CATEGORY)));
        item.setRating(c.getInt(c.getColumnIndexOrThrow(KEY_RATING)));
        item.setInstalls(c.getInt(c.getColumnIndexOrThrow(KEY_INSTALLS)));
        item.setVersion(c.getString(c.getColumnIndexOrThrow(KEY_VERSION)));
        item.setDeveloper(c.getString(c.getColumnIndexOrThrow(KEY_DEVELOPER)));
        item.setEmail(c.getString(c.getColumnIndexOrThrow(KEY_EMAIL)));
        item.setAddress(c.getString(c.getColumnIndexOrThrow(KEY_ADDRESS)));
        item.setWebsite(c.getString(c.getColumnIndexOrThrow(KEY_WEBSITE)));
        item.setLinkUrl(c.getString(c.getColumnIndexOrThrow(KEY_LINKURL)));
        item.setMyPackageName(c.getString(c.getColumnIndexOrThrow(KEY_PACKAGENAME)));
        item.setPreviewImageUrl(c.getString(c.getColumnIndexOrThrow(KEY_PREVIEWIMAGEURL)));
        item.setImageUrl(c.getString(c.getColumnIndexOrThrow(KEY_IMAGEURL)));
        item.setPreviewVideoImageUrl(c.getString(c.getColumnIndexOrThrow(KEY_PREVIEWVIDEOIMAGEURL)));
        item.setVideoUrl(c.getString(c.getColumnIndexOrThrow(KEY_VIDEOURL)));
        item.setText1(c.getString(c.getColumnIndexOrThrow(KEY_TEXT1)));
        item.setText2(c.getString(c.getColumnIndexOrThrow(KEY_TEXT2)));
        item.setText3(c.getString(c.getColumnIndexOrThrow(KEY_TEXT3)));
        item.setNumber1(c.getInt(c.getColumnIndexOrThrow(KEY_NUMBER1)));
        item.setNumber2(c.getInt(c.getColumnIndexOrThrow(KEY_NUMBER2)));
        item.setNumber3(c.getInt(c.getColumnIndexOrThrow(KEY_NUMBER3)));
        item.setCreateAt(c.getInt(c.getColumnIndexOrThrow(KEY_CREATEAT)));
        item.setUpdateAt(c.getInt(c.getColumnIndexOrThrow(KEY_UPDATEAT)));
        item.setStartAt(c.getInt(c.getColumnIndexOrThrow(KEY_STARTAT)));
        item.setEndAt(c.getInt(c.getColumnIndexOrThrow(KEY_ENDAT)));
        item.setRemoveAt(c.getInt(c.getColumnIndexOrThrow(KEY_REMOVEAT)));

        return item;
    }
}

