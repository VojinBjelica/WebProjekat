Vue.component("register", {
	data: function () {
		    return {
		      list:null,
		      user:{name:null,surname:null,username:null,password:null,gender:null,dateOfBirth:null}
		   
		    }
	},
	template: ` 
<div style="background-color:gray;position: fixed;
  left:40%;
  top:25%;
  width:25%;
  height:70%;
  text-align:center;">
<h2>Register</h2>
<form name="myForm">
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td>
<p>Name:</p>
</td>
<td>
<input id="name" v-model="user.name" v-on:blur="validateName" type="text" />
</td>
</tr>
<tr>
<td>
<p>Surname:</p>
</td>
<td>
<input id="surname" v-model="user.surname" v-on:blur="validateSurname" type="text" />
</td>
</tr>
<tr>
<td>
<p>Username:</p>
</td>
<td>
<input id="username" v-model="user.username" v-on:blur="duplicateUsername" type="text" />
</td>
</tr>
<tr>
<td>
<p>Password:</p>
</td>
<td>
<input v-model="user.password" type="password" />
</td>
</tr>
<tr>
<td>
<p>Gender:</p>
</td>
<td>
<select v-model="user.gender">
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
<input v-model="user.dateOfBirth" type="date" />
</td>
</tr>
<tr>
<td>
<a href="#/login" ><input type="button" value="Login"/></a>
</td>
<td>
<a href="#/login" ><input  type="button" v-on:click="validateText();duplicateUsername();addCustomer();" value="Register"/></a>
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
		          .then(response => (response.data))
		          
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
		axios
			.get('customer/listinit', this.list)
			.then(response => (this.list = response.data));
	}
})