<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="" method="post">
    <input type="text" name="password" id="password">
    <input type="submit" value="OK">
    </form>

    <?php
        if (isset($_POST['password'])) {
            $hash = password_hash($_POST['password'], PASSWORD_DEFAULT);
            echo "<pre>$hash</pre>";
        }
    ?>
</body>
</html>


