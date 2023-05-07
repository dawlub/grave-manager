<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/style.css">
    <link rel="stylesheet" type="text/css" href="public/css/dashboard-visits.css">
    <script src="https://kit.fontawesome.com/f83d14d316.js" crossorigin="anonymous"></script>
    <title>Dashboard page</title>
</head>

<body>
<div class="dashboard-container">
    <nav>
        <img src="public/img/logo.svg">
        <ul class="dashboard-menu">
            <li>
                <i class="fa-solid fa-user-group"></i>
                <a href="#" class="button">relatives</a>
            </li>
            <li>
                <i class="fa-solid fa-calendar-check"></i>
                <a href="#" class="button">calendar</a>
            </li>
            <li>
                <i class="fa-solid fa-bell"></i>
                <a href="#" class="button">notifications</a>
            </li>
            <li>
                <i class="fa-solid fa-message"></i>
                <a href="#" class="button">messages</a>
            </li>
            <li>
                <i class="fa-solid fa-gear"></i>
                <a href="#" class="button">settings</a>
            </li>
        </ul>
    </nav>
    <main>
        <header>
            <div class="search-bar">
                <form class="search-form">
                    <div class="search-input-wrapper">
                        <i class="fa-solid fa-magnifying-glass search-icon"></i>
                        <input type="text" class="search-input" placeholder="Search relatives">
                    </div>
                </form>
            </div>
            <div class="add-relatives">
                <i class="fa-sharp fa-solid fa-plus"></i>
                add relatives
            </div>
        </header>
        <section class="relatives-form">
            <h1>Add Relative to your collection</h1>
            <form action="addRelative" method="POST" ENCTYPE="multipart/form-data">
                <?php if(isset($messages)) {
                    foreach ($messages as $message) {
                        echo $message;
                    }
                }
                ?>
                <input name="fullName" type="text" placeholder="Full name">
                <input name="dateOfBirth" type="text" placeholder="Date of birth">
                <input name="dateOfDeath" type="text" placeholder="Date of death">
                <input name="location" type="text" placeholder="Location">
                <input type="file" name="file">
                <button type="submit">add</button>
            </form>
        </section>
    </main>
</div>
</body>