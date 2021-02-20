<?php

function connect() {
    $host = '127.0.0.1';
    $database = 'place';
    $user = 'root';
    $password = '123';

    $pdo = new PDO(
            'mysql:host=' . $host . '; dbname=' . $database, 
            $user, 
            $password, 
            array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));
    $pdo -> setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    $pdo -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    return $pdo;
} 