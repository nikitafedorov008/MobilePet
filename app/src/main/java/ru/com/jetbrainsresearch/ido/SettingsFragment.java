package ru.com.jetbrainsresearch.ido;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import es.dmoral.toasty.Toasty;

import static ru.com.jetbrainsresearch.ido.MainActivity.player;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER1;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER2;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER3;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER4;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public boolean volume = true;
    TextView rateUs, geoLocation, darkMode, soundNotifiaction, aboutUs;
    SharedPreferences mSettings;
    EditText editPetName;
    public static String strNickName;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editPetName = (EditText) view.findViewById(R.id.edit_pet_name);
        strNickName = editPetName.getText().toString();

        rateUs = (TextView) view.findViewById(R.id.ittv1);
        geoLocation = (TextView) view.findViewById(R.id.ittv2);
        darkMode = (TextView) view.findViewById(R.id.ittv3);
        soundNotifiaction = (TextView) view.findViewById(R.id.ittv4);
        aboutUs = (TextView) view.findViewById(R.id.ittv5);

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getActivity(),  "We didn't published app at store",   Toast.LENGTH_SHORT).show();
            }
        });

        geoLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getActivity(),  "ok",   Toast.LENGTH_SHORT).show();
            }
        });

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getActivity(),  "sorry, not supported now",   Toast.LENGTH_SHORT).show();
            }
        });

        soundNotifiaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (volume = true){
                    player.setVolume(0.0f, 0.0f);
                    Toasty.info(getActivity(),  "Volume off",   Toast.LENGTH_SHORT).show();
                    volume = false;
                }
                if (volume = false) {
                    player.setVolume(0.5f, 0.5f);
                    Toasty.info(getActivity(),  "Volume on",   Toast.LENGTH_SHORT).show();
                    volume = true;
                }

            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getActivity(),  "We didn't published app at store",   Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME, strNickName);

        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSettings.contains(APP_PREFERENCES_NAME)) {
            strNickName = mSettings.getString(APP_PREFERENCES_NAME, null);
            PetFragment.petName.setText(strNickName);
        }
    }

}
