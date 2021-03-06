<?php



function convertToInteger($entity, $ids) {
    // convert ids from string to integer
    $count = count($entity);
    for ($i =  0; $i < $count; $i++) {
        foreach($ids as $id) {
            $entity[$i][$id] = intval($entity[$i][$id]);
        }
    }
    return $entity;
}


function exitError(Exception $e) {
    $internalServerError=500;

    header('Content-Type: text/plain; charset=utf-8');
    http_response_code($internalServerError);
    echo $e->getMessage();
    exit(1);
}


function getSessionStatus() {
    if (isset($_SESSION['auth']) && isset($_SESSION['admin']) && isset($_SESSION['login'])) {
        $result = array('login' => $_SESSION['login'], 'auth' => $_SESSION['auth'], 'admin' => $_SESSION['admin']);
    } else {
        $result = array('login' => "", 'auth' => false, 'admin' => false);
    }
    return $result;

}

function throwOnFailedLogin() {
    $status = getSessionStatus();
    if ($status['auth'] == false) {
        throw new \Exception('Wrong username or password');
    }
}


function throwOnInvalidSession() {
    $status = getSessionStatus();
    if ($status['auth'] == false) {
        throw new \Exception('Session is not valid');
    }
}


function throwOnNonAdminSession() {
    $status = getSessionStatus();
    if ($status['auth'] == false || $status['admin'] == false) {
        throw new \Exception('Session is not valid or missing permission');
    }
}

function getUserRole() {
    return 0;
}

function getAdminRole() {
    return 1;
}
function isAdmin($role) {
    return $role == getAdminRole();
}

