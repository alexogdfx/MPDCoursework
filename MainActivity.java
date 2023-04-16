/*  Starter project for Mobile Platform Development in Semester B Session 2018/2019
    You should use this project as the starting point for your assignment.
    This project simply reads the data from the required URL and displays the
    raw data in a TextField
*/

//
// Name                 _________________
// Student ID           _________________
// Programme of Study   _________________
//

// Update the package name to include your Student Identifier
package org.me.gcu.ojelade_alexander_s2004585;


// import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// import gcu.mpd.bgsdatastarter.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private TextView rawDataDisplay;
    private Button startButton;

    private String result = "";

    private String url1 = "";
    private String urlSource = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";

    private String[] quakes;


    LinkedList<quakeClass> alist = null;
    quakeClass quake = null;
    private SearchView searchView;

    public LinkedList<quakeClass> getQuakeList() {
        return alist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkedList<quakeClass> alist = null;
        quakes = new String[50];
        // Set up the raw links to the graphical components
        //  rawDataDisplay = (TextView) findViewById(R.id.rawDataDisplay);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);


        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()

        {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search when user submits the search query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search as user types in the search query
                return false;
            }
        });

        // Set up the search button listener
        Button searchButton = findViewById(R.id.button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QuakeSearchDialogFragment dialogFragment = new QuakeSearchDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "QuakeSearchDialogFragment");



            }
        });

        // Write list to Log for testing
    }

    public void onClick(View aview) {

        // Construct the URL for the selected date

        new Thread(new Runnable() {
            public void run() {
                accessWeb aw = new accessWeb();
                result = aw.getResult(urlSource);
                alist = new parseDataTask().parseData(result);






                // Update the UI components from the main UI thread
                runOnUiThread(new Runnable() {
                    public void run() {
                        // Get the ListView

                        ListView listView = findViewById(R.id.listView);



                        // Create the adapter and set it to the ListView
                        QuakeListAdapter adapter = new QuakeListAdapter(MainActivity.this, alist);
                        listView.setAdapter(adapter);

                        quakeClass firstQuake = alist.get(0);
                        Log.d("Magnitude", "Magnitude of first quake: " + firstQuake.getMagnitude());



                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                adapter.filter(query);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                adapter.filter(newText);
                                return false;
                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                // Get the selected earthquake
                                quakeClass selectedEarthquake = alist.get(position);

                                // Inflate the layout for the popup dialog
                                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                                View popupView = inflater.inflate(R.layout.popup_earthquake_details, null);

                                // Set the earthquake details in the popup dialog
                                TextView titleTextView = popupView.findViewById(R.id.titleTextView);
                                TextView descriptionTextView = popupView.findViewById(R.id.descriptionTextView);
                                TextView linkTextView = popupView.findViewById(R.id.linkTextView);
                                TextView pubdateTextView = popupView.findViewById(R.id.pubdateTextView);
                                TextView catTextView = popupView.findViewById(R.id.catTextView);
                                TextView latTextView = popupView.findViewById(R.id.latTextView);
                                TextView longTextView = popupView.findViewById(R.id.longTextView);


                                titleTextView.setText(selectedEarthquake.getTitle());
                                descriptionTextView.setText(selectedEarthquake.getDescription());
                                linkTextView.setText(selectedEarthquake.getLink());
                                pubdateTextView.setText(selectedEarthquake.getTitle());
                                catTextView.setText(selectedEarthquake.getDescription());
                                latTextView.setText(selectedEarthquake.getLink());
                                longTextView.setText(selectedEarthquake.getLink());




                                // Create the AlertDialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Earthquake Details");
                                builder.setView(popupView);
                                builder.setPositiveButton("OK", null);
                                AlertDialog dialog = builder.create();
                                // Show the popup dialog
                                dialog.show();
                            }
                        });
                    }
                });
            }
        }).start();
    }
}