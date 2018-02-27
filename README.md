[![GitHub release](https://img.shields.io/github/release/searchy2/AndroidCrossPromotion.svg?style=flat-square)](https://github.com/searchy2/AndroidCrossPromotion/releases) [![Libraries.io for GitHub](https://img.shields.io/librariesio/github/searchy2/AndroidCrossPromotion.svg?style=flat-square)](https://github.com/searchy2/AndroidCrossPromotion) [![Github file size](https://img.shields.io/badge/Size-91%20KB-e91e63.svg?style=flat-square)](http://www.methodscount.com/?lib=com.github.searchy2%3AAndroidCrossPromotion%3A1.3.1) [![Library methods count](https://img.shields.io/badge/Methods%20count-332-e91e63.svg?style=flat-square)](http://www.methodscount.com/?lib=com.github.searchy2%3AAndroidCrossPromotion%3A1.3.1)

# Android Cross Promotion

Android Cross Promotion is a self hosted cross promotion platform for your own apps. Advertise your own apps within your apps easily. Setup in under 20 minutes!

Android Cross Promotion is part of the Custom UI collection of ready-made, essential, and elegant Android code libraries. This library was created to give developers a way to cross promote their own apps without the need for bloated ad libraries or Google Play Services. Android Cross Promotion gives developers the ability to update the apps they are promoting from the backend and have changes reflected in their apps instantaneously.  

# Features
* **Complete control** - add, remove, and update promoted apps in realtime. 
* **Dynamic updates** - changes are made via server, not in app code. 
* **Local storage** - promoted app data is cached for instantaneous loading. 
* **Smart caching** - changes to data are tracked to minimize network traffic. 
* **Small size** - adds very little bloat. 
* **Few methods** - uses far fewer methods than any ad libraries or Google Play Services. 
* **Miminal dependencies** - this library requires only one dependency! 
* **Highly customizable** - code can be easily modified and extended.
* **Easy and fast** - integrate this libary into your project in 20 minutes!

---
# 20 Minute Setup Guide

## Server

### Server Upload
Upload all files in `server` to your web server. The backend can run from any subdomain or folder. 

Server Requirements: 
>PHP 5.5 - 5.6

>MySQL

>PDO Extension

### Database Setup
Create a new database and user with all privileges. 
Open `db.php` and fill the following fields with your credentials.

```php
//Database credentials.
$C['DB_HOST'] = "localhost";                      //usually localhost
$C['DB_USER'] = "ray_crosspromo";                 //DB user
$C['DB_PASS'] = "12345678";                       //DB password
$C['DB_NAME'] = "ray_crosspromotion";             //DB name
```
Initialize your database by running `initialize.php` from your browser. 

### Create App Promotion
Currently, there is no GUI/Frontend to help you insert app data into the database. Instead, you can create app promotional listings directly in your database. 
Open the `ads` table in your database to create your app listings. 

A few sample app listings are provided as examples. 

```text
Main Ad Table
     * id - primary key.
     * fromUserId - id of user that created ad.
     * adType - category type of app data returned.
     * segment - campaign A/B segmentation.
     * location - geo targeting.
     * deviceVersion - limit ad to supported devices only. Use minimum API values (e.g. Android Nougat  = 26)
     * weight - prioritize ad display frequency and order. Use 0-100.
     * price - cost of app in cents. (e.g. $0.99 = 99).
     * title, description, descriptionShort, category, rating, installs, version, developer, email, address, website - app details.
     * subtitle - ad secondary text/tagline.
     * linkUrl - app link.
     * packageName - app package name.
     * imgUrl, previewImgUrl - high and low res ad images.
     * videoUrl, previewVideoImgUrl - video URL and placeholder image URL.
     * text1, text2, text3 - extra customizable text fields.
     * number1, number2, number3 - extra customizable number fields.
     * createAt - ad created time in UTC.
     * updateAt - ad updated time.
     * startAt - ad campaign begins displaying.
     * endAt- ad campaign stops displaying.
     * removeAt- ad removed time.
     * views - ad views.
     * clicks - ad clicks.
     * sales - ad conversions/installs.
```
Once the app listings have been created, proceed to add it to your application. 

## Android App

### Gradle Dependency
Add this line to your `build.gradle` project. Use the latest release version for the version code. 

```java
repositories {
    maven { url 'https://jitpack.io' }
}
implementation 'com.github.searchy2:AndroidCrossPromotion:latest-version'
```

Add the following dependencies.

```java
implementation 'com.android.support:appcompat-v7:latest-version'
implementation 'com.android.support:design:latest-version'
implementation 'com.android.volley:volley:latest-version'
```

### Server Declaration
Declare your custom ad server URL by adding a `CustomAds` metavalue to AndroidManifest.xml.

```java
<meta-data android:value="http://apprewards.org/crosspromotion/" android:name="CustomAds"/>
```

### Manifest Override
Add `tools:replace="android:theme"` to your application tag in AndroidManifest.xml.

```java
<application
    android:name=".App"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme"
    tools:replace="android:theme">
```
This line allows you to override the library theme for customization. 

### Theme Customization
Add an `AdTheme` to your `styles.xml`.
```java
<style name="AdTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
```
Using the default values allows you to match the library appearance to your app. 

# Usage

Just add these lines whereever you want to open the cross promotion library, that's it. 

```java
Intent intent = new Intent(mContext, stream.crosspromotion.AdActivity.class);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
```
This code starts the `AdActivity` Activity which loads your list of apps. 

---
# Customization


`AD_DEVELOPER_ID` - Setting this value enables the Google Play icon in the Toolbar which links to your developer page with all your apps. 
Learn more about [Developer Pages](https://support.google.com/googleplay/android-developer/answer/6226441). 

`AD_TITLE` - Set a custom Toolbar title. Leaving this blank sets "More Apps" as the title by default. 

Here is an example:

```java
Intent intent = new Intent(mContext, stream.crosspromotion.AdActivity.class);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.putExtra(AdActivity.AD_DEVELOPER_ID, "8647251961827166428");
intent.putExtra(AdActivity.AD_TITLE, "More Apps from Stream");
mContext.startActivity(intent);
```
