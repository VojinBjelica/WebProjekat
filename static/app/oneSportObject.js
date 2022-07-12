Vue.component("oneSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null},
			searchData: {type:null, priceFrom:null, priceTo:null},
			comment: {customer:null, sportObject:null,text:null,mark:null,approved:null,id:null},
			trainingList:null,
			commentList:null,
			fullcommentList:null,
			roleCaught:null,
			loggedRole:null,
			tworole:null
		}
	},
	template: `
		<div class= "home-page">
		
			<nav class="navbar navbar-expand-sm mb-3  bg-dark" v-if="loggedRole == 'None'">
    			<div class="container-fluid">
    				<ul class="navbar-nav ms-auto">
    					<li class="nav-item">
    						<button  v-on:click="goToLogin()" class="btn btn-success btn-margin-left"  >Login</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToRegister()" class="btn btn-success btn-margin-left" >Register</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToHomePage()" class="btn btn-primary btn-margin-left" >Home</button>
    					</li>
    					
    				</ul>
    			</div>
    		</nav>
    		
    		<nav class="navbar navbar-expand-sm mb-3 bg-dark" v-if="loggedRole == 'Administrator'">
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
    		
    		<nav class="navbar navbar-expand-sm mb-3 bg-dark" v-if="loggedRole == 'Manager'">
    			<div class="container-fluid">
    				<ul class="navbar-nav ms-auto">
    					
    					<li class="nav-item">
    						<button v-on:click="goToEditProfile()" class="btn btn-primary btn-margin-left" >Edit profile</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToHomePage()" class="btn btn-primary btn-margin-left" >Home</button>
    					</li>
    					<li class="nav-item">
    					
    						<button v-on:click="goToManagersSO()" class="btn btn-primary btn-margin-left" >My sport object</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="logoutUser()" class="btn btn-danger btn-margin-left" >Log out</button>
    					</li>
    				</ul>
    			</div>
    		</nav>
    		
    		<nav class="navbar navbar-expand-sm mb-3 bg-dark" v-if="loggedRole == 'Coach'">
    			<div class="container-fluid">
    				<ul class="navbar-nav ms-auto">
    					
    					<li class="nav-item">
    						<button v-on:click="goToEditProfile()" class="btn btn-primary btn-margin-left" >Edit profile</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToHomePage()" class="btn btn-primary btn-margin-left" >Home</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="goToMyTrainings()" class="btn btn-primary btn-margin-left"  >My trainings</button>
    					</li>
    					<li class="nav-item">
    						<button v-on:click="logoutUser()" class="btn btn-danger btn-margin-left" >Log out</button>
    					</li>
    				</ul>
    			</div>
    		</nav>
    		
    		<nav class="navbar navbar-expand-md mb-3 bg-dark" v-if="loggedRole == 'Customer'">
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
			
			<div class="mso-container " style="width:70%; margin-bottom:20px">
				<img v-bind:src="sportObject.logo" style="width:20%"/>
				<div style="display:flex;flex-direction:column;">
					<p class="mso-name  h3" >{{sportObject.objectName}}</p>
					<div class="d-flex ">
						<p style="font-size:20px;margin-left:10px">{{sportObject.objectType}}</p>
						<p style="font-size:20px;margin-left:20px" >Status: <span class="badge rounded-pill bg-success" v-if="sportObject.status == true">Open</span><span class="badge rounded-pill bg-danger" v-else>Closed</span></p>
					</div>
					<div>
						<p style="font-size:20px;margin-left:10px">Rating: <span class="badge rounded-pill bg-primary">{{sportObject.avarageMark}}</span></p>
					</div>
					<div>
						<p style="font-size:20px;margin-left:10px">Offer: {{sportObject.objectOffer}}</p>
					</div>
				</div>
				<div style="margin-left:50px; margin-top:45px" class="d-flex flex-column">
					<p style="font-size:20px;margin-left:10px">Location: {{sportObject.location.longitude + ', ' + sportObject.location.latitude + ', ' + sportObject.location.address.streetAndNumber + ', '
	    				+ sportObject.location.address.city + ', ' + sportObject.location.address.zipCode}}</p>
					<p style="font-size:20px;margin-left:10px">Work hour: {{sportObject.workHour}}</p>
				</div>
			</div>
			<br>
			<br>
			<div class="container-fluid">
				
				
				<div class="sport-objects-view d-flex flex-row" style="margin-bottom:10px">
    		
				<table id="trTable"  style="margin-bottom:10px; height:200px">
	    			<tr bgcolor="grey">
	    				<th style="min-width:50px">Name</th>
	    				<th style="min-width:50px">Type</th>
	    				<th style="min-width:50px">Picture</th>
	    				<th style="min-width:50px">Description</th>
	    				<th style="min-width:50px">Coach </th>
	    				<th style="min-width:50px">Price <button v-on:click="sortPriceDescending()" >&uarr;</button><button v-on:click="sortPriceAscending()" >&darr;</button></th>
	    				<th style="min-width:50px">Date <button v-on:click="sortDateDescending()" >&uarr;</button><button v-on:click="sortDateAscending()" >&darr;</button></th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sObject in trainingList">
	    				<td >{{sObject.name}}</td>
	    				<td >{{sObject.type}}</td>
	    				<td ><img v-bind:src="sObject.picture" style="width:100px; height:100px;"></img></td>
	    				<td >{{sObject.description}}</td>
	    				<td >{{sObject.trainer.name}}</td>
	    				<td >{{sObject.price}}</td>
	    				<td >{{sObject.trainingDate}}</td>
	    			</tr>
	    		</table>
	    		
	    		<div style="margin-left:20px">
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Price from: </p>
	    				<input id="search-training-price-from" v-model="searchData.priceFrom" class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Price to: </p>
	    				<input id="search-training-price-to" v-model="searchData.priceTo" class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by type: </p>
	    				<select id="search-training-type" v-model=searchData.type class="form-select">
	    					<option value="None" selected > </option>
	    					<option value="Personal">Personal trainings</option>
	    					<option value="Group">Group trainings</option>
	    					<option value="Gym">Gym</option>
	    				</select>
	    			</div>
	    			<div class="search-btn-wrapper">
	    				
	    				<button class="btn btn-secondary" v-on:click="validateSearch()" >Search</button>&nbsp
	    				<button v-if="roleCaught == true" class="btn btn-secondary" v-on:click="deleteObject();goBack();" >Delete Object</button>
	    			</div>
	    		</div>
	    		
	    		</div>
	    		<table v-if="tworole == false" align="center" border="2px"  id="comments"  >
	    			
	    			
	    			<tr class="data"  v-for="sObject in commentList">
	    				<td >{{sObject.customer.name}}</td>
	    				<td >&nbsp</td>
	    				<td >{{sObject.customer.surname}}</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >{{sObject.text}}</td>
	    				
	    			</tr>
	    			<tr>
	    			</tr>
	    		</table>
	    		<table v-if="tworole == true" align="center" border="2px"  id="comments"  >
	    			
	    			
	    			<tr class="data"  v-for="sObject in fullcommentList">
	    				<td >{{sObject.customer.name}}</td>
	    				<td >&nbsp</td>
	    				<td >{{sObject.customer.surname}}</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >{{sObject.text}}</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td >&nbsp</td>
	    				<td ><button v-if="sObject.approved == 0" v-on:click="approveComment(sObject);idi();">Approve</button></td>
	    				
	    				
	    				
	    			</tr>
	    			<tr>
	    			</tr>
	    		</table>
	    		<input v-model="comment.text" align="center" type="text"  id="komentar" width="600px" height="200px"><br/>
	    		<input align="center" type="button" v-on:click="addComment()" value="Add Comment">
	    		
			</div>
		</div>
	
	`,
	mounted() {
		axios
			.get('sportObjects/fillList', this.commentList)
			.then(response => this.commentList = response.data);
		axios
			.get('sportObjects/fillfullList', this.fullcommentList)
			.then(response => this.fullcommentList = response.data);
		axios
			.post('sportObject/showOne', this.sportObject)
			.then(response => this.sportObject = response.data);
		axios
			.post('sportObject/catchrole', this.sportObject)
			.then(response => this.catchRole(response.data));
		axios
			.post('sportObject/showtable', this.trainingList)
			.then(response => this.trainingList = response.data);
		axios
			.get('sportObject/addView')
			.then(response => response.data);
			
		axios
			.get('customer/getLoggedRole') 
			.then(response => this.loggedRole = response.data);
		
	},
	methods: {
		approveComment : function(comm)
		{
			axios
				.post('sportObjects/approveComment', {"id": '' + comm.id})
				.then(response => (toast('Training ' + com.id + " has been canceled")))
		},
		idi : function()
		{
			router.go(0);
		},
		logoutUser : function () {
				//this.hideButton("");
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (response.data))
		         router.push("/");
		         router.go(0);
		},
		goToLogin : function() {
			router.push(`/login`);
		},
		goToEditProfile : function() {
			router.push(`/editprofile`);
		},
		
		goToRegister : function() {
			router.push(`/register`);
		},
		goToUsersView : function() {
			router.push(`/userview`);
		},
		goToMyTrainings : function()
		{
			router.push(`/coachTraining`);
		},
		goToCustomerTrainings : function()
		{
			router.push(`/customerTraining`);
		},
		goToDues : function()
		{
			router.push(`/duesPayment`);
		},
		goToPromoCodes : function()
		{
			router.push(`/promoCodes`);
		},
		goToSchedule : function()
		{
			router.push(`/scheduleTraining`);
		},
		goToManagersSO : function() {
			router.push(`/managersSportObject`);
		},
		
		goToHomePage : function() {
			router.push("/");
		},
		
		addComment : function()
		{
			
			axios
				.post('sportObjects/addComment',this.comment)
				.then(response => response.data);
		},
		goBack  : function()
		{
			router.push(`/`);
		},
		deleteObject : function()
		{
			axios
			.post('sportObject/deleteObject', this.sportObject)
			.then(response => response.data);
		},
		catchRole : function(role)
		{
			if(role == "Administrator")this.roleCaught = true;
			else this.roleCaught = false;
			if(role == "Manager")this.tworole = true;
			if(role == "Administrator")this.tworole = true;
			if(role == "")this.tworole = false;
			if(role == "Coach")this.tworole = false;
			if(role == "Customer")this.tworole = false;
			
		},
		searchTrainings : function() {
			var typeSearch = document.getElementById('search-training-type').value;
			var priceFromSearch = document.getElementById('search-training-price-from').value;
			var priceToSearch = document.getElementById('search-training-price-to').value;
		
			if (priceFromSearch == "") {
				this.searchData.priceFrom = "None";
			}
			
			if (priceToSearch == "") {
				this.searchData.priceTo = "None";
			}
			
			if (typeSearch == "") {
				this.searchData.type = "None";
			}
			
			axios
				.post('sportObject/searchTrainingsOfObject', this.searchData)
				.then(response => this.trainingList = response.data);
		},
		
		validateSearch : function() {
			var priceFromSearch = document.getElementById('search-training-price-from').value;
			var priceToSearch = document.getElementById('search-training-price-to').value;
			var patternPrice = /^([0-9]+(.[0-9]+)?)?$/;
			
			
			var validFlagFrom = true;
			var validFlagTo = true;
			
			if(!patternPrice.test(priceFromSearch)) {
				validFlagFrom = false;
			} 
			if (!patternPrice.test(priceToSearch)) {
				validFlagTo = false;
			}
			if (priceFromSearch == "") {
				validFlagFrom = true;
			}
			if (priceToSearch == "") {
				validFlagTo = true;
			}
			
			if (priceFromSearch == "None") {
				validFlagFrom = true;
			}
			if (priceToSearch == "None") {
				validFlagTo = true;
			}
			
			if (validFlagTo == false || validFlagFrom == false) {
				alert("Price must be a number (example: 4.8)");
			} else {
				this.searchTrainings();
			}
		},
		comparePriceUp : function (a,b) {
			return a.price - b.price;
		},
		comparePriceDown : function (a,b) {
			return b.price - a.price;
		},
		sortPriceAscending : function() {
			
				this.trainingList.sort(this.comparePriceUp);
		},
		
		sortPriceDescending : function() {
			this.trainingList.sort(this.comparePriceDown);
		},
		compareDateUp : function(a,b) {
			return new Date(a.trainingDate) -  new Date(b.trainingDate);
		},
		
		compareDateDown : function (a,b) {
			return new Date(b.trainingDate) - new Date(a.trainingDate);
		},
		sortDateAscending :  function() {
			
				this.trainingList.sort(this.compareDateUp);
				
		},
		
		sortDateDescending : function() {
			this.trainingList.sort(this.compareDateDown);
		}
		
	}
	
	
	
	
});