<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Menu Donor</li>
    <li class="active">Input No Registrasi</li>
    <li class="active">Input Hemoglobin dan Tekanan Darah</li>
    <li class="active">Hasil</li>
</ol>
<?php
if(isset($_COOKIE['berhasil'])){
?>
	<div class="panel panel-success">
    	<div class="panel-heading"><?php echo $_COOKIE['berhasil']; ?></div>
    </div>
<?php } ?>
<?php if(isset($_COOKIE['gagal'])){ ?>
    <div class="panel panel-danger">
    	<div class="panel-heading"><?php echo $_COOKIE['gagal']; ?></div>
    </div>
<?php } ?>
<div class="konten">
	<?php
	$a = query("select * from pendonor where id_pendonor='$_GET[id]'");
	$b = mysqli_fetch_array($a);
	?>
	PERNYATAAN KELAYAKAN DONOR<br />
	selamat, pendonor dinyatakan layak untuk melakukan proses donor darah<br /><br />
	<table>
		<tr>
			<td>Nama Pendonor</td>
			<td> : <?php echo $b['nama']; ?></td>
		</tr>
		<tr>
			<td>Donasi Berikutnya</td>
			<td> : <?php echo @date('d/m/Y', strtotime($b['donasi_selanjutnya'])); ?></td>
		</tr>
	</table>
</div>


