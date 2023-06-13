<?php

require_once 'Repository.php';
require_once __DIR__ .'/../model/Relative.php';

class UserRelativeRepository extends Repository
{
    public function getUserRelatives(int $id): array
    {
        $result = [];

        $stmt = $this->database->connect()->prepare(
            'SELECT * FROM user_relative WHERE user_id = :user_id');
        $stmt->bindParam(':user_id', $id, PDO::PARAM_STR);
        $stmt->execute();
        $relatives = $stmt->fetchAll(PDO::FETCH_ASSOC);
        foreach ($relatives as $relative) {
            $result[] = new Relative(
                $relative['full_name'],
                $relative['date_of_birth'],
                $relative['date_of_death'],
                $relative['location'],
                $relative['image'],
            );
        }
        return $result;
    }
}