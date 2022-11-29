<?php
	include "connect.php";
	$username = $_GET["username"];
	$password = $_GET["password"];
	$query="SELECT * FROM users where username = '$username' and password = '$password'";
	$data=mysqli_query($conn,$query);
	if(mysqli_num_rows($data)==1)
	{
		echo("1");
	}
	else
	{
		echo("0");
	}
?>