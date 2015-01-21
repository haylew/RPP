package gemz.com.rpp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Eamun on 7/30/2014.
 */
public class VehicleDB extends SQLiteOpenHelper {

    //region StaticVars
    private static final String[] PROJ = new String[] {
            VehicleSchema.VehicleTable._ID,
            VehicleSchema.VehicleTable.COLUMN_NAME_YEAR,
            VehicleSchema.VehicleTable.COLUMN_NAME_MAKE,
            VehicleSchema.VehicleTable.COLUMN_NAME_MODEL,
            VehicleSchema.VehicleTable.COLUMN_NAME_TRIM,
            VehicleSchema.VehicleTable.COLUMN_NAME_MSRP,
            VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE,
            VehicleSchema.VehicleTable.COLUMN_NAME_MPG,
            VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE
    };

    private static final String TEXT_TYPE = " TEXT";
    //private static final String INT_TYPE = " INTEGER";
    private static final String DBL_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + VehicleSchema.VehicleTable.TABLE_NAME + " (" +
                    VehicleSchema.VehicleTable._ID + " INTEGER PRIMARY KEY," +
                    VehicleSchema.VehicleTable.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_MAKE + TEXT_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_TRIM + TEXT_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_MSRP + DBL_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE + TEXT_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_MPG + DBL_TYPE + COMMA_SEP +
                    VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE + TEXT_TYPE  + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + VehicleSchema.VehicleTable.TABLE_NAME;


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Vehicle.db";
    //endregion


    public VehicleDB(Context context) {
        //context
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        //TODO: Insert all of the makes, models, etc
        VehicleData[] vehicleFords = VehicleDataLoad.getFords();

        for (int i = 0; i < vehicleFords.length; i++)
        {
            db.insert(VehicleSchema.VehicleTable.TABLE_NAME,null,insertVehicle(vehicleFords[i]));
        }

        VehicleData[] vehicleToyotas = VehicleDataLoad.getToyotas();

        for (int i = 0; i < vehicleToyotas.length; i++)
        {
            db.insert(VehicleSchema.VehicleTable.TABLE_NAME,null,insertVehicle(vehicleToyotas[i]));
        }



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

    public ArrayList<String> loadYears()
    {
        ArrayList<String> al = new ArrayList<String>();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c =sqldb.query(
                true,
                VehicleSchema.VehicleTable.TABLE_NAME,
                new String[] { VehicleSchema.VehicleTable.COLUMN_NAME_YEAR},
                null,
                null,
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.moveToFirst() == false)
        {
            return null;
        }
        else
        {
            advance = true;
        }

        while (advance) {
            al.add(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_YEAR)));
            advance = c.moveToNext();
        }

