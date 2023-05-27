<?php

require_once 'AppController.php';
require_once __DIR__ .'/../model/User.php';
require_once __DIR__.'/../repository/UserRepository.php';

class LoginController extends AppController
{
    private $userRepository;


    public function __construct()
    {
        parent::__construct();
        $this->userRepository = new UserRepository();
    }


    public function login()
    {
        $userRepository = new UserRepository();
        if (!$this->isPost()) {
            return $this->render('login');
        }
        $email = $_POST["email"];
        $password = $_POST["password"];

        $user = $userRepository->getUser($email);

        if (!$user) {
            return $this->render('login', ['messages' => ['User not exist']]);
        }

        if ($user->getEmail() !== $email) {
            return $this->render('login', ['messages' => ['User with provided email address not exist!']]);
        }
        if ($user->getPassword() !== $password) {
            return $this->render('login', ['messages' => ['Wrong password']]);
        }

        $url = "http://$_SERVER[HTTP_HOST]";
        header("Location: {$url}/dashboard");
    }

    public function registration() {
        if (!$this->isPost()) {
            return $this->render('registration');
        }
        //TODO during refactor and dividng on service layer extract password and confirm password and validate

        $user = new User($_POST['email'], $_POST['password'], $_POST['firstName'], $_POST['lastName']);

        $this->userRepository->addUser($user);

        return $this->render('login', ['messages' => $this->messages]);
    }
}