package br.com.softpeach.www.gettingmarriage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Fragment fragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_drawer);

        if(savedInstanceState == null){
            fragment = new FragmentHome();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.add(R.id.frameLayout, fragment);
            transaction.commit();
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                fragment = null;
                if(id == R.id.home){
                    fragment = new FragmentHome();
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.gallery){
                    fragment = new FragmentGallery();
                    Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.send){
                    fragment = new FragmentSend();
                    Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.share){
                    fragment = new FragmentShare();
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                }

                if(fragment != null){
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameLayout, fragment);
                    transaction.commit();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
