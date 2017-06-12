
<?php
$db_name="free fud test";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";

$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name) or die('Unable to connect');
if(!$con){
echo "Connection Error...".mysqli_connect_error();
}

echo "<h3>Databade connection successful...</h3>";

$query=mysqli_query($con,"SELECT * FROM freefoodtesttable");

if($query){
	while($row=mysqli_fetch_array($query)){
		$flag[] =$row;
	}
	
	print(json_encode($flag));
	
}
mysqli_close($con);

?>