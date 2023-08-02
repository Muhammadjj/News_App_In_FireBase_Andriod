package org.pk.cas.fragements;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import org.pk.cas.newsappwithfirebase.LoginPageActivity;
import org.pk.cas.newsappwithfirebase.MainActivity;
import org.pk.cas.newsappwithfirebase.R;

public class ProfileSetting extends Fragment {

    MaterialButton profile_btn;
    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();

    public ProfileSetting() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_setting, container, false);

        profile_btn = view.findViewById(R.id.profile_setting_button);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
//                final SharedPreferences sharedPreferences = getContext().getSharedPreferences("Data", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.commit();
                Intent intent = new Intent(getActivity().getApplication(), LoginPageActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}