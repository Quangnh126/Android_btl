<?php
	include "connect.php";
	$newproductarr = array();
	$query = "SELECT entity_id, name, cost, image, description, category FROM product ORDER BY entity_id DESC LIMIT 6";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($newproductarr, new NewProduct(
			$row['entity_id'],
			$row['name'],
			$row['cost'],
			$row['image'],
			$row['description'],
			$row['category']
			// $row['entity_id'],
			// $row['category'],
			// $row['name'],
			// $row['description'],
			// $row['base_cost'],
			// $row['cost'],
			// $row['special_cost'],
			// $row['image']
		));
	}
	echo json_encode($newproductarr);

	class NewProduct{
		function newproduct($id,$productname,$price,$image,$productdetail,$productid){
				$this->id=$id;
				$this->productname=$productname;
				$this->price=$price;
				$this->image=$image;
				$this->productdetail=$productdetail;
				$this->productid=$productid;
			}
		
			// function newproduct($entity_id,$category,$name,$description,$base_cost,$cost,$special_cost,$image){
			// 	$this->entity_id=$entity_id;
			// 	$this->category=$category;
			// 	$this->name=$name;
			// 	$this->description=$description;
			// 	$this->base_cost=$base_cost;
			// 	$this->cost=$cost;
			// 	$this->special_cost=$special_cost;
			// 	$this->image=$image;
			// }
	}
?>