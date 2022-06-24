Vue.component("editprofile", {
	data: function () {
		    return {
			  hideFlag:null,
		      list:null,
		      user:{name:null,surname:null,username:null,password:null,gender:null,dateOfBirth:null,role:null}
		   
		    }
	},
	template: ` 
<div style="background-color:gray;position: fixed;
  left:40%;
  top:15%;
  width:25%;
  height:70%;
  text-align:center;">
<h2 class="h2">Edit profile</h2>
<form name="myForm">
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td>
<p>Name:</p>
</td>
<td>
<input id="name" class="form-control" v-model="user.name" v-on:blur="validateName" type="text" />
</td>
</tr>
<tr>
<td>
<p>Surname:</p>
</td>
<td>
<input id="surname" class="form-control" v-model="user.surname" v-on:blur="validateSurname" type="text" />
</td>
</tr>
<tr>
<td>
<p>Username:</p>
</td>
<td>
<input id="username" class="form-control" v-model="user.username" v-on:blur="duplicateUsername" type="text" />
</td>
</tr>
<tr>
<td>
<p>Password:</p>
</td>
<td>
<input v-model="user.password" class="form-control" type="password" />
</td>
</tr>
<tr>
<td>
<p>Gender:</p>
</td>
<td>
<select v-model="user.gender" class="form-select" >
<option>Male</option>
<option>Female</option>
</select>
</td>
</tr>
<tr>
<td>
<p>Date:</p>
</td>
<td>
<input v-model="user.dateOfBirth" class="form-control" type="date" />
</td>
</tr>
<tr>
<td>
<a href="#/" ><input type="button"  v-on:click="editProfile();"  class="btn btn-success" value="Edit"/></a>
</td>
<td>
<a href="#/" ><input  type="button"  class="btn btn-success"  value="Cancel"/></a>
</td>
</tr>
</table>
</form>
</div> `
, 
methods : {
	editProfile : function () {
				axios  
		          .post('customer/editprofile',this.user)
		          .then(response => (response.data))
		          }
		
	}
	
,mounted(){
	
	}
})