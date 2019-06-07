package ru.com.jetbrainsresearch.ido;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import ru.com.jetbrainsresearch.ido.home.HomeFragment;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton petFB, homeFB, statisticsFB, settingsFB;
    final Fragment fragment1 = new PetFragment();
    final Fragment fragment2 = new HomeFragment();
    final Fragment fragment3 = new StatisticsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        petFB = (FloatingActionButton) findViewById(R.id.pet_fab);
        homeFB = (FloatingActionButton) findViewById(R.id.home_fab);
        statisticsFB = (FloatingActionButton) findViewById(R.id.statistic_fab);
        settingsFB = (FloatingActionButton) findViewById(R.id.settings_fab);
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();

        petFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
            }
        });

        homeFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
            }
        });

        statisticsFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
            }
        });

        settingsFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}