package org.pk.cas.fragements;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.pk.cas.newsappwithfirebase.R;
import org.pk.recyclerview_fragment.Model_Class_Fragment;
import org.pk.recyclerview_fragment.Recycler_View_Adapter;

import java.util.ArrayList;


public class PakNews extends Fragment {
    WebView webView;
    RecyclerView recyclerView;
    Recycler_View_Adapter adapter;
    FirebaseDatabase database =FirebaseDatabase.getInstance();

    public PakNews() {
        // Required empty public constructor
    }


    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pak_news, container, false);


        webView = view.findViewById(R.id.webView_pk_news);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().getJavaScriptEnabled();


        recyclerView = view.findViewById(R.id.pak_news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Model_Class_Fragment> options =
                new FirebaseRecyclerOptions.Builder<Model_Class_Fragment>()
                        .setQuery(database.getReference().child("Newsapp"), Model_Class_Fragment.class)
                        .build();


        adapter = new Recycler_View_Adapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });


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