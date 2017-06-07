package coffee.prototype.android.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import coffee.protoype.android.todolist.model.QueryHelper;
import coffee.protype.android.todolist.adapters.CompletedTaskAdapter;
import coffee.protype.android.todolist.adapters.TaskAdapter;

public class CompletedTasks extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private QueryHelper helper = new QueryHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        Toolbar homeToolbar = (Toolbar) findViewById(R.id.completed_toolbar);
        setSupportActionBar(homeToolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.completed_task_recycler_view);

        //creates a linear layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //sets a linear layout
        recyclerView.setLayoutManager(linearLayoutManager);
        CompletedTaskAdapter mAdapter = new CompletedTaskAdapter(this, helper.populateCompeltedTaskAdapter(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater homeMenuInflater = getMenuInflater();
        homeMenuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_item) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(100);
            Intent addTask = new Intent(this, AddTask.class);
            startActivity(addTask);

        } else if (item.getItemId() == R.id.home) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(100);
            Intent home = new Intent(this, HomeScreen.class);
            startActivity(home);

        }
        return super.onOptionsItemSelected(item);
    }
}
