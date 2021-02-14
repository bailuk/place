<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/error.php');

try {
    $method = $_SERVER['REQUEST_METHOD'];
    $entity = new Entity(connect(), 'student');

    if ($method == 'GET') {
        $what = "student.id AS id, student.name AS name, city.plz AS plz, city.city AS city, city.id AS id_city";
        $join = "LEFT JOIN city ON city.id = student.id_city";

        if (isset($_GET['id'])) {
            $result = $entity->fetch($what, $join . " WHERE student.id = ?", array($_GET['id']));
        } else {
            $result = $entity->fetch($what, $join);
        }


        // convert ids from string to integer
        $count = count($result);
        for ($i =  0; $i < $count; $i++) {
            $result[$i]['id'] = intval($result[$i]['id']);
            $result[$i]['id_city'] = intval($result[$i]['id_city']);
        }


        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);
    } else if ($method == 'POST') {
        $input = (array) json_decode(file_get_contents('php://input'), TRUE);
        $entity->create(array('name' => $input['name'], 'id_city' => $input['id_city']));
        header('HTTP/1.1 201 Created');

    } else if ($method == 'PUT') {
        echo "updateUser";
    } else if ($method == 'DELETE') {
        echo "deleteUser";
    }
} catch (Exception $e) {
    exitError($e);
}
