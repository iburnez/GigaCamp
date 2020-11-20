package com.wgu.gigacamp.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gigacamp.db";
    private static int DATABASE_VERSION = 1;

    /**
     * _______________________________________________________________________
     *                            DECLARE TABLE CONSTANTS
     * -----------------------------------------------------------------------
     */

    //ACCOUNT TABLE CONSTANTS
    public static final String TABLE_ACCOUNT = "account";
    public static final String ACCOUNT_TABLE_ID = "accountID";
    public static final String ACCOUNT_FNAME = "fName";
    public static final String ACCOUNT_LNAME = "lName";
    public static final String ACCOUNT_EMAIL = "email";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT_PHONE_NUM = "phoneNum";
    public static final String[] ACCOUNT_COLS = {ACCOUNT_TABLE_ID, ACCOUNT_FNAME, ACCOUNT_LNAME,
                                             ACCOUNT_EMAIL, ACCOUNT_PASSWORD,ACCOUNT_PHONE_NUM };

    //CAMPGROUND TABLE CONSTANTS
    public static final String TABLE_CAMPGROUND = "campground";
    public static final String CAMPGROUND_TABLE_ID = "cgID";
    public static final String CAMPGROUND_ACCOUNT_ID = "accountID";
    public static final String CAMPGROUND_ADDRESS_ID = "addressID";
    public static final String CAMPGROUND_NAME = "cgName";
    public static final String CAMPGROUND_DESC = "cgDesc";
    public static final String CAMPGROUND_EMAIL = "cgEmail";
    public static final String CAMPGROUND_PHONE = "cgPhone";
    public static final String CAMPGROUND_OPEN_TIME = "cgOpenTime";
    public static final String CAMPGROUND_CLOSE_TIME = "cgCloseTime";
    public static final String[] CAMPGROUND_COLS = {CAMPGROUND_TABLE_ID, CAMPGROUND_ACCOUNT_ID,
                        CAMPGROUND_ADDRESS_ID, CAMPGROUND_NAME, CAMPGROUND_DESC, CAMPGROUND_EMAIL,
                        CAMPGROUND_PHONE, CAMPGROUND_OPEN_TIME, CAMPGROUND_CLOSE_TIME};

    //CAMPSITE TABLE CONSTANTS
    public static final String TABLE_CAMPSITE = "campsite";
    public static final String CAMPSITE_TABLE_ID = "csID";
    public static final String CAMPSITE_CAMPGROUND_ID = "cgID";
    public static final String CAMPSITE_SITE_NUM = "campsiteNum";
    public static final String CAMPSITE_PRICE = "price";
    public static final String CAMPSITE_MAX_GUESTS = "maxGuests";
    public static final String[] CAMPSITE_COLS = {CAMPSITE_TABLE_ID, CAMPSITE_CAMPGROUND_ID,
                                    CAMPSITE_SITE_NUM, CAMPSITE_PRICE,CAMPSITE_MAX_GUESTS};

    //ADDRESS TABLE CONSTANTS
    public static final String TABLE_ADDRESS = "address";
    public static final String ADDRESS_TABLE_ID = "addressID";
    public static final String ADDRESS_CITY_ID = "cityID";
    public static final String ADDRESS_LINE_1 = "addressLine1";
    public static final String ADDRESS_LINE_2 = "addressLine2";
    public static final String[] ADDRESS_COLS = {ADDRESS_TABLE_ID, ADDRESS_CITY_ID,
                                                ADDRESS_LINE_1, ADDRESS_LINE_2};

    //CITY TABLE CONSTANTS
    public static final String TABLE_CITY = "city";
    public static final String CITY_TABLE_ID = "cityID";
    public static final String CITY_STATE_ID = "stateID";
    public static final String CITY_NAME = "cityName";
    public static final String CITY_ZIPCODE = "zipcode";
    public static final String[] CITY_COLS = {CITY_TABLE_ID, CITY_STATE_ID,
                                                CITY_NAME, CITY_ZIPCODE};

    //STATE TABLE CONSTANTS
    public static final String TABLE_STATE = "state";
    public static final String STATE_TABLE_ID = "addressID";
    public static final String STATE_NAME = "stateName";
    public static final String[] STATE_COLS = {STATE_TABLE_ID, STATE_NAME};

    //RESERVATION TABLE CONSTANTS
    public static final String TABLE_RESERVATIONS = "reservations";
    public static final String RESERVATIONS_TABLE_ID = "resID";
    public static final String RESERVATIONS_CAMPSITE_ID = "csID";
    public static final String RESERVATIONS_TRAVELER_ID = "travelerAccountID";
    public static final String RESERVATIONS_ARRIVAL = "arrivalDate";
    public static final String RESERVATIONS_DEPARTURE = "departureDate";
    public static final String RESERVATIONS_NAME = "resName";
    public static final String RESERVATIONS_PHONE = "resPhone";
    public static final String RESERVATIONS_EMAIL = "resEmail";
    public static final String[] RESERVATIONS_COLS = {RESERVATIONS_TABLE_ID, RESERVATIONS_CAMPSITE_ID,
            RESERVATIONS_TRAVELER_ID, RESERVATIONS_ARRIVAL, RESERVATIONS_DEPARTURE, RESERVATIONS_NAME,
            RESERVATIONS_PHONE, RESERVATIONS_EMAIL};

    /**
     * _______________________________________________________________________
     *                            DECLARE QUERY CONSTANTS
     * -----------------------------------------------------------------------
     */

    //ACCOUNT TABLE CREATE STRING
    private static final String TABLE_ACCOUNT_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ACCOUNT + " ( " +
            ACCOUNT_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ACCOUNT_FNAME + " TEXT, " +
            ACCOUNT_LNAME + " TEXT, " +
            ACCOUNT_EMAIL + " TEXT, " +
            ACCOUNT_PASSWORD + " TEXT, " +
            ACCOUNT_PHONE_NUM + " TEXT );";

    //STATE NOTES TABLE CREATE STRING
    private static final String TABLE_STATE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_STATE + " ( " +
            STATE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STATE_NAME + " TEXT );";

    //CITY NOTES TABLE CREATE STRING
    private static final String TABLE_CITY_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_CITY + " ( " +
            CITY_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CITY_STATE_ID + " INTEGER, " +
            CITY_NAME + " TEXT, " +
            CITY_ZIPCODE + " TEXT, " +
            "FOREIGN KEY (" + CITY_STATE_ID +
            ") REFERENCES " + TABLE_STATE +
            " ( " +  STATE_TABLE_ID + " ));";

    //ADDRESS NOTES TABLE CREATE STRING
    private static final String TABLE_ADDRESS_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ADDRESS + " ( " +
            ADDRESS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ADDRESS_CITY_ID + " INTEGER, " +
            ADDRESS_LINE_1 + " TEXT, " +
            ADDRESS_LINE_2 + " TEXT, " +
            "FOREIGN KEY (" + ADDRESS_CITY_ID +
            ") REFERENCES " + TABLE_CITY +
            " ( " +  CITY_TABLE_ID + " ));";

    //CAMPGROUND TABLE CREATE STRING
    private static final String TABLE_CAMPGROUND_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_CAMPGROUND + " ( " +
            CAMPGROUND_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPGROUND_ACCOUNT_ID + " INTEGER, " +
            CAMPGROUND_ADDRESS_ID + " INTEGER, " +
            CAMPGROUND_NAME + " TEXT, " +
            CAMPGROUND_DESC + " TEXT, " +
            CAMPGROUND_EMAIL + " TEXT, " +
            CAMPGROUND_PHONE + " TEXT, " +
            CAMPGROUND_OPEN_TIME + " TEXT, " +
            CAMPGROUND_CLOSE_TIME + " TEXT, " +
            "FOREIGN KEY (" + CAMPGROUND_ACCOUNT_ID +
            ") REFERENCES " + TABLE_ACCOUNT +
            " ( " +  ACCOUNT_TABLE_ID + " )," +
            "FOREIGN KEY (" + CAMPGROUND_ADDRESS_ID +
            ") REFERENCES " + TABLE_ADDRESS +
            " ( " +  ADDRESS_TABLE_ID + " )" +
            "ON DELETE CASCADE \n " +
            "ON UPDATE CASCADE );";

    //CAMPSITE NOTES TABLE CREATE STRING
    private static final String TABLE_CAMPSITE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_CAMPSITE + " ( " +
            CAMPSITE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPSITE_CAMPGROUND_ID + " INTEGER, " +
            CAMPSITE_SITE_NUM + " TEXT, " +
            CAMPSITE_PRICE + " INTEGER, " +
            CAMPSITE_MAX_GUESTS + " INTEGER, " +
            "FOREIGN KEY (" + CAMPSITE_CAMPGROUND_ID +
            ") REFERENCES " + TABLE_CAMPGROUND +
            " ( " +  CAMPGROUND_TABLE_ID + " )" +
            "ON DELETE CASCADE \n " +
            "ON UPDATE CASCADE );";

    //RESERVATIONS TABLE CREATE STRING
    private static final String TABLE_RESERVATIONS_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RESERVATIONS + " ( " +
            RESERVATIONS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RESERVATIONS_CAMPSITE_ID + " INTEGER, " +
            RESERVATIONS_TRAVELER_ID + " INTEGER, " +
            RESERVATIONS_ARRIVAL + " DATE, " +
            RESERVATIONS_DEPARTURE + " DATE, " +
            RESERVATIONS_NAME + " TEXT, " +
            RESERVATIONS_PHONE + " TEXT, " +
            RESERVATIONS_EMAIL + " TEXT, " +
            "FOREIGN KEY (" + RESERVATIONS_CAMPSITE_ID +
            ") REFERENCES " + TABLE_CAMPSITE +
            " ( " +  CAMPSITE_TABLE_ID + " )," +
            "FOREIGN KEY (" + RESERVATIONS_TRAVELER_ID +
            ") REFERENCES " + TABLE_ACCOUNT +
            " ( " +  ACCOUNT_TABLE_ID + " ));";

    /**
     * _______________________________________________________________________
     *                       CONSTRUCTORS & DEFAULT METHODS
     * -----------------------------------------------------------------------
     */

    /**
     * DEFAULT CONSTRUCTOR FOR DBOpenHelper Class
     * @param context -
     */
    public DBOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * CREATE NEW DB
     * @param db sqlLitedb
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * UPGRADE DB FROM OLD TO NEW VERSION
     * @param db sqlLite db
     * @param oldVersion old db version
     * @param newVersion new db version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPGROUND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPSITE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
        onCreate(db);
    }

    /**
     * CREATE TABLES METHOD
     * can be called anytime not just on create
     */
    public void createTable(){
        this.getWritableDatabase().execSQL(TABLE_ACCOUNT_CREATE);
        this.getWritableDatabase().execSQL(TABLE_STATE_CREATE);
        this.getWritableDatabase().execSQL(TABLE_CITY_CREATE);
        this.getWritableDatabase().execSQL(TABLE_ADDRESS_CREATE);
        this.getWritableDatabase().execSQL(TABLE_CAMPGROUND_CREATE);
        this.getWritableDatabase().execSQL(TABLE_CAMPSITE_CREATE);
        this.getWritableDatabase().execSQL(TABLE_RESERVATIONS_CREATE);
    }
}
