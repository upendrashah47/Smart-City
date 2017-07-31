package com.us.smartcity.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.us.smartcity.bean.AreaBean;
import com.us.smartcity.utils.DBHelper;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Upen on 14/7/17 in SmartCity.
 */

public class AreaBll {

    public Context context;

    public void AreaBll(Context context) {
        this.context = context;
    }

    public void verify(AreaBean areaBean) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;

        try {
            sql = "SELECT id FROM area WHERE id =" + areaBean.id;
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            if (cursor != null && cursor.getCount() == 0) {
                this.insert(areaBean);
            } else {
                this.update(areaBean);
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

    public void insert(AreaBean areaBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO area (id, name, zone_id) VALUES (?, ?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);
            Log.print(this.getClass() + " :: insert() :: ", sql);

            stmt.bindString(1, "" + areaBean.id);
            stmt.bindString(2, "" + areaBean.name);
            stmt.bindString(3, "" + areaBean.zoneId);

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

    public void insert(ArrayList<AreaBean> areaBeanArrayList) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO zone (id, name, zone_id) VALUES (?, ?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

            Log.print(this.getClass() + " :: insert() :: ", sql);
            AreaBean areaBean;
            for (int i = 0; i < areaBeanArrayList.size(); i++) {
                areaBean = areaBeanArrayList.get(i);
                stmt.clearBindings();
                stmt.bindString(1, "" + areaBean.id);
                stmt.bindString(2, "" + areaBean.name);
                stmt.bindString(3, "" + areaBean.zoneId);

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

    public void update(AreaBean areaBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "UPDATE area SET name=? zone_id=? WHERE id=?";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

            stmt.bindString(1, "" + areaBean.name);
            stmt.bindString(2, "" + areaBean.zoneId);
            stmt.bindString(3, "" + areaBean.id);

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
            sql = "DELETE FROM area WHERE id = " + id;
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

    public ArrayList<AreaBean> getZoneList(int Id) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;
        AreaBean areaBean;
        ArrayList<AreaBean> areaBeanArrayList = null;

        try {
            sql = "SELECT id, name, zone_id FROM area";
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            areaBeanArrayList = new ArrayList<>();
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    areaBean = new AreaBean();
                    areaBean.id = cursor.getInt(0);
                    areaBean.name = cursor.getString(1);
                    areaBeanArrayList.add(areaBean);
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
        return areaBeanArrayList;
    }
}
