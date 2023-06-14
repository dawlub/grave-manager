<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="public/css/login.css">
    <title>Login page</title>
</head>

<body>
    <div class="container">
        <div class="logo">
            <img src="public/img/logo.svg">
        </div>
        <div class="login-container">
            <form class="login-form" action="login" method="POST">
                <div class="messages">
                <?php if(isset($messages)) {
                    foreach ($messages as $message) {
                        echo $message;
                    }
                }
                ?>
                </div>
                <input name="email" type="text" placeholder="example@domain.com">
                <input name="password" type="password" placeholder="password">
                <button type="submit">login</button>
                <a class="registration-link" href="registration">Don't have account? Sign up</a>
            </form>
        </div>
    </div>
</body>