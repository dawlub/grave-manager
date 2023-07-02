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
                $relative['id'],
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

    public function updateVisitDate(int $relativeId, string $visitDate)
    {
        $stmt = $this->database->connect()->prepare(
            'UPDATE user_relative SET visit = :visitDate
                     WHERE relative_id = :relativeId AND user_id = :userId');
        $stmt->bindValue(':visitDate', $visitDate, PDO::PARAM_STR);
        $stmt->bindValue(':relativeId', $relativeId, PDO::PARAM_INT);
        $stmt->bindValue(':userId', $_SESSION['id'], PDO::PARAM_INT);
        $stmt->execute();
    }
}