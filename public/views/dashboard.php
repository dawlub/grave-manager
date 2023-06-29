<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="public/css/dashboard-visits.css">
    <script src="https://kit.fontawesome.com/f83d14d316.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="public/js/search.js" defer></script>
    <title>Dashboard page</title>
</head>
<body>
    <div class="dashboard-container">
        <?php include 'navigation-panel.php'; ?>
        <main>
            <header>
                <div class="search-bar">
                        <div class="search-input-wrapper">
                            <i class="fa-solid fa-magnifying-glass search-icon"></i>
                            <input class="search-input" placeholder="Search relatives">
                        </div>
                </div>
                <div class="add-relatives">
                    <a href="/addRelative"> <i class="fa-sharp fa-solid fa-plus"></i>add relative</a>
                </div>
            </header>
            <header class="visits-header">
                NEXT PLANNED VISITS
            </header>
            <section class="relatives">
                <?php foreach ($relatives as $relative): ?>
                <div id="relative">
                    <div>
                        <h3>Next planned visit is in progres</h3>
                    </div>
                    <img src="public/uploads/<?= $relative->getImage(); ?>">
                    <div>
                        <h2>
                            <?= $relative->getFullName() ?>
                        </h2>
                        <ul>
                            <li>
                                <i class="fa-solid fa-star"></i>
                                <span><?= $relative->getDateOfBirth() ?></span>
                            </li>
                            <li>
                                <i class="fa-solid fa-cross"></i>
                                <span><?= $relative->getDateOfDeath() ?></span>
                            </li>
                            <li>
                                <i class="fa-solid fa-location-dot"></i>
                                <span><?= $relative->getLocation() ?></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <?php endforeach; ?>
            </section>
        </main>
    </div>
</body>

<template id="relative-template">
    <div id="">
        <img src="">
        <div>
            <h2 name="fullName"></h2>
            <ul>
                <li>
                    <i class="fa-solid fa-star"></i>
                    <span name="dateOfBirth"></span>
                </li>
                <li>
                    <i class="fa-solid fa-cross"></i>
                    <span name="dateOfDeath"></span>
                </li>
                <li>
                    <i class="fa-solid fa-location-dot"></i>
                    <span name="location"></span>
                </li>
                <li>
                <button class="add-searched">Add to your collection</button>
                </li>
            </ul>
        </div>
    </div>
</template