<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/error.php');

try {

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
            $result = $entity->fetch($what, "WHERE city LIKE ?", array($city));
        } else {
            $result = $entity->fetch($what);
        }

        // convert ids from string to integer
        $count = count($result);
        for ($i =  0; $i < $count; $i++) {
            $result[$i]['id'] = intval($result[$i]['id']);
        }

        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);
    }
} catch (Exception $e) {
    exitError($e);
}
