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
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER1;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER2;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER3;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER4;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

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
