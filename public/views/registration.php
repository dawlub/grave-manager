<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/login.css"><link>
    <script type="text/javascript" src="/public/js/script.js" defer></script>
    <title>Registration page</title>
</head>

<body>
    <div class="container">
        <div class="logo">
            <img src="public/img/logo.svg">
        </div>
        <div class="registration-container">
            <form class="registration-form" action="registration" method="POST">
                <div class="messages">
                    <?php
                    if(isset($messages)){
                        foreach($messages as $message) {
                            echo $message;
                        }
                    }
                    ?>
                </div>
                <input name="firstName" type="text" placeholder="first name">
                <input name="lastName" type="text" placeholder="last name">
                <input name="email" type="text" placeholder="email@email.com">
                <input name="password" type="password" placeholder="password">
                <input name="confirmedPassword" type="password" placeholder="confirm password">
                <button type="submit">create account</button>
            </form>
        </div>
    </div>
</body>