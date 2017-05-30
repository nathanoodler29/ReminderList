package coffee.prototype.android.todolist;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import coffee.protoype.android.todolist.model.QueryHelper;
import coffee.protoype.android.todolist.model.Task;
import coffee.protoype.android.todolist.model.Time;

public class AddTask extends AppCompatActivity {
    private EditText taskTitle;
    private EditText taskDescription;
    private Task task = new Task();
    private QueryHelper helper = new QueryHelper();
    private EditText time;
    private Time userTime = new Time();
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskTitle = (EditText) findViewById(R.id.task_name);
        time = (EditText) findViewById(R.id.task_time);

        validateTitle();


    }


    public void navigateToBackHome(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        finish();
        Intent home = new Intent(this, HomeScreen.class);
        startActivity(home);
    }


    public void validateTitle() {

        //Text watcher reads what a user is typing.
        taskTitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //Text watcher, monitors what text is typed by the user
            @Override
            public void afterTextChanged(Editable s) {
                //Converts the input from a user
                String userInput = taskTitle.getText().toString();

                //if the edit text doesn't contain text
                if (userInput.isEmpty()) {
                    //Throw error related to being blank
                    taskTitle.setError("Please don't leave blank");
                    //Sets drink name to not valid, if a user breaks validation rule.
                    task.setTaskName("not valid");
                    //Validates if any special chars are used.
                } else if (userInput.contains("*") | userInput.contains("\0") | userInput.contains("\'")
                        | userInput.contains("\0")
                        | userInput.contains("\"") | userInput.contains("\b") | userInput.contains("\n")
                        | userInput.contains("\r") | userInput.contains("\t") | userInput.contains("\t")
                        | userInput.contains("\\") | userInput.contains("%")) {
                    //Sets errors if spec chars are used.
                    taskTitle.setError("Special characters can't be used");
                    task.setTaskName("not valid");
                } else if (userInput.matches("^([a-zA-Z]+ ?){4,15}")) {
                    task.setTaskName(userInput);
                    Toast.makeText(getApplicationContext(), "Valid Task Title", Toast.LENGTH_SHORT).show();

                } else if (!userInput.matches("^([a-zA-Z] ?){4,15}")) {

                    taskTitle.setError("Task name, needs to be between 4 and 15 characters");

                    task.setTaskName("not valid");


                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void openClockPrompt(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
        userTime.setHour(mHour);
        userTime.setMin(mMinute);


    }

    public int priorityOfTask(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        double coffeeVolume = 0.00;

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.low_prioity:


                if (checked) {
                    //If Instant 236.00 is selected set this as drink volume
                    task.setTaskColor(R.color.lowPrority);

                }

                break;
            case R.id.meduim_prioity:
                if (checked) {
                    //If Instant 254.00 is selected set this as drink volume
                    task.setTaskColor(R.color.medPrority);


                }

                break;
            case R.id.high_prioity:
                if (checked) {
                    //If Instant 473.00 is selected set this as drink volume
                    task.setTaskColor(R.color.highPrority);


                }

                break;

        }

        return task.getTaskColor();
    }


    public void validateFields(View view) {
        //need to have logic here, so if it's two fields, then add two if not then add three
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        if (task.getTaskName() != "not valid" && task.getTaskName() != null) {
            Toast.makeText(getApplicationContext(), "Valid name", Toast.LENGTH_SHORT).show();
            helper.addTaskToTable(getApplicationContext(), task.getTaskName(), String.valueOf(task.getTaskColor()));
            helper.populateTaskAdapter(getApplicationContext());
            finish();
            Intent changeToDrinksRecipt = new Intent(this, HomeScreen.class);
            startActivity(changeToDrinksRecipt);


        } else {
            Toast.makeText(getApplicationContext(), "Fields are empty or aren't valid please review.", Toast.LENGTH_SHORT).show();

        }
    }


}
