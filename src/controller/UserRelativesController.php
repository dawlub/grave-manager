<?php

require_once 'AppController.php';
require_once __DIR__ .'/../model/Relative.php';
require_once __DIR__.'/../repository/UserRelativeRepository.php';

class UserRelativesController extends AppController
{
    private $userRelativeRepository;
    public function __construct()
    {
        parent::__construct();
        $this->userRelativeRepository = new UserRelativeRepository();
    }

    public function relatives()
    {
        $relatives = $this->userRelativeRepository->getUserRelatives($_SESSION['id']);
        return $this->render('dashboard', ['relatives' => $relatives]);
    }
}