package com.cse110.apk404.myCalendar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import com.cse110.apk404.myCalendar.R;
import com.cse110.apk404.myCalendar.eventListHandler.ByteArrayViaString;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarDB;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObject;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObjectList;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarSQLiteDB;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.Serializer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.AbstractCollection;
import java.util.ArrayList;


/**
 * The main view on start contains the CalendarViewFragment
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    HttpURLConnection connection = null;
    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    String urlServer = "https://calendarserver.herokuapp.com/CalendarS";
    private String mEmail;
    private String mPassword;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    boolean isLoggedIn = false;
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set the fragment initially */
        CalendarViewFragment fragment = new CalendarViewFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        /* Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set Actionbar Icon */
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);


        /* Floating action button */
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /* Add Snackbar on click */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Add Event", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                Bundle extras = new Bundle();
                extras.putBoolean("IS_EDIT_EVENT",false);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        /* Navigation drawer */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        /* Change email in the nav drawer programatically */
//        View headerView = navigationView.getHeaderView(0);
//        TextView emailText = (TextView) headerView.findViewById(R.id.email);
//        emailText.setText("newemail@email.com");

        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isLoggedIn = extras.getBoolean("isLoggedIn");
            email = extras.getString("email");
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Change individual navigationView menu item color, Only change the first item "Today" to pink
//        MenuItem item = menu.findItem(R.id.nav_calender_view);
//        SpannableString spanString = new SpannableString(item.getTitle().toString());
//        spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0);
//        item.setTitle(spanString);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calender_view) {
            //Set the fragment initially
            CalendarViewFragment fragment = new CalendarViewFragment();
//            fragment.getView().setBackgroundColor(Color.WHITE);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            // Handle the camera action

        } else if (id == R.id.nav_add_event) {
//            Intent intent = new Intent(this, AddEventActivity.class);
//            this.startActivity(intent);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_start_new) {
            Log.d("Restart", "Restart DB");


            // TODO create all three new lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            EventListHandler.initStaticList(); // Intialize list once at the begining
            try {
                CalendarDB.initDBLocal(this);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }

        } else if (id == R.id.nav_sync) {

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        try {

            // TODO update all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // Save lists from EventListHandler to database

            CalendarDB.updateListLocal(0, EventListHandler.getStaticList(), this);
            //CalendarDB.updateListLocal(1, EventListHandler.getDynamicList(), this);
            //CalendarDB.updateListLocal(2, EventListHandler.getDeadlineList(), this);

            if (isLoggedIn) {

                // TODO update all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list = new ArrayList<>();
                list.add(EventListHandler.getStaticList());
                //list.add(EventListHandler.getStaticList());
                //list.add(EventListHandler.getStaticList());

                byte[] bytes = Serializer.serialize(list);
                String uploadString = ByteArrayViaString.byteArrayToString(bytes);

                URL url = new URL(urlServer);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("email", mEmail)
                        .appendQueryParameter("command", "uploadData")
                        .appendQueryParameter("backup", uploadString);
                String query = builder.build().getEncodedQuery();

                PrintWriter writer = new PrintWriter(connection.getOutputStream());
                writer.write(query);
                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            Log.e("Save", e.getMessage());
        }
    }

}
