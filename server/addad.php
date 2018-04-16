<?php
include_once("init.php");
try {
    echo "\r<br\r>Add Ad - START";

    $currentTime = time();
    $ads = new ads($dbo);

    $ad = array("fromUserId" => 1,
        "adType" => 0,
        "status" => 0,
        "segment" => "Default",
        "location" => "Global",
        "deviceVersion" => 21,
        "weight" => 51,
        "price" => 0,
        "title" => "Message AI - Write Better Messages (Free)",
        "subtitle" => "Send better, more positive messages and improve relationships 💝",
        "description" => "Have you ever sent a text you later regretted? Maybe you were too negative or angry at the time. Message AI warns you when you're about to send a message that could harm your relationships!

In addition, Message AI helps you understand how people really feel by analyzing conversations in Messenger, WhatsApp, Tinder, Snapchat, Kik, Instagram, and Line. All messaging apps are supported!

<b>How it works: </b>
Our MessageIQ artificial intelligence analyzes your messages and shows you your Positivity Score. We score each word from -100 to 100 so you can identify positive/negative emotions.

Better communication is the #1 way to improve relationships. Many people don't realize how angry they sound when tired and end up hurting the people they care about unintentionally! 

<b>We're in this together</b>
Message AI is the friend that always looks out for you. We'll not only warn you if you sound negative, we'll also show you when your friends are being negative. 

<b>Features: </b>
Find out if your crush likes you back ❤️
Who secretly likes you? Discover your secret admirers 😙
Monitor your relationship with artificial intelligence!
Be more positive and increase your likability 🔥
Identify passive aggressiveness so you can defuse toxic situations.
Uncover hidden attitudes people have. How do people REALLY feel?
Pick up on warning signals that someone's about to ghost you with AI 👻
Build better relationships with improve communication. 
Be a better friend and more positive person!",
        "descriptionShort" => "Message AI helps you sound more positive in your messages, increasing your likability and improving relationships 💝",
        "category" => "Social",
        "rating" => 5.0,
        "installs" => 500,
        "version" => "0.9.4",
        "developer" => "Straight Up",
        "email" => "support@messageai.co",
        "address" => "Kansas City, Kansas",
        "website" => "http://messageai.co",
        "linkUrl" => "https://play.google.com/store/apps/details?id=ai.message.lite",
        "packageName" => "ai.message.lite",
        "previewImgUrl" => "https://lh3.googleusercontent.com/5wtW75qqbqk1-iprBeW4hAxy6iw56zX6EQ8mRBfoUlWWZtxYDpvtZw8EnjpdjJ7VnAUg=s128-rw",
        "imgUrl" => "https://lh3.googleusercontent.com/5wtW75qqbqk1-iprBeW4hAxy6iw56zX6EQ8mRBfoUlWWZtxYDpvtZw8EnjpdjJ7VnAUg=s256-rw",
        "previewVideoImgUrl" => "https://lh3.googleusercontent.com/gTxlSPIPzfe1NoBsryU5dkw5I9kuJRE6RsznCWav76MaoKHfm0YwbD7oF4AbuAxe6QE=w360",
        "videoUrl" => "",
        "text1" => "",
        "text2" => "",
        "text3" => "",
        "number1" => 0,
        "number2" => 0,
        "number3" => 0,
        "startAt" => $currentTime,
        "endAt" => 2000000000);

    $exists = $ads->existsApp($ad["packageName"]);
    if ($exists["error"])
    {
        $ads->add($ad);
        echo "\r<br\r>Ad Added";
    }
    else
    {
        echo "\r<br\r>Ad Exists - Not Added";
    }

    unset($ad);
    echo "\r<br\r>Add Ad - END";

} catch (Exception $e) {

    die ($e->getMessage());
}
