<?php
include('pdo_connect.php');
/* User-defined functions */
 function getAllRecords($sql, $data=null){
        /*  This method returns an array of Associative arrays. */
        global $db;
        // prepare an object of  PDOStatement class using SQL statement
        $stm = $db->prepare($sql);

        // execute the statement
        $stm->execute($data);
        // obtain data using fetchAll() method
        $result = $stm->fetchAll(PDO::FETCH_ASSOC);
        return $result;
 }
 function checkValidUser(){
        // validate user
        $sql ="select username, password,uID from users where username=:username and password=:pwd";
        //$sql = "select customer_id, first_name, last_name from `warhawk_customersuser` where
              //  username=:username and pwd = :pwd";
        // define values for parameters
        $values = array(':username'=>$_POST['username'], ':pwd'=>$_POST['pwd']);
        $result = getOneRecord($sql, $values);
        return $result;
 }
   function getOneRecord($sql, $parameter = null){
        global $db;
        $statement = $db->prepare($sql);
        // execute the SQL statement
        $statement->execute($parameter);
        // return result
        $result = $statement->fetch(PDO::FETCH_ASSOC);

        return $result;
  }

?>

