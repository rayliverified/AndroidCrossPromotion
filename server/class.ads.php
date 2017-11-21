<?php
class ads extends db_connect
{
    private $requestFrom = 0;
    private $language = 'en';

    public function __construct($dbo = NULL, $requestFrom = 0)
    {
        parent::__construct($dbo);

        $this->setRequestFrom($requestFrom);
    }

    public function getAllCount()
    {
        $stmt = $this->db->prepare("SELECT count(*) FROM ads");
        $stmt->execute();

        return $number_of_rows = $stmt->fetchColumn();
    }

    public function getMaxIdAds()
    {
        $stmt = $this->db->prepare("SELECT MAX(id) FROM ads");
        $stmt->execute();

        return $number_of_rows = $stmt->fetchColumn();
    }

    public function exists($adsId)
    {
        $result = array("error" => true,
            "error_code" => ERROR_UNKNOWN);

        $stmt = $this->db->prepare("SELECT * FROM ads WHERE id = (:adsId) LIMIT 1");
        $stmt->bindParam(":adsId", $adsId, PDO::PARAM_INT);

        if ($stmt->execute()) {

            if ($stmt->rowCount() > 0) {

                $result = array("error" => false,
                    "error_code" => ERROR_SUCCESS);
            }
        }

        return $result;
    }

    public function info($adsId)
    {
        $result = array("error" => true,
            "error_code" => ERROR_UNKNOWN);

        $stmt = $this->db->prepare("SELECT * FROM ads WHERE id = (:adsId) LIMIT 1");
        $stmt->bindParam(":adsId", $adsId, PDO::PARAM_INT);

        if ($stmt->execute()) {

            if ($stmt->rowCount() > 0) {

                $row = $stmt->fetch();

                $result = array("error" => false,
                    "id" => $row['id'],
                    "fromUserId" => $row['fromUserId'],
                    "adType" => $row['adType'],
                    "status" => $row['status'],
                    "segment" => $row['segment'],
                    "location" => $row['location'],
                    "deviceVersion" => $row['deviceVersion'],
                    "weight" => $row['weight'],
                    "price" => $row['price'],
                    "title" => $row['title'],
                    "subtitle" => $row['subtitle'],
                    "description" => $row['description'],
                    "descriptionShort" => $row['descriptionShort'],
                    "category" => $row['category'],
                    "rating" => $row['rating'],
                    "installs" => $row['installs'],
                    "version" => $row['version'],
                    "developer" => $row['developer'],
                    "email" => $row['email'],
                    "address" => $row['address'],
                    "website" => $row['website'],
                    "linkUrl" => $row['linkUrl'],
                    "packageName" => $row['packageName'],
                    "previewImgUrl" => $row['previewImgUrl'],
                    "imgUrl" => $row['imgUrl'],
                    "previewVideoImgUrl" => $row['previewVideoImgUrl'],
                    "videoUrl" => $row['videoUrl'],
                    "text1" => $row['text1'],
                    "text2" => $row['text2'],
                    "text3" => $row['text3'],
                    "number1" => $row['number1'],
                    "number2" => $row['number2'],
                    "number3" => $row['number3'],
                    "createAt" => $row['createAt'],
                    "updateAt" => $row['updateAt'],
                    "startAt" => $row['startAt'],
                    "endAt" => $row['endAt'],
                    "removeAt" => $row['removeAt'],
                    "views" => $row['views'],
                    "clicks" => $row['clicks'],
                    "sales" => $row['sales']);
            }
        }

        return $result;
    }

    public function get($updateAt = 0)
    {
        $result = array("error" => false,
            "error_code" => ERROR_SUCCESS,
            "items" => array());

        $currentTime = time();
        $stmt = $this->db->prepare("SELECT * FROM ads WHERE removeAt = 0 AND updateAt > (:updateAt) AND endAt > (:currentTime) ORDER BY id DESC");
        $stmt->bindParam(':updateAt', $updateAt, PDO::PARAM_INT);
        $stmt->bindParam(':currentTime', $currentTime, PDO::PARAM_INT);

        if ($stmt->execute()) {

            if ($stmt->rowCount() > 0) {

                while ($row = $stmt->fetch()) {

                    $ad = array("id" => $row['id'],
                        "fromUserId" => $row['fromUserId'],
                        "adType" => $row['adType'],
                        "status" => $row['status'],
                        "segment" => $row['segment'],
                        "location" => $row['location'],
                        "deviceVersion" => $row['deviceVersion'],
                        "weight" => $row['weight'],
                        "price" => $row['price'],
                        "title" => $row['title'],
                        "subtitle" => $row['subtitle'],
                        "description" => $row['description'],
                        "descriptionShort" => $row['descriptionShort'],
                        "category" => $row['category'],
                        "rating" => $row['rating'],
                        "installs" => $row['installs'],
                        "version" => $row['version'],
                        "developer" => $row['developer'],
                        "email" => $row['email'],
                        "address" => $row['address'],
                        "website" => $row['website'],
                        "linkUrl" => $row['linkUrl'],
                        "packageName" => $row['packageName'],
                        "previewImgUrl" => $row['previewImgUrl'],
                        "imgUrl" => $row['imgUrl'],
                        "previewVideoImgUrl" => $row['previewVideoImgUrl'],
                        "videoUrl" => $row['videoUrl'],
                        "text1" => $row['text1'],
                        "text2" => $row['text2'],
                        "text3" => $row['text3'],
                        "number1" => $row['number1'],
                        "number2" => $row['number2'],
                        "number3" => $row['number3'],
                        "createAt" => $row['createAt'],
                        "updateAt" => $row['updateAt'],
                        "startAt" => $row['startAt'],
                        "endAt" => $row['endAt'],
                        "removeAt" => $row['removeAt']);

                    array_push($result['items'], $ad);
                    unset($ad);
                }
            }
        }

        return $result;
    }

    public function removeByUser($fromUserId)
    {

        $result = array("error" => true,
            "error_code" => ERROR_UNKNOWN,
            "count" => 0);

        $stmt = $this->db->prepare("SELECT id FROM ads WHERE fromUserId = (:fromUserId) AND removeAt = 0");
        $stmt->bindParam(':fromUserId', $fromUserId, PDO::PARAM_STR);

        if ($stmt->execute()) {

            while ($row = $stmt->fetch()) {

                $this->remove($row['id']);
            }

            $result = array("error" => false,
                "error_code" => ERROR_SUCCESS,
                "count" => $stmt->rowCount());
        }

        return $result;
    }

    public function remove($adsId)
    {
        $result = array("error" => true);

        $itemInfo = $this->exists($adsId);

        if ($itemInfo['error'] === true) {

            return $result;
        }

        $currentTime = time();

        $stmt = $this->db->prepare("UPDATE ads SET removeAt = (:removeAt) WHERE id = (:adsId)");
        $stmt->bindParam(":adsId", $adsId, PDO::PARAM_INT);
        $stmt->bindParam(":removeAt", $currentTime, PDO::PARAM_INT);

        if ($stmt->execute()) {

            $result = array("error" => false);
        }

        return $result;
    }

    public function setLanguage($language)
    {
        $this->language = $language;
    }

    public function getLanguage()
    {
        return $this->language;
    }

    public function setRequestFrom($requestFrom)
    {
        $this->requestFrom = $requestFrom;
    }

    public function getRequestFrom()
    {
        return $this->requestFrom;
    }
}