        return al;
    }

    public ArrayList<String> loadMakes(int year)
    {
        ArrayList<String> al = new ArrayList<String>();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c =sqldb.query(
                true,
                VehicleSchema.VehicleTable.TABLE_NAME,
                new String[] { VehicleSchema.VehicleTable.COLUMN_NAME_MAKE},
                VehicleSchema.VehicleTable.COLUMN_NAME_YEAR + " = ?",
                new String[] { String.valueOf(year)},
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.moveToFirst() == false)
        {
            return null;
        }
        else
        {
            advance = true;
        }

        while (advance) {
            al.add(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAKE)));
            advance = c.moveToNext();
        }

        return al;
    }


    public ArrayList<String> loadModels(int year, String makes)
    {
        ArrayList<String> al = new ArrayList<String>();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c =sqldb.query(
                true,
                VehicleSchema.VehicleTable.TABLE_NAME,
                new String[] { VehicleSchema.VehicleTable.COLUMN_NAME_MODEL},
                VehicleSchema.VehicleTable.COLUMN_NAME_YEAR + " = ? AND "  + VehicleSchema.VehicleTable.COLUMN_NAME_MAKE + " = ? " ,
                new String[] { String.valueOf(year), makes},
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.moveToFirst() == false)
        {
            return null;
        }
        else
        {
            advance = true;
        }

        while (advance) {
            al.add(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MODEL)));
            advance = c.moveToNext();
        }

        return al;
    }

    public ArrayList<String> loadTrims(int year, String makes, String models)
    {
        ArrayList<String> al = new ArrayList<String>();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c =sqldb.query(
                true,
                VehicleSchema.VehicleTable.TABLE_NAME,
                new String[] { VehicleSchema.VehicleTable.COLUMN_NAME_TRIM},
                VehicleSchema.VehicleTable.COLUMN_NAME_YEAR + " = ? AND "  + VehicleSchema.VehicleTable.COLUMN_NAME_MAKE + " = ? AND " +  VehicleSchema.VehicleTable.COLUMN_NAME_MODEL + " = ? ",
                new String[] { String.valueOf(year), makes, models},
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.moveToFirst() == false)
        {
            return null;
        }
        else
        {
            advance = true;
        }

        while (advance) {
            al.add(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM)));
            advance = c.moveToNext();
        }

        return al;
    }


    public ArrayList<VehicleData> loadVehicles()
    {
        ArrayList<VehicleData> al = new ArrayList<VehicleData>();

        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c = sqldb.query(
                VehicleSchema.VehicleTable.TABLE_NAME,
                PROJ,
                null,
                null,
                null,
                null,
                null,
                null);

        if (c.moveToFirst() == false) {
            return null;
        }

        VehicleData temp = new VehicleData();
        while (!c.isLast()) {
            temp.setVehicleID(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable._ID)));
            temp.setYear(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_YEAR)));
            temp.setMake(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAKE)));
            temp.setModel(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MODEL)));
            temp.setTrim(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM)));
            temp.setMsrp(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM)));
            temp.setInsuranceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE)));
            temp.setMpg(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MPG)));
            temp.setMaintenanceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE)));
            al.add(temp);
            c.moveToNext();
        }


        return al;
    }

    public VehicleData getVehicle(int year, String make, String model, String trim)
    {
        VehicleData temp = new VehicleData();


        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor c =sqldb.query(
                true,
                VehicleSchema.VehicleTable.TABLE_NAME,
                PROJ,
                VehicleSchema.VehicleTable.COLUMN_NAME_YEAR + " = ? AND "  + VehicleSchema.VehicleTable.COLUMN_NAME_MAKE + " = ? AND " +  VehicleSchema.VehicleTable.COLUMN_NAME_MODEL + " = ? AND "
                + VehicleSchema.VehicleTable.COLUMN_NAME_TRIM + " = ? ",
                new String[] { String.valueOf(year), make, model, trim},
                null,
                null,
                null,
                null);

        boolean advance = false;
        if (c.moveToFirst() == false)
        {
            return null;
        }
        else
        {
            temp.setVehicleID(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable._ID)));
            temp.setYear(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_YEAR)));
            temp.setMake(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAKE)));
            temp.setModel(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MODEL)));
            temp.setTrim(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM)));
            temp.setMsrp(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MSRP)));
            temp.setInsuranceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE)));
            temp.setMpg(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MPG)));
            temp.setMaintenanceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE)));
            return temp;
        }




        //return null;
    }




    public VehicleData getVehicleData (int vehicleID)
    {
        SQLiteDatabase sqldb = getReadableDatabase();

        String value = String.valueOf(vehicleID);

        Cursor c = sqldb.query(
                VehicleSchema.VehicleTable.TABLE_NAME,
                PROJ,
                VehicleSchema.VehicleTable._ID + " = ?",
                new String[] { value },
                null,
                null,
                null,
                null);


        if (c.getColumnCount() < 1)
        {
            VehicleData temp = new VehicleData();
            temp.setVehicleID(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable._ID)));
            temp.setYear(c.getInt(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_YEAR)));
            temp.setMake(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAKE)));
            temp.setModel(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MODEL)));
            temp.setTrim(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM)));
            temp.setMsrp(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MSRP)));
            temp.setInsuranceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE)));
            temp.setMpg(c.getDouble(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MPG)));
            temp.setMaintenanceFactor(c.getString(c.getColumnIndexOrThrow(VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE)));
            return temp;
        }

        return null;
    }


    private ContentValues insertVehicle(VehicleData vd)
    {
        ContentValues cv = new ContentValues(8);
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_YEAR, vd.getYear());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_MAKE, vd.getMake());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_MODEL, vd.getModel());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_TRIM, vd.getTrim());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_MSRP, vd.getMsrp());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_INSURANCE, vd.getInsuranceFactor());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_MPG, vd.getMpg());
        cv.put(VehicleSchema.VehicleTable.COLUMN_NAME_MAINTENANCE, vd.getMaintenanceFactor());

        //SQLiteDatabase sqldb = this.getWritableDatabase();

        //int vehicleID = (int) sqldb.insert(VehicleSchema.VehicleTable.TABLE_NAME,null,cv);


        return cv;
    }


}
