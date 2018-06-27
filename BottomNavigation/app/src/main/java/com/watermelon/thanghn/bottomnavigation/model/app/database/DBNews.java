package com.watermelon.thanghn.bottomnavigation.model.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 2/22/2018.
 */

public class DBNews extends SQLiteOpenHelper {
    private String TAG = "DBNews";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Newspaper.db";
    public static final String TABLE_NAME = "Newspaper";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DECRIPTION = "decription";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_CONTENT = "content";
    public static final String COLUMN_NAME_IMAGE = "image";
    public static final String COLUMN_NAME_AUTHOR = "author";
    Context context;

    public DBNews(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public DBNews(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBNews(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_NEW_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_LINK + " TEXT PRIMARY KEY,"
                + COLUMN_NAME_TITLE + " TEXT,"
                + COLUMN_NAME_DECRIPTION + " TEXT,"
                + COLUMN_NAME_DATE + " TEXT,"
                + COLUMN_NAME_CONTENT + " NTEXT,"
                + COLUMN_NAME_IMAGE + " TEXT,"
                + COLUMN_NAME_AUTHOR + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_NEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //lay 1 du lieu
    public NewsOff getNewPaper(String link) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_LINK, COLUMN_NAME_TITLE, COLUMN_NAME_DECRIPTION, COLUMN_NAME_DATE, COLUMN_NAME_CONTENT, COLUMN_NAME_IMAGE, COLUMN_NAME_AUTHOR}, COLUMN_LINK + "=?",
                new String[]{link}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        NewsOff newPaper = new NewsOff(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return newPaper;
    }


    public List<NewsOff> getAllNewPaper() {
        List<NewsOff> newPaperList = new ArrayList<NewsOff>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK));
            String title = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE));
            String decription = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DECRIPTION));
            String date = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE));
            String content = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CONTENT));
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_IMAGE));
            String author = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_AUTHOR));
            newPaperList.add(new NewsOff(link, title, date, decription, content, image, author));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return newPaperList;
    }


    public boolean insertdb(String link, String title, String des, String date, String content, String img, String author) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_LINK, link);
        values.put(COLUMN_NAME_DECRIPTION, des);
        values.put(COLUMN_NAME_DATE, date);
        values.put(COLUMN_NAME_CONTENT, content);
        values.put(COLUMN_NAME_IMAGE, img);
        values.put(COLUMN_NAME_AUTHOR, author);
        if (db.insert(TABLE_NAME, null, values) == -1) {
            Log.d(TAG, "Lỗi lưu");
            db.close();
            return false;
        } else {
            Log.d(TAG, "Lưu thành công");
            db.close();
            return true;
        }
    }

    public void delete(NewsOff newPaper) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_LINK + " = ?", new String[]{String.valueOf(newPaper.getLink())});
        db.close();
    }
}
