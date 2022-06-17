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
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td>
<p>Name:</p>
</td>
<td>
<input v-model="user.name" type="text" />
</td>
</tr>
<tr>
<td>
<p>Surname:</p>
</td>
<td>
<input v-model="user.surname" type="text" />
</td>
</tr>
<tr>
<td>
<p>Username::</p>
</td>
<td>
<input v-model="user.username" type="text" />
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
<input v-model="user.gender" type="text" />
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
<input type="button" v-on:click="writeCustomers()" value="Login"/>
</td>
<td>
<input  type="button" v-on:click="addCustomer()" value="Register"/>
</td>
</tr>
</table>
</div> `
, 
	methods : {
		writeCustomers : function () {
			alert("Usao")
				axios  
		          .post('customer/write',this.user)
		          .then(response => (alert(response.data)))
		          },
		addCustomer : function () {
			alert("Usao u add")
				axios  
		          .post('customer/add',this.user)
		          .then(response => (alert(response.data)))
		          
		          }
		
		          
			
		 
	}
,mounted(){}
})