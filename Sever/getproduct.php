<?php
	include "connect.php";
	$page =$_GET['page'];
	$producttypeid=$_GET['ProductID'];
	$space=5;
	$limit=($page-1)*$space;
	$productarr=array();
	$query="SELECT entity_id, name, cost, image, description, category FROM product WHERE category = $producttypeid LIMIT $limit,$space";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($productarr, new Product(
			$row['entity_id'],
			$row['name'],
			$row['cost'],
			$row['image'],
			$row['description'],
			$row['category']
		));
	}
	echo json_encode($productarr);
	class Product{
		function product($id,$productname,$price,$image,$productdetail,$productid){
			$this->id=$id;
			$this->productname=$productname;
			$this->price=$price;
			$this->image=$image;
			$this->productdetail=$productdetail;
			$this->productid=$productid;
		}
	}
?>