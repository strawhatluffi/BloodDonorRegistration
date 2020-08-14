<?php
session_start();
require_once("koneksi.php");
$id_petugas = escape($_SESSION['ptg']);
$nama = escape($_POST['nama']);
$username = escape($_POST['username']);
$password = escape($_POST['password']);
$a = query("update petugas set nama_petugas='$nama', username='$username', password='$password' where id_petugas='$id_petugas'");
if($a){
	setcookie("berhasil", "berhasil megedit akun", time()+2);
}else{
	setcookie("gagal", "gagal mengedit akun, ".mysqli_error(), time()+2);
}
header("location:index.php?h=akun");
?>