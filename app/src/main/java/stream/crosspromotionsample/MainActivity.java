package stream.crosspromotionsample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String mTitleText;

    FrameLayout mFragmentContainer;
    FragmentManager mFragmentManager;
    MainFragment mMainFragment;

    Context mContext;
    private final String mActivity = this.getClass().getSimpleName();

    Boolean restore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentContainer = findViewById(R.id.fragment_container);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {

            Log.d(mActivity, "Restore");
            restore = savedInstanceState.getBoolean("restore");
            mTitleText = savedInstanceState.getString("mTitleText");
            Fragment f = mFragmentManager.findFragmentById(R.id.fragment_container);
            if(f == null) {
                LoadFragment(mTitleText);
            }
        } else {

            restore = false;
            mTitleText = Constants.SCREEN_MAIN;
            LoadFragment(mTitleText);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putString("mTitleText", mTitleText);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void LoadFragment(String screen) {
        Log.d("Menu", screen);
        switch (screen) {
            case Constants.SCREEN_MAIN:
                mMainFragment = MainFragment.newInstance();
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mMainFragment, Constants.SCREEN_MAIN)
                        .commit();
                break;
            default:
                break;
        }
    }
}
