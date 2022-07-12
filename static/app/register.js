Vue.component("register", {
	data: function () {
		    return {
			  temp:null,
			  roleCaught:null,
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
<h2 class="h2">Register</h2>
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
<tr id="roleid">
<td>
<p>Role:</p>
</td>
<td>
<select v-model="user.role" class="form-select" >
<option  value="Customer">Customer</option>
<option :disabled="this.temp" value = "Manager">Manager</option>
<option :disabled="this.temp" value = "Coach">Coach</option>
</select>
</td>
</tr>

<tr>
<td>
<a href="#/login" ><input type="button"  class="btn btn-success" value="Login"/></a>
</td>
<td>
<input v-if="roleCaught == false"  type="button"  class="btn btn-success" v-on:click="validateText();duplicateUsername();addCustomer();goToLogin();" value="Register"/>
<input v-if="roleCaught == true"  type="button"  class="btn btn-success" v-on:click="validateText();duplicateUsername();addCustomer();goToStart();" value="Register "/>

</td>
</tr>
</table>
</form>
</div> `
, 
	methods : {
		writeCustomers : function () {
				axios  
		          .post('customer/write',this.user)
		          .then(response => (response.data))
		          },
		addCustomer : function () {
				axios  
		          .post('customer/add',this.user)
		          .then(response => response.data)
		          
		          },
		hideCheck: function() {
			
			axios 
				.post('customer/hidecombo', this.hideFlag)
				.then(response =>  this.hideButton(response.data))
		},
		
		hideButton: function(check) {
			this.hideFlag = check;
			
			if (this.hideFlag == "Administrator" || this.hideFlag == "Coach" || this.hideFlag == "Manager" ) {
				this.temp = false;
		
				}
			else if (this.hideFlag == "Customer") {
				
				this.temp = true;
			}
				},
		  validateName : function() {
			var x = document.getElementById('name').value;
			if(x == "")alert("Popuniti polje ime");
			let regex = new RegExp('[A-Z][a-z]+');
			if(!regex.test(x))
			{
				alert("Invalid name");
			}
			
				
		},
		catchRole : function(role)
		{
			if(role == "Administrator")this.roleCaught = true;
			else this.roleCaught = false;
		} ,
		
		 validateSurname : function() {
			let y = document.getElementById('surname').value;
			if(y == "")alert("Popuniti polje prezime");
			let regexx = new RegExp('[A-Z][a-z]+');
			if(!regexx.test(y))
			{
				alert("Invalid username");
			}
			
				
		},
		 validateText : function() {
			var x = document.getElementById('name').value;
			if(x == "")alert("Fill name input");
			let y = document.getElementById('surname').value;
			if(y == "")alert("Fill surname input");
			let z = document.getElementById('username').value;
			if(z == "")alert("Fill username input");
			
				
		},
		goToLogin : function() {
			router.push(`/login`);
		},
		goToStart : function() {
			router.push(`/`);
		},
		duplicateUsername : function()
		{
			let z = document.getElementById('username').value;
			if(z == "")alert("Fill username input");
			for (const s in this.list)
			{
				if(this.list[s].username.toLowerCase() == z)
				{
					alert("Username is already taken");
				}
			}
			let regexxx = new RegExp('[A-Z]*[a-z]*[1-9]*');
			if(!regexxx.test(z))
			{
				alert("Invalid username");
			}
		}
		
	}
	
,mounted(){
	this.temp = true;
	this.hideCheck();
		axios
			.get('customer/listinit', this.list)
			.then(response => (this.list = response.data));
		axios
			.post('sportObject/catchrole', this.list)
			.then(response => this.catchRole(response.data));
	}
})