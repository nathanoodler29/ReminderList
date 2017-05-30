package coffee.prototype.android.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import coffee.protoype.android.todolist.model.QueryHelper;
import coffee.protype.android.todolist.adapters.TaskAdapter;

public class HomeScreen extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private TaskAdapter mAdapter;
    private ImageView noReminderSetImage;
    private TextView noReminderSetText;


    private QueryHelper helper = new QueryHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        createBottomNavigation();

        noReminderSetImage = (ImageView)findViewById(R.id.no_reminders_set);
        noReminderSetText = (TextView) findViewById(R.id.no_reminders_set_text);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.add_reminder_floating_button);

        //Creates a recycler view for goals
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_recycleview);
        //creates a linear layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //sets a linear layout
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new TaskAdapter(this, helper.populateTaskAdapter(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);



        if (mAdapter.getItemCount()>0){
            noReminderSetImage.setVisibility(View.INVISIBLE);
            noReminderSetText.setVisibility(View.INVISIBLE);


        }

            //Insert the red wine values in the database.
            helper.populateTaskAdapter(getApplicationContext());

            mAdapter.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    hideNavBar();
                    hideFloatingButton();


                }else{
                    displayNavBar();
                    displayFloatingButton();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater homeMenuInflater = getMenuInflater();
        homeMenuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==  R.id.add_item) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(100);
            finish();
            Intent addTask = new Intent(this, AddTask.class);
            startActivity(addTask);

        } else if (item.getItemId() == R.id.home) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(100);

        }
        return super.onOptionsItemSelected(item);
    }


    public void navigateToAddTask(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Intent addTask = new Intent(this, AddTask.class);
        startActivity(addTask);
    }

    public void createBottomNavigation(){
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.button_add_item) {
                    finish();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);

                    finish();
                    Intent addTask = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(addTask);

                } else if (item.getItemId() == R.id.bottom_home) {
                    finish();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);


                }
                return true;
            }
        });
    }

    public void hideNavBar(){
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setVisibility(View.INVISIBLE);

    }



    public void displayNavBar(){
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setVisibility(View.VISIBLE);

    }

    public void hideFloatingButton(){
        floatingActionButton = (FloatingActionButton)findViewById(R.id.add_reminder_floating_button);


        floatingActionButton.setVisibility(View.INVISIBLE);

    }

    public void displayFloatingButton(){
        floatingActionButton = (FloatingActionButton)findViewById(R.id.add_reminder_floating_button);

        floatingActionButton.setVisibility(View.VISIBLE);

    }

}
