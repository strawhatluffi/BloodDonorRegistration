<?php
require_once("../koneksi.php");
/*
$_POST['aksi'] = 'data';
$_POST['id'] = '00001';
*/
if(isset($_POST['aksi'])){
	$aksi = escape($_POST['aksi']);
	if($aksi=='terkait'){
		$id_pendonor = escape($_POST['id']);
		$query = query("select * from pendonor where id_pendonor='$id_pendonor'");
		$result = mysqli_fetch_array($query);
		$gol_darah = $result['gol_dar'];
		$query2 = query("select * from kebutuhan_darah where goldar='$gol_darah'");
		$jml = mysqli_num_rows($query2);
		if($jml==0){
			$data[] = null;
		}else{
			while($result2 = mysqli_fetch_array($query2)){
				$data[] = $result2;
			}
		}
		echo json_encode($data);
	}else if($aksi=='semua'){
		$query2 = query("select * from kebutuhan_darah");
		$jml = mysqli_num_rows($query2);
		if($jml==0){
			$data[] = null;
		}else{
			while($result2 = mysqli_fetch_array($query2)){
				$data[] = $result2;
			}
		}
		echo json_encode($data);
	}
}
?>