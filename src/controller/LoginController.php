<?php

require_once 'AppController.php';
require_once __DIR__ .'/../model/User.php';

class LoginController extends AppController {

    public function login() {
        $user = new User('sample@pk.edu.pl', 'admin', 'sample firstName', 'sample lastName');
        if (!$this -> isPost()){
            return $this -> render('login');
        }
        $email = $_POST["email"];
        $password = $_POST["password"];
        if ($user->getEmail() !== $email){
            return $this -> render('login', ['messages' => ['User with provided email address not exist!']]);
        }
        if ($user->getPassword() !== $password) {
            return $this -> render('login', ['messages' => ['Wrong password']]);
        }

        $url = "http://$_SERVER[HTTP_HOST]";
        header("Location: {$url}/dashboard");
    }
}