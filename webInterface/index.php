<!DOCTYPE html>
<html>
<head>
  <style>
    thead { background-color: #800080; color: #fff; font-weight: bold;  }

  </style>
</head>

<?php
session_start();

// Model: include user-defined methods
 include('model.php');
// include('assets/menu.html');
  include('assets/header.html');
  include('views/homepage.php');



//Obtain distinct movies types
//$movietypes = getAllRecords($sql);
if (isset($_SESSION['username'])) {
  //Display menu 
  include('assets/menu.php');
}
// include the menu

	$mode = '';
	if (isset($_GET['mode']))
        // Read user input
    $mode = $_GET['mode'];
switch ($mode){
        // display tags input table 
        case 'add_newtags':

        include('views/tags.php');

        break;

        //inserting tags
        case 'insert_tags':
        $toy = $_SESSION['username'];
        global $db;

       $artist_name = $_POST['artist'];
        $your_tags = $_POST['tags'];
        
        $sql = "SELECT uID FROM `users` where username='{$toy}'";
        $data = getOneRecord($sql);
        $userId = $data['uID'];
        
        $query = "CALL tagArtist('{$your_tags}','{$artist_name}',$userId)";
        $stmt = $db->prepare($query);

        if (!$stmt) {
         echo "Prepare failed to load!!";
        }
        If($stmt){
        echo "<br>";

        echo "<b> Your Tag is entered successfully.</b>";

        }
        $stmt->execute();


        break;

        // pulling out the similar track from API
        case 'similar':
        include('views/similar_track.html');

        break;

        case  'users':
		$labels = array("username","password","ID");
                // Define SQL statement
                $sql = "SELECT * FROM `users`";
	
                // Obtain a list of drama type movies
                $data = getAllRecords($sql);
                // Use a template to display the output
                include('views/tableview.php');
                break;
	case 'by_tag_name':
                $labels = array("Artists Names");
                $tags=$_POST['input3'];
		//sql adventure statement
		$sql = "SELECT a.aName from artists a, uses u, tags t where t.tValue like '{$tags}' and t.tID = u.tID and u.aID = a.aID GROUP BY a.aName ORDER BY a.aName;";
		$data = getAllRecords($sql);

                // Use a template to display the output
                include('views/tableview.php');
                break;

        case 'artist_tags':
                $labels = array("Artists Tags");
                $tags =$_POST['input2'];
	
                // Define SQL statement
                $sql = "SELECT t.tValue FROM tags t, uses u,  artists a WHERE a.aName like '{$tags}' and a.aID = u.aID and u.tID = t.tID GROUP BY t.tValue ORDER BY t.tValue;";

                // Obtain a list of tags
                $data = getAllRecords($sql);
                // Use a template to display the output
                include('views/tableview.php');
                break;
        case 'popular_artists':
		$labels = array("Popular Artists Name");

                $sql = "Select aName from artists order by aPlays desc limit 3;";
	         
                $data = getAllRecords($sql);
                // display result
                include('views/tableview.php');
                break;
         case 'api':
        //echo $_POST['input'];
        $string =$_POST['input'];
        $noSpaces = str_replace(' ', '%20', $string);
        
        
         // Read JSON file
        $readjson = file_get_contents('http://localhost:8081/artist/'.$noSpaces) ;
        
        //Decode JSON
        //var_dump(json_decode($readjson, true));

        // Decode JSON data to PHP associative array
        $data2 = json_decode($readjson,true);
        //print_r($data);
        echo"<br/>";
        print("<b><h3>Artist Name:</h3></b> ".$data2["name"]);
        echo"<br/>";

        $similar = $data2['similar'];
        $dart=$similar['artist'];
        echo"<br/>";

        echo "<b><h3>Similar Artists are: </h3></b>";
        foreach($dart as $arr){
        print("<br>".$arr["name"]);

        }   
                break;
        case 'api2':
        //echo $_POST['input'];
        $strong =$_POST['artists'];
        $noSpace = str_replace(' ', '%20', $strong);
        $string1 =$_POST['songs'];
        $noSpare = str_replace(' ', '%20', $string1);
                
        // Read JSON file
        $readjson = file_get_contents('http://localhost:8081/similarTrack/'.$noSpace.'/'.$noSpare) ;
                
        //Decode JSON
        //var_dump(json_decode($readjson, true));
        
        // Decode JSON data to PHP associative array
        $data1 = json_decode($readjson,true);
        //print_r($data);
        echo"<br/>";
        //print("Track Name: ".$data["track"])."<br/>";
        echo"<br/>";
        
        $similar3 = $data1['track'];   
     
        echo "<b><h3>Similar Tracks are: </h3></b>";

        foreach($similar3 as $arro){
        print("<br>".$arro["name"]);
        
        }   
        break;

	case 'checkLogin' :

        $data = checkValidUser();
        if ($data != null){
         $_SESSION['username'] = $data['username'];
                                
        }

        header('Location: index.php');
        break;

        case 'logout' :
                // destroy session variables and display login form
                session_destroy();
                setcookie(session_name(), '', time()-1000, '/');
                $_SESSION = array();
                // display default view
                header('Location: index.php');
                break;


        default:

        // display default Home page
	include('views/defaultview.php');
        break;
}
 include('assets/footer.html');
?>
</html>
