package com.us.smartcity.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.us.smartcity.R;
import com.us.smartcity.bean.MenuBean;
import com.us.smartcity.utils.DBHelper;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Krunal on 18/03/17.
 */
public class MeetingQuestionBll {
    public Context context;

    public MeetingQuestionBll(Context context) {
        this.context = context;
    }

    public void verify(MenuBean meetingQuestionBean) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;

        try {
            sql = "SELECT id FROM meetingquestion WHERE id =" + meetingQuestionBean.id;
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            if (cursor != null && cursor.getCount() == 0) {
                this.insert(meetingQuestionBean);
            } else {
                this.update(meetingQuestionBean);
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

    public void insert(MenuBean meetingQuestionBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO meetingquestion (id, mid, qid, seq_no, que, type, yes_qn_id, no_qn_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);
            Log.print(this.getClass() + " :: insert() :: ", sql);

//            stmt.bindString(1, "" + meetingQuestionBean.id);
//            stmt.bindString(2, "" + meetingQuestionBean.mId);
//            stmt.bindString(3, "" + meetingQuestionBean.qId);
//            stmt.bindString(4, "" + meetingQuestionBean.seqNo);
//            stmt.bindString(5, meetingQuestionBean.que);
//            stmt.bindString(6, "" + meetingQuestionBean.type);
//            stmt.bindString(7, "" + meetingQuestionBean.yesQnId);
//            stmt.bindString(8, "" + meetingQuestionBean.noQnId);

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

    public void insert(ArrayList<MenuBean> meetingList) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "INSERT INTO meetingquestion (id, mid, qid, seq_no, que, type, yes_qn_id, no_qn_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

            Log.print(this.getClass() + " :: insert() :: ", sql);
            MenuBean meetingQuestionBean;
            for (int i = 0; i < meetingList.size(); i++) {
                meetingQuestionBean = meetingList.get(i);
                stmt.clearBindings();
//                stmt.bindString(1, "" + meetingQuestionBean.id);
//                stmt.bindString(2, "" + meetingQuestionBean.mId);
//                stmt.bindString(3, "" + meetingQuestionBean.qId);
//                stmt.bindString(4, "" + meetingQuestionBean.seqNo);
//                stmt.bindString(5, meetingQuestionBean.que);
//                stmt.bindString(6, "" + meetingQuestionBean.type);
//                stmt.bindString(7, "" + meetingQuestionBean.yesQnId);
//                stmt.bindString(8, "" + meetingQuestionBean.noQnId);

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

    public void update(MenuBean meetingQuestionBean) {
        String sql;
        SQLiteDatabase db = null;
        SQLiteStatement stmt;

        try {
            sql = "UPDATE meetingquestion SET mid=?, qid=?, seq_no=?, que=?, type=?, yes_qn_id=?, no_qn_id=? WHERE id=?";
            db = new DBHelper(context).execute();
            stmt = db.compileStatement(sql);

//            stmt.bindString(1, "" + meetingQuestionBean.mId);
//            stmt.bindString(2, "" + meetingQuestionBean.qId);
//            stmt.bindString(3, "" + meetingQuestionBean.seqNo);
//            stmt.bindString(4, meetingQuestionBean.que);
//            stmt.bindString(5, "" + meetingQuestionBean.type);
//            stmt.bindString(6, "" + meetingQuestionBean.yesQnId);
//            stmt.bindString(7, "" + meetingQuestionBean.noQnId);
//            stmt.bindString(8, "" + meetingQuestionBean.id);

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

    public void deleteMeetingQuestion(int id) {
        DBHelper dbHelper = null;
        String sql;

        try {
            dbHelper = new DBHelper(this.context);
            sql = "DELETE FROM meetingquestion WHERE mid = " + id;
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

    public ArrayList<MenuBean> getQuestionList(int meetingId) {
        DBHelper dbHelper = null;
        String sql;
        Cursor cursor = null;
        MenuBean meetingQuestionBean;
        ArrayList<MenuBean> questionList = null;

        try {
            sql = "SELECT id, mid, qid, seq_no, que, type, yes_qn_id, no_qn_id FROM meetingquestion WHERE mid=" + meetingId + " ORDER BY seq_no";
            dbHelper = new DBHelper(this.context);
            cursor = dbHelper.query(sql);

            questionList = new ArrayList<>();
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
//                    meetingQuestionBean = new MeetingQuestionBean();
//                    meetingQuestionBean.id = cursor.getInt(0);
//                    meetingQuestionBean.mId = cursor.getInt(1);
//                    meetingQuestionBean.qId = cursor.getInt(2);
//                    meetingQuestionBean.seqNo = cursor.getInt(3);
//                    meetingQuestionBean.que = cursor.getString(4);
//                    meetingQuestionBean.type = cursor.getInt(5);
//                    meetingQuestionBean.yesQnId = cursor.getInt(6);
//                    meetingQuestionBean.noQnId = cursor.getInt(7);
//                    questionList.add(meetingQuestionBean);
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
        return questionList;
    }
}