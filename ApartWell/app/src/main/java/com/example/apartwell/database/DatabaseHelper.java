package com.example.apartwell.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.apartwell.models.Booking;
import com.example.apartwell.models.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;

    private static final  String DATABASE_NAME = "AMS";
    private static final int DATABASE_VERSION = 3;

    // Table Names
    private static final String TABLE_User = "User";
    private static final String TABLE_Booking = "Booking";

    // User Table Columns
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_SECONDNAME = "secondname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILENO = "mobile";

    // Booking Table Columns
    private static final String KEY_BOOKING_ID = "booking_id";
    private static final String KEY_USER_ID_BOOKING = KEY_USER_ID;
    private static final String KEY_FACILITY = "facility";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_DURATION = "duration";



    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_User +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_USER_NAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_FIRSTNAME + " TEXT," +
                KEY_SECONDNAME + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_MOBILENO + " TEXT" +
                ")";

        String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_Booking +
                "(" +
                KEY_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_USER_ID_BOOKING + " INTEGER," +
                KEY_FACILITY + " TEXT," +
                KEY_STATUS + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT," +
                KEY_DURATION + " TEXT," +
                "FOREIGN KEY ("+KEY_USER_ID_BOOKING+") REFERENCES " + TABLE_User+"("+KEY_USER_ID+")"+
                ")";

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
            onCreate(sqLiteDatabase);
        }
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public long addUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        long userId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_NAME, user.getUsername());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_FIRSTNAME, user.getFirstName());
            values.put(KEY_SECONDNAME, user.getSecondName());
            values.put(KEY_EMAIL, user.getEmail());
            values.put(KEY_MOBILENO, user.getMobileNo());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_User, values, KEY_USER_NAME + "= ?", new String[]{user.getUsername()});

            // Check if update succeeded
            if (rows == 1) {
                //Cursor cursor = db.rawQuery(UserSelectQuery, new String[]{String.valueOf(user.userName)});
                Cursor cursor = db.query(TABLE_User, new String[]{KEY_USER_ID}, KEY_USER_NAME + "= ?", new String[]{user.getUsername()}, null, null , null);
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                userId = db.insertOrThrow(TABLE_User, null, values);
                Log.d("add","userID = " + userId);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return userId;

    }

    public List<User> getAllUser() {
        List<User> user_list = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN User
        // ON POSTS.KEY_POST_USER_ID_FK = User.KEY_USER_ID

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_User, new String[]{KEY_USER_ID, KEY_USER_NAME, KEY_PASSWORD, KEY_FIRSTNAME, KEY_SECONDNAME, KEY_EMAIL, KEY_MOBILENO},null,null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User newUser = new User();
                    newUser.setUser_id(""+ cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                    newUser.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                    newUser.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                    newUser.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    newUser.setSecondName(cursor.getString(cursor.getColumnIndex(KEY_SECONDNAME)));
                    newUser.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                    newUser.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_MOBILENO)));


                    user_list.add(newUser);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return user_list;
    }

    public long addBooking(Booking booking){
        SQLiteDatabase db = getWritableDatabase();

        long bookingID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_ID_BOOKING, booking.getUserID());
            values.put(KEY_FACILITY, booking.getFacility());
            values.put(KEY_STATUS, booking.getStatus());
            values.put(KEY_DATE, booking.getDate());
            values.put(KEY_TIME, booking.getTime());
            values.put(KEY_DURATION, booking.getDuration());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique



            bookingID = db.insertOrThrow(TABLE_Booking, null, values);
            Log.d("add","bookingID = " + bookingID);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return bookingID;

    }

    public List<User> getUser(String username){
        List<User> user_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_User, new String[]{KEY_USER_ID, KEY_USER_NAME, KEY_PASSWORD, KEY_FIRSTNAME, KEY_SECONDNAME, KEY_EMAIL, KEY_MOBILENO},KEY_USER_NAME + "= ?",new String[]{username}, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User newUser = new User();
                    newUser.setUser_id(""+ cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                    newUser.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                    newUser.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                    newUser.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    newUser.setSecondName(cursor.getString(cursor.getColumnIndex(KEY_SECONDNAME)));
                    newUser.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                    newUser.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_MOBILENO)));


                    user_list.add(newUser);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return user_list;

    }

    public List<Booking> getBookings(String userID){
        List<Booking> bookingList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_Booking, null,KEY_USER_ID_BOOKING + "= ?",new String[]{userID}, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Booking newBooking = new Booking();
                    newBooking.setBookingID(""+ cursor.getInt(cursor.getColumnIndex(KEY_BOOKING_ID)));
                    newBooking.setUserID(cursor.getString(cursor.getColumnIndex(KEY_USER_ID_BOOKING)));
                    newBooking.setFacility(cursor.getString(cursor.getColumnIndex(KEY_FACILITY)));
                    newBooking.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
                    newBooking.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                    newBooking.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                    newBooking.setDuration(cursor.getString(cursor.getColumnIndex(KEY_DURATION)));

                    bookingList.add(newBooking);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return bookingList;

    }
}

