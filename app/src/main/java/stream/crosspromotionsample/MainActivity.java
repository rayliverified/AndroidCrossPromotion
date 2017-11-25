package stream.crosspromotionsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import stream.crosspromotion.AdActivity;
import stream.crosspromotion.AdListFragment;

public class MainActivity extends AppCompatActivity {

    String mTitleText;

    FrameLayout mFragmentContainer;
    FragmentManager mFragmentManager;
    AdListFragment mAdListFragment;

    Context mContext;
    private final String mActivity = this.getClass().getSimpleName();

    Boolean restore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        mFragmentContainer = findViewById(R.id.fragment_container);
        mFragmentManager = getSupportFragmentManager();

        ActionBar();

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

    public void LoadFragment(String screen) {
        Log.d("Menu", screen);
        switch (screen) {
            case Constants.SCREEN_MAIN:
                mAdListFragment = AdListFragment.newInstance();
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mAdListFragment, Constants.SCREEN_MAIN)
                        .commit();
                break;
            default:
                break;
        }
    }

    private void ActionBar() {
        ActionBar toolBar = getSupportActionBar();
        toolBar.setDisplayShowCustomEnabled(true);
        toolBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        toolBar.setCustomView(R.layout.toolbar_home);
        toolBar.setElevation(0);
        Toolbar parent = (Toolbar) toolBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ImageView toolbarIcon = findViewById(R.id.toolbar_icon);
        toolbarIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(AdActivity.AD_DEVELOPER_ID, getString(R.string.developer_id));
                intent.putExtra(AdActivity.AD_TITLE, "More Apps from Stream Inc");
                mContext.startActivity(intent);
            }
        });
    }
}
