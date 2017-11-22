package stream.crosspromotion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

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

    public void setItem(final Main item) {

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

        VolleySingleton.getInstance(mContext).getImageLoader().get(item.getPreviewImageUrl(), ImageLoader.getImageListener(mImage, R.drawable.icon_apk_circle, R.drawable.icon_apk_circle));
        mRating.setText(Double.toString(item.getRating()));

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
