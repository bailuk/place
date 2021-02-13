<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/error.php');

try {
    $entity = new Entity(connect(), 'city');

    if (isset($_GET)) {
        $what = "city.id AS id, city.plz AS plz, city.city AS city";

        if (isset($_GET['id'])) {
            $result = $entity->load($what, $_GET['id']);

        } else if (isset($_GET['plz'])) {
            $result = $entity->fetch($what, "WHERE plz = :plz", array(':plz' => $_GET['plz']));

        } else if (isset($_GET['city'])) {
            $city = "$_GET[city]";
            $result = $entity->fetch($what, "WHERE city = :city", array(':city' => $city));

        } else if (isset($_GET['name'])) {
            $city = "%$_GET[name]%";
            $result = $entity->fetch($what, "WHERE city LIKE :city", array(':city' => $city));

        } else {
            $result = $entity->fetch($what);

        }
    }

    header("content-Type: application/json; charset=utf-8'");
    echo json_encode($result);

} catch (Exception $e) {
    exitError($e);
}

