<?php

class RelativeDto
{
    private $id;
    private $visit;
    private $fullName;
    private $dateOfBirth;
    private $dateOfDeath;
    private $location;
    private $image;

    public function __construct(int $id, string $visit, string $fullName, string $dateOfBirth,
                                string $dateOfDeath, string $location, $image)
    {
        $this->id = $id;
        $this->visit = $visit;
        $this->fullName = $fullName;
        $this->dateOfBirth = $dateOfBirth;
        $this->dateOfDeath = $dateOfDeath;
        $this->location = $location;
        $this->image = $image;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getFullName(): string
    {
        return $this->fullName;
    }

    public function setFullName(string $fullName)
    {
        $this->fullName = $fullName;
    }

    public function getDateOfBirth(): string
    {
        return $this->dateOfBirth;
    }

    public function setDateOfBirth(string $dateOfBirth)
    {
        $this->dateOfBirth = $dateOfBirth;
    }

    public function getDateOfDeath(): string
    {
        return $this->dateOfDeath;
    }

    public function setDateOfDeath(string $dateOfDeath)
    {
        $this->dateOfDeath = $dateOfDeath;
    }

    public function getLocation(): string
    {
        return $this->location;
    }

    public function setLocation(string $location)
    {
        $this->location = $location;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image)
    {
        $this->image = $image;
    }

    public function getVisit(): string
    {
        return $this->visit;
    }

    public function setVisit(string $visit): void
    {
        $this->visit = $visit;
    }

    public function setId($id): void
    {
        $this->id = $id;
    }

}