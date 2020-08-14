<script type="text/javascript">
	$(function(){
		/*
		$('#data').DataTable({
			"ordering": false
		});
		*/
		$('#form-input').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
				hemoglobin: {
	                validators: {
	                    notEmpty: {
	                        message: 'Hemoglobin harus diisi'
	                    },
						numeric: {
							message: 'Isi dengan bilangan',
							decimalSeparator:'.'
						}
	                }
	            },
	            tekanan_darah: {
	                validators: {
	                    notEmpty: {
	                        message: 'Tekanan Darah harus diisi'
	                    },
	                }
	            }
	        }
	    });
	    $('#kuisioner').hide();
	    $('#btnKuisioner').click(function(){
	    	if($('#hemoglobin').val()==''){
	    		alert('hemoglobin harus diisi');
	    	}else if($('#tekanan_darah').val()==''){
	    		alert('tekanan darah harus diisi');
	    	}else{
	    		$('#biodata').hide();
	    		$('#kuisioner').show();
	    	}
	    });
	    $('#btnBiodata').click(function(){
	    	$('#biodata').show();
	    	$('#kuisioner').hide();
	    });
});
</script>
<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Menu Donor</li>
    <li><a href="javascript:history.go(-1);">Input No Registrasi</a></li>
    <li class="active">Input Hemoglobin dan Tekanan Darah</li>
</ol>
<div class="konten">
	BIODATA PENDONOR<br />
	<?php
	if(!isset($_POST['no_registrasi'])){
		echo "<script>alert('harap isi no registrasi dulu');javascript:history.go(-1);</script>";
	}else{
		$no_registrasi = escape($_POST['no_registrasi']);
		$q = query("select * from daftar_donor d join pendonor p on d.id_pendonor=p.id_pendonor where id_reg='$no_registrasi'");
		$jml = mysqli_num_rows($q);
		if($jml=='0'){
			echo "<script>alert('no registrasi tidak ditemukan');javascript:history.go(-1);</script>";
		}
		$b = mysqli_fetch_array($q);
		$qSelisih = query("select time_to_sec(timediff(now(), tgl_daftar ))/3600 selisih_jam from daftar_donor where id_reg='$b[id_reg]'");
		$rSelisih = mysqli_fetch_array($qSelisih);
		if($rSelisih['selisih_jam']>2){
			echo "<script>alert('Maaf, anda melebihi waktu untuk mendonor!');javascript:history.go(-1);</script>";
		}
	}
	?>
	<form class="form-horizontal" id="form-input" method="post" action="menu_donor3.php">
		<input type="hidden" name="id_pendonor" value="<?php echo $b['id_pendonor']; ?>">
		<div id="biodata">
			<div class="form-group">
				<label class="col-md-3 control-label">No. Registrasi</label>
				<div class="col-md-9">
					<input type="text" name="no_registrasi" class="form-control" placeholder="No Registrasi" readonly value="<?php echo $b['id_reg']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Nama</label>
				<div class="col-md-9">
					<input type="text" name="nama" class="form-control" placeholder="Nama" readonly value="<?php echo $b['nama']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Email</label>
				<div class="col-md-9">
					<input type="text" name="email" class="form-control" placeholder="Email" readonly value="<?php echo $b['email']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">No. HP</label>
				<div class="col-md-9">
					<input type="text" name="no_hp" class="form-control" placeholder="No HP" readonly value="<?php echo $b['no_hp']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Jenis Kelamin</label>
				<div class="col-md-9">
					<input type="text" name="jk" class="form-control" placeholder="L/P" readonly value="<?php echo $b['jk']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Tgl Lahir</label>
				<div class="col-md-9">
					<input type="text" name="tgl_lahir" class="form-control" placeholder="dd/mm/YY" readonly value="<?php echo @date("d/m/Y", strtotime($b['tgl_lhr'])); ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Berat Badan</label>
				<div class="col-md-9">
					<input type="text" name="berat_badan" class="form-control" placeholder="Berat Badan" readonly value="<?php echo $b['berat_badan']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Pekerjaan</label>
				<div class="col-md-9">
					<input type="text" name="nama" class="form-control" placeholder="Pekerjaan" readonly value="<?php echo $b['pekerjaan']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Golongan Darah</label>
				<div class="col-md-9">
					<input type="text" name="gol_dar" class="form-control" placeholder="gol_dar" readonly value="<?php echo $b['nama']; ?>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Hemoglobin</label>
				<div class="col-md-9">
					<input type="number" min="1" autofocus id="hemoglobin" name="hemoglobin" class="form-control" placeholder="Hemoglobin">
					
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Tekanan Darah</label>
				<div class="col-md-9">
					<input type="text" name="tekanan_darah" id="tekanan_darah" class="form-control" placeholder="sistolik/diastolik">
				</div>
			</div>
			<div class="form-group">
				<input type="submit" value="Lanjut &gt;" class="btn btn-success tombol-kanan">
				<input type="button" id="btnKuisioner" value="Lihat Kuisioner" class="btn btn-success tombol-kanan">
			</div>
		</div>
		<div id="kuisioner">
			<?php
			$qKuisioner = query("select * from kusioner k join jawaban j on k.id_kuisioner=j.id_kuisioner where id_daftar='$b[id_daftar]' order by k.id_kuisioner");
			$no = 1;
			while($rKuisioner = mysqli_fetch_array($qKuisioner)){
			?>
			<div class="form-group">
				<label class="col-md-11 control-label"><?php echo $no.' . '.$rKuisioner['soal']; ?></label>
				<div class="col-md-1 control-label">: <?php
				if ($rKuisioner['jawaban']=='Y') {
					echo  'ya';
				}
				else if ($rKuisioner['jawaban']=='T') {
					echo 'Tidak';
				}
				else {
					echo' ';
				} ?></div>
			</div>
			<?php $no++; } ?>
			<div class="form-group">
				<input type="submit" value="Lanjut &gt;" class="btn btn-success tombol-kanan">
				<input type="button" id="btnBiodata" value="Lihat Biodata" class="btn btn-success tombol-kanan">
			</div>
		</div>
	</form>
</div>


