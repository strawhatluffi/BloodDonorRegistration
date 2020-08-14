<script type="text/javascript">
	$(function(){


		$('#data').DataTable({
			"ordering": false
		});
});
</script>
<ol class="breadcrumb custom-breadcrumb">
    <li class="active">Data Pendonor</li>
</ol>
<div class="konten">
	Data Pendonor<br />
	<table id="data" class="table table-bordered table-hover" cellspacing="0" width="100%">
                	<thead>
                        <tr>
                            <th>Id Pendonor</th>
                            <th>Nama</th>
                            <th>No HP</th>
                            <th>Email</th>
                            <th>Jk</th>
                            <th>Berat Badan</th>
                            <th>Gol Darah</th>
                            <th>Donasi Terakhir</th>
                            <th>Donasi Selanjutnya</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<?php
                        $x = query("select * from pendonor order by nama desc");
                        while($y = mysqli_fetch_array($x)){
                        ?>
                        <tr>
                            <td><?php echo $y['id_pendonor']; ?></td>
                            <td><?php echo $y['nama']; ?></td>
                            <td><?php echo $y['no_hp']; ?></td>
                            <td><?php echo $y['email']; ?></td>
                            <td><?php echo $y['jk']; ?></td>
                            <td><?php echo $y['berat_badan']; ?></td>
                            <td><?php echo $y['gol_dar']; ?></td>
                            <td><?php echo $y['donasi_terakhir']!=null?@date("d/m/Y", strtotime($y['donasi_terakhir'])):''; ?></td>
                            <td><?php echo $y['donasi_selanjutnya']!=null?@date("d/m/Y", strtotime($y['donasi_selanjutnya'])):''; ?></td>
                        </tr>
                        <?php } ?>
                    </tbody>
                </table>
</div>


