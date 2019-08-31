
 <?php
        if (!isset($_SESSION['username'])){
?>
  <div class='row align-items-center justify-content-center'>
        <div id='frame' class='col-6'><br><br><br>
         <h4>Please sign in</h4><br>
		 
                  <form action='index.php?mode=checkLogin' method='post'>

                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-id-badge"></i></span>
                          </div>
                          <input type="text" size='50' name='username'  placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-lock"></i></span>
                          </div>
                         <input type="password" size='50' name='pwd' placeholder="Password" aria-label="Password" aria-describedby="basic-addon1">
                        </div>

			<p>
                                <button type='submit' class='btn btn-primary' >Sign in </button>
                                <button type='reset' class='btn' >Clear</button>
			</p>
                  </form>
				  
               </div>

</div>
<?php
}  else {
  
  echo "<div class='row align-items-center justify-content-center'>
  <div class='col-12'>";
   // valid user. Display default view
//include('assets/menu.php');
?>

<?php 
}
?>
