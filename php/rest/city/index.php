<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');

try {

    session_start();
    
    throwOnInvalidSession();

    
    $method = $_SERVER['REQUEST_METHOD'];

    if ($method == 'GET') {
        $entity = new Entity(connect(), 'city');
        $what = "city.id AS id, city.plz AS plz, city.city AS city";

        if (isset($_GET['id'])) {
            $result = $entity->load($what, $_GET['id']);
        } else if (isset($_GET['plz'])) {
            $result = $entity->fetch($what, "WHERE plz = ?", array($_GET['plz']));
        } else if (isset($_GET['city'])) {
            $city = "$_GET[city]";
            $result = $entity->fetch($what, "WHERE city = ?", array($city));
        } else if (isset($_GET['name'])) {
            $city = "%$_GET[name]%";
            $result = $entity->fetch($what, "WHERE city LIKE ? OR plz LIKE ? ORDER BY plz LIMIT 10", array($city, $city));
        } else {
            $result = $entity->fetch($what);
        }

        convertToInteger($result, array('id'));

        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);

    }
} catch (Exception $e) {
    exitError($e);
}
