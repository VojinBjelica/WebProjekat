Vue.component("promoCodes", {
	data: function () {
		    return {
			
		      code:{promoCodeName:null,fromDate:null,toDate:null,numberOfUsing:null,discount:null}
		   
		    }
	},
	template: ` 
<div>
	<nav class="navbar navbar-expand-sm mb-3 bg-dark">
    			<div class="container-fluid">
    				<ul class="navbar-nav ms-auto">
    					<li class="nav-item">
    						<button v-on:click="goToRegister();" class="btn btn-success btn-margin-left" >Register</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToEditProfile()" class="btn btn-primary btn-margin-left" >Edit profile</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToHomePage()" class="btn btn-primary btn-margin-left" >Home</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToUsersView()" class="btn btn-primary btn-margin-left" >Users</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToPromoCodes()" class="btn btn-primary btn-margin-left" >Codes</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="logoutUser()" class="btn btn-danger btn-margin-left" >Log out</button>
    					</li>
    				</ul>
    			</div>
    		</nav>


<div style="background-color:gray;position: fixed;
  left:40%;
  top:15%;
  width:25%;
  height:70%;
  text-align:center;">
  	
  
  
  <br/>
  <br/>
<h2 class="h2">Promo Codes</h2>
<form name="myForm">
<br/>

<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td>
<p>Name:</p>
</td>
<td>
<input id="name" v-model="code.promoCodeName" class="form-control" type="text" />
</td>
</tr>
<tr>
<td>
<p>From:</p>
</td>
<td>
<input id="name" v-model="code.fromDate" class="form-control" type="date" />
</td>
</tr>
<tr>
<td>
<p>To:</p>
</td>
<td>
<input id="surname" v-model="code.toDate" class="form-control"   type="date" />
</td>
</tr>
<tr>
<td>
<p>Using Times:</p>
</td>
<td>
<input id="username" v-model="code.numberOfUsing" class="form-control"  type="text" />
</td>
</tr>
<tr>
<td>
<p>Discount(%):</p>
</td>
<td>
<input class="form-control" v-model="code.discount" type="text" />
</td>
</tr>


<tr>
<td>
<a href="#/" ><input type="button"  class="btn btn-success" v-on:click="addPromoCode();" value="Make code"/></a>
</td>
<td>
<a href="#/" ><input  type="button"  class="btn btn-success"  value="Cancel"/></a>
</td>
</tr>
</table>
</form>
</div>
</div> `
, 
	methods : {
		addPromoCode : function () {
				axios  
		          .post('customer/addPromoCode',this.code)
		          .then(response => response.data)
		},
		goToRegister : function() {
			router.push(`/register`);
		},
		goToEditProfile : function() {
			router.push(`/editprofile`);
		},
		goToHomePage : function() {
			router.push("/");
		},
		goToUsersView : function() {
			router.push(`/userview`);
		},
		goToPromoCodes : function()
		{
			router.push(`/promoCodes`);
		},
		logoutUser : function () {
				//this.hideButton("");
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (response.data))
		         router.push("/");
		         router.go(0);
		}
		
		
	}
	
,mounted(){
	
	}
})