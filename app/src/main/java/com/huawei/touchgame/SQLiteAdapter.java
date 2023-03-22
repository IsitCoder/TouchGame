package com.huawei.touchgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAdapter {

    public static final String MYDATABASE_NAME = "MY_DATABASE";
    public static final String MYDATABASE_TABLE = "Score_Table";
    public static final int MYDATABASE_VERSION = 3;
    public static final String KEY_CONTENT = "Name";
    public static final String KEY_CONTENT1 = "Score";



    private static final String SCRIPT_CREATE_DATABASE = "create table " + MYDATABASE_TABLE + " (" + KEY_CONTENT + " text not null, "+ KEY_CONTENT1 +" int);";

    private static final String SCRIPT_CREATE_TABLE2 = "create table "
            + MYDATABASE_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CONTENT + " text not null,"
            + KEY_CONTENT1 + " int);";


    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SQLiteAdapter(Context c) {
        context = c;
    }

    public SQLiteAdapter openToRead() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }
    public SQLiteAdapter openToWrite() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        sqLiteHelper.close();
    }
    public long insert(String content,int content2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT, content);
        contentValues.put(KEY_CONTENT1, content2);
        return sqLiteDatabase.insert(MYDATABASE_TABLE, null,
                contentValues);
    }
    public int deleteAll() {
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }


    //SELECT * FROM MY_TABLE2 WHERE PROGRAMME="CS" OR PROGRAMEE="IA" Order by NAME DESC

    public String queryByScore() {
        String[] columns = new String[] { KEY_CONTENT,KEY_CONTENT1 };
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, null, null, null, null, KEY_CONTENT1+" DESC");
        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT);
        int index_CONTENT1 = cursor.getColumnIndex(KEY_CONTENT1);
        for (cursor.moveToFirst(); !(cursor.isAfterLast());
             cursor.moveToNext()) {
            result = result + cursor.getString(index_CONTENT) + "|"
                    + cursor.getString(index_CONTENT1) +"\n";}
        return result;
    }



    public class SQLiteHelper extends SQLiteOpenHelper {
        public SQLiteHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SCRIPT_CREATE_TABLE2);
        }
    }

}
