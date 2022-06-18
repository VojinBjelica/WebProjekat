Vue.component("register", {
	data: function () {
		    return {
		      
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
<input id="name" v-model="user.name" v-on:change="validateName" type="text" />
</td>
</tr>
<tr>
<td>
<p>Surname:</p>
</td>
<td>
<input id="surname" v-model="user.surname" v-on:change="validateSurname" type="text" />
</td>
</tr>
<tr>
<td>
<p>Username:</p>
</td>
<td>
<input id="username" v-model="user.username" v-on:change="validateUsername" type="text" />
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
<a href="#/" ><input type="button" v-on:click="writeCustomers()" value="Login"/></a>
</td>
<td>
<input  type="button" v-on:click="addCustomer()" value="Register"/>
</td>
</tr>
</table>
</form>
</div> `
, 
	methods : {
		writeCustomers : function () {
			alert("Usao");
				axios  
		          .post('customer/write',this.user)
		          .then(response => (alert(response.data)))
		          },
		addCustomer : function () {
			alert("Usao u add");
				axios  
		          .post('customer/add',this.user)
		          .then(response => (alert(response.data)))
		          
		          },
		  validateName : function() {
			var x = document.getElementById('name').value;
			let regex = new RegExp('[A-Z][a-z]+');
			if(!regex.test(x))
			{
				alert("Nije dobro uneto ime");
			}
			
				
		},
		 validateSurname : function() {
			let y = document.getElementById('surname').value;
			let regexx = new RegExp('[A-Z][a-z]+');
			if(!regexx.test(y))
			{
				alert("Nije dobro uneto prezime");
			}
			
				
		},
		 validateUsername : function() {
			let z = document.getElementById('username').value;
			let regexxx = new RegExp('[A-Z]*[a-z]*[1-9]*');
			if(!regexxx.test(z))
			{
				alert("Nije dobro unet username");
			}
			
				
		}
		
		
		
		          
			
		 
	}
,mounted(){}
})