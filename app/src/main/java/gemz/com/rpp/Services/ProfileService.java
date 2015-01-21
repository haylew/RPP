package gemz.com.rpp.Services;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import gemz.com.rpp.Data.ProfileDB;
import gemz.com.rpp.Data.ProfileData;
import gemz.com.rpp.Data.ProfileSchema;

public class ProfileService extends Service {

    private final IBinder mBinder = new LocalBinder();


    private ProfileDB dbhelper;
    private SQLiteDatabase sqldb;

    List<ProfileData> pd;


   public class LocalBinder extends Binder {
       public ProfileService getService(){
            return ProfileService.this;
       }
   }

    public ProfileService() {
        dbhelper = new ProfileDB(this);
        //sqldb = dbhelper.getReadableDatabase();
        loadData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }


    private void loadData()
    {
        //dbhelper = new ProfileDB(this);
        pd = dbhelper.loadProfiles();
    }





    public void newProfile()
    {
        // Gets the data repository in write mode
        sqldb = dbhelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(ProfileSchema.ProfileTable.COLUMN_NAME_PROFILEID, "");
        values.put(ProfileSchema.ProfileTable.COLUMN_NAME_NAME, "");
        values.put(ProfileSchema.ProfileTable.COLUMN_NAME_AGE, "");
        values.put(ProfileSchema.ProfileTable.COLUMN_NAME_ZIPCODE, "");
        values.put(ProfileSchema.ProfileTable.COLUMN_NAME_ANNUALMILES, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = sqldb.insert(
                ProfileSchema.ProfileTable.TABLE_NAME,
                "null",
                values);
    }



}

