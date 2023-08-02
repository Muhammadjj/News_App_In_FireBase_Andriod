package org.pk.cas.fragements;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.pk.cas.newsappwithfirebase.R;
import org.pk.recyclerview_fragment.Model_Class_Fragment;
import org.pk.recyclerview_fragment.Recycler_View_Adapter;


public class IslamicNews extends Fragment {

    RecyclerView recyclerView;
    Recycler_View_Adapter adapter;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public IslamicNews() {
        // Required empty public constructor
    }


    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_islamic_news, container, false);

        recyclerView = view.findViewById(R.id.islamic_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Model_Class_Fragment> options =
                new FirebaseRecyclerOptions.Builder<Model_Class_Fragment>()
                        .setQuery(firebaseDatabase.getReference().child("Islamic"), Model_Class_Fragment.class)
                        .build();


        adapter = new Recycler_View_Adapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}