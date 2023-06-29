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

    public function addToCollection()
    {
        $inputJSON = file_get_contents('php://input');
        $input = json_decode($inputJSON, TRUE);

        if (!isset($input['id'])) {
            http_response_code(400);
            echo json_encode(["error" => "'id' is missing from request data."]);
            return;
        }
        $relativeId = $input['id'];

        $this->message[] = $relativeId;

        $this->userRelativeRepository->addUserRelativeRelation($relativeId);

        echo json_encode(['message' => "Relative with id $relativeId was added to the collection"]);
    }
}