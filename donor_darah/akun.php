<script type="text/javascript">
	$(function(){
		
		
		$('#form-input').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
				username: {
	                validators: {
	                    notEmpty: {
	                        message: 'Username harus diisi'
	                    },
	                }
	            },
	            nama: {
	                validators: {
	                    notEmpty: {
	                        message: 'Nama harus diisi'
	                    },
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: 'Password harus diisi'
	                    },
	                }
	            }
	        }
	    });
});
</script>
<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Edit Akun</li>
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
	Edit Akun<br />
	<?php
    $a = query("select * from petugas where id_petugas='$_SESSION[ptg]'");
    $b = mysqli_fetch_array($a);
    ?>
	<form class="form-horizontal" id="form-input" method="post" action="akun_proses.php">
		<div class="form-group">
			<label class="col-md-3 control-label">Nama</label>
			<div class="col-md-9">
				<input type="text" name="nama" class="form-control" placeholder="input nama" value="<?php echo isset($b)?$b['nama_petugas']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Username</label>
			<div class="col-md-9">
				<input type="text" name="username" class="form-control" placeholder="input username" value="<?php echo isset($b)?$b['username']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Password</label>
			<div class="col-md-9">
				<input type="password" name="password" class="form-control" placeholder="input password" value="<?php echo isset($b)?$b['password']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"></label>
			<div class="col-md-9">
				<input type="submit" value="Simpan" class="btn btn-success">
				<a href="?h=akun"><input type="button" value="Batal" class="btn btn-danger"></a>
			</div>
		</div>
	</form>
</div>
	

