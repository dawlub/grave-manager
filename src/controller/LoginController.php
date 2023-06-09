<?php
session_start();

require_once 'AppController.php';
require_once __DIR__ .'/../model/User.php';
require_once __DIR__.'/../repository/UserRepository.php';

class LoginController extends AppController
{
    private $userRepository;
    private String $url;

    public function __construct()
    {
        parent::__construct();
        $this->url = "http://$_SERVER[HTTP_HOST]";
        $this->userRepository = new UserRepository();
    }


    public function login()
    {
        if(empty($_SESSION['id'])) {

            if (!$this->isPost()) {
                return $this->render('login');
            }
            $email = $_POST["email"];
            $password = $_POST["password"];

            $user = $this->userRepository->getUser($email);

            if (!$user) {
                return $this->render('login', ['messages' => ['User not exist']]);
            }

            if ($user->getEmail() !== $email) {
                return $this->render('login', ['messages' => ['User with provided email address not exist!']]);
            }
            if (!password_verify($password, $user->getPassword())) {
                return $this->render('login', ['messages' => ['Wrong password']]);
            }
            $userId = $this->userRepository->getUserId($user);
            $_SESSION['id'] = $userId;
        }
        header("Location: {$this->url}/dashboard");
    }

    public function registration() {
        if (!$this->isPost()) {
            return $this->render('registration');
        }
        $email = $_POST['email'];
        $password = $_POST['password'];
        $firstName = $_POST['firstName'];
        $lastName = $_POST['lastName'];

        $user = new User(
            $email,
            password_hash($password, PASSWORD_DEFAULT),
            $firstName,
            $lastName);

        $this->userRepository->addUser($user);

        return $this->render('login', ['messages' => $this->messages]);
    }

    public function logout()
    {
            $_SESSION = [];
            session_unset();
            session_destroy();

            header("Location: {$this->url}/login");
    }
}