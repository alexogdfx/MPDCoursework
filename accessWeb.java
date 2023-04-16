 package org.me.gcu.ojelade_alexander_s2004585;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class accessWeb //Class for accessing web and taking xml data
{


    String result = "";
    String url = null;
    //set global variables



    public String getResult(String urlsource) {


        url = urlsource;

        URL aurl;
        URLConnection yc;
        BufferedReader in = null;
        String inputLine = "";
        boolean istrue = false;//set more variables



        Log.e("MyTag","in run");

        try
        {
            Log.e("MyTag","in try");
            aurl = new URL(url);
            yc = aurl.openConnection();
            in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            //
            // Throw away the first 2 header lines before parsing
            while ((inputLine = in.readLine()) != null)
            {
                if(inputLine.contains("rss")){ //if xml line contains <rss> add to result
                    result = result + inputLine;
                    Log.e("MyTag",inputLine);
                }
                else if(inputLine.contains("channel")){//if xml line contains <channel> patch it
                    istrue = false;
                    //count++;
                }else if(inputLine.contains("item") ){//if xml line contains <item> set boolean to true
                    istrue = true;
                    //count++;
                }

                if(istrue && !inputLine.contains("channel") ){//if xml line doesnt contain <channel> and bool is true
                    //start adding items to result
                    result = result + inputLine;
                    Log.e("MyTag",inputLine);
                }




            }
            in.close();
        }
        catch (IOException ae)
        {
            Log.e("MyTag", "ioexception");
        }


        return result; //returns result string
    }
}

