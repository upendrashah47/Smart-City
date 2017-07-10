package com.us.smartcity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bhaskar on 29-01-2016.
*/

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


public class DatabaseHelper extends SQLiteOpenHelper {


    static String TAG = DatabaseHelper.class.getSimpleName();
    private static String DB_PATH = "";
    private static final String DB_NAME = "SmartCity.db";
    static int version = 1;
    private static DatabaseHelper mDBConnection;
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    Cursor cursor;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

        this.myContext = context;
        DB_PATH = "/data/data/" + context.getApplicationContext().getPackageName() + "/databases/";
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        copyDataBase(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            db.execSQL("CREATE TABLE \"login_activity\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                    "" + " \"MobileNO\" VARCHAR NOT NULL ," + " \"password\" VARCHAR NOT NULL");
        }
    }

    public synchronized void openDataBase() throws SQLException {
        if (myDataBase != null) {
            myDataBase.close();
        }
        myDataBase = mDBConnection.getWritableDatabase();

    }

   @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {

        try {
            super.getWritableDatabase();
        } catch (Exception e) {
        }

        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);
        myDataBase.setVersion(version);
        return myDataBase;
    }

    public void copyDataBase(SQLiteDatabase db) {
        try {

            InputStream myInput = myContext.getAssets().open(DB_NAME);
            File file = new File(DB_PATH + DB_NAME);
            file.delete();


            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            myOutput.flush();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized DatabaseHelper getDBAdapterInstance(Context context) {
        if (mDBConnection == null) {
            mDBConnection = new DatabaseHelper(context,DB_NAME, null, version);
        }
        return mDBConnection;
    }

    public void insertData(String Firstname, String Emailid, String password,
                           String conf_password, String Birthdate, String mobileno) {
        ContentValues values = new ContentValues();
        values.put("FirstName", Firstname);

        values.put("Email_id", Emailid);
        values.put("Password", password);
        values.put("Conf_Password", conf_password);
        values.put("Birthdate", Birthdate);
        values.put("MobileNO", mobileno);

        long rowid = myDataBase.insert("SignupActivity", null, values);
        Log.d("DBoperation", "row inserted........." + rowid);
    }

/*
        public void insertUser1(String username, String password) {
            ContentValues values = new ContentValues();
            values.put("ROLLNO", username);
            values.put("UName", password);

            long rowid = myDataBase.insert("Demo", null, values);
            Log.d("DBoperation", "row inserted........." + rowid);
        }
*/

    public long UpdateUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        String wheres = "id=?";
        String IDS = String.valueOf(3);
        String[] whereArgs = {IDS.toString()};
        long rowId = myDataBase.update("SignupActivity", values, wheres, whereArgs);
        Log.d("DBoperation", "row updated........." + rowId);

        return rowId;
    }

    public long delete() {
        String wheres = "id=?";
        String IDS = String.valueOf(1);
        String[] whereArgs = {IDS.toString()};
        long rawId = myDataBase.delete("SignupActivity", wheres, whereArgs);
        return rawId;
    }

    public void selectUser() {
        String name = null;

        Cursor cursor = myDataBase.rawQuery("Select * from SignupActivity", null);

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", "" + cursor.getInt(0));
                Log.d("UserName", "" + cursor.getInt(1));
                Log.d("Password", "" + cursor.getInt(2));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }



   /* public String searchPass(String mobno) {
        myDataBase=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME + " WHERE MobileNo=?";
        Cursor cursor=myDataBase.rawQuery(query,null);
        String mno,pwd;
        pwd="not found";
        if(cursor.moveToFirst())
        {
            do {
               mno =cursor.getString(0);


                if(mno.equals(mobno))
                {
                    pwd=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pwd;
    }*/
}