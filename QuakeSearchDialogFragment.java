package org.me.gcu.ojelade_alexander_s2004585;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;


public class QuakeSearchDialogFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private QuakeSearchAdapter adapter;
    private LinkedList<quakeClass> quakeList;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_earthquake_search, null);
        quakeList = new LinkedList<>();
        quakeList.addAll(((MainActivity)getActivity()).getQuakeList());

        // Initialize your RecyclerView, adapter, and quakeList
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new QuakeSearchAdapter(quakeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        SearchView searchView = view.findViewById(R.id.search_view);
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

        builder.setView(view);

        return builder.create();
    }
}
