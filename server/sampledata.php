<?php
include_once("init.php");
try {
    echo "Sample Data Creation - START";

    $currentTime = time();
    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 21,
        "weight" => 50,
        "price" => 0,
        "title" => "Crowdfunding Projects",
        "subtitle" => "Browse all crowdfunding projects from Kickstarter and Indiegogo in one app!",
        "description" => "What's inside?
✓ Crowdfunding Community - join the best crowdfunding community today!
✓ Top Project Feed - all crowdfunding projects from Kickstarter and Indiegogo ranked by popularity!
✓ Latest Projects Feed - find the newest projects here first.
✓ Vote on projects to level up and earn cool rewards.
✓ Comments - discuss projects with the crowdfunding community.
✓ Leaderboards - track your crowdfunding score and compete to be the top crowdfunder! 
✓ Submit new crowdfunding projects.

App Features:
☆ Native Android app w/smooth and elegant design.
☆ Personalizable profile page. 
☆ Real time voting and commenting platform.
☆ In-app messaging to chat with other crowdfunders.
☆ Integrated browser to view projects without leaving the app.

★ Where you can find us:
Web - http://crowdfunding.stream/
Twitter - http://twitter.com/crowdfunding
Email - admin@crowdfunding.stream",
        "descriptionShort" => "Browse all crowdfunding projects from Kickstarter and Indiegogo in one app! Built by crowdfunding enthusiasts, Crowdfunding Projects is the place to share and talk about the hottest new crowdfunding projects with fellow crowdfunding addicts.",
        "category" => "Social",
        "rating" => 5.0,
        "installs" => 10000,
        "version" => "6.1",
        "developer" => "Stream Inc",
        "email" => "admin@crowdfunding.stream",
        "address" => "801 Eldridge St.",
        "website" => "http://crowdfunding.stream",
        "linkUrl" => "https://play.google.com/store/apps/details?id=io.ideastarter",
        "packageName" => "io.ideastarter",
        "previewImgUrl" => "http://lh3.googleusercontent.com/R-vJInTblK1KBOqZaSDm_ac270QBHsiIcU9agHnN-rrp9K_lkN8rLzGIH8asCfkb420Q=w128-rw",
        "imgUrl" => "http://lh3.googleusercontent.com/R-vJInTblK1KBOqZaSDm_ac270QBHsiIcU9agHnN-rrp9K_lkN8rLzGIH8asCfkb420Q=w256-rw",
        "previewVideoImgUrl" => "https://lh3.googleusercontent.com/3coPDBP3zFcy0vriR2rhVn8BbpxIk_iXUOMzhLGHeIx35ZJ_JfyytshxvFt1QrVgnwc=h360-rw",
        "videoUrl" => "",
        "text1" => "",
        "text2" => "",
        "text3" => "",
        "number1" => 0,
        "number2" => 0,
        "number3" => 0,
        "startAt" => $currentTime,
        "endAt" => 2000000000);

    $ads = new ads($dbo);
    $result = $ads->add($ad);

    echo "\r<br\r>Sample Data Creation - END";

} catch (Exception $e) {

    die ($e->getMessage());
}
