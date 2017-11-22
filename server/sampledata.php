<?php
include_once("init.php");
try {
    echo "\r<br\r>Sample Data Creation - START";

    $currentTime = time();
    $ads = new ads($dbo);

    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 21,
        "weight" => 50,
        "price" => 0,
        "title" => "Crowdfunding Projects",
        "subtitle" => "Browse all crowdfunding projects in one app!",
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
        "version" => "3.1.0",
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
    $ads->add($ad);
    unset($ad);

    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 15,
        "weight" => 49,
        "price" => 0,
        "title" => "Rocket Notes",
        "subtitle" => "The World's Fastest Note Taking App!",
        "description" => "Rocket Notes Features:
🚀 Creating a new note is as easy as typing a text message! 
🚀 Just start writing and Rocket Note will do the rest. 
🚀 Notes are saved automatically.
🚀 Minimalistic - no more worrying about formatting or how the note looks. 
🚀 Taking a note is easy; leave the fonts, colors, and bold/italics to Microsoft Word.
🚀 Recent notes are always visible from your home screen and never more than ONE tap away.
🚀 No more opening an app to take notes; just start writing instead!

Rocket Images Features:
☆ Forget writing, snap a picture instead! 
☆ Recent photo notes are displayed in a home screen gallery and instantly visible. 
☆ Image notes are only ONE tap away!
☆ Tapping on a thumbnail opens the picture in full screen mode for viewing and sharing.
☆ Photos are stored separate from your gallery app. 
☆ No more searching for important notes buried under your selfies, having work documents show up in your slideshows, or getting boring images backed up via Google Photos. 

Rocket Share Features:
✓ Share text and images directly to Rocket Notes. 
✓ Does not interrupt what you are doing.
✓ Image URLs shared to the app are automatically downloaded! 

★ Where you can find us: ★
Twitter - http://twitter.com/rayliverified",
        "descriptionShort" => "Fast. Simple. Create a note in one tap! Create image and text notes directly from your home screen!",
        "category" => "Productivity",
        "rating" => 5.0,
        "installs" => 500,
        "version" => "1.1.0",
        "developer" => "Stream Inc",
        "email" => "admin@apprewards.org",
        "address" => "801 Eldridge St.",
        "website" => "http://apprewards.org/rocketnotes/index.html",
        "linkUrl" => "https://play.google.com/store/apps/details?id=stream.rocketnotes",
        "packageName" => "stream.rocketnotes",
        "previewImgUrl" => "https://lh3.googleusercontent.com/tYGJBG8mc7lwC0ZxQUxif2FVMFI8L8xRkPON0ytkWVPTI67ggkrgDl3JpRu9jW0W3sLJ=w128-rw",
        "imgUrl" => "https://lh3.googleusercontent.com/tYGJBG8mc7lwC0ZxQUxif2FVMFI8L8xRkPON0ytkWVPTI67ggkrgDl3JpRu9jW0W3sLJ=w256-rw",
        "previewVideoImgUrl" => "https://lh3.googleusercontent.com/s1IcJ6DUCPUgl2ZxGLqld8ROsARVBPDemnsfcfda0vJ8SQsoAOmbinTCcqpFfc48IA=h360-rw",
        "videoUrl" => "",
        "text1" => "",
        "text2" => "",
        "text3" => "",
        "number1" => 0,
        "number2" => 0,
        "number3" => 0,
        "startAt" => $currentTime,
        "endAt" => 2000000000);
    $ads->add($ad);
    unset($ad);

    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 15,
        "weight" => 48,
        "price" => 0,
        "title" => "Doodle Donut",
        "subtitle" => "Play the yummiest arcade game ever!",
        "description" => "Gameplay Highlights:
✓ Satisfy your donut cravings without gaining a single pound! 
✓ Feast on visually delightful doodle art!
✓ Experience classic arcade style gameplay!
✓ Fight against mouthwatering donuts!
✓ Collect refreshing coffees and level up!
✓ Test your reflexes with daring acrobatic maneuvers!
✓ Realistic donut calorie counts!
✓ Help Tummy Yummy™ burn calories and lose weight!

Game Features:
☆ Enjoy beautiful high definition doodle art
☆ Fabulously fluid 60 FPS action
☆ No personal information collected, safe for kids!
☆ Impressively intuitive and responsive controls
☆ Discover over 12 flavor-filled donuts
☆ Uncover new donut powers
☆ Unlock over 30 achievements
☆ Compete in global leaderboards
☆ Track realistic calorie counts and number of donuts ate
☆ Relax in the coffee store with great music
☆ Can you discover all the easter eggs?

