<?php

class UserRelativesController extends AppController
{
    private $userRelativeRepository;
    public function __construct()
    {
        parent::__construct();
        $this->userRelativeRepository = new UserRelativeepository();
    }

    public function getUserRelatives()
    {

    }
}