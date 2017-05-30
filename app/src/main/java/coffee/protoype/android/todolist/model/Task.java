package coffee.protoype.android.todolist.model;

/**
 * Created by Noodle on 25/05/2017.
 */

public class Task {

    private int taskColor;
    private String taskName;


    public Task() {

    }

    public Task(String taskName, int taskColor) {

        this.taskName = taskName;

        this.taskColor = taskColor;


    }

    public int getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(int taskColor) {
        this.taskColor = taskColor;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


}
