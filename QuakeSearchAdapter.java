package org.me.gcu.ojelade_alexander_s2004585;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class QuakeSearchAdapter extends RecyclerView.Adapter<QuakeSearchAdapter.ViewHolder> {

    private LinkedList<quakeClass> quakeList;
    private LinkedList<quakeClass> filteredQuakeList;

    public QuakeSearchAdapter(LinkedList<quakeClass> quakeList) {
        this.quakeList = quakeList;
        this.filteredQuakeList = new LinkedList<>(quakeList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        quakeClass quake = filteredQuakeList.get(position);
        holder.titleTextView.setText(quake.getTitle());

        int color;
        if (quake.getMagnitude() >= 0.0 && quake.getMagnitude() <= 1.9) {
            color = Color.GREEN;
        } else if (quake.getMagnitude() >= 2.0 && quake.getMagnitude() <= 2.9) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
        holder.itemView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return filteredQuakeList.size();
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
        }
    }
}

