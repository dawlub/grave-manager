<?php

require 'Routing.php';

$path = trim($_SERVER['REQUEST_URI'], '/');
$path = parse_url($path, PHP_URL_PATH);

Routing::get('registration', 'LoginController');
Routing::get('dashboard', 'UserRelativesController');
Routing::post('login', 'LoginController');
Routing::post('addRelative', 'RelativeController');
Routing::post('search', 'RelativeController');
Routing::post('logout', 'LoginController');
Routing::post('addToCollection', 'UserRelativesController');

Routing::run($path);
