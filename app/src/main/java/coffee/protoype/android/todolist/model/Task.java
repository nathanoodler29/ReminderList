package coffee.protoype.android.todolist.model;

/**
 * Created by Noodle on 25/05/2017.
 */

public class Task {
    private int taskNum;
    private String taskDescription;
    private String isTaskDone;
    private String taskTime;
    private int taskColor;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    private String taskName;


    public Task() {

    }

    public Task(int taskNum, String taskDescription, String isTaskDone, String taskTime) {

        this.taskNum = taskNum;

        this.taskDescription = taskDescription;
        this.isTaskDone = isTaskDone;
        this.taskTime = taskTime;


    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public int getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(int taskColor) {
        this.taskColor = taskColor;
    }


    public Task(int tasknum, String taskDescription, String isTaskDone) {

    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getIsTaskDone() {
        return isTaskDone;
    }

    public void setIsTaskDone(String isTaskDone) {
        this.isTaskDone = isTaskDone;
    }


}
