<?php
require_once("../koneksi.php");
/*
$_POST['aksi'] = 'daftar';
$_POST['id_pendonor'] = '00001';
$_POST['id_kebutuhan'] = '0000001';
$_POST["id_kuisioner"] = array('1', '2');
$_POST["jawaban"] = array('Y', 'T');
*/
if(isset($_POST['aksi'])){
	$aksi = escape($_POST['aksi']);
	if($aksi=='daftar'){
		$id_daftar = generate_daftar();
		$id_pendonor = escape($_POST['id_pendonor']);
		$id_reg = generate_reg();
		$id_kebutuhan = escape($_POST['id_kebutuhan']);
		//echo("insert into daftar_donor(id_daftar, id_pendonor, id_reg, id_kebutuhan) values('$id_daftar', '$id_pendonor', '$id_reg', '$id_kebutuhan')");
		$query = query("insert into daftar_donor(id_daftar, id_pendonor, id_reg, id_kebutuhan) values('$id_daftar', '$id_pendonor', '$id_reg', '$id_kebutuhan')");
		$jawabans = $_POST['jawaban'];
		$id_kuisioners = $_POST['id_kuisioner'];
		for($i=0; $i<sizeof($id_kuisioners); $i++){
			$id_kuisioner = $id_kuisioners[$i];
			$jawaban = $jawabans[$i];
			$query2 = query("insert into jawaban(id_daftar, id_kuisioner, jawaban) values('$id_daftar', '$id_kuisioner', '$jawaban')");
		}
		if($query){
			$data[] = array('status' => 'berhasil', 'id_reg' => $id_reg);
		}else{
			$data[] = array('status' => 'gagal');
		}
		echo json_encode($data);
	}
}
function generate_daftar(){
		$query = query("select cast(id_daftar as unsigned) no_terakhir from daftar_donor order by no_terakhir desc");
		$hsl1 = mysqli_num_rows($query);
		if($hsl1==0){
			$id_daftar = '00001';
		}else{
			$result1 = mysqli_fetch_array($query);
			if($result1['no_terakhir']<9){
				$id_daftar = '0000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<99){
				$id_daftar = '000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<999){
				$id_daftar = '00'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<9999){
				$id_daftar = '0'.($result1['no_terakhir']+1);
			}else{
				$id_daftar = ($result1['no_terakhir']+1);
			}
		}
		return $id_daftar;
}
function generate_reg(){
	//3112170001 (tglBlnThnUrut)
	//1234567890
	$query = query("select cast(substring(id_reg, 7, 4) as unsigned) no_terakhir from daftar_donor where date(tgl_daftar)=date(now()) order by no_terakhir desc");
	$hsl1 = mysqli_num_rows($query);
		if($hsl1==0){
			$id_daftar = @date('dmy').'0001';
		}else{
			$result1 = mysqli_fetch_array($query);
			if($result1['no_terakhir']<9){
				$id_daftar = @date('dmy').'000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<99){
				$id_daftar = @date('dmy').'00'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<999){
				$id_daftar = @date('dmy').'0'.($result1['no_terakhir']+1);
			}else{
				$id_daftar = @date('dmy').($result1['no_terakhir']+1);
			}
		}
	return $id_daftar;

}
?>