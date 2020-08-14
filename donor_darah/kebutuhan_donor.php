<script type="text/javascript">
	$(function(){


		$('#data').DataTable({
			"ordering": false
		});

		$('#form-input').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
				nama: {
	                validators: {
	                    notEmpty: {
	                        message: 'Nama Resipien harus diisi'
	                    },
	                }
	            },
	            tgl_lahir: {
	                validators: {
	                    notEmpty: {
	                        message: 'Tgl Lahir harus diisi'
	                    },
	                }
	            },
	            kebutuhan: {
	                validators: {
	                    notEmpty: {
	                        message: 'Kebutuhan Kantung harus diisi'
	                    },
						numeric: {
							message: 'Isi dengan bilangan',
							decimalSeparator:'.'
						}
	                }
	            },
	            kontak: {
	                validators: {
	                    notEmpty: {
	                        message: 'Kontak harus diisi'
	                    },
						numeric: {
							message: 'Isi nomor telepon dengan benar'
						}
	                }
	            },
	            wali: {
	                validators: {
	                    notEmpty: {
	                        message: 'Wali harus diisi'
	                    },
	                }
	            },
	        }
	    });
	    $("#tgl_lahir").datepicker({
			autoclose: true,
			format: 'dd/mm/yyyy',
			todayHighlight: true
		})
		.on('changeDate', function(e) {
	        $('#form-input').bootstrapValidator('revalidateField', $(this).prop('name'));
	    });
});
</script>
<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Kebutuhan Donor</li>
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
	Kebutuhan Donor<br />
	<?php
    if(isset($_GET['id'])){
        $a = query("select * from kebutuhan_darah where id_kebutuhan='$_GET[id]'");
        $b = mysqli_fetch_array($a);
    }
    ?>
	<form class="form-horizontal" id="form-input" method="post" action="kebutuhan_donor_proses.php" enctype="multipart/form-data">
		<input type="hidden" name="aksi" value="<?php echo isset($b)?'edit':'tambah'; ?>" />
		<input type="hidden" name="kode_lama" value="<?php echo isset($b)?$b['id_kebutuhan']:''; ?>" />
		<div class="form-group">
			<label class="col-md-3 control-label">Nama Resipien</label>
			<div class="col-md-9">
				<input type="text" name="nama" class="form-control" placeholder="input nama resipien" value="<?php echo isset($b)?$b['nama_resipien']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Jenis Kelamin</label>
			<div class="col-md-9">
				<input type="radio" name="jk" value="Pria" <?php echo isset($b)&&$b['jenis_kel']=='Pria'?'checked':'checked'; ?>> Pria
				<input type="radio" name="jk" value="Wanita" <?php echo isset($b)&&$b['jenis_kel']=='Wanita'?'checked':''; ?>> Wanita
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Tgl Lahir</label>
			<div class="col-md-9">
				<input type="text" name="tgl_lahir" id="tgl_lahir" class="form-control" placeholder="dd/mm/yyyy" value="<?php echo isset($b)?@date('d/m/Y', strtotime($b['tgl_lahir'])):''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Gol Darah</label>
			<div class="col-md-9">
				<input type="radio" name="gol_darah" value="A" <?php echo isset($b)&&$b['goldar']=='A'?'checked':'checked'; ?>> A
				<input type="radio" name="gol_darah" value="B" <?php echo isset($b)&&$b['goldar']=='B'?'checked':''; ?>> B
				<input type="radio" name="gol_darah" value="AB" <?php echo isset($b)&&$b['goldar']=='AB'?'checked':''; ?>> AB
				<input type="radio" name="gol_darah" value="O" <?php echo isset($b)&&$b['goldar']=='O'?'checked':''; ?>> O
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Kebutuhan Kantung</label>
			<div class="col-md-9">
				<input type="text" name="kebutuhan" class="form-control" placeholder="input kebutuhan kantung" value="<?php echo isset($b)?$b['kebutuhan_kantung']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Kontak</label>
			<div class="col-md-9">
				<input type="text" name="kontak" class="form-control" placeholder="input kontak" value="<?php echo isset($b)?$b['kontak']:''; ?>">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Wali</label>
			<div class="col-md-9">
				<input type="text" name="wali" class="form-control" placeholder="input wali" value="<?php echo isset($b)?$b['wali_pasien']:''; ?>">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label"></label>
			<div class="col-md-9">
				<input type="submit" value="Simpan" class="btn btn-success">
				<a href="?h=kebutuhan_donor"><input type="button" value="Batal" class="btn btn-danger"></a>
			</div>
		</div>
	</form>
	<table id="data" class="table table-bordered table-hover" cellspacing="0" width="100%">
                	<thead>
                        <tr>
                            <th>Id Kebutuhan</th>
                            <th>Nama Resipien</th>
                            <th>Jk</th>
                            <th>Tgl Lahir</th>
                            <th>Goldar</th>
                            <th>Kebutuhan Kantung</th>
                            <th>Kontak</th>
                            <th>Wali</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<?php
                        $x = query("select * from kebutuhan_darah order by id_kebutuhan desc");
                        while($y = mysqli_fetch_array($x)){
                        ?>
                        <tr>
                            <td><?php echo $y['id_kebutuhan']; ?></td>
                            <td><?php echo $y['nama_resipien']; ?></td>
                            <td><?php echo $y['jenis_kel']; ?></td>
                            <td><?php echo @date("d/m/Y", strtotime($y['tgl_lahir'])); ?></td>
                            <td><?php echo $y['goldar']; ?></td>
                            <td><?php echo $y['kebutuhan_kantung']; ?></td>
                            <td><?php echo $y['kontak']; ?></td>
                            <td><?php echo $y['wali_pasien']; ?></td>
                            <td align="center">
                            	<a href="?h=kebutuhan_donor&id=<?php echo $y['id_kebutuhan']; ?>" title="edit"><img src="images/edit.png" width="20" height="20" /></a>
                                <a href="kebutuhan_donor_proses.php?id=<?php echo $y['id_kebutuhan']; ?>" title="hapus" onclick="return confirm('yakin menghapus?')"><img src="images/remove.png" width="20" height="20" /></a>
                            </td>
                        </tr>
                        <?php } ?>
                    </tbody>
                </table>
</div>


