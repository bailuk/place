<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/error.php');

try {
    $entity = new Entity(connect(), 'student');

    if (isset($_GET)) {
        $what = "student.id AS id, student.name AS name, city.plz AS plz, city.city AS city, city.id AS cityId";
        $join = "LEFT JOIN city ON city.id = student.id_city";

        if (isset($_GET['id'])) {
            $result = $entity->fetch($what, $join . " WHERE id=:id", array(':id' => $_GET['id']));
        } else {
            $result = $entity->fetch($what, $join);
        }
    }

    // convert ids from string to integer
    $count = count($result);
    for ($i =  0; $i < $count; $i++) {
        $result[$i]['id']=intval($result[$i]['id']);
        $result[$i]['cityId']=intval($result[$i]['cityId']);
    }
    

    header("content-Type: application/json; charset=utf-8'");
    echo json_encode($result);

} catch (Exception $e) {
    exitError($e);
}
