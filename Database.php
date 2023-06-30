<?php

require_once 'configuration.php';

class Database
{
    private $username;
    private $password;
    private $host;
    private $database;
    private $connection;
    private static $dbInstance;
    private function __construct()
    {
        $this->username = USERNAME;
        $this->password = PASSWORD;
        $this->host = HOST;
        $this->database = DATABASE;
        $this->connection = $this->setConnection();
    }

    private function setConnection()
    {
        try {
            $connection = new PDO("pgsql:host=$this->host;port=5432;dbname=$this->database",
            $this->username, $this->password);

            $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            return $connection;
        } catch (PDOException $e) {
            die("Connection failed: " .$e->getMessage());
        }
    }

    public static function getDbInstance()
    {
        if (!self::$dbInstance) {
            self::$dbInstance = new self();
        }

        return self::$dbInstance;
    }

    public function connect()
    {
        return $this->connection;
    }
}