<?php
require_once("../koneksi.php");
//$_POST['aksi'] = 'cek';
if(isset($_POST['aksi'])){
	$aksi = escape($_POST['aksi']);
	if($aksi=='cek'){
		$email = escape($_POST['email']);
		$password = escape($_POST['password']);
		$query = query("select * from pendonor where email='$email' and password='$password'");
		$jml = mysqli_num_rows($query);
		if($jml==0){
			$data[] = array('status' => 'gagal');
		}else{
			$result = mysqli_fetch_array($query);
			$id_pendonor = $result['id_pendonor'];
			$data[] = array('status' => 'berhasil', 'id_pendonor' => $id_pendonor);
		}
		echo json_encode($data);
	}
}
?>