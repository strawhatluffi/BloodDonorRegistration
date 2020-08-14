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
				no_registrasi: {
	                validators: {
	                    notEmpty: {
	                        message: 'No Registrasi harus diisi'
	                    },
	                }
	            }
	        }
	    });
	});
</script>
<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Menu Donor</li>
    <li class="active">Input No Registrasi</li>
</ol>
<div class="konten">
	MENU DONOR<br />
	<form class="form-horizontal" id="form-input" action="?h=menu_donor2" method="post">
		<div class="form-group">
			<label class="col-md-3 control-label">Masukkan No. Registrasi</label>
			<div class="col-md-9">
				<input type="text" name="no_registrasi" maxlength="10" class="form-control" placeholder="No Registrasi">
			</div>
		</div>
		<div class="form-group">
			<input type="submit" value="Lanjut &gt;" class="btn btn-success tombol-kanan">
		</div>
	</form>
</div>
	

