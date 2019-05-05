package stream.crosspromotion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AdActivity extends AppCompatActivity {

    public static String AD_URL = "AD_URL";
    public static String AD_TITLE = "AD_TITLE";
    public static String AD_DEVELOPER_ID = "AD_DEVELOPER_ID";
    private final String mActivity = getClass().getSimpleName();

    String mScreen; //Active fragment ID.
    String adUrl; //Ad server URL.
    String title; //ActionBar title.
    String developerUrl; //Developer page URL.

    FrameLayout mFragmentContainer;
    FragmentManager mFragmentManager;
    AdListFragment mAdListFragment;

    Context mContext;

    Boolean restore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acp);
        mContext = getApplication().getApplicationContext();

        getData();

        AdActivity.this.setTitle(title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mFragmentContainer = findViewById(R.id.fragment_adcontainer);
        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            Log.d(mActivity, "Restore");
            restore = savedInstanceState.getBoolean("restore");
            mScreen = savedInstanceState.getString("screen");
            Fragment f = mFragmentManager.findFragmentById(R.id.fragment_adcontainer);
            if (f == null) {
                LoadFragment(mScreen);
            }
        } else {
            restore = false;
            mScreen = Constants.SCREEN_MAIN;
            LoadFragment(mScreen);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putString("screen", mScreen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu_store);
        if (developerUrl != null) {
            item.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.menu_store) {
            Utils.OpenDeveloperUrl(mContext, developerUrl);
        }

        return super.onOptionsItemSelected(item);
    }

    public void LoadFragment(String screen) {
        Log.d("Menu", screen);
        //noinspection SwitchStatementWithTooFewBranches
        switch (screen) {
            case Constants.SCREEN_MAIN:
                mAdListFragment = AdListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString(AD_URL, adUrl);
                mAdListFragment.setArguments(bundle);
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_adcontainer, mAdListFragment, Constants.SCREEN_MAIN)
                        .commit();
                break;
            default:
                break;
        }
    }

    /**
     * Get data passed by intent.
     */
    private void getData() {
        Log.d("AdActivity", "Get Data");
        if (getIntent() != null) {
            //Get ad server URL.
            if (getIntent().getStringExtra(AD_URL) != null) {
                adUrl = getIntent().getStringExtra(AD_URL);
            }
            //Get ActionBar title.
            if (getIntent().getStringExtra(AD_TITLE) != null) {
                title = getIntent().getStringExtra(AD_TITLE);
            } else {
                title = getString(R.string.title);
            }
            //Get developer page URL.
            if (getIntent().getStringExtra(AD_DEVELOPER_ID) != null) {
                developerUrl = getIntent().getStringExtra(AD_DEVELOPER_ID);
            }
        }
    }
}
