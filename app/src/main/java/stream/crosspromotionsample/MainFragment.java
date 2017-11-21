package stream.crosspromotionsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    TextView mMessage;

    RecyclerView mRecyclerView;
    MainAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<Main> mList;

    Boolean restore = false;

    Context mContext;
    final String mActivity = this.getClass().getSimpleName();
    private static final String STATE_LIST = "State Adapter Data";

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();

        if (savedInstanceState != null) {

            mList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            mAdapter = new MainAdapter(getActivity(), mList);

            restore = savedInstanceState.getBoolean("restore");

        } else {

            mList = new ArrayList<>();

            Main main = new Main();
            main.setMyPackageName("io.ideastarter");
            main.setPreviewImageUrl("http://lh3.googleusercontent.com/R-vJInTblK1KBOqZaSDm_ac270QBHsiIcU9agHnN-rrp9K_lkN8rLzGIH8asCfkb420Q=w128-rw");
            main.setRating(5.0);
            main.setTitle("Crowdfunding Projects");
            main.setSubTitle("Browse all crowdfunding projects from Kickstarter and Indiegogo in one app!");
            mList.add(main);

            mAdapter = new MainAdapter(getActivity(), mList);

            restore = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMessage = rootView.findViewById(R.id.message);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if (mList.size() == 0) {

            showMessage();

        } else {

            hideMessage();
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putParcelableArrayList(STATE_LIST, mList);
    }

    public void showMessage() {

        mMessage.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {

        mMessage.setVisibility(View.GONE);
    }
}