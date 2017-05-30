package coffee.protoype.android.todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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


//        createToastWithText("row id of goal id" + newRowId);
        //Log cat used to show that a database insertion is occuring.

        db.close();

    }



    public ArrayList<Task> populateTaskAdapter(Context context) {
        TaskDataBase dbHelper = new TaskDataBase(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TaskEntry.TABLE_NAME, null);
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.clear();


        if (cursor.moveToFirst()) {


            do {

                String taskName = cursor.getString(1);

                String taskColor = cursor.getString(2);


                Task task = new Task(1,taskName, "");

                task.setTaskNum(1);
                task.setTaskName(taskName);
                task.setTaskColor(Integer.parseInt(taskColor));

                taskList.add(task);

            } while (cursor.moveToNext());



            Toast.makeText(context,"num in db"+cursor.getCount(),Toast.LENGTH_LONG).show();

            cursor.close();

        }

//        createToastWithText("goals list+" + goalsList);
        db.close();
        return taskList;
    }
}
