Vue.component("login", {
	data: function () {
		    return {
		      RegisterTitle:"Register",
		       user:{name:null,surname:null,username:null,password:null,gender:null,dateOfBirth:null},
		      total:0
		    }
	},
	template: ` 
	
<div style="background-color:gray;position: fixed;
  left:40%;
  top:25%;
  width:25%;
  height:45%;
  text-align:center;">
  
<input type="button" v-on:click="logoutUser()" style="margin-left:280px;" value="Logout">
<h2>Login</h2>
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td colspan="2">
<p>Username:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input id="username" v-model="user.username" v-on:change="validateUsername" type="text" />
</td>
</tr>
<tr>
<td colspan="2">
<p>Password:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input v-model="user.password" type="password" />
</td>
</tr>
<tr>
<td>
<input v-on:click="loginUser()" type="button" value="Login"/>
</td>
<td>
<a href="#/register"> <input  type="button" value="Register"/></a>
</td>
</tr>
</table>
</div> `
,methods : {
		loginUser : function () {
			alert("Usao u login")
				axios  
		          .post('customer/login',this.user)
		          .then(response => (alert(response.data)))
		          },
		   logoutUser : function () {
			alert("Usao u logout")
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (alert(response.data)))
		          },
		   validateUsername : function() {
			let z = document.getElementById('username').value;
			let regexxx = new RegExp('[A-Z]*[a-z]*[1-9]*');
			if(regexxx.test(z) != true)
			{
				alert("Nije dobro unet username!");
			}
				
			
				
		}
		
		          },
mounted(){}

});