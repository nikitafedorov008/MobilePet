package ru.com.jetbrainsresearch.ido;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import es.dmoral.toasty.Toasty;

import static android.support.constraint.Constraints.TAG;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER1;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER2;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER3;
import static ru.com.jetbrainsresearch.ido.model.Pet.APP_PREFERENCES_COUNTER4;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetFragment extends Fragment {


    private SharedPreferences mSettings;
    public static IntentResult result;
    public TextView countTv, countTv2, countTv3, countTv4;
    public static TextView petName;
    public FloatingActionButton feedFb;
    public ImageButton countBtn;
    public int count1 = 50, count2 = 50, count3 = 50, count4 = 50;

    public PetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet, container, false);
        countTv = (TextView) view.findViewById(R.id.count_tv);
        countTv2 = (TextView) view.findViewById(R.id.count_tv2);
        countTv3 = (TextView) view.findViewById(R.id.count_tv3);
        countTv4 = (TextView) view.findViewById(R.id.count_tv4);
        petName = (TextView) view.findViewById(R.id.pet_name);
        countBtn = (ImageButton) view.findViewById(R.id.count_btn);
        feedFb = (FloatingActionButton) view.findViewById(R.id.feed_fb);

        countTv.setText(String.valueOf(count1));
        countTv2.setText(String.valueOf(count2));
        countTv3.setText(String.valueOf(count3));
        countTv4.setText(String.valueOf(count4));
        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCount();
            }
        });
        feedFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    IntentIntegrator integrator = new IntentIntegrator(getActivity());
                    integrator.setPrompt("Scan QR/BAR");
                    integrator.setOrientationLocked(false);
                    //integrator.initiateScan();
                    IntentIntegrator.forSupportFragment(PetFragment.this).initiateScan();
            }
        });

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER1, count1);
        editor.putInt(APP_PREFERENCES_COUNTER2, count2);
        editor.putInt(APP_PREFERENCES_COUNTER3, count3);
        editor.putInt(APP_PREFERENCES_COUNTER4, count4);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSettings.contains(APP_PREFERENCES_COUNTER1)) {
            count1 = mSettings.getInt(APP_PREFERENCES_COUNTER1, 0);
            countTv.setText(String.valueOf(count1));
        }
        if (mSettings.contains(APP_PREFERENCES_COUNTER2)) {
            count2 = mSettings.getInt(APP_PREFERENCES_COUNTER2, 0);
            countTv2.setText(String.valueOf(count2));
        }
        if (mSettings.contains(APP_PREFERENCES_COUNTER3)) {
            count3 = mSettings.getInt(APP_PREFERENCES_COUNTER3, 0);
            countTv3.setText(String.valueOf(count3));
        }
        if (mSettings.contains(APP_PREFERENCES_COUNTER4)) {
            count4 = mSettings.getInt(APP_PREFERENCES_COUNTER4, 0);
            countTv4.setText(String.valueOf(count4));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barcode = result.getContents();
        if (barcode != null){
            countTv3.setText(String.valueOf(count3 += 50));
            Toasty.success(getActivity(), String.valueOf(result).replace("Format:QR_CODE Contents: " + "RAW bytes:\\(64 bytes\\)EC level:MBarcode image: null", ""), Toast.LENGTH_LONG).show();{
                new ru.com.jetbrainsresearch.ido.api.restApiQrCheck()
                        .restApiQrCodeCheck(String.valueOf(result)) { result ->

                }
            }
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run(){
                    new ru.com.jetbrainsresearch.ido.api.restApiQrCheck().restApiQrCodeCheck(String.valueOf(result));

                }
            });

            thread.start();
        } else {
            Toasty.error(getActivity(), "you didn't feed me",   Toast.LENGTH_SHORT).show();
        }
    }

    private void increaseCount() {
        int current = Integer.parseInt((String) countTv.getText());
        countTv.setText(String.valueOf(count1 +=20));
        countTv2.setText(String.valueOf(count2 -= 2));
        countTv3.setText(String.valueOf(count3 -= 5));
        countTv4.setText(String.valueOf(count4 += 2));

        if (count1 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count2 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count3 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count4 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }
        //pause
    }

    private void feedCount() {
        countTv.setText(String.valueOf(count1 +=20));
        countTv2.setText(String.valueOf(count2 -= 2));
        countTv3.setText(String.valueOf(count3 -= 5));
        countTv4.setText(String.valueOf(count4 += 2));

        if (count1 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count2 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count3 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count4 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }
        //pause
    }

}