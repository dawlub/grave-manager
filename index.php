<?php

require 'Routing.php';

$path = trim($_SERVER['REQUEST_URI'], '/');
$path = parse_url($path, PHP_URL_PATH);

Routing::get('registration', 'DefaultController');
Routing::get('dashboard', 'DefaultController');
Routing::post('login', 'LoginController');

Routing::run($path);
