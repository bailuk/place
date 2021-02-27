<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');


$users = array(
    array('password' => "123", 'name' => "hans", 'admin' => false),
    array('password' => "admin", 'name' => "admin", 'admin' => true),
);


function echoSessionStatus() {
    $result = getSessionStatus();
    header("content-Type: application/json; charset=utf-8'");
    echo json_encode($result);
}

function isAdmin($role) {
    return $role == 1;
}


try {

    session_start();
    
    $method = $_SERVER['REQUEST_METHOD'];
    $entity = new Entity(connect(), 'user');

    if ($method == 'GET') {
        if (isset($_GET['logout'])) {
            $_SESSION['auth'] = false;
        }
        echoSessionStatus();

    } else if ($method == 'POST' or $method == 'PUT') {
        

        if (isset($_GET['logout'])) {
            $_SESSION['auth'] = false;
            echoSessionStatus();
    
            
        } else {

            $_SESSION['auth'] = false;

            $input = (array) json_decode(file_get_contents('php://input'), TRUE);
            $login = $input['name'];
            $password = $input['password'];
            

            $userList = $entity->fetch("*", "WHERE login = ?", array($login));
            foreach( $userList as $user) {
                if ($user['login'] == $login && password_verify($password, $user['password'])) {
                    $_SESSION['name'] = $login;
                    $_SESSION['admin'] = isAdmin($user['role']);
                    $_SESSION['auth'] = true;
                    break;
                }
            }

            throwOnFailedLogin();
            echoSessionStatus();
        }
    }
} catch (Exception $e) {
    exitError($e);
}

