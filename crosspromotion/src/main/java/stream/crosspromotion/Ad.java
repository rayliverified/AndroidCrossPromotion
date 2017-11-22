package stream.crosspromotion;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class Ad extends Application implements Parcelable {

    public long id;
    public long fromUserId;
    public int adType;
    public int status;
    public String segment;
    public String location;
    public int deviceVersion;
    public int weight;
    public int price;
    public String title;
    public String subTitle;
    public String description;
    public String descriptionShort;
    public String category;
    public double rating;
    public int installs;
    public String version;
    public String developer;
    public String email;
    public String address;
    public String website;
    public String linkUrl;
    public String packageName;
    public String previewImageUrl;
    public String imageUrl;
    public String previewVideoImageUrl;
    public String videoUrl;
    public String text1;
    public String text2;
    public String text3;
    public int number1;
    public int number2;
    public int number3;
    public int createAt;
    public int updateAt;
    public int startAt;
    public int endAt;
    public int removeAt;

    private final String mActivity = this.getClass().getSimpleName();

    public Ad() {

    }

    public Ad(JSONObject jsonData) {
        try {
            this.setId(jsonData.getLong("id"));
            this.setFromUserId(jsonData.getLong("fromUserId"));
            this.setAdType(jsonData.getInt("adType"));
            this.setStatus(jsonData.getInt("status"));
            this.setSegment(jsonData.getString("segment"));
            this.setLocation(jsonData.getString("location"));
            this.setDeviceVersion(jsonData.getInt("deviceVersion"));
            this.setWeight(jsonData.getInt("weight"));
            this.setPrice(jsonData.getInt("price"));
            this.setTitle(jsonData.getString("title"));
            this.setSubTitle(jsonData.getString("subtitle"));
            this.setDescription(jsonData.getString("description"));
            this.setDescriptionShort(jsonData.getString("descriptionShort"));
            this.setCategory(jsonData.getString("category"));
            this.setRating(jsonData.getDouble("rating"));
            this.setInstalls(jsonData.getInt("installs"));
            this.setVersion(jsonData.getString("version"));
            this.setDeveloper(jsonData.getString("developer"));
            this.setEmail(jsonData.getString("email"));
            this.setAddress(jsonData.getString("address"));
            this.setWebsite(jsonData.getString("website"));
            this.setLinkUrl(jsonData.getString("linkUrl"));
            this.setMyPackageName(jsonData.getString("packageName"));
            this.setPreviewImageUrl(jsonData.getString("previewImgUrl"));
            this.setImageUrl(jsonData.getString("imgUrl"));
            this.setPreviewVideoImageUrl(jsonData.getString("previewVideoImgUrl"));
            this.setVideoUrl(jsonData.getString("videoUrl"));
            this.setText1(jsonData.getString("text1"));
            this.setText2(jsonData.getString("text2"));
            this.setText3(jsonData.getString("text3"));
            this.setNumber1(jsonData.getInt("number1"));
            this.setNumber2(jsonData.getInt("number2"));
            this.setNumber3(jsonData.getInt("number3"));
            this.setCreateAt(jsonData.getInt("createAt"));
            this.setUpdateAt(jsonData.getInt("updateAt"));
            this.setStartAt(jsonData.getInt("startAt"));
            this.setEndAt(jsonData.getInt("endAt"));
            this.setRemoveAt(jsonData.getInt("removeAt"));
        } catch (Throwable t) {

            Log.e(mActivity, "Error JSON: \"" + jsonData.toString() + "\"");

        } finally {

            Log.d(mActivity, "JSON: " + jsonData.toString());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(int deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getInstalls() {
        return installs;
    }

    public void setInstalls(int installs) {
        this.installs = installs;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMyPackageName() {
        return packageName;
    }

    public void setMyPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreviewVideoImageUrl() {
        return previewVideoImageUrl;
    }

    public void setPreviewVideoImageUrl(String previewVideoImageUrl) {
        this.previewVideoImageUrl = previewVideoImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getCreateAt() {
        return createAt;
    }

    public void setCreateAt(int createAt) {
        this.createAt = createAt;
    }

    public int getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(int updateAt) {
        this.updateAt = updateAt;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getEndAt() {
        return endAt;
    }

    public void setEndAt(int endAt) {
        this.endAt = endAt;
    }

    public int getRemoveAt() {
        return removeAt;
    }

    public void setRemoveAt(int removeAt) {
        this.removeAt = removeAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.fromUserId);
        dest.writeInt(this.adType);
        dest.writeInt(this.status);
        dest.writeString(this.segment);
        dest.writeString(this.location);
        dest.writeInt(this.deviceVersion);
        dest.writeInt(this.weight);
        dest.writeInt(this.price);
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.description);
        dest.writeString(this.descriptionShort);
        dest.writeString(this.category);
        dest.writeDouble(this.rating);
        dest.writeInt(this.installs);
        dest.writeString(this.version);
        dest.writeString(this.developer);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.website);
        dest.writeString(this.linkUrl);
        dest.writeString(this.packageName);
        dest.writeString(this.previewImageUrl);
        dest.writeString(this.imageUrl);
        dest.writeString(this.previewVideoImageUrl);
        dest.writeString(this.videoUrl);
        dest.writeString(this.text1);
        dest.writeString(this.text2);
        dest.writeString(this.text3);
        dest.writeInt(this.number1);
        dest.writeInt(this.number2);
        dest.writeInt(this.number3);
        dest.writeInt(this.createAt);
        dest.writeInt(this.updateAt);
        dest.writeInt(this.startAt);
        dest.writeInt(this.endAt);
        dest.writeInt(this.removeAt);
    }

    protected Ad(Parcel in) {
        this.id = in.readLong();
        this.fromUserId = in.readLong();
        this.adType = in.readInt();
        this.status = in.readInt();
        this.segment = in.readString();
        this.location = in.readString();
        this.deviceVersion = in.readInt();
        this.weight = in.readInt();
        this.price = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.description = in.readString();
        this.descriptionShort = in.readString();
        this.category = in.readString();
        this.rating = in.readDouble();
        this.installs = in.readInt();
        this.version = in.readString();
        this.developer = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.website = in.readString();
        this.linkUrl = in.readString();
        this.packageName = in.readString();
        this.previewImageUrl = in.readString();
        this.imageUrl = in.readString();
        this.previewVideoImageUrl = in.readString();
        this.videoUrl = in.readString();
        this.text1 = in.readString();
        this.text2 = in.readString();
        this.text3 = in.readString();
        this.number1 = in.readInt();
        this.number2 = in.readInt();
        this.number3 = in.readInt();
        this.createAt = in.readInt();
        this.updateAt = in.readInt();
        this.startAt = in.readInt();
        this.endAt = in.readInt();
        this.removeAt = in.readInt();
    }

    public static final Creator<Ad> CREATOR = new Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel source) {
            return new Ad(source);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };
}
