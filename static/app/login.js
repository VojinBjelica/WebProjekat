Vue.component("login", {
	data: function () {
		    return {
		      RegisterTitle:"Register",
		      username:null,
		      password:null,
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
<h2>Login</h2>
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td colspan="2">
<p>Username:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input type="text" />
</td>
</tr>
<tr>
<td colspan="2">
<p>Password:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input type="text" />
</td>
</tr>
<tr>
<td>
<input type="button" value="Login"/>
</td>
<td>
<a href="#/register"> <input v-on:click="writeCustomers()"  type="button" value="Register"/></a>
</td>
</tr>
</table>
</div> `
,mounted(){}
});