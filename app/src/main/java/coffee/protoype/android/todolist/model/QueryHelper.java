package coffee.protoype.android.todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Noodle on 29/05/2017.
 */

public class QueryHelper {

    public void addTaskToTable(Context context, String taskName, String taskColor) {
        TaskDataBase dbHelper = new TaskDataBase(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(TaskContract.TaskEntry.COLUMN_TASK_Title, taskName);

        contentValues.put(TaskContract.TaskEntry.COLUMN_TASK_COLOR, taskColor);

        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, contentValues);

        db.close();

    }


    public ArrayList<Task> populateTaskAdapter(Context context) {
        TaskDataBase dbHelper = new TaskDataBase(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TaskEntry.TABLE_NAME + " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_COMPLETION + " = " + "0", null);
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.clear();


        if (cursor.moveToFirst()) {


            do {

                String taskName = cursor.getString(1);

                String taskColor = cursor.getString(2);


                Task task = new Task(taskName, Integer.parseInt(taskColor));

                task.setTaskName(taskName);
                task.setTaskColor(Integer.parseInt(taskColor));

                taskList.add(task);

            } while (cursor.moveToNext());


            cursor.close();

        }

        db.close();
        return taskList;
    }


    public ArrayList<Task> populateCompeltedTaskAdapter(Context context) {
        TaskDataBase dbHelper = new TaskDataBase(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TaskEntry.TABLE_NAME + " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_COMPLETION + " = " + "1", null);
        ArrayList<Task> completedTask = new ArrayList<Task>();
        completedTask.clear();


        if (cursor.moveToFirst()) {


            do {

                String taskName = cursor.getString(1);

                String taskColor = cursor.getString(2);


                Task task = new Task(taskName, Integer.parseInt(taskColor));

                task.setTaskName(taskName);
                task.setTaskColor(Integer.parseInt(taskColor));

                completedTask.add(task);

            } while (cursor.moveToNext());


            cursor.close();

        }

        db.close();
        return completedTask;
    }
    public void updateCurrentTaskToComplete(Context context,String name) {
        TaskDataBase dbHelper = new TaskDataBase(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TaskEntry.TABLE_NAME + " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_Title + " = " + "'"+name+"'", null);
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.clear();


        if (cursor.moveToFirst()) {


            do {

                String taskID = cursor.getString(0);

                String query = ("UPDATE " + TaskContract.TaskEntry.TABLE_NAME + " SET " + TaskContract.TaskEntry.COLUMN_TASK_COMPLETION + " = " + "1"
                        + " WHERE "+ TaskContract.TaskEntry.TASK_ID+" = "+"'"+taskID+"'");

                db.execSQL(query);


            } while (cursor.moveToNext());


            cursor.close();

        }

        db.close();

    }

}
