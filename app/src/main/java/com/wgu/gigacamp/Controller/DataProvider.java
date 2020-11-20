package com.wgu.gigacamp.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataProvider extends ContentProvider {

    /**
     * _______________________________________________________________________
     *                             DECLARE VARIABLES
     * -----------------------------------------------------------------------
     */

    //Declare SQLite DB Var Name
    private SQLiteDatabase db;

    /**
     * _______________________________________________________________________
     *                             DECLARE CONSTANTS
     * -----------------------------------------------------------------------
     */

    //Authority & Path String Constants
    private static final String AUTHORITY = "com.wgu.gigacamp.controller.dataprovider";
    private static final String ACCOUNT_PATH = "account";
    private static final String CAMPGROUND_PATH = "campground";
    private static final String CAMPSITE_PATH = "campsite";
    private static final String ADDRESS_PATH = "address";
    private static final String CITY_PATH = "city";
    private static final String STATE_PATH = "state";
    private static final String RESERVATIONS_PATH = "reservations";

    //Path URIs
    public static final Uri ACCOUNT_URI = Uri.parse("content://" + AUTHORITY + "/" + ACCOUNT_PATH);
    public static final Uri CAMPGROUND_URI = Uri.parse("content://" + AUTHORITY + "/" + CAMPGROUND_PATH);
    public static final Uri CAMPSITE_URI = Uri.parse("content://" + AUTHORITY + "/" + CAMPSITE_PATH);
    public static final Uri ADDRESS_URI = Uri.parse("content://" + AUTHORITY + "/" + ADDRESS_PATH);
    public static final Uri CITY_URI = Uri.parse("content://" + AUTHORITY + "/" + CITY_PATH);
    public static final Uri STATE_URI = Uri.parse("content://" + AUTHORITY + "/" + STATE_PATH);
    public static final Uri RESERVATIONS_URI = Uri.parse("content://" + AUTHORITY + "/" + RESERVATIONS_PATH);


    //Constants to identify the requested operations
    private static final int ACCOUNT = 1;
    private static final int ACCOUNT_ID = 2;
    private static final int CAMPGROUND = 3;
    private static final int CAMPGROUND_ID = 4;
    private static final int CAMPSITE = 5;
    private static final int CAMPSITE_ID = 6;
    private static final int ADDRESS = 7;
    private static final int ADDRESS_ID = 8;
    private static final int CITY = 9;
    private static final int CITY_ID = 10;
    private static final int STATE = 11;
    private static final int STATE_ID = 12;
    private static final int RESERVATIONS = 13;
    private static final int RESERVATIONS_ID = 14;


    //UriMatcher Initialization
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(AUTHORITY, ACCOUNT_PATH, ACCOUNT);
        uriMatcher.addURI(AUTHORITY, ACCOUNT_PATH + "/#", ACCOUNT_ID);
        uriMatcher.addURI(AUTHORITY, CAMPGROUND_PATH, CAMPGROUND);
        uriMatcher.addURI(AUTHORITY, CAMPGROUND_PATH + "/#", CAMPGROUND_ID);
        uriMatcher.addURI(AUTHORITY, CAMPSITE_PATH, CAMPSITE);
        uriMatcher.addURI(AUTHORITY, CAMPSITE_PATH + "/#", CAMPSITE_ID);
        uriMatcher.addURI(AUTHORITY, ADDRESS_PATH, ADDRESS);
        uriMatcher.addURI(AUTHORITY, ADDRESS_PATH + "/#", ADDRESS_ID);
        uriMatcher.addURI(AUTHORITY, CITY_PATH, CITY);
        uriMatcher.addURI(AUTHORITY, CITY_PATH + "/#", CITY_ID);
        uriMatcher.addURI(AUTHORITY, STATE_PATH, STATE);
        uriMatcher.addURI(AUTHORITY, STATE_PATH + "/#", STATE_ID);
        uriMatcher.addURI(AUTHORITY, RESERVATIONS_PATH, RESERVATIONS);
        uriMatcher.addURI(AUTHORITY, RESERVATIONS_PATH + "/#", RESERVATIONS_ID);
    }


    /**
     * _______________________________________________________________________
     *                             METHODS
     * -----------------------------------------------------------------------
     */


    @Override
    public boolean onCreate() {
        //Create database for app
        DBOpenHelper helper = new DBOpenHelper(getContext());
        db = helper.getWritableDatabase();
        return true;
    }

    /**
     * SQL QUERY
     * Override of default SQL query
     * Switch statement controls which query is executed in which table of SQL Db
     * @param uri uri for param table for SQL query
     * @param projection gets columns from table for SQL query
     * @param selection param to select specific item(s) from DB in SQL query
     * @param selectionArgs param for selection args for SQL query
     * @param sortOrder sort order for SQL query
     * @return results of SQL query from DB
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch(uriMatcher.match(uri)){
            case ACCOUNT:
                return db.query(DBOpenHelper.TABLE_ACCOUNT, DBOpenHelper.ACCOUNT_COLS, selection,
                        null, null, null, DBOpenHelper.ACCOUNT_TABLE_ID + " ASC");
            case CAMPGROUND:
                return db.query(DBOpenHelper.TABLE_CAMPGROUND, DBOpenHelper.CAMPGROUND_COLS, selection,
                        null, null, null, DBOpenHelper.CAMPGROUND_TABLE_ID + " ASC");
            case CAMPSITE:
                return db.query(DBOpenHelper.TABLE_CAMPSITE, DBOpenHelper.CAMPSITE_COLS, selection,
                        null, null, null, DBOpenHelper.CAMPSITE_TABLE_ID + " ASC" );
            case ADDRESS:
                return db.query(DBOpenHelper.TABLE_ADDRESS, DBOpenHelper.ADDRESS_COLS, selection,
                        null, null, null, DBOpenHelper.ADDRESS_TABLE_ID + " ASC");
            case CITY:
                return db.query(DBOpenHelper.TABLE_CITY, DBOpenHelper.CITY_COLS, selection, null,
                        null, null, DBOpenHelper.CITY_TABLE_ID);
            case STATE:
                return db.query(DBOpenHelper.TABLE_STATE, DBOpenHelper.STATE_COLS, selection, null,
                        null, null, DBOpenHelper.STATE_TABLE_ID);
            case RESERVATIONS:
                return db.query(DBOpenHelper.TABLE_RESERVATIONS, DBOpenHelper.RESERVATIONS_COLS, selection, null,
                        null, null, DBOpenHelper.RESERVATIONS_TABLE_ID);
            default:
                throw new IllegalArgumentException("Unsupported uri: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * SQL INSERT
     * Override of default SQL insert statement
     * Switch statement controls which table of SQL Db the insert statement is executed in
     * @param uri uri for param table for SQL insert statement
     * @param values values to be inserted into insert statement
     * @return uri for inserted record
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        switch (uriMatcher.match(uri)){
            case ACCOUNT:
                id =  db.insert(DBOpenHelper.TABLE_ACCOUNT, null, values);
                return uri.parse(ACCOUNT_PATH + "/" + id);
            case CAMPGROUND:
                id = db.insert(DBOpenHelper.TABLE_CAMPGROUND, null, values);
                return uri.parse(CAMPGROUND_PATH + "/" + id);
            case CAMPSITE:
                id = db.insert(DBOpenHelper.TABLE_CAMPSITE, null, values);
                return uri.parse(CAMPSITE_PATH + "/" + id);
            case ADDRESS:
                id = db.insert(DBOpenHelper.TABLE_ADDRESS, null, values);
                return  uri.parse(ADDRESS_PATH + "/" + id);
            case CITY:
                id = db.insert(DBOpenHelper.TABLE_CITY, null, values);
                return  uri.parse(CITY_PATH + "/" + id);
            case STATE:
                id = db.insert(DBOpenHelper.TABLE_STATE, null, values);
                return  uri.parse(STATE_PATH + "/" + id);
            case RESERVATIONS:
                id = db.insert(DBOpenHelper.TABLE_RESERVATIONS, null, values);
                return  uri.parse(RESERVATIONS_PATH + "/" + id);
            default:
                throw new IllegalArgumentException("Default Case - Unsupported uri: " + uri);
        }
    }

    /**
     * SQL DELETE
     * Override of default SQL delete
     * Switch statement which table of SQL Db delete statement is executed in
     * @param uri uri for param table for SQL delete statement
     * @param selection param to select specific item(s) from DB in SQL delete statement
     * @param selectionArgs aram for selection args for SQL delete statement
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch(uriMatcher.match(uri)){
            case ACCOUNT:
                return db.delete(DBOpenHelper.TABLE_ACCOUNT, selection, selectionArgs);
            case CAMPGROUND:
                return db.delete(DBOpenHelper.TABLE_CAMPGROUND, selection, selectionArgs);
            case CAMPSITE:
                return db.delete(DBOpenHelper.TABLE_CAMPSITE, selection, selectionArgs);
            case ADDRESS:
                return db.delete(DBOpenHelper.TABLE_ADDRESS, selection, selectionArgs);
            case CITY:
                return db.delete(DBOpenHelper.TABLE_CITY, selection, selectionArgs);
            case STATE:
                return db.delete(DBOpenHelper.TABLE_STATE, selection, selectionArgs);
            case RESERVATIONS:
                return db.delete(DBOpenHelper.TABLE_RESERVATIONS, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Unsupported uri: " + uri);
        }
    }

    /**
     * SQL UPDATE
     * Override of default SQL Update statement
     * Switch statement which table of SQL Db Update statement is executed in
     * @param uri uri for param table for SQL Update statement
     * @param values values to be Updated
     * @param selection param to select specific item(s) from DB in Update statement
     * @param selectionArgs param for selection args for update statement
     * @return uri to updated record
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch(uriMatcher.match(uri)){
            case ACCOUNT:
                return db.update(DBOpenHelper.TABLE_ACCOUNT, values, selection, selectionArgs);
            case CAMPGROUND:
                return db.update(DBOpenHelper.TABLE_CAMPGROUND, values, selection, selectionArgs);
            case CAMPSITE:
                return db.update(DBOpenHelper.TABLE_CAMPSITE, values, selection, selectionArgs);
            case ADDRESS:
                return db.update(DBOpenHelper.TABLE_ADDRESS, values, selection, selectionArgs);
            case CITY:
                return db.update(DBOpenHelper.TABLE_CITY, values, selection, selectionArgs);
            case STATE:
                return db.update(DBOpenHelper.TABLE_STATE, values, selection, selectionArgs);
            case RESERVATIONS:
                return db.update(DBOpenHelper.TABLE_RESERVATIONS, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Unsupported uri: " + uri);
        }
    }
}
