package com.example.ksachdev.myringtonemanager;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EventsListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScheduleEventsActivity();
            }
        });

        renderList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showScheduleEventsActivity(){
        Intent intent = new Intent(this,ScheduleEventsActivity.class);
        startActivity(intent);
    }

    public void renderList(){
        TextView emptyList_txt = (TextView) findViewById(R.id.temp_txt);
        db = DatabaseHelper.getInstance(this);
        ArrayList<Event> events = db.getAllEvents();
        if(events.size() > 0){
            emptyList_txt.setVisibility(View.INVISIBLE);
            if(adapter != null){
                adapter.clear();
            }
            adapter = new EventsListAdapter(this,events);
            listView = (ListView) findViewById(R.id.events_listview);

            listView.setAdapter(adapter);
        }else{

            emptyList_txt.setVisibility(View.VISIBLE);
        }

    }
}