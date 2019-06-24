package com.example.practical4;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private String forwardMessage;

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                      SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Configure the search info and add any event listeners...

        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                displayToast(getString(R.string.action_settings_message));
                return true;

            case R.id.action_favorite:
                displayToast(getString(R.string.action_favorite_message));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
 public void launchSecondActivity(View view){
    Intent intent = new Intent(this, SecondActivity.class);
   startActivity(intent);}

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hourOfDay_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hourOfDay + ":" + minute);

        Toast.makeText(this, "Time: " + timeMessage,
                Toast.LENGTH_SHORT).show();
    }


    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);

        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");

        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_SHORT).show();
                    }
                });
        myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

        // Create and show the AlertDialog.
        myAlertBuilder.show();
    }




}
