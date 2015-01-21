package gemz.com.rpp.Data;

import android.provider.BaseColumns;

/**
 * Created by Eamun on 7/30/2014.
 */
public class VehicleSchema {


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public VehicleSchema() {}

    /* Inner class that defines the table contents */
    public static abstract class VehicleTable implements BaseColumns {
        public static final String TABLE_NAME = "Vehicles";
        //public static final String COLUMN_NAME_PROFILEID = "profileID";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MAKE = "make";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_TRIM = "trim";
        public static final String COLUMN_NAME_MSRP = "MSRP";
        public static final String COLUMN_NAME_INSURANCE = "insurance";
        public static final String COLUMN_NAME_MPG = "MPG";
        public static final String COLUMN_NAME_MAINTENANCE = "maintenance";


    }
}
