package gemz.com.rpp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Eamun on 7/21/2014.
 */
public class ProfileDB  extends SQLiteOpenHelper {


    //private DatabaseHelper mOpenHelper;

    //private ArrayList<ProfileData> mPD;

    private static final String[] PROJ = new String[] {
    ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID,
    ProfileSchema.ProfileTable.COLUMN_NAME_NAME,
    ProfileSchema.ProfileTable.COLUMN_NAME_AGE,
    ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE,
    ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES,
    };




    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGERT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProfileSchema.ProfileTable.TABLE_NAME + " (" +
                    ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID + " INTEGER PRIMARY KEY," +
                    //ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID + TEXT_TYPE + COMMA_SEP +
                    ProfileSchema.ProfileTable.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ProfileSchema.ProfileTable.COLUMN_NAME_AGE + INT_TYPE + COMMA_SEP +
                    ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE + INT_TYPE + COMMA_SEP +
                    ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES + INT_TYPE  + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProfileSchema.ProfileTable.TABLE_NAME;


    // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Profile.db";

        public ProfileDB(Context context) {
            //context
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }


    public ArrayList<ProfileData> loadProfiles()
    {
        ArrayList<ProfileData> al = new ArrayList<ProfileData>();

        //SQLiteDatabase.CREATE_IF_NECESSARY

        //SQLiteDatabase sqldb = getReadableDatabase();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        //SQLiteDatabase sqldb = SQLiteDatabase.openOrCreateDatabase("Profile.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        /*
        String projection[] = new String[5];
        projection[0] = ProfileSchema.ProfileTable._ID;
        projection[1] = ProfileSchema.ProfileTable.COLUMN_NAME_NAME;
        projection[2] = ProfileSchema.ProfileTable.COLUMN_NAME_AGE;
        projection[3] = ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE;
        projection[4] = ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES;
        */

        Cursor c = sqldb.query(
                ProfileSchema.ProfileTable.TABLE_NAME,
                PROJ,
                null,
                null,
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (!c.moveToFirst()) {
            return null;
        }
        else
        {
            advance = true;
        }




        while (advance) {
            ProfileData temp = new ProfileData();
            temp.setProfileID(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID)));
            temp.setName(c.getString(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_NAME)));
            temp.setAge(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_AGE)));
            temp.setZipcode(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE)));
            temp.setAnnMiles(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES)));
            al.add(temp);
            advance = c.moveToNext();
        }

        sqldb.close();
        return al;
    }

    /*
    public ProfileData getProfileData (int profileID)
    {
        SQLiteDatabase sqldb = getReadableDatabase();
        String[] selectionArgs = {String.valueOf(profileID)};
        Cursor c = sqldb.query(
                ProfileSchema.ProfileTable.TABLE_NAME,
                PROJ,null,null,
                //"_id=?",
                //selectionArgs ,
                null,
                null,
                null,
                null);


        if (c.getPosition() > 0)
        {
            ProfileData temp = new ProfileData();
            temp.setProfileID(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable._ID)));
            temp.setName(c.getString(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_NAME)));
            temp.setAge(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_AGE)));
            temp.setZipcode(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE)));
            temp.setAnnMiles(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES)));
            return temp;
        }

        return null;
    }
    */


    public ArrayList<ProfileData> getProfileData (int profileID)
    {
        ArrayList<ProfileData> al = new ArrayList<ProfileData>();

        SQLiteDatabase sqldb = getReadableDatabase();
        String[] selectionArgs = {String.valueOf(profileID)};
        Cursor c = sqldb.query(
                ProfileSchema.ProfileTable.TABLE_NAME,
                PROJ,null,null,
                //"_id=?",
                //selectionArgs ,
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.getPosition() > 0) {
            advance = true;
        }
        else
        {
            return null;
        }


        while (advance)
        {
            ProfileData temp = new ProfileData();
            temp.setProfileID(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID)));
            temp.setName(c.getString(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_NAME)));
            temp.setAge(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_AGE)));
            temp.setZipcode(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE)));
            temp.setAnnMiles(c.getInt(c.getColumnIndexOrThrow(ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES)));
            al.add(temp);
            return al;
        }

        return null;
    }


    public int insertProfile(ProfileData pd)
    {
        ContentValues cv = new ContentValues(5);
        cv.put(ProfileSchema.ProfileTable.COLUMN_NAME_NAME, pd.getName());
        cv.put(ProfileSchema.ProfileTable.COLUMN_NAME_AGE, pd.getAge());
        cv.put(ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE, pd.getZipcode());
        cv.put(ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES, pd.getAnnMiles());

        SQLiteDatabase sqldb = this.getWritableDatabase();

        int returnVal;
        returnVal = (int) sqldb.insert(ProfileSchema.ProfileTable.TABLE_NAME,null,cv);
        sqldb.close();
        return returnVal;


        //return profileID;
    }

    /*
    public String[] getProfileNames()
    {

        if (mPD == null)
        {
            mPD = loadProfiles();
        }


        String[] temp = new String[mPD.size()];
        ProfileData pdTemp;

        for (int i = 0; i < mPD.size(); i++)
        {
            pdTemp = mPD.get(i);
            temp[i] = pdTemp.getName();
        }

        return temp;
    }
    */

}

