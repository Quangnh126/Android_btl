<?php
	include "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$OrderCode = $value['OrderCode'];
		$ProductID = $value['ProductID'];
		$ProductName = $value['ProductName'];
		$Price = $value['Price'];
		$Number = $value['Number'];

		$query = "INSERT INTO order_items (entity_id, order_id, product_id, product_name, cost, quantity)
					VALUES (null, '$OrderCode', '$ProductID', '$ProductName', '$Price', '$Number')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else {
		echo "0";
	}

?>
