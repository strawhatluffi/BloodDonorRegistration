<?php
require_once("../koneksi.php");
//$_POST['aksi'] = 'data';
//$_POST['id'] = '00002';
/*
$_POST['nama'] = 'a';
$_POST['email'] = 'b';
$_POST['password'] = 'c';
$_POST['no_hp'] = 'a';
$_POST['jk'] = 'd';
$_POST['tgl_lahir'] = '22/01/1990';
$_POST['pekerjaan'] = 'mahasiswi';
$_POST['gol_darah'] = 'a';
$_POST['bb'] = '40';
*/
if(isset($_POST['aksi'])){
	$aksi = escape($_POST['aksi']);
	if($aksi=='daftar'){
		$nama = escape($_POST['nama']);
		$no_hp = escape($_POST['no_hp']);
		$email = escape($_POST['email']);
		$password = escape($_POST['password']);
		$jk = escape($_POST['jk']);
		$tgl_lahirs = explode("/", escape($_POST['tgl_lahir']));
		$tgl_lahir = $tgl_lahirs[2].'-'.$tgl_lahirs[1].'-'.$tgl_lahirs[0];
		$pekerjaan = escape($_POST['pekerjaan']);
		$gol_darah = escape($_POST['gol_darah']);
		$bb = escape($_POST['bb']);
		$query1 = query("select cast(id_pendonor as unsigned) no_terakhir from pendonor order by no_terakhir desc");
		$hsl1 = mysqli_num_rows($query1);
		if($hsl1==0){
			$id_pendonor = '00001';
		}else{
			$result1 = mysqli_fetch_array($query1);
			if($result1['no_terakhir']<9){
				$id_pendonor = '0000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<99){
				$id_pendonor = '000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<999){
				$id_pendonor = '00'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<9999){
				$id_pendonor = '0'.($result1['no_terakhir']+1);
			}else{
				$id_pendonor = ($result1['no_terakhir']+1);
			}
		}
		$query2 = query("insert into pendonor(id_pendonor, no_hp, email, password, nama, jk, tgl_lhr, berat_badan, pekerjaan, gol_dar) values('$id_pendonor', '$no_hp', '$email', '$password', '$nama', '$jk', '$tgl_lahir', '$bb', '$pekerjaan', '$gol_darah')");
		if($query2){
			$data[] = array('status' => 'berhasil', 'id_pendonor' => $id_pendonor);
		}else{
			$data[] = array('status' => 'gagal');
		}
		echo json_encode($data);
	}else if($aksi=='data'){
		$id = escape($_POST['id']);
		$query = query("select * ,datediff(date(now()), donasi_selanjutnya) selisih from pendonor where id_pendonor='$id'");
		$jml = mysqli_num_rows($query);
		if($jml==0){
			$data[] = null;
		}else{
			while($result = mysqli_fetch_array($query)){
				$tgl_lhr = @date('d/m/Y', strtotime($result['tgl_lhr']));
				if($result['donasi_selanjutnya'] == null) {
					$donasiselanjutnya = "";
				}else{
					$donasiselanjutnya = @date('d/m/Y', strtotime($result['donasi_selanjutnya']));
				}
				$data[] = array('id_pendonor' => $result['id_pendonor'], 'no_hp' => $result['no_hp'], 'email' => $result['email'], 'password' => $result['password'], 'nama' => $result['nama'], 'jk' => $result['jk'], 'tgl_lhr' => $tgl_lhr, 'berat_badan' => $result['berat_badan'], 'pekerjaan' => $result['pekerjaan'], 'gol_dar' => $result['gol_dar'], 'selisih' => $result['selisih'], 'donorselanjutnya' => $donasiselanjutnya);
			}
		}
		echo json_encode($data);
	}else if($aksi=='update'){
		$id_pendonor = escape($_POST['id']);
		$nama = escape($_POST['nama']);
		$no_hp = escape($_POST['no_hp']);
		$email = escape($_POST['email']);
		$password = escape($_POST['password']);
		$jk = escape($_POST['jk']);
		$tgl_lahirs = explode("/", escape($_POST['tgl_lahir']));
		$tgl_lahir = $tgl_lahirs[2].'-'.$tgl_lahirs[1].'-'.$tgl_lahirs[0];
		$pekerjaan = escape($_POST['pekerjaan']);
		$gol_darah = escape($_POST['gol_darah']);
		$bb = escape($_POST['bb']);
		$query2 = query("update pendonor set no_hp='$no_hp', email='$email', password='$password', nama='$nama', jk='$jk', tgl_lhr='$tgl_lahir', berat_badan='$bb', pekerjaan='$pekerjaan', gol_dar='$gol_darah' where id_pendonor='$id_pendonor'");
		if($query2){
			$data[] = array('status' => 'berhasil');
		}else{
			$data[] = array('status' => 'gagal');
		}
		echo json_encode($data);
	}
}
?>