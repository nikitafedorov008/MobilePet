package ru.com.jetbrainsresearch.ido.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

public class Prefs {
    public static final String TAMO = "TAMO_P";

    private SharedPreferences.Editor ed;
    private SharedPreferences sPref;

    public Prefs(Context ctx) {
        sPref =  ctx.getSharedPreferences("SHOPUKACOM", Context.MODE_PRIVATE); //PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());// ctx.getSharedPreferences("SHOPUKACOM", MODE_PRIVATE);
        ed = sPref.edit();
    }

    public void set(String id, String e) {
        ed.putString(id, e);
        ed.apply();
    }

    public void set(String id, Integer e) {
        ed.putInt(id, e);
        ed.apply();
    }

    public void set(String id, Boolean e) {
        ed.putBoolean(id, e);
        ed.apply();
    }

    public SharedPreferences getPref() {
        return sPref;
    }

    public void inc(int id, int x) {
        set(TAMO + id, getPref().getInt(String.format("%s%d", TAMO, id), 1) + x);
    }


    public void dec(int id, int x) {
        set(TAMO + id, getPref().getInt(String.format("%s%d", TAMO, id), 1) - x);
    }

    public int get(int id) {
        return getPref().getInt(String.format("%s%d", TAMO, id), 1);
    }

    public void set(int id, int x) {
        set(TAMO + id, x);
    }


}