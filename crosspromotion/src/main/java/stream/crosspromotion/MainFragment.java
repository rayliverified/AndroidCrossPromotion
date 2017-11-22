package stream.crosspromotion;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            mAdapter = new MainAdapter(getActivity(), mList);

            restore = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMessage = rootView.findViewById(R.id.message);
        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItems();
            }
        });

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if (mList.size() == 0) {

            showMessage();

        } else {

            hideMessage();
        }

        if (!restore)
        {
            getItems();
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putParcelableArrayList(STATE_LIST, mList);
    }

    public void getItems() {

        Log.d(mActivity, "GetItems");

        CustomRequest jsonReq = new CustomRequest(Request.Method.POST, Constants.METHOD_ADS_GET, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(mActivity, "Ads JSON: " + response.toString());

                        if (!isAdded() || getActivity() == null) {

                            Log.e(mActivity, "Fragment Not Added to Activity");

                            return;
                        }

                        try {
                            if (!response.getBoolean("error")) {

                                if (response.has("items")) {

                                    JSONArray itemsArray = response.getJSONArray("items");
                                    if (itemsArray.length() > 0) {

                                        for (int i = 0; i < itemsArray.length(); i++) {

                                            JSONObject itemObj = (JSONObject) itemsArray.get(i);
                                            Main item = new Main(itemObj);
                                            mList.add(item);
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();

                        } finally {

                            FinishLoad();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (!isAdded() || getActivity() == null) {

                    Log.e(mActivity, "Fragment Not Added to Activity");
                    return;
                }

                Log.e(mActivity, "Error: " + error.toString());
                FinishLoad();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("clientId", Constants.CLIENT_ID);
//                params.put("updateAt", Integer.toString(0));

                return params;
            }
        };

        VolleySingleton.getInstance(mContext).addToRequestQueue(jsonReq);
    }

    public void FinishLoad() {
        if (mAdapter.getItemCount() == 0) {

            if (this.isVisible()) {

                showMessage();
            }

        } else {

            hideMessage();
        }
        mAdapter.notifyDataSetChanged();
    }


    public void showMessage() {

        mMessage.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {

        mMessage.setVisibility(View.GONE);
    }
}