<?php

require_once 'src/controller/DefaultController.php';
require_once 'src/controller/AppController.php';
require_once 'src/controller/LoginController.php';
require_once 'src/controller/RelativeController.php';
require_once 'src/controller/UserRelativesController.php';

class Routing {
    public static $routes;

    public static function get($url, $view)
    {
        self::$routes[$url] = $view;
    }

    public static function post($url, $view)
    {
        self::$routes[$url] = $view;
    }

    public static function run($url)
    {

        $urlParts = explode("/", $url);
        $action = $urlParts[0];

        if (!array_key_exists($action, self::$routes)) {
            die("Wrong url!");
        }

        $controller = self::$routes[$action];
        $object = new $controller;
        $action = $action ?: 'index';

        $id = $urlParts[1] ?? '';

        $object->$action($id);
    }
}