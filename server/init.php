<?php
include_once("db.php");

foreach ($C as $name => $val) {

    define($name, $val);
}

foreach ($B as $name => $val) {

    define($name, $val);
}

$dsn = "mysql:host=".DB_HOST.";dbname=".DB_NAME;
$dbo = new PDO($dsn, DB_USER, DB_PASS, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8mb4"));