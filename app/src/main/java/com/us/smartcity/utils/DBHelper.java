package com.us.smartcity.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.us.smartcity.R;

/**
 * Created by Upen on 4/7/17 in SmartCity.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 33;
    private Context context;

    public DBHelper(Context context) {
        super(context, Config.DB_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase execute() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransactionNonExclusive();
        return db;
    }

    public void execute(String stmt) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Log.print(this.getClass() + " :: execute() :: ", stmt);
            db.execSQL(stmt);
        } catch (Exception e) {
            Log.error(this.getClass() + " :: execute() :: ", e);
        } finally {
            db.close();
            db = null;
        }
    }

    public Cursor query(String stmt) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Log.print(this.getClass() + " :: query() :: ", stmt);
            cursor = db.rawQuery(stmt, null);
            cursor.moveToPosition(-1);
        } catch (Exception e) {
            Log.error(this.getClass() + " :: query() :: ", e);
        } finally {
            db.close();
            db = null;
        }

        return cursor;
    }

    public void upgrade(int level) {
        Log.print("============ level ========== " + level);
        switch (level) {
            case 0:
                doUpdate0();
                break;
        }
    }

    public void doUpdate0() {
        this.execute(Utils.getResourceString(context, R.string.createZone));
        this.execute(Utils.getResourceString(context, R.string.createArea));
        this.execute(Utils.getResourceString(context, R.string.createTouristPlace));
        this.execute("INSERT INTO zone VALUES (1,'Central')");
        this.execute("INSERT INTO zone VALUES (2,'East')");
        this.execute("INSERT INTO zone VALUES (3,'North')");
        this.execute("INSERT INTO zone VALUES (4,'South')");
        this.execute("INSERT INTO zone VALUES (5,'West')");
        this.execute("INSERT INTO zone VALUES (6,'New West')");

        this.execute("INSERT INTO area VALUES (1,'Girdharnagar',1)");
        this.execute("INSERT INTO area VALUES (2,'Madhupura',1)");
        this.execute("INSERT INTO area VALUES (3,'Dudheshvar',1)");
        this.execute("INSERT INTO area VALUES (4,'Shahpur',1)");
        this.execute("INSERT INTO area VALUES (5,'Dariapur',1)");
        this.execute("INSERT INTO area VALUES (6,'Kalupur',1)");
        this.execute("INSERT INTO area VALUES (7,'Raikhad',1)");
        this.execute("INSERT INTO area VALUES (8,'Jamalpur',1)");
        this.execute("INSERT INTO area VALUES (9,'Khadia',1)");

        this.execute("INSERT INTO area VALUES (10,'Nikol',2)");
        this.execute("INSERT INTO area VALUES (11,'Viratnagar',2)");
        this.execute("INSERT INTO area VALUES (12,'Bapunagar',2)");
        this.execute("INSERT INTO area VALUES (13,'Gomtipur',2)");
        this.execute("INSERT INTO area VALUES (14,'Rakhial',2)");
        this.execute("INSERT INTO area VALUES (15,'Rajpur',2)");
        this.execute("INSERT INTO area VALUES (16,'Arbudanagr',2)");
        this.execute("INSERT INTO area VALUES (17,'Odhav',2)");
        this.execute("INSERT INTO area VALUES (18,'Mahavirnagar',2)");
        this.execute("INSERT INTO area VALUES (19,'Khadia',2)");
        this.execute("INSERT INTO area VALUES (20,'Bhaipura - Hatkeshwar',2)");
        this.execute("INSERT INTO area VALUES (21,'Ramol - Hathijan',2)");

        this.execute("INSERT INTO area VALUES (22,'Sardarnagar',3)");
        this.execute("INSERT INTO area VALUES (23,'Nobalnagar',3)");
        this.execute("INSERT INTO area VALUES (24,'Naroda',3)");
        this.execute("INSERT INTO area VALUES (25,'Kubernagar',3)");
        this.execute("INSERT INTO area VALUES (26,'Saijpur-Bogha',3)");
        this.execute("INSERT INTO area VALUES (27,'Meghaninagar',3)");
        this.execute("INSERT INTO area VALUES (28,'Asarva',3)");
        this.execute("INSERT INTO area VALUES (29,'Naroda Road',3)");
        this.execute("INSERT INTO area VALUES (30,'India Colony',3)");
        this.execute("INSERT INTO area VALUES (31,'Krushnanagar',3)");
        this.execute("INSERT INTO area VALUES (32,'Thakkarbapanagar',3)");
        this.execute("INSERT INTO area VALUES (33,'Saraspur',3)");
        this.execute("INSERT INTO area VALUES (34,'Nava Naroda',3)");

        this.execute("INSERT INTO area VALUES (35,'Behrampura',4)");
        this.execute("INSERT INTO area VALUES (36,'Kankariya',4)");
        this.execute("INSERT INTO area VALUES (37,'Indrapuri',4)");
        this.execute("INSERT INTO area VALUES (38,'Khokhra',4)");
        this.execute("INSERT INTO area VALUES (39,'Maninagar',4)");
        this.execute("INSERT INTO area VALUES (40,'Danilimda',4)");
        this.execute("INSERT INTO area VALUES (41,'Lambha',4)");
        this.execute("INSERT INTO area VALUES (42,'Isanpur',4)");
        this.execute("INSERT INTO area VALUES (43,'Ghodasar',4)");
        this.execute("INSERT INTO area VALUES (44,'Vatva',4)");

        this.execute("INSERT INTO area VALUES (45,'Chandkheda-Motera',5)");
        this.execute("INSERT INTO area VALUES (46,'Sabarmati',5)");
        this.execute("INSERT INTO area VALUES (47,'Naranpura',5)");
        this.execute("INSERT INTO area VALUES (48,'Nava Vadaj',5)");
        this.execute("INSERT INTO area VALUES (49,'Juna Vadaj',5)");
        this.execute("INSERT INTO area VALUES (50,'S.P Stadium',5)");
        this.execute("INSERT INTO area VALUES (51,'Aambawadi',5)");
        this.execute("INSERT INTO area VALUES (52,'Navrangpura',5)");
        this.execute("INSERT INTO area VALUES (53,'Paldi',5)");
        this.execute("INSERT INTO area VALUES (54,'Vasna',5)");
        this.execute("INSERT INTO area VALUES (55,'Motera',5)");

        this.execute("INSERT INTO area VALUES (56,'Gota',6)");
        this.execute("INSERT INTO area VALUES (57,'Chandlodiya',6)");
        this.execute("INSERT INTO area VALUES (58,'Kali',6)");
        this.execute("INSERT INTO area VALUES (59,'Ranip',6)");
        this.execute("INSERT INTO area VALUES (60,'Ghatlodiya',6)");
        this.execute("INSERT INTO area VALUES (61,'Thaltej',6)");
        this.execute("INSERT INTO area VALUES (62,'Bodakdev',6)");
        this.execute("INSERT INTO area VALUES (63,'Sarkhej',6)");
        this.execute("INSERT INTO area VALUES (64,'Jodhpur',6)");
        this.execute("INSERT INTO area VALUES (65,'Vejalpur',6)");
        this.execute("INSERT INTO area VALUES (66,'Makarba',6)");
        this.execute("INSERT INTO area VALUES (67,'Memnagar',6)");
        this.execute("INSERT INTO area VALUES (68,'Vastrapur',6)");
    }

}
