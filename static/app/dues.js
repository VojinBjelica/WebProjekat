Vue.component("duesPayment", {
	
	data: function() {
		return {
			due: {ID:null, duesType:null, payDate:null, expirationDateAndTime:null, price:null, customer:null, status:null,promoCode:null},
			selektovano:null,
			tekst:null,
			datum:null,
			shower:null,
			price:null
		}
	},
	template: `
	<div>
		<nav class="navbar navbar-expand-md bg-dark">
    			<div class="container-fluid ">
    				<ul class="navbar-nav ms-auto">
    					<li class="nav-item">
    						<button v-on:click="goToEditProfile()" class="btn btn-primary btn-margin-left" >Edit profile</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToHomePage()" class="btn btn-primary btn-margin-left" >Home</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToCustomerTrainings()" class="btn btn-primary btn-margin-left" >My trainings</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToDues()" class="btn btn-primary btn-margin-left" >Dues</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToSchedule()" class="btn btn-primary btn-margin-left" >Schedule trainings</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="logoutUser()" class="btn btn-danger btn-margin-left" >Log out</button>
    					</li>
    				</ul>
    			</div>
    		</nav>
		<div style="background-color:gray;position: fixed;
  left:40%;
  top:10%;
  width:25%;
  height:auto;
  text-align:center;">
  

<h2 class="title">Dues</h2>
<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td colspan="2">
<p class="form-label" >Dues type:</p>
</td>
</tr>
<tr>
<td colspan="2">
<select v-model="due.duesType" v-on:change="selectChanged(due.duesType);disByType();"  >
<option value="Month">Month</option>
<option value="Year">Year</option>
</select>
</td>
</tr>

<tr>
<td colspan="2">
<p class="form-label" >{{this.tekst}}</p>
</td>
</tr>
<tr>
<td colspan="2">
<p class="form-label" >Pay date:</p>
</td>
</tr>
<tr >
<td colspan="2">
<input id="datumPocetka" v-model="due.payDate" class="form-control" type="date"  />
</td>
</tr>
<tr>
<td colspan="2">
<p class="form-label" >Expiration:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input id="isticanje" v-model="due.expirationDateAndTime" class="form-control" type="date" />
</td>
</tr>
<tr>
<td colspan="2">
<p class="form-label" >Price:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input id="cena" v-model="due.price" class="form-control"/>
</td>
</tr>
<tr>
<td colspan="2">
<p class="form-label" >Promo Code:</p>
</td>
</tr>
<tr>
<td colspan="2">
<input id="kod" v-model="due.promoCode" class="form-control"/>
</td>
</tr>
<tr>
<td>
<input  type="button" class="btn btn-primary" v-on:click="makeDue();expCheck();" style="margin-top:10px" value="Pay"/>
&nbsp
<input  type="button" class="btn btn-primary" v-on:click="calculateNewPrice();" style="margin-top:10px" value="Discount"/>

</td>
<td>
&nbsp
<a href="#/"> <input  type="button" class="btn btn-primary" style="margin-top:10px" value="Cancel"/></a>
</td>
</tr>
</table>
</div>
</div>
	
	`,
	methods : {
		makeDue : function () {
				axios  
		          .post('customer/due',this.due)
		          .then(response => response.data)
		          },
		disByType : function () {
				axios  
		          .post('customer/calcdis',this.due)
		          .then(response => this.due = response.data)
		          },      
		expCheck : function()
		{
			 axios  
		    	.post('customer/expchecker',this.user)
		    	.then(response => response.data)
		}   ,
		calculateNewPrice : function () {
				axios  
		          .post('customer/promoDisc',this.due)
		          .then(response => this.due = response.data)
		          },
		selectChanged : function(temp)
		{
			if(temp == "Month")
			{
				this.selektovano = "Month";
				this.tekst = "Clanarina vazi mesec dana pocev od dana uplate pa narednih 30.Cena je 2500.U cenu ulazi samo teretana zajedno sa tusevima."
				
				//this.shower = true;
				var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();
var mmm = String(today.getMonth() + 1).padStart(2, '0'); 
var yyy = today.getFullYear();	
	this.due.payDate = yyyy + '-' +  mm + '-' + dd;
	//document.getElementById("datumPocetka").value = '2022-07-04';
		
	if(parseInt(mm) == 12)
	{
		mm = String(1).padStart(2, '0');
		yyy = today.getFullYear() + 1;
	} 
	else
	{
		yyy = yyyy;
		mmm = String(today.getMonth() + 2).padStart(2, '0');
		
	}
	this.due.expirationDateAndTime = yyy + '-' +  mmm + '-' + dd;
	//document.getElementById("isticanje"). value = yyy + '-' +  mmm + '-' + dd;
	//document.getElementById("cena"). value = "2500";
	this.due.price = 2500;
		
			}
			else 
			{
				
				this.selektovano = "Year";
				this.tekst = "Clanarina vazi godinu dana pocev od dana uplate pa do istog datuma sledece godine.Cena je 25000.U cenu ulazi samo teretana zajedno sa tusevima."
				//this.shower = false;
							var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();
var mmm = String(today.getMonth() + 1).padStart(2, '0'); 
var yyy = today.getFullYear();	
	this.due.expirationDateAndTime = yyyy + '-' +  mm + '-' + dd;
		
	
	yyy = today.getFullYear() + 1;
	
	this.due.expirationDateAndTime = yyy + '-' +  mmm + '-' + dd;
	//document.getElementById("cena"). value = "25000";
		this.due.price = 25000;
			}
		},	 
	
	goToEditProfile : function() {
			router.push(`/editprofile`);
		},
		goToHomePage : function() {
			router.push("/");
		},
		goToCustomerTrainings : function()
		{
			router.push(`/customerTraining`);
		},
		goToDues : function()
		{
			router.push(`/duesPayment`);
		},
		goToSchedule : function()
		{
			router.push(`/scheduleTraining`);
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
		axios  
		    .post('customer/calculateType',this.due)
		    .then(response => response.data)
}
})
	
	