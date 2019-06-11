package ru.com.jetbrainsresearch.ido;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Map;

import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    SharedPreferences mSettings;
    EditText editPetName;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }

}
