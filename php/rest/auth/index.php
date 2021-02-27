<?php

require_once('../../db/connect.php');
require_once('../../entity/entity.php');
require_once('../../util/util.php');


$users = array(
    array('password' => "123", 'name' => "hans", 'admin' => false),
    array('password' => "admin", 'name' => "admin", 'admin' => true),
);


try {

    session_start();
    
    $method = $_SERVER['REQUEST_METHOD'];

    if ($method == 'GET') {
        $result = getSessionStatus();
        header("content-Type: application/json; charset=utf-8'");
        echo json_encode($result);


    } else if ($method == 'POST') {
        

        if (isset($_GET['logout'])) {
            $_SESSION['auth'] = false;
            $result = getSessionStatus();
            header("content-Type: application/json; charset=utf-8'");
            echo json_encode($result);
    
            
        } else {

            $input = (array) json_decode(file_get_contents('php://input'), TRUE);

            $name = $input['name'];
            $password = $input['password'];

            $_SESSION['auth'] = false;
            foreach( $users as $user) {
                if ($user['name'] == $name && $user['password'] == $password) {
                    $_SESSION['name'] = $name;
                    $_SESSION['admin'] = $user['admin'];
                    $_SESSION['auth'] = true;
                    break;
                }
            }
            $result = getSessionStatus();
            header("content-Type: application/json; charset=utf-8'");
            echo json_encode($result);
    
        }
    }

} catch (Exception $e) {
    exitError($e);
}
