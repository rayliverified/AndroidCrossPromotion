package stream.crosspromotion;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    String mTitleText;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
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
