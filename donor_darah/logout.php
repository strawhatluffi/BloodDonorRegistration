<?php
session_start();
unset($_SESSION['ptg']);
header("location:login.php");
?>