<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="public/css/dashboard-visits.css">
    <script src="https://kit.fontawesome.com/f83d14d316.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="public/js/search.js" defer></script>
    <script type="text/javascript">
        $(document).ready(function () {
            const visitDates = document.querySelectorAll('.visit-date');
            visitDates.forEach((visitDate) => {
                visitDate.addEventListener('keydown', (event) => {
                    if (event.key === 'Enter') {
                        event.preventDefault();
                        visitDate.contentEditable = false;
                        const relativeId = visitDate.getAttribute('data-relative-id');
                        const newVisitDate = visitDate.textContent.trim();
                        console.log('Relative ID:', relativeId);
                        console.log('New Visit Date:', newVisitDate);
                        // Send AJAX request to update visit date in the database

                        fetch('/updateVisit', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({
                                id: relativeId,
                                visitDate: newVisitDate
                            }),
                        })
                            .then((response) => response.text())
                            .then((data) => {
                                console.log(data);
                            })
                            .catch((error) => {
                                console.error('An error occurred:', error);
                            });
                    }
                });

                visitDate.addEventListener('click', () => {
                    visitDate.contentEditable = true;
                    visitDate.focus();
                });

                visitDate.addEventListener('blur', () => {
                    visitDate.contentEditable = false;
                });
            });
        });
    </script>
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
                <div class="relative">
                    <div>
                        <h3 class="visit-date" data-relative-id="<?= $relative->getId(); ?>">
                            <?= $relative->getVisit() ?>
                        </h3>
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
        <div class="relative-template">
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
            </ul>
            <button class="add-searched">Add to your collection</button>
        </div>
    </div>
</template>