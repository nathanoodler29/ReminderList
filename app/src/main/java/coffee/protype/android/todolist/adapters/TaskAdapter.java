package coffee.protype.android.todolist.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import coffee.prototype.android.todolist.R;
import coffee.protoype.android.todolist.model.QueryHelper;
import coffee.protoype.android.todolist.model.Task;
import coffee.protoype.android.todolist.model.TaskContract;
import coffee.protoype.android.todolist.model.TaskDataBase;

/**
 * created by Noodle on 25/05/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Context mContext;
    private QueryHelper queryHelper = new QueryHelper();


    public static class ViewHolder extends
            RecyclerView.ViewHolder {
        private TextView taskTitle;
        private Button completedButton;
        private CardView reminderCard;



        public ViewHolder(View v) {
            super(v);
        }
    }

    public TaskAdapter(Context context, ArrayList<Task> taskArrayList) {
        tasks = taskArrayList;
        mContext = context;

    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(
                parent.getContext()).inflate(

                R.layout.generic_menu_item, parent, false);

        final TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(v);
        viewHolder.taskTitle = (TextView) v.findViewById(R.id.task_body);
        viewHolder.completedButton = (Button) v.findViewById(R.id.completed_button);
        viewHolder.reminderCard = (CardView) v.findViewById(R.id.reminder_card);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskAdapter.ViewHolder holder, final int position) {
        holder.taskTitle.setText(tasks.get(position).getTaskName());


        if (tasks.get(position).getTaskColor()== R.color.lowPrority) {
            holder.reminderCard.setCardBackgroundColor(Color.parseColor("#4DFA90"));

        }else   if (tasks.get(position).getTaskColor()== R.color.medPrority) {
            holder.reminderCard.setCardBackgroundColor(Color.parseColor("#FABE4D"));

        }else   if (tasks.get(position).getTaskColor()== R.color.highPrority) {
            holder.reminderCard.setCardBackgroundColor(Color.parseColor("#E94E77"));

        }

        holder.completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mContext.getApplicationContext();
                //Sets a mild vibrate once button is clicked.
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                holder.getAdapterPosition();

                queryHelper.updateCurrentTaskToComplete(context,tasks.get(holder.getAdapterPosition()).getTaskName());
//                deleteTaskToTable(context, tasks.get(holder.getAdapterPosition()).getTaskName());
//                RemoveItem(holder.getAdapterPosition());

                removeItem(holder.getAdapterPosition());



            }
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }
//
//    private void deleteTaskToTable(Context context, String taskName) {
//        TaskDataBase dbHelper = new TaskDataBase(context);
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TaskEntry.TABLE_NAME +
//                " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_Title + " = " + "'" + taskName + "'", null);
//        ArrayList<Task> taskList = new ArrayList<Task>();
//        taskList.clear();
//
//
//        if (cursor.moveToFirst()) {
//
//
//            db.execSQL("DELETE FROM " + TaskContract.TaskEntry.TABLE_NAME + " WHERE " + TaskContract.TaskEntry.COLUMN_TASK_Title + " = " + "'" + taskName + "'");
//
//
//            cursor.close();
//
//        }
//        db.close();
//    }

    private void removeItem(int position) {
        tasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tasks.size());
    }

}


