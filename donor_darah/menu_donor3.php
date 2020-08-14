<?php
session_start();
require_once("koneksi.php");
$no_registrasi = escape($_POST['no_registrasi']);
$id_pendonor = escape($_POST['id_pendonor']);
$id_petugas = $_SESSION['ptg'];
$hemoglobin = escape($_POST['hemoglobin']);
$tekanan_darah = explode("/", escape($_POST['tekanan_darah']));
$sistolik = $tekanan_darah[0];
$diastolik = $tekanan_darah[1];
//batas hemoglobin dan tekanan darah
if($hemoglobin <12.5 || $hemoglobin>17){
	echo "<script>alert('hemoglobin tidak memenuhi syarat, minimal 12.5, maksimal 17');javascript:history.go(-1);</script>";
}else if($sistolik <100 || $sistolik >170){
	echo "<script>alert('sistolik tidak memenuhi syarat, minimal 100, maksimal 170');javascript:history.go(-1);</script>";
}else if($diastolik <70 || $diastolik >100){
	echo "<script>alert('diastolik tidak memenuhi syarat, minimal 70, maksimal 100');javascript:history.go(-1);</script>";
}else{
	$a = query("insert into proses_donor(id_reg, id_pendonor, id_petugas, hemoglobin, tekanan_darah, tgl_donor, status_proses) values('$no_registrasi', '$id_pendonor', '$id_petugas', '$hemoglobin', '$sistolik/$diastolik', date(now()), 'ok')");
	query("update pendonor set donasi_terakhir=date(now()), donasi_selanjutnya=DATE_ADD(date(now()) , INTERVAL 70 DAY) where id_pendonor='$id_pendonor'");
	if($a){
		setcookie("berhasil", "berhasil memproses donor darah", time()+2);
	}else{
		setcookie("gagal", "gagal memproses donor darah, ".mysqli_error(), time()+2);
	}
	header("location:index.php?h=menu_donor4&id=$id_pendonor");
}
?>
	

