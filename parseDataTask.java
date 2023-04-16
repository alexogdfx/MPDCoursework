package org.me.gcu.ojelade_alexander_s2004585;

import static android.content.ContentValues.TAG;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

public class parseDataTask {


    LinkedList<quakeClass> templist = null;
    quakeClass quake = null;
    private String[] quakes;


    public LinkedList<quakeClass> parseData(String dataToParse) {


        templist = new LinkedList<>();


        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Found a start tag
                if (eventType == XmlPullParser.START_TAG) {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        //do nothing

                    } else if (xpp.getName().equalsIgnoreCase("item")) {

                        quake = new quakeClass();
                        Log.e("MyTag", "Item Start Tag found");

                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.e("MyTag", "title is " + temp);
                        quake.setTitle(temp);
                    } else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("link")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            // Do something with text
                            Log.e("MyTag", "link is " + temp);
                            quake.setLink(temp);
                        } else
                        if (xpp.getName().equalsIgnoreCase("description")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            // Do something with text
                            Log.e("MyTag", " description is " + temp);
                            quake.setDescription(temp);

                            String[] locParts = temp.split(";");
                            for (String part : locParts) {
                                if (part.contains("Location:")) {
                                    String locationString = part.trim().split(":")[1];
                                    quake.setLocation(locationString);
                                    Log.e("MyTag", "location is " + locationString);
                                    break;
                                }
                            }

                            int depthStartIndex = temp.indexOf("Depth:");
                            if (depthStartIndex != -1) {
                                String depthString = temp.substring(depthStartIndex + "Depth:".length()).trim().split(" ")[0];
                                float depth = Float.parseFloat(depthString);
                                quake.setDepth(depth);
                                Log.e("MyTag", " depth is " + depth);
                            }



                            String[] magParts = temp.split(";");
                            for (String part : magParts) {
                                if (part.contains("Magnitude:")) {
                                    String magnitudeString = part.trim().split(":")[1];
                                    float magnitude = Float.parseFloat(magnitudeString);
                                    quake.setMagnitude(magnitude);
                                    Log.e("MyTag", " magnitude is " + magnitude);

                                    break;
                                }
                            }
                        }
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("pubDate")) {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                // Do something with text
                                Log.e("MyTag", "pubDate is " + temp);
                                quake.setPubDate(temp);
                            } else
                                // Check which Tag we have
                                if (xpp.getName().equalsIgnoreCase("category")) {
                                    // Now just get the associated text
                                    String temp = xpp.nextText();
                                    // Do something with text
                                    Log.e("MyTag", "category is " + temp);
                                    quake.setCat(temp);
                                } else
                                    // Check which Tag we have
                                    if (xpp.getName().equalsIgnoreCase("lat")) {
                                        // Now just get the associated text
                                        String temp = xpp.nextText();
                                        // Do something with text
                                        Log.e("MyTag", "lat is " + temp);
                                        quake.setLatitude(Double.parseDouble(temp));
                                    } else
                                        // Check which Tag we have
                                        if (xpp.getName().equalsIgnoreCase("long")) {
                                            // Now just get the associated text
                                            String temp = xpp.nextText();
                                            // Do something with text
                                            Log.e("MyTag", "long is " + temp);
                                            quake.setLongitude(Double.parseDouble(temp));

                                        }
                        } else if (eventType == XmlPullParser.END_TAG) {
                            if (xpp.getName().equalsIgnoreCase("item")) {
                                Log.e("MyTag", "quake is " + quake.toString());
                                templist.add(quake);
                            } else if (xpp.getName().equalsIgnoreCase("rss")) {
                                int size;
                                size = templist.size();
                                Log.e("MyTag", "Size of list  is  " + size);
                            }
                        }


                        // Get the next event
                        eventType = xpp.next();

                    } // End of while

                    return templist;
                } catch(XmlPullParserException ae1){
                    Log.e("MyTag", "Parsing error" + ae1.toString());
                } catch(IOException ae1){
                    Log.e("MyTag", "IO error during parsing");
                }

                Log.e("MyTag", "End document");

                return templist;

            }


        }

