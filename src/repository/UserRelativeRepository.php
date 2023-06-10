<?php

class UserRelativeRepository
{
    public function getUserRelatives(int $id): array
    {
        $result = [];

        $stmt = $this->database->connect()->prepare(
            'SELECT * FROM user_relative WHERE user_id = :id');
        $stmt->execute();
        $relatives = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo $relatives[1]['full_name'];
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