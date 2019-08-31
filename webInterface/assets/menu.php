<!-- include a list of links -->
<div class='row'>
  <div class='col-sm-4'>
        <a href='index.php'><img src='header_image.jpg' alt='Music_Image' height='175'></a>
  </div>
 <!-- <div class='col-sm-8'>
	<h1><span class="badge badge-secondary">Online Movie Store</span></h1>
   
  </div> -->
</div>
<div class="container-fluid">
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="index.php"><b>Home</b> <span class="sr-only">(current)</span></a>
      </li>
      <div>
      <form action="index.php?mode=artist_tags" method="post">
      &nbsp;&nbsp; Tags: <input type="text" name="input2" placeholder="Enter Artist Name" >
  <input type="submit" value="Search" style="background-color:purple">
</form>
</div>    

      <!--<li class="nav-item">
        <a class="nav-link" href="index.php?mode=artist_tags"><b>Artists Tags</b></a>
		<li class="nav-item">
    
        <a class="nav-link" href="index.php?mode=by_tag_name"><b>Artists By Tag Names</b></a>
      </li>
      </li>
      --><div>
      <form action="index.php?mode=by_tag_name" method="post">
      &nbsp;&nbsp; Retreive: <input type="text" name="input3" placeholder="Artist By Tag">
  <input type="submit" value="Search" style="background-color:purple">
</form>
</div>
 <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <b>Other Search:</b> </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
     <a class='dropdown-item' href='index.php?mode=popular_artists'>Popular Artists</a>
     <a class='dropdown-item' href='index.php?mode=similar'>Search Track</a>
        </div>
       </li>
      <li>
        <a class="nav-link" href="?mode=add_newtags"><b> Add New Tags</b></a>
    </li>
		
		<li class="nav-item">
         <a class="nav-link" href="index.php?mode=logout" style="color:purple" ><b>Sign out</b></a>
       </li>
      </li>
      <form action="index.php?mode=api" method="post">
      &nbsp;&nbsp; Name: <input type="text" name="input" placeholder="Find Similar Artist">
  <input type="submit" value="Search" style="background-color:purple" >
</form>
    </ul>
  </div>
 
</nav>
<div>
