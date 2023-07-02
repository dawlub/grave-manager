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

    public function dashboard()
    {
        if (!empty($_SESSION['id'])) {
            $relatives = $this->userRelativeRepository->getUserRelatives($_SESSION['id']);
            return $this->render('dashboard', ['relatives' => $relatives]);
        } else {
            header("Location: {$this->url}/login");
            return $this->render('login');
        }
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

    public function updateVisit()
    {
        $inputJSON = file_get_contents('php://input');
        $input = json_decode($inputJSON, true);

        if (!isset($input['id']) || !isset($input['visitDate'])) {
            http_response_code(400);
            echo json_encode(['error' => "'id' or 'visitDate' is missing from request data."]);
            return;
        }

        $relativeId = intval($input['id']);
        $visitDate = $input['visitDate'];

        $this->userRelativeRepository->updateVisitDate($relativeId, $visitDate);
    }
}