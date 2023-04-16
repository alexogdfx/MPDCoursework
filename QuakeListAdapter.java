package org.me.gcu.ojelade_alexander_s2004585;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.me.gcu.ojelade_alexander_s2004585.R;
import org.me.gcu.ojelade_alexander_s2004585.quakeClass;

import java.util.LinkedList;

public class QuakeListAdapter extends BaseAdapter {

    private final int[] colors = {Color.GREEN, Color.YELLOW, Color.RED};

    private Context context;
    private LinkedList<quakeClass> quakeList;
    private LinkedList<quakeClass> filteredQuakeList;

    public QuakeListAdapter(Context context, LinkedList<quakeClass> quakeList) {
        this.context = context;
        this.quakeList = quakeList;
        this.filteredQuakeList = new LinkedList<>(quakeList);
    }

    @Override
    public int getCount() {
        return filteredQuakeList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredQuakeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.title_text_view);

        quakeClass quake = filteredQuakeList.get(position);
        titleTextView.setText(quake.getTitle());

        int color;
        if (quake.getMagnitude() >= 0.0 && quake.getMagnitude() <= 1.9) {
            color = colors[0];
        } else if (quake.getMagnitude() >= 2.0 && quake.getMagnitude() <= 2.9) {
            color = colors[1];
        } else {
            color = colors[2];
        }
        convertView.setBackgroundColor(color);
        return convertView;

    }

    public void filter(String query) {
        filteredQuakeList.clear();
        for (quakeClass quake : quakeList) {
            if (quake.getPubDate().toLowerCase().contains(query.toLowerCase())) {
                filteredQuakeList.add(quake);
            }
        }
        notifyDataSetChanged();
    }
}
