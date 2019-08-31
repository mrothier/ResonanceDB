<div class='container-fluid'>
  <!DOCTYPE html>
  <html>
<?php
include('pdo_connect.php');

?>
  <h3>Add Your New Tag</h3>

<form action='index.php?mode=insert_tags' method='post'>
<table class='table'>
<tr>
  <td>Artist name:</td><td><input type='text' name='artist'
        placeholder='Enter Artist Name' /></td>
</tr>
<tr>
  <td>Tags:</td><td><input type='text' name='tags'
        placeholder='Enter Your Tag' /></td>
</tr>
</table>
<p><button type='submit' class='btn btn-primary'>Add New Tag</button>
</form>
</div>

</html>


