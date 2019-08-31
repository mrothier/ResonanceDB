<?php
$user = 'komi';
$pass = 'Z6Bk8g45'; // first initial last initial last 4-digits of ID
$db_info='mysql:host=resonancedb.ctyc91zxkqhu.us-east-2.rds.amazonaws.com:1150; dbname=ResonanceDB'; // replace my unsername by yours
if(!$db_info){
    echo "not connected";
    
}else{
    global $db;
    $db = new PDO($db_info, $user, $pass);
   

}
/*try {
    $db = new PDO($db_info, $user, $pass);

} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
}
*/

?>
