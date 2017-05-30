package coffee.protoype.android.todolist.model;

import android.provider.BaseColumns;

/**
 * Created by Noodle on 29/05/2017.
 */

public class TaskContract {
    private TaskContract() {
    }

    /**
     * This class is used to retrieve column names, to ensure they are spelled correctly.
     * As such a columns are referenced in the schema.
     */
    public static final class TaskEntry implements BaseColumns {
        public final static String TABLE_NAME = "Task";
        public final static String TASK_ID = BaseColumns._ID;
        public final static String COLUMN_TASK_Title = "task_title";
        public final static String COLUMN_TASK_COMPLETION = "task_completion";
        public final static String COLUMN_TASK_COLOR = "task_color";

        public final static String COLUMN_TASK_Time = "task_color";

    }
}

