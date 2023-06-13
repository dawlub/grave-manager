<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/add-relatives.css">
    <link rel="stylesheet" type="text/css" href="public/css/dashboard.css">
    <script src="https://kit.fontawesome.com/f83d14d316.js" crossorigin="anonymous"></script>
    <title>Add relative page</title>
</head>

<body>
<div class="dashboard-container">
    <?php include 'navigation-panel.php'; ?>
    <main class="add-relatives-main">
        <section class="add-relatives-section">
            <h1>Add Relative to Database</h1>
            <form action="addRelative" method="POST" ENCTYPE="multipart/form-data">
                <div class="messages">
                    <?php
                    if(isset($messages)){
                        foreach($messages as $message) {
                            echo $message;
                        }
                    }
                    ?>
                </div>
                <input name="fullName" type="text" placeholder="Full name">
                <input name="dateOfBirth" type="text" placeholder="Date of birth">
                <input name="dateOfDeath" type="text" placeholder="Date of death">
                <input name="location" type="text" placeholder="Location">
                <input class="file-button" type="file" name="file">
                <button class="submit-relative-button" type="submit">add</button>
            </form>
        </section>
    </main>
</div>
</body>