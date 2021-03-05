<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');


function echoSessionStatus() {
    $result = getSessionStatus();
    header("content-Type: application/json; charset=utf-8'");
    echo json_encode($result);
}




function signoutOnRequest() {
    if (isset($_GET['signout'])) {
        signout();
        return true;
    }
    return false;
}


function signout() {
    $_SESSION['auth'] = false;
    $_SESSION['admin'] = false;
    session_destroy();
}


function authenticate($user, $input) {
    if ($user['login'] == $input['login'] && password_verify($input['password'], $user['password'])) {
        $_SESSION['login'] = $input['login'];
        $_SESSION['admin'] = isAdmin($user['role']);
        $_SESSION['auth'] = true;
    }
}


try {

    session_start();
    
    $method = $_SERVER['REQUEST_METHOD'];
    $entity = new Entity(connect(), 'user');

    if ($method == 'DELETE') {
        signout();

    } else if ($method == 'GET') {
        signoutOnRequest();
        echoSessionStatus();

    } else if ($method == 'POST' or $method == 'PUT') {
        
        $_SESSION['auth'] = false;

        $input = (array) json_decode(file_get_contents('php://input'), TRUE);
        $users = $entity->fetch("*", "WHERE login = ?", array($input['login']));
        foreach( $users as $user) {
            authenticate($user, $input);
            break;
        }

        throwOnFailedLogin();
        echoSessionStatus();
    }
} catch (Exception $e) {
    exitError($e);
}

