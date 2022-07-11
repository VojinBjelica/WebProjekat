Vue.component("customerTraining", {
	data: function () {
		return {
			trainingList:null,
			personalList:null,
			groupList:null,
			searchData: {soName:null, priceFrom:null, priceTo:null, dateFrom:null, dateTo:null, soType:null, type:null}
			
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
		<div class="add-so d-flex justify-content-center" >
		<div >
			<p class="h3 ms-5 mb-3">My trainings</p>
			<div class="a">
    			<table id="a" style="margin:auto;width:auto;">
	    			<tr bgcolor="grey">
	    				<th style="min-width:150px">Name </th>
	    				<th style="min-width:150px">Object</th>
	    				<th style="min-width:150px">Object type</th>
	    				<th style="min-width:150px">Date</th>
	    				<th style="min-width:150px">Price</th>
	    				<th style="min-width:150px">Training type</th>
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.sportObject.objectType}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    				<td >{{sTraining.price}}</td>
	    				<td >{{sTraining.type}}</td>
	    			</tr>
	    		</table>
	    	</div>
	    	<br/>
	    	<br/>
	    	<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

		</div>
		
		
		<div style="margin-left:20px">
	    			
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by sport object name: </p>
	    				<input id="search-training-so-name" v-model="searchData.soName" class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by sport object type: </p>
	    				<select id="search-training-so-type" v-model="searchData.soType" class="form-select">
	    					<option value="None" selected > </option>
	    					<option value="Gym">Gym</option>
	    					<option value="Pool">Pool</option>
	    					<option value="SportCenter">SportCenter</option>
	    				</select>
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Date from: </p>
	    				<input id="search-training-date-from" v-model="searchData.dateFrom" class="form-control"  type="date" placeholder="Search...">
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Date to: </p>
	    				<input id="search-training-date-to" v-model="searchData.dateTo" class="form-control"  type="date" placeholder="Search...">
	    			</div>
	    			
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
	    				<select id="search-training-type" v-model="searchData.type" class="form-select">
	    					<option value="None" selected > </option>
	    					<option value="Personal">Personal trainings</option>
	    					<option value="Group">Group trainings</option>
	    					<option value="Gym">Gym</option>
	    				</select>
	    			</div>
	    			<div class="search-btn-wrapper">
	    				
	    				<button class="btn btn-secondary" v-on:click="validateSearch()" >Search</button>
	    			</div>
	    		
		</div>
		
		</div>
	</div>
	
	`,
	methods : {
		
		goBack : function() {
			router.push(`/`);
		},
		
		searchTrainings : function() {
			var nameSearch = document.getElementById('search-training-so-name').value;

			var priceFromSearch = document.getElementById('search-training-price-from').value;
			var priceToSearch = document.getElementById('search-training-price-to').value;
			var soTypeSearch = document.getElementById('search-training-so-type').value;
			var typeSearch = document.getElementById('search-training-type').value;
			
			if (nameSearch == "") {
				this.searchData.soName = "None";
			}
			
			if (priceFromSearch == "") {
				this.searchData.priceFrom = "None";
			}
			if (priceToSearch == "") {
				this.searchData.priceTo = "None";
			}
			if (soTypeSearch == null) {
				this.searchData.soType = None;
			}
			
			if (typeSearch == null) {
				this.searchData.type = None;
			}
			
			axios
				.post('customer/searchTrainingsForCustomer', this.searchData)
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
		
	},
	mounted() {
		axios
			.get('customer/showcustomertrainings', this.trainingList)
			.then(response => this.trainingList = response.data);
		}
		});