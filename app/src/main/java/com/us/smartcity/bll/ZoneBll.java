package com.us.smartcity.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.us.smartcity.bean.MenuBean;
import com.us.smartcity.bean.ZoneBean;
import com.us.smartcity.utils.DBHelper;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Upen on 14/7/17 in SmartCity.
 */

public class ZoneBll {

    public Context context;

    public void ZoneBll(Context context) {
        this.context = context;
    }

    public void verify(ZoneBean zoneBean) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;

        try {
            sql = "SELECT id FROM zone WHERE id =" + zoneBean.id;
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            if (cursor != null && cursor.getCount() == 0) {
                this.insert(zoneBean);
            } else {
                this.update(zoneBean);
            }
        } catch (Exception e) {
            Log.error(this.getClass() + " :: verify()", e);
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            if (dbHelper != null)
                dbHelper.close();

            Utils.freeMemory();
        }
    }

    public void insert(ZoneBean zoneBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO zone (id, name) VALUES (?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);
            Log.print(this.getClass() + " :: insert() :: ", sql);

            stmt.bindString(1, "" + zoneBean.id);
            stmt.bindString(2, "" + zoneBean.name);

            stmt.execute();
            stmt.clearBindings();

            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            Log.error(this.getClass() + " :: insert()", e);
        } finally {
            // release
            if (db != null)
                db.close();
            Utils.freeMemory();
        }
    }

    public void insert(ArrayList<ZoneBean> zoneBeanArrayList) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO zone (id, name) VALUES (?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

            Log.print(this.getClass() + " :: insert() :: ", sql);
            ZoneBean zoneBean;
            for (int i = 0; i < zoneBeanArrayList.size(); i++) {
                zoneBean = zoneBeanArrayList.get(i);
                stmt.clearBindings();
                stmt.bindString(1, "" + zoneBean.id);
                stmt.bindString(2, "" + zoneBean.name);

                stmt.execute();
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            Log.error(this.getClass() + " :: insert()", e);
        } finally {
            // release
            if (db != null)
                db.close();
            Utils.freeMemory();
        }
    }

    public void update(ZoneBean zoneBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "UPDATE zone SET name=? WHERE id=?";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

            stmt.bindString(1, "" + zoneBean.name);
            stmt.bindString(2, "" + zoneBean.id);

            stmt.execute();
            stmt.clearBindings();

            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            Log.error(this.getClass() + " :: update()", e);
        } finally {
            // release
            if (db != null)
                db.close();
            Utils.freeMemory();
        }
    }

    public void deleteZone(int id) {
        DBHelper dbHelper = null;
        String sql;

        try {
            dbHelper = new DBHelper(this.context);
            sql = "DELETE FROM zone WHERE id = " + id;
            dbHelper.execute(sql);
        } catch (Exception e) {
            Log.error(this.getClass() + " :: deleteQuestion()", e);
            Log.print(this.getClass() + " :: deleteQuestion()", " " + e);
        } finally {
            if (dbHelper != null)
                dbHelper.close();
            Utils.freeMemory();
        }
    }

    public ArrayList<ZoneBean> getZoneList(int Id) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;
        ZoneBean zoneBean;
        ArrayList<ZoneBean> zoneBeanArrayList = null;

        try {
            sql = "SELECT id, name FROM zone";
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            zoneBeanArrayList = new ArrayList<>();
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    zoneBean = new ZoneBean();
                    zoneBean.id = cursor.getInt(0);
                    zoneBean.name = cursor.getString(1);
                    zoneBeanArrayList.add(zoneBean);
                }
            }
        } catch (Exception e) {
            Log.error(this.getClass() + " :: getQuestionList()", e);
            Log.print(this.getClass() + " :: getQuestionList()", " " + e);
        } finally {
            // release
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            if (dbHelper != null)
                dbHelper.close();

            Utils.freeMemory();
        }
        return zoneBeanArrayList;
    }
}
