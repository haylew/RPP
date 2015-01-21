package gemz.com.rpp.Data;

import android.provider.BaseColumns;

/**
 * Created by Eamun on 7/21/2014.
 */
public final class ProfileSchema {


        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
        public ProfileSchema() {}

        /* Inner class that defines the table contents */
        public static abstract class ProfileTable implements BaseColumns {
            public static final String TABLE_NAME = "Profiles";
            public static final String COLUMN_NAME_PROFILEID = "profileID";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_AGE = "age";
            public static final String COLUMN_NAME_ZIPCODE = "zipcode";
            public static final String COLUMN_NAME_ANNUALMILES = "annualmiles";

        }

}
