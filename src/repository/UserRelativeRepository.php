<?php

require_once 'Repository.php';
require_once __DIR__ .'/../model/RelativeDto.php';

class UserRelativeRepository extends Repository
{
    public function getUserRelatives(int $id): array
    {
        $result = [];

        $stmt = $this->database->connect()->prepare(
            'SELECT r.*, ur.visit FROM user_relative ur 
                JOIN relative r ON ur.relative_id = r.id
                WHERE ur.user_id = :user_id');
        $stmt->bindParam(':user_id', $id, PDO::PARAM_INT);
        $stmt->execute();
        $relatives = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo $relatives['full_name'];
        foreach ($relatives as $relative) {
            $result[] = new RelativeDto(
                $relative['visit'],
                $relative['full_name'],
                $relative['date_of_birth'],
                $relative['date_of_death'],
                $relative['location'],
                $relative['image'],
            );
        }
        return $result;
    }


    public function addUserRelativeRelation(int $id) {
        $stmt = $this->database->connect()->prepare('
            INSERT INTO user_relative (user_id, relative_id, visit)
                VALUES (?, ?, ?)');
        $stmt->execute([
            $_SESSION['id'],
            $id,
            'Not Planned'
        ]);
    }
}