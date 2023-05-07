<?php

require_once 'src/controller/DefaultController.php';
require_once 'src/controller/AppController.php';
require_once 'src/controller/LoginController.php';
require_once 'src/controller/RelativeController.php';

class Routing {
    private static $routes;
    public static function get($url, $view) {
        self::$routes[$url] = $view;
    }

    public static function post($url, $view) {
        self::$routes[$url] = $view;
    }

    public static function run($url) {
        $action = explode("/", $url)[0];
        if (!array_key_exists($action, self::$routes)) {
            die("Wrong url");
        }
        $controller = self::$routes[$action];
        $controllerObject = new $controller;

        $controllerObject -> $action();
    }
}