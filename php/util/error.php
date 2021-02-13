<?php


function exitError(Exception $e) {
    $internalServerError=500;

    header('Content-Type: text/html; charset=utf-8');
    http_response_code($internalServerError);
    echo "<pre>";
    echo $e->getMessage();
    echo "</pre>";
    exit(1);
}
