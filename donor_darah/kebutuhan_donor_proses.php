<?php
session_start();
require_once("koneksi.php");
if(!isset($_POST['aksi'])){
	$id_kebutuhan = urldecode($_GET['id']);
	$a = query("delete from kebutuhan_darah where id_kebutuhan='$id_kebutuhan'");
	if($a){
		setcookie("berhasil", "berhasil menghapus kebutuhan donor", time()+2);
	}else{
		setcookie("gagal", "gagal menghapus kebutuhan donor", time()+2);
	}
	header("location:index.php?h=kebutuhan_donor");
}else if($_POST['aksi']=='tambah'){
	$id_kebutuhan = id_kebutuhan();
	$nama = escape($_POST['nama']);
	$tgl_lahirs = explode("/", escape($_POST['tgl_lahir']));
	$tgl_lahir = $tgl_lahirs[2].'-'.$tgl_lahirs[1].'-'.$tgl_lahirs[0];
	$jk = escape($_POST['jk']);
	$gol_darah = escape($_POST['gol_darah']);
	$kebutuhan = escape($_POST['kebutuhan']);
	$kontak = escape($_POST['kontak']);
	$wali = escape($_POST['wali']);
	$a = query("insert into kebutuhan_darah(id_kebutuhan, nama_resipien, jenis_kel, tgl_lahir, goldar, kebutuhan_kantung, kontak, wali_pasien) values('$id_kebutuhan', '$nama', '$jk', '$tgl_lahir', '$gol_darah', '$kebutuhan', '$kontak', '$wali')");
	if($a){
		setcookie("berhasil", "berhasil menambah kebutuhan donor", time()+2);
	}else{
		setcookie("gagal", "gagal menambah kebutuhan donor, ".mysqli_error(), time()+2);
	}
	header("location:index.php?h=kebutuhan_donor");
}else if($_POST['aksi']=='edit'){
	$kode_lama = escape($_POST['kode_lama']);
	$nama = escape($_POST['nama']);
	$tgl_lahirs = explode("/", escape($_POST['tgl_lahir']));
	$tgl_lahir = $tgl_lahirs[2].'-'.$tgl_lahirs[1].'-'.$tgl_lahirs[0];
	$jk = escape($_POST['jk']);
	$gol_darah = escape($_POST['gol_darah']);
	$kebutuhan = escape($_POST['kebutuhan']);
	$kontak = escape($_POST['kontak']);
	$wali = escape($_POST['wali']);
	$a = query("update kebutuhan_darah set nama_resipien='$nama', jenis_kel='$jk', tgl_lahir='$tgl_lahir', goldar='$gol_darah', kebutuhan_kantung='$kebutuhan', kontak='$kontak', wali_pasien='$wali' where id_kebutuhan='$kode_lama'");
	if($a){
		setcookie("berhasil", "berhasil mengedit kebutuhan donor", time()+2);
	}else{
		setcookie("gagal", "gagal mengedit kebutuhan donor, ".mysqli_error(), time()+2);
	}
	header("location:index.php?h=kebutuhan_donor");
}
function id_kebutuhan(){
	$query1 = query("select cast(id_kebutuhan as unsigned) no_terakhir from kebutuhan_darah order by no_terakhir desc");
		$hsl1 = mysqli_num_rows($query1);
		if($hsl1==0){
			$id_kebutuhan = '0000001';
		}else{
			$result1 = mysqli_fetch_array($query1);
			if($result1['no_terakhir']<9){
				$id_kebutuhan = '000000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<99){
				$id_kebutuhan = '00000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<999){
				$id_kebutuhan = '0000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<9999){
				$id_kebutuhan = '000'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<99999){
				$id_kebutuhan = '00'.($result1['no_terakhir']+1);
			}else if($result1['no_terakhir']<999999){
				$id_kebutuhan = '0'.($result1['no_terakhir']+1);
			}else{
				$id_kebutuhan = ($result1['no_terakhir']+1);
			}
		}
		return $id_kebutuhan;
}
?>