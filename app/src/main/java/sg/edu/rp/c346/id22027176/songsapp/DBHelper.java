package sg.edu.rp.c346.id22027176.songsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "songs.db";
    private static final String TABLE_SONGS = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT," +
                COLUMN_SINGERS + " TEXT," +
                COLUMN_YEAR + " INTEGER," +
                COLUMN_STARS + " INTEGER" + ")";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
        Log.i("info", createTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        // Create table(s) again
        onCreate(db);

    }

    public ArrayList<Songs> getSongs() {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};

        Cursor cursor = db.query(TABLE_SONGS, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                Integer year = Integer.valueOf(cursor.getString(3));
                Integer stars = Integer.valueOf(cursor.getString(4));
                Songs obj = new Songs(id, title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public void insertSong(String title, String sing, Integer year, Integer stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, sing);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        db.insert(TABLE_SONGS, null, values);
        // Close the database connection
        db.close();
    }

    public int updateSong(Songs song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGERS, song.getSingers());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_STARS, song.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(song.getId())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();
        return result;
    }

}