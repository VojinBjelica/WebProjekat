Vue.component("login", {
	data: function () {
		    return {
			flag:"",
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
  

<h2 class="title">Login</h2>
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td colspan="2">
<p class="form-label" >Username:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input id="username" class="form-control" v-model="user.username" v-on:change="validateUsername" type="text" />
</td>
</tr>
<tr>
<td colspan="2">
<p class="form-label" >Password:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input v-model="user.password" class="form-control" type="password" />
</td>
</tr>
<tr>
<td>
<input v-on:click="loginUser();" type="button" class="btn btn-primary" style="margin-top:10px" value="Login"/>
</td>
<td>
<a href="#/register"> <input  type="button" class="btn btn-primary" style="margin-top:10px" value="Register"/></a>
</td>
</tr>
</table>
</div> `
,methods : {
		loginUser : function () {
				axios  
		          .post('customer/login',this.user)
		          .then(response => (this.check(response.data)))
        
		        
		          
		          },
		   logoutUser : function () {
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (response.data))
		          },
		   validateUsername : function() {
			let z = document.getElementById('username').value;
			let regexxx = new RegExp('[A-Z]*[a-z]*[1-9]*');
			if(regexxx.test(z) != true)
			{
				alert("Invalid username!");
			}
			
				
			
				
		},
		popup : function()
			{
				
				if(this.flag == "login")alert("Your account doesn't exist");
			},
		check : function(data)
		{
			this.flag = data;
			
			if(data == "Administrator" || data == "Coach" || data == "Manager" ||data == "Customer" )
			{
				router.push(`/`);
				alert("Login success");
			}
			else
			{
				router.push(`/login`);
				alert("Login unsuccess");
			} 
			
		}
}
,mounted(){}
		
})