package stream.crosspromotion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdListFragment extends Fragment {

    TextView mMessage;

    RecyclerView mRecyclerView;
    MainAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<Ad> mList;
    DatabaseHelper dbHelper;

    Boolean restore = false;

    Context mContext;
    final String mActivity = this.getClass().getSimpleName();
    private static final String STATE_LIST = "State Adapter Data";

    public AdListFragment() {
    }

    public static AdListFragment newInstance() {
        return new AdListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        dbHelper = DatabaseHelper.getInstance(mContext);

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
                GetItems();
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
            LoadItems();
            GetItems();
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putParcelableArrayList(STATE_LIST, mList);
    }

    public void GetItems() {

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
                                        dbHelper.AddAdsBatch(itemsArray);
                                        LoadItems();
                                    }
                                }
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();

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
                params.put("updateAt", Integer.toString(dbHelper.GetAdsLatestUpdate(dbHelper.GetMaxAdID(), Constants.AD_LIMIT)));

                return params;
            }
        };

        VolleySingleton.getInstance(mContext).addToRequestQueue(jsonReq);
    }

    public void LoadItems() {

        ArrayList<Ad> items;
        if (mList != null && !mList.isEmpty()) {
            items = dbHelper.GetAds(Constants.AD_LIMIT);
        } else {
            mList.clear();
            items = dbHelper.GetAds(Constants.AD_LIMIT);
        }
        mList.addAll(items);

        FinishLoad();
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

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        Context mContext;
        ArrayList<Ad> mList;

        public final String mActivity = this.getClass().getSimpleName();

        public MainAdapter(Context context, ArrayList<Ad> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            return new MainViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            Ad item = mList.get(position);
            holder.setItem(item);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public Ad getItem(int position) {
            return mList.get(position);
        }

        public class MainViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout mLayout;
            private ImageView mImage;
            private TextView mRating;
            private TextView mTitle;
            private TextView mDescription;
            private TextView mPrice;
            private TextView mBtnInstall;

            View.OnClickListener itemClick;

            Context mContext;
            private final String mActivity = this.getClass().getSimpleName();

            public MainViewHolder(View itemView) {

                super(itemView);

                mLayout = itemView.findViewById(R.id.container);
                mImage = itemView.findViewById(R.id.image);
                mRating = itemView.findViewById(R.id.rating);
                mTitle = itemView.findViewById(R.id.title);
                mDescription = itemView.findViewById(R.id.description);
                mPrice = itemView.findViewById(R.id.price);
                mBtnInstall = itemView.findViewById(R.id.btn_install);

                mContext = itemView.getContext();
            }

            public void setItem(final Ad item) {

                itemClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String packageName = item.getMyPackageName();
                        if (Utils.isAppInstalled(mContext, "com.android.vending")) {
                            intent.setData(Uri.parse("market://details?id=" + packageName));
                            try {
                                mContext.startActivity(intent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                intent.setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s", packageName)));
                                mContext.startActivity(intent);
                            }
                        } else {
                            intent.setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s", packageName)));
                            mContext.startActivity(intent);
                        }
                    }
                };
                mLayout.setOnClickListener(itemClick);
                mImage.setOnClickListener(itemClick);
                mBtnInstall.setOnClickListener(itemClick);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    VolleySingleton.getInstance(mContext).getImageLoader().get(item.getPreviewImageUrl(), ImageLoader.getImageListener(mImage, R.drawable.icon_apk_circle, R.drawable.icon_apk_circle));
                }
                else
                {
                    VolleySingleton.getInstance(mContext).getImageLoader().get(item.getPreviewImageUrl(), ImageLoader.getImageListener(mImage, R.drawable.icon_apk_circle_old, R.drawable.icon_apk_circle_old));
                }                mRating.setText(Double.toString(item.getRating()));

                mTitle.setText(item.getTitle());
                mDescription.setText(item.getSubTitle());

                if (item.getPrice() == 0)
                {
                    mPrice.setText("FREE");
                }
                else
                {
                    mPrice.setText("$" + item.getPrice()/100);
                }
            }
        }
    }
}