★ Where you can find us:
Web - http://apprewards.org/doodledonut/index.html
Twitter - http://twitter.com/rayliverified",
        "descriptionShort" => "Jump to battle tasty donuts and drink delicious coffees in the most action-packed donut game ever! Start your coffee fueled adventure today and jump as high as you can in the yummiest game ever!",
        "category" => "Arcade",
        "rating" => 5.0,
        "installs" => 500,
        "version" => "6.0",
        "developer" => "Stream Inc",
        "email" => "admin@apprewards.org",
        "address" => "801 Eldridge St.",
        "website" => "http://apprewards.org/doodledonut/index.html",
        "linkUrl" => "https://play.google.com/store/apps/details?id=com.DoodleDonut",
        "packageName" => "com.DoodleDonut",
        "previewImgUrl" => "https://lh3.googleusercontent.com/L2veVvuA8k1yjpYQj7hxb1yocpGgt-lvFEfpzMYCqPUsTwZihcev2pg5zkeBD3ChrSI=w128-rw",
        "imgUrl" => "https://lh3.googleusercontent.com/L2veVvuA8k1yjpYQj7hxb1yocpGgt-lvFEfpzMYCqPUsTwZihcev2pg5zkeBD3ChrSI=w256-rw",
        "previewVideoImgUrl" => "https://lh3.googleusercontent.com/cq0RTFJCsoRqujcSA64kzqJr2tO9U5n8XsypMFRITq8oB2ui_8N09DpzGsYfFiG4W_Y=h360-rw",
        "videoUrl" => "",
        "text1" => "",
        "text2" => "",
        "text3" => "",
        "number1" => 0,
        "number2" => 0,
        "number3" => 0,
        "startAt" => $currentTime,
        "endAt" => 2000000000);
    $ads->add($ad);
    unset($ad);

    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 15,
        "weight" => 47,
        "price" => 0,
        "title" => "Blank Icon/Widget",
        "subtitle" => "100% transparent app icon and widgets.",
        "description" => "Amazing Features:
✓ Most blank and transparent app in the app store
✓ Invisible app icon and widget
✓ Blank Widgets help customize homescreen
✓ Use blank icons as placeholders to add additional screens to your launcher
✓ Prevent newly installed apps from messing up your app layout
✓ Prank your friends by placing invisible widgets on their homescreen

NOTE: The new Adaptive Icons introduced in Android Oreo makes the app icon white and not transparent. The only way to create a blank icon placeholder is to use Blank Widgets. (applies to Android 8.0+ and Samsung users)

★ Where you can find us:
Twitter - http://twitter.com/rayliverified",
        "descriptionShort" => "Blank Icon is a completely transparent app icon for homescreen customization and testing purposes. Blank Icon also includes Blank Widgets that can be used to customize the homescreen. Check out these amazing features!",
        "category" => "Tools",
        "rating" => 5.0,
        "installs" => 10000,
        "version" => "2.0.9",
        "developer" => "Stream Inc",
        "email" => "admin@apprewards.org",
        "address" => "801 Eldridge St.",
        "website" => "http://apprewards.org/blankicon/index.html",
        "linkUrl" => "https://play.google.com/store/apps/details?id=com.blankicon",
        "packageName" => "com.blankicon",
        "previewImgUrl" => "https://lh3.googleusercontent.com/CT1M2pKlUhGx4w5UHqarn6oSU_sa7L7XRW2-hQrfNi9oou6W81PbJnWi-9PbEfC_3g=w128-rw",
        "imgUrl" => "https://lh3.googleusercontent.com/CT1M2pKlUhGx4w5UHqarn6oSU_sa7L7XRW2-hQrfNi9oou6W81PbJnWi-9PbEfC_3g=w256-rw",
        "previewVideoImgUrl" => "https://lh3.googleusercontent.com/JMQxI2HkyWvWMgeBmVg7cUOsoqdym5lnxEjKQeZ8D0wTqe2UFJRGklJT-_dQXVlNJPeg=h360-rw",
        "videoUrl" => "",
        "text1" => "",
        "text2" => "",
        "text3" => "",
        "number1" => 0,
        "number2" => 0,
        "number3" => 0,
        "startAt" => $currentTime,
        "endAt" => 2000000000);
    $ads->add($ad);
    unset($ad);

    echo "\r<br\r>Sample Data Creation - END";

} catch (Exception $e) {

    die ($e->getMessage());
}
