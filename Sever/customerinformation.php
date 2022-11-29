<?php
	include "connect.php";
	// $customername = $_POST['customername'];
	// $phonenumber = $_POST['phonenumber'];
	// $email = $_POST['email'];
	$customername = "Tuan";
	$phonenumber = "0389048218";
	$email = "tuan@gmail.com";
	if(strlen($customername)>0 && strlen($email)>0 && strlen($phonenumber)>0)
	{
		// $queryCheck="SELECT entity_id FROM custumer WHERE name = '$customername' AND telephone = '$phonenumber' AND email = '$email' ";
		// $dataCheck=mysqli_query($conn,$queryCheck);
		// if($dataCheck){
		// 	$query = "UPDATE custumer SET name = '$customername',telephone ='$phonenumber' ,emai = '$email' WHERE name = '$customername' AND telephone = '$phonenumber' AND email = '$email' ";
		// 	if(mysqli_query($conn,$query)){
		// 		$billid  = $conn->update_id;
		// 		echo $billid;
		// 	}else
		// 	echo 'failed';
		// }else{
		// 	$query = "INSERT INTO custumer (entity_id,name,telephone,email,address) VALUES (NULL,'$customername','$phonenumber','$email',NULL)";
		// 	if(mysqli_query($conn,$query)){
		// 		$billid  = $conn->insert_id;
		// 		echo $billid;
		// 	}else
		// 	echo 'failed';
		// }
		$query = "INSERT INTO custumer (entity_id,name,telephone,email,address) VALUES (NULL,'$customername','$phonenumber','$email',NULL)";
		if(mysqli_query($conn,$query)){
			$billid  = $conn->insert_id;
			echo $billid;
		}else
		echo 'failed';
		
	}
	else {
		echo "Ban hay kiem tra lai cac du lieu";
	}
?>