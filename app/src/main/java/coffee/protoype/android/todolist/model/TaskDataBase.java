package coffee.protoype.android.todolist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * created by Noodle on 29/05/2017.
 */

public class TaskDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 3;

    public TaskDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TASK_TABLE = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + "("
                + TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskContract.TaskEntry.COLUMN_TASK_Title + " TEXT NOT NULL, "
                + TaskContract.TaskEntry.COLUMN_TASK_COLOR  + " TEXT NOT NULL, "
                + TaskContract.TaskEntry.COLUMN_TASK_COMPLETION + " INTEGER DEFAULT 0);";

        Log.v("Creating task table", "task table");

        db.execSQL(SQL_CREATE_TASK_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DROP_TASK_TABLE;

        SQL_DROP_TASK_TABLE = "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;

        db.execSQL(SQL_DROP_TASK_TABLE);

        Log.v("Task table exists", "Dropping table");


    }
}
