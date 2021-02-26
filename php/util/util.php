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

    header('Content-Type: text/html; charset=utf-8');
    http_response_code($internalServerError);
    echo "<pre>";
    echo $e->getMessage();
    echo "</pre>";
    exit(1);
}


function getSessionStatus() {
    if (isset($_SESSION['auth']) && isset($_SESSION['admin']) && isset($_SESSION['name'])) {
        $result = array('name' => $_SESSION['name'], 'auth' => $_SESSION['auth'], 'admin' => $_SESSION['admin']);
    } else {
        $result = array('name' => "", 'auth' => false, 'admin' => false);
    }
    return $result;

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
