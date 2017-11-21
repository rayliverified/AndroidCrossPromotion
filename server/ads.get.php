<?php
include_once("init.php");
if (!empty($_POST)) {

    $clientId = isset($_POST['clientId']) ? $_POST['clientId'] : 0;
    $updateAt = isset($_POST['updateAt']) ? $_POST['updateAt'] : 0;

    $clientId = helper::clearInt($clientId);
    $updateAt = helper::clearInt($updateAt);

    if ($clientId != CLIENT_ID) {

        api::printError(ERROR_UNKNOWN, "Error client Id.");
    }

    $result = array("error" => true,
                    "error_code" => ERROR_UNKNOWN);

    $ads = new ads($dbo);
    $result = $ads->get($updateAt);

    echo json_encode($result);
    exit;
}
