<?php
include_once("init.php");
try {
    /*
     * Main Ad Table
     * id - primary key.
     * fromUserId - id of user that created ad.
     * adType - category type of app data returned.
     * segment -
     * location - geo targeting.
     * deviceVersion - limit ad to supported devices only. Use integer values (e.g. Android Nougat  = 711)
     * weight - prioritize ad display frequency and order. Use 0-100.
     * App details -
     * title, description, descriptionShort, category, rating, installs, version, developer, email, address, website
     * subtitle - ad secondary text/tagline.
     * linkUrl - app link.
     * packageName - app package name.
     * imgUrl, previewImgUrl - high and low res ad images.
     * videoUrl, previewVideoImgUrl - video URL and placeholder image URL.
     * text1, text2, text3 - extra customizable text fields.
     * int1, int2, int3 - extra customizable number fields.
     * createAt - ad created time in UTC.
     * updateAt - ad updated time.
     * startAt - ad campaign begins displaying.
     * endAt- ad campaign stops displaying.
     * removeAt- ad removed time.
     * views - ad views.
     * clicks - ad clicks.
     * sales - ad conversions/installs.
     */
    $sth = $dbo->prepare("CREATE TABLE IF NOT EXISTS ads (
								id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
								fromUserId int(11) UNSIGNED DEFAULT 0,
								adType int(11) UNSIGNED DEFAULT 0,
								segment VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								location VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								deviceVersion int(11) UNSIGNED DEFAULT 0,
								weight int(11) UNSIGNED DEFAULT 0,
								title varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								subtitle varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								description varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								descriptionShort varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								category VARCHAR(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								rating DECIMAL(2, 3) NOT NULL DEFAULT 5,
								installs int(11) UNSIGNED DEFAULT 0,
								version VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								developer VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								email VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								address VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								website VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								linkUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								packageName VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								previewImgUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								imgUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								previewVideoImgUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
                                videoUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
                                text1 VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
                                text2 VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
                                text3 VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
                                int1 int(11) UNSIGNED DEFAULT 0,
                                int2 int(11) UNSIGNED DEFAULT 0,
                                int3 int(11) UNSIGNED DEFAULT 0,
								createAt int(11) UNSIGNED DEFAULT 0,
								updateAt int(11) UNSIGNED DEFAULT 0,
								startAt int(11) UNSIGNED DEFAULT 0,
								endAt int(11) UNSIGNED DEFAULT 0,
								removeAt int(11) UNSIGNED DEFAULT 0,
								views int(11) UNSIGNED DEFAULT 0,
								clicks int(11) UNSIGNED DEFAULT 0,
								sales int(11) UNSIGNED DEFAULT 0,
								PRIMARY KEY  (id)) ENGINE=MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci");
    $sth->execute();

    /*
     * Extra Ad Images Table
     * id - primary key.
     * adId - id of ad the image is tied to.
     * imgUrl, previewImageUrl - high and low res ad images.
     * createAt - image created time in UTC.
     * removeAt - image removed time.
     */
    $sth = $dbo->prepare("CREATE TABLE IF NOT EXISTS images (
								id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
								adId int(11) UNSIGNED DEFAULT 0,
								previewImgUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								imgUrl VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
								createAt int(11) UNSIGNED DEFAULT 0,
								removeAt int(11) UNSIGNED DEFAULT 0,
								PRIMARY KEY  (id)) ENGINE=MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci");
    $sth->execute();

    /*
     * Users Table
     * id - primary key.
     * advertisingId - unique user identifier.
     * accessToken - generated security token.
     * fcm_regid - Android FCM id.
     * ios_fcm_regid - iOS FCM id.
     * firstname, lastname, fullname, username - user data.
     * createAt - user creation time in UTC.
     * updateAt - user updated time.
     * u_agent - user device info.
     * ip_addr - user ip address.
     */
    $sth = $dbo->prepare("CREATE TABLE IF NOT EXISTS users (
								id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
								advertisingId varchar(50) DEFAULT '',
								accessToken varchar(50) DEFAULT '',
                                gcm_regid TEXT,
								ios_fcm_regid TEXT,
								firstname VARCHAR(75) NOT NULL DEFAULT '',
								lastname VARCHAR(75) NOT NULL DEFAULT '',
                                fullname VARCHAR(150) NOT NULL DEFAULT '',
                                username VARCHAR(50) NOT NULL DEFAULT '',
                                createAt int(11) UNSIGNED DEFAULT 0,
                                updateAt int(11) UNSIGNED DEFAULT 0,
								u_agent varchar(300) DEFAULT '',
								ip_addr CHAR(32) NOT NULL DEFAULT '',
								PRIMARY KEY  (id), UNIQUE KEY (advertisingId)) ENGINE=MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci");
    $sth->execute();

    /*
     * Ad Request Log Table
     * id - primary key.
     * accountId - id of user requesting ad.
     * accessToken - generated security token.
     * createAt - ad request time in UTC.
     * u_agent - user device info.
     * ip_addr - user ip address.
     */
    $sth = $dbo->prepare("CREATE TABLE IF NOT EXISTS access_data (
								id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
								accountId int(11) UNSIGNED NOT NULL,
								accessToken varchar(50) DEFAULT '',
								createAt int(10) UNSIGNED DEFAULT 0,
								u_agent varchar(300) DEFAULT '',
								ip_addr CHAR(32) NOT NULL DEFAULT '',
								PRIMARY KEY  (id)) ENGINE=MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci");
    $sth->execute();

    /*
     * Ad Analytics Events Table
     * id - primary key.
     * accountId - id of user analytic event is tied to.
     * analytics_type - category type of analytics event (e.g. click, view, sale)
     * statId - id of ad analytic event is tied to.
     * statInt - integer stat value.
     * statText - text stat value.
     * notifyId - internal notification id.
     * createAt - analytic event created time in UTC.
     * removeAt - analytic event remove time.
     * u_agent - user device info.
     * ip_addr - user ip address.
     */
    $sth = $dbo->prepare("CREATE TABLE IF NOT EXISTS analytics (
								id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
								accountId int(11) UNSIGNED DEFAULT 0,
								analytics_type int(11) UNSIGNED DEFAULT 0,
								statId int(11) UNSIGNED DEFAULT 0,
								statInt int(11) UNSIGNED DEFAULT 0,
								statText varchar(50) DEFAULT '',
                                notifyId int(11) UNSIGNED DEFAULT 0,
								createAt int(11) UNSIGNED DEFAULT 0,
								removeAt int(11) UNSIGNED DEFAULT 0,
								u_agent varchar(300) DEFAULT '',
								ip_addr CHAR(32) NOT NULL DEFAULT '',
								PRIMARY KEY  (id)) ENGINE=MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci");
    $sth->execute();

    echo "Database creation success!";

} catch (Exception $e) {

    die ($e->getMessage());
}
