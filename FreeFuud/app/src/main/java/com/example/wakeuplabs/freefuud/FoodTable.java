package com.example.wakeuplabs.freefuud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
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
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.provider.SyncStateContract.Columns.DATA;

public class FoodTable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*DatabaseHelper myDb;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;*/

    ListView lv;
    ArrayAdapter<String> adapter;
    String address="http://10.0.2.2/webapp/init.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_table);
        //String method = "update";

        lv=(ListView) findViewById(R.id.listView1); //Check this later into XML file
        //move this to background thread but for now, I will allow network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        //Retrive Data
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Adapter
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        //BackgroundTask backgroundTask = new BackgroundTask(this);










        /*listView = (ListView)findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.raw_layout);
        listView.setAdapter(listDataAdapter);
        userDbHelper = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformation(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{

                String events,food,location,time;
                events = cursor.getString(0);
                food = cursor.getString(1);
                location = cursor.getString(2);
                time = cursor.getString(3);
                DataProvider dataProvider = new DataProvider(events,food,location,time);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */
    }



    private void getData() throws IOException {
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();//i dunno what i clieked but it fixed an error. I think I added exception

            con.setRequestMethod("GET");

            is=new BufferedInputStream(con.getInputStream());

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //ReAd is Content into a String

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();

            while((line=br.readLine()) != null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();


        }
        //PARSE JSON DATA
        catch (Exception e) {
            e.printStackTrace();
        }
        try {

            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            data=new String[ja.length()];

            for(int i=0;i<ja.length(); i++){
                jo = ja.getJSONObject(i);
                data[i]=jo.getString("Event");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_table, menu);
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
    */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
