<?php
	include "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$Username = $value['username'];
		$Password = $value['password'];
		$Phonenumber = $value['phonenumber'];
		$query = "INSERT INTO users (username, password, phonenumber)
					VALUES ('$Username', '$Password', '$Phonenumber')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else {
		echo "0";
	}
?>
