<?php

require_once 'Repository.php';
require_once __DIR__ .'/../model/Relative.php';

class RelativeRepository extends Repository
{
    public function getRelative(int $id): ?Relative
    {
        $stmt = $this->database->connect()->prepare('
            SELECT * FROM public.relative WHERE id = :id;
        ');
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
        $stmt->execute();

        $relative = $stmt->fetch(PDO::FETCH_ASSOC);

        //TODO try catch with exception and info relative not found
        if ($relative == false) {
            return null;
        }

        return new Relative(
            $relative['planned_visit'],
            $relative['full_name'],
            $relative['date_of_birth'],
            $relative['date_of_death'],
            $relative['location'],
            $relative['image'],
        );
    }

    public function getRelatives(): array
    {
        $result = [];

        $stmt = $this->database->connect()->prepare(
            'SELECT * FROM relative');
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
                $relative['user_id']
            );
        }
        return $result;
    }

    public function addRelative(Relative $relative): void
    {
        $stmt = $this->database->connect()->prepare('
            INSERT INTO relative (full_name, date_of_birth, date_of_death, location, image, user_id)
                VALUES (?, ?, ?, ?, ?, ?)');
        $useId = 1;
        $stmt->execute([
            $relative->getFullName(),
            $relative->getDateOfBirth(),
            $relative->getDateOfDeath(),
            $relative->getLocation(),
            $relative->getImage(),
            $useId
        ]);
    }
}