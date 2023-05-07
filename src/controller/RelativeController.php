<?php

require_once 'AppController.php';
require_once __DIR__ .'/../model/Relative.php';

class RelativeController extends AppController {

    const MAX_FILE_SIZE = 1024*1024;
    const SUPPORTED_TYPES = ['image/png', 'image/jpg'];
    const UPLOAD_DIRECTORY = '/../public/uploads/';

    private $messages = [];

    public function addRelative()
    {
        if ($this->isPost() && is_uploaded_file($_FILES['file']['tmp_name']) && $this->validateFile($_FILES['file'])) {
            move_uploaded_file(
                $_FILES['file']['tmp_name'], dirname(__DIR__).self::UPLOAD_DIRECTORY.$_FILES['file']['name']
            );

            $relative = new Relative($_POST['nextPlannedVisit'],$_POST['fullName'], $_POST['dateOfBirth'],
                $_POST['dateOfDeath'], $_POST['location'], $_FILES['file']);

            return $this->render('dashboard', ['messages' => $this->messages]);
        }
        return $this->render('add-relative-dashboard', ['messages' => $this->message]);
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