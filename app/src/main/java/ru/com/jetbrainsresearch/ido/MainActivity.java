package ru.com.jetbrainsresearch.ido;

import android.content.Intent;
import android.media.MediaPlayer;
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

    private MediaPlayer player;

    private FloatingActionButton petFB, mapFB, statisticsFB, settingsFB, cartFB;
    final Fragment fragment1 = new PetFragment();
    final Fragment fragment2 = new HomeFragment();
    final Fragment fragment3 = new StatisticsFragment();
    final Fragment fragment4 = new SettingsFragment();
    final Fragment fragment5 = new CartFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        petFB = (FloatingActionButton) findViewById(R.id.pet_fab);
        mapFB = (FloatingActionButton) findViewById(R.id.map_fab);
        statisticsFB = (FloatingActionButton) findViewById(R.id.statistic_fab);
        settingsFB = (FloatingActionButton) findViewById(R.id.settings_fab);
        cartFB = (FloatingActionButton) findViewById(R.id.cart_fab);
        fm.beginTransaction().add(R.id.main_container, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
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

        mapFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
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
                fm.beginTransaction().hide(active).show(fragment4).commit();
                active = fragment4;
            }
        });

        cartFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().hide(active).show(fragment5).commit();
                active = fragment5;
            }
        });


        player = MediaPlayer.create(this, R.raw.what_is_love);
        player.setLooping(true);
        player.setVolume(0.5f, 0.5f);
        player.start();

    }

    /*@Override
    protected void onStart() {
        super.onStart();
    }*/

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

        /*if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
            return true;

        }
        return false;

    }

}
