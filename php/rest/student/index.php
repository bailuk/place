<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');

try {
    $method = $_SERVER['REQUEST_METHOD'];
    $entity = new Entity(connect(), 'student');

    if ($method == 'GET') {
        $what = "student.id AS id, student.name AS name, student.marker AS marker, city.plz AS plz, city.city AS city, city.id AS id_city";
        $join = "LEFT JOIN city ON city.id = student.id_city";

        if (isset($_GET['id'])) {
            $result = $entity->fetch($what, $join . " WHERE student.id = ?", array($_GET['id']));
        } else {
            $result = $entity->fetch($what, $join);
        }
        convertToInteger($result, array('id', 'id_city'));

        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);

    } else if ($method == 'POST') {
        $input = (array) json_decode(file_get_contents('php://input'), TRUE);
        $entity->create(array('name' => $input['name'], 'id_city' => $input['id_city'], 'marker' => $input['marker']));
        header('HTTP/1.1 201 Created');

    } else if ($method == 'PUT') {
        if (isset($_GET['id'])) {
            $input = (array) json_decode(file_get_contents('php://input'), TRUE);
            $entity->update($_GET['id'], array('name' => $input['name'], 'id_city' => $input['id_city'], 'marker' => $input['marker']));
        } else {
            throw new \Exception('Missing parameter: id');
        }

    } else if ($method == 'DELETE') {
        if (isset($_GET['id'])) {
            $entity->delete($_GET['id']);
        } else {
            throw new \Exception('Missing parameter: id');
        }

        // TODO: throw missing parameter error
    }
} catch (Exception $e) {
    exitError($e);
}
