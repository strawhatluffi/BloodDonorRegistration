<?php
require_once("../koneksi.php");
/*
$_POST['aksi'] = 'data';
$_POST['id'] = '00001';
*/
if(isset($_POST['aksi'])){
	$aksi = escape($_POST['aksi']);
	if($aksi=='data'){
		$id_pendonor = escape($_POST['id']);
		$query = query("select * from pendonor where id_pendonor='$id_pendonor'");
		$result = mysqli_fetch_array($query);
		$jk = $result['jk'];
		if($jk=='L'){
			$query2 = query("select * from kusioner where jenis='umum' order by id_kuisioner");
		}else{
			$query2 = query("select * from kusioner order by id_kuisioner");
		}
		$jml = mysqli_num_rows($query2);
		if($jml==0){
			$data[] = null;
		}else{
			while($result2 = mysqli_fetch_array($query2)){
				$data[] = $result2;
			}
		}
		echo json_encode($data);
	}else if($aksi=='jawab'){
		$id_pendonor = escape($_POST['id_pendonor']);
	}
}
?>