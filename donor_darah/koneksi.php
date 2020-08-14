<?php
function connect_db(){
	$host = "localhost";
	$usr = "root";
	$pwd = "12345678";
	$db = "donor_darah";
	$con = mysqli_connect($host, $usr, $pwd, $db);
	if(!$con){
		$result = array(
						'status' => 'gagal koneksi database server',
						'error_no' => 'error baris: '.mysqli_connect_errno().PHP_EOL,
						'error_problem' => 'masalah error: '.mysqli_connect_error().PHP_EOL
					);
		echo json_encode($result);
	}
	return $con;
}
function escape($string){
	return mysqli_real_escape_string(connect_db(), $string);
}
function query($string_query){
	$result = mysqli_query(connect_db(), $string_query);
	return $result;
}
function query_return_id($string_query){
	$con = connect_db();
	$result = mysqli_query($con, $string_query);
	return mysqli_insert_id($con);
}
?>