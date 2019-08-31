<?php

   // create a table of information
   echo "<table  class='table'>";

	echo"<thead><tr>";
foreach ($labels as $row ){
echo"<td>$row</td>";}
echo"</tr></thead>";
  foreach ($data as $row) {
     /* Each element ($movie) is an associative array.
        We can use foreach loop to display values
     */
     // create a table row using each record
     echo "<tr>";
     foreach($row as $label=>$value){
        echo "<td>{$value}</td>";
     }
     echo "</tr>";
   } // end for
   echo "</table>";
?>

