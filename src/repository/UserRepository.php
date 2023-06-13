<?php

require_once 'Repository.php';
require_once __DIR__ .'/../model/User.php';

class UserRepository extends Repository
{
    public function getUser(string $email): ?User
    {
        $stmt = $this->database->connect()->prepare('
            SELECT * FROM public.user WHERE email = :email
        ');
        $stmt->bindParam(':email', $email, PDO::PARAM_STR);
        $stmt->execute();

        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        //TODO try catch with exception and info user not found
        if ($user == false) {
            return null;
        }

        return new User(
            $user['email'],
            $user['password'],
            $user['first_name'],
            $user['last_name']
        );
    }

    public function getUserId(User $user): int {
        $stmt = $this->database->connect()->prepare('
        SELECT * FROM "user" WHERE email = :email
        ');

        //$email = $user->getEmail();
        $stmt->bindParam(':email', $user->getEmail(), PDO::PARAM_STR);
        $stmt->execute();

        $fetchedUserData = $stmt->fetch(PDO::FETCH_ASSOC);
        return $fetchedUserData['id'];
    }

    public function addUser(User $user): void
    {
        $stmt = $this->database->connect()->prepare('
            INSERT INTO public.user (email, password, first_name, last_name)
                VALUES (?, ?, ?, ?)');
        $stmt->execute([
            $user->getEmail(),
            $user->getPassword(),
            $user->getFirstName(),
            $user->getLastName()
        ]);
    }
}