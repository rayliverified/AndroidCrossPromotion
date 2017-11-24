package stream.crosspromotion;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class AdActivity extends AppCompatActivity {

    public static String AD_TITLE = "AD_TITLE";
    public static String AD_DEVELOPER_ID = "AD_DEVELOPER_ID";

    String mTitleText;
    String title;
    String developerUrl;

    FrameLayout mFragmentContainer;
    FragmentManager mFragmentManager;
    AdListFragment mMainFragment;

    Context mContext;
    private final String mActivity = this.getClass().getSimpleName();

    Boolean restore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        if (getIntent() != null)
        {
            if (getIntent().getStringExtra(AD_DEVELOPER_ID) != null)
            {
                developerUrl = getIntent().getStringExtra(AD_DEVELOPER_ID);
            }
            if (getIntent().getStringExtra(AD_TITLE) != null)
            {
                title = getIntent().getStringExtra(AD_TITLE);
            }
            else
            {
                title = getString(R.string.title);
            }
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(title);

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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putString("mTitleText", mTitleText);
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
        if (id == android.R.id.home)
        {
            onBackPressed();
        }
        else if (id == R.id.menu_store)
        {
            Utils.OpenDeveloperUrl(mContext, developerUrl);
        }

        return super.onOptionsItemSelected(item);
    }

    public void LoadFragment(String screen) {
        Log.d("Menu", screen);
        switch (screen) {
            case Constants.SCREEN_MAIN:
                mMainFragment = AdListFragment.newInstance();
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mMainFragment, Constants.SCREEN_MAIN)
                        .commit();
                break;
            default:
                break;
        }
    }
}
