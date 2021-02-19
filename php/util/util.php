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
