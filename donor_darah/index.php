<?php
session_start();
if(!isset($_SESSION['ptg'])){
	header("location:login.php");
}
require_once("koneksi.php");
?>
<html>
	<head>
		<title>admin donor darah</title>
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script type="text/javascript" src="jquery/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/plugins/validator/dist/css/bootstrapValidator.min.css">
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/plugins/datatables/jquery.dataTables.min.css">
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/plugins/datepicker/datepicker3.css">
		<script type="text/javascript" src="bootstrap-3.3.7-dist/plugins/validator/dist/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="bootstrap-3.3.7-dist/plugins/datatables/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="bootstrap-3.3.7-dist/plugins/datatables/dataTables.bootstrap.min.js"></script>
		<script type="text/javascript" src="bootstrap-3.3.7-dist/plugins/datepicker/bootstrap-datepicker.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-custom navbar-fixed-top">
		    <a class="navbar-brand brand-custom" href="#">Admin Donor Darah</a>		    
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href="#" class="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		    </ul>
		</nav>
		<div class="container container-custom">
			<div class="row">
				<div class="panel-konten left">
					<?php
					if(!isset($_GET['h'])){
						require_once("home.php");
					}else{
						require_once("$_GET[h].php");
					}
					?>
				</div>
				<div class="panel-menu">
					<div class="biodata">
						<span class="id"><?php echo data_login()['id_petugas']; ?></span><br />
						<span class="nama">
							<?php echo data_login()['nama_petugas']; ?><br />
							<a href="?h=akun">Edit Akun</a>
							|
							<a href="logout.php">Logout</a>
						</span>
					</div>
					<div class="menu-area">
						<div class="judul-menu">MENU AREA <span class="glyphicon glyphicon-list"></span></div>
						<a href="?h=menu_donor"><div class="menu-item"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp; Menu Donor</div></a>
						<a href="?h=data_pendonor"><div class="menu-item"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp; Data Pendonor</div></a>
						<a href="?h=kebutuhan_donor"><div class="menu-item"><span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp; Kebutuhan Donor</div></a>
					</div>
				</div>
			</div>
			<div class="row footer">
				&copy;2017 donor darah
			</div>
		</div>
	</body>
</html>
<?php
function data_login(){
	$query = query("select * from petugas where id_petugas='$_SESSION[ptg]'");
	return mysqli_fetch_array($query);
}
?>