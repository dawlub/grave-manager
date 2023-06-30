<?php

require_once 'AppController.php';
require_once __DIR__ .'/../model/Relative.php';
require_once __DIR__.'/../repository/RelativeRepository.php';

class RelativeController extends AppController {

    const MAX_FILE_SIZE = 1024*1024;
    const SUPPORTED_TYPES = ['image/png', 'image/jpeg'];
    const UPLOAD_DIRECTORY = '/../public/uploads/';

    private $messages = [];

    private $relativeRepositry;

    public function __construct()
    {
        parent::__construct();
        $this->relativeRepositry = new RelativeRepository();
    }

    public function addRelative()
    {
        if(!empty($_SESSION["id"])) {
            if ($this->isPost() && is_uploaded_file($_FILES['file']['tmp_name']) && $this->validateFile($_FILES['file'])) {
                move_uploaded_file(
                    $_FILES['file']['tmp_name'], dirname(__DIR__) . self::UPLOAD_DIRECTORY . $_FILES['file']['name']
                );
                $relative = new Relative($_POST['fullName'], $_POST['dateOfBirth'], $_POST['dateOfDeath'],
                    $_POST['location'], $_FILES['file']['name']);
                $this->relativeRepositry->addRelative($relative);

                return $this->render('dashboard', [
                    'relatives' => $this->relativeRepositry->getRelatives(), 'messages' => $this->messages]);
            }
            return $this->render('add-relative-dashboard', ['messages' => $this->message]);
        } else {
            header("Location: {$this->url}/login");
            return $this->render('login');
        }
    }


    public function search()
    {
        $contentType = isset($_SERVER["CONTENT_TYPE"]) ? trim($_SERVER["CONTENT_TYPE"]) : '';

        if( $contentType === "application/json") {
            $content = trim(file_get_contents("php://input"));
            $decoded = json_decode($content, true);

            header("Content-typ: application/json");
            http_response_code(200);

            echo json_encode($this->relativeRepositry->getRelativeByName($decoded['search']));
        }
    }


    private function validateFile(array $file) : bool
    {
        return $this->validateFileSize($file['size']) && $this->validateFileType($file['type']);
    }

    private function validateFileType($type): bool
    {
        if (!isset($type) || !in_array($type, self::SUPPORTED_TYPES)) {
            $this->messages[] = 'File type is not supported.';
            return false;
        }
        return true;
    }

    private function validateFileSize($size): bool
    {
        if ($size > self::MAX_FILE_SIZE) {
            $this->messages[] = 'File is to large.';
            return false;
        }
        return true;
    }
}