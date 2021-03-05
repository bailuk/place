<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');


try {

    session_start();
    throwOnNonAdminSession();

    $method = $_SERVER['REQUEST_METHOD'];
    $entity = new Entity(connect(), 'user');

    if ($method == 'DELETE') {
        // delete non-admin user

        if (isset($_GET['id'])) {
            $what = "id, login, role";
            $where = "WHERE id = ? AND role = ?";
            $result = $entity->fetch($what, $where, array($_GET['id'], getUserRole()));

            if (count($result) == 1) {
                $entity->delete($_GET['id']);

            } else {
                throw new \Exception("Can't delete user with id $_GET[id]");
            }
            
        } else {
            throw new \Exception('Missing parameter: id');
        }

    } else if ($method == 'GET') {
        // get list of all users or get user with id

        $what = "id, login, role";

        if (isset($_GET['id'])) {
            $result = $entity->fetch($what, "WHERE id = ?", array($_GET['id']));
        } else {
            $result = $entity->fetch($what);
        }

        convertToInteger($result, array('id', 'role'));
        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);


    } else if ($method == 'POST') {
        // add non-admin user

        $input = (array) json_decode(file_get_contents('php://input'), TRUE);
        $password = password_hash($input['password'], PASSWORD_DEFAULT);
        $entity->create(array('login' => $input['login'], 'password' => $password, 'role' => getUserRole()));
        header('HTTP/1.1 201 Created');


    } else if ($method == 'PUT') {
        // update password of non-admin user
        
        if (isset($_GET['id'])) {
            $what = "id, role";
            $where = "WHERE id = ? AND role = ?";
            $result = $entity->fetch($what, $where, array($_GET['id'], getUserRole()));
            
            if (count($result) == 1) {
                $input = (array) json_decode(file_get_contents('php://input'), TRUE);
                $entity->update($_GET['id'], array('password' => password_hash($input['password'], PASSWORD_DEFAULT)));
                        
            } else {
                throw new \Exception("Could not update password for user with id $_GET[id]");
            }
                
        } else {
            throw new \Exception('Missing parameter: id');
        }

    }
} catch (Exception $e) {
    exitError($e);
}

