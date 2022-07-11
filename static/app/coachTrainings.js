Vue.component("coachTraining", {
	data: function () {
		return {
			trainingList:null,
			personalList:null,
			groupList:null,
			searchData: {soName:null, soType:null, priceFrom:null, priceTo:null, type:null}
			
		}
	},
	template: `
		<div>
			<nav class="navbar navbar-expand-sm  bg-dark">
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
		<div class="add-so d-flex justify-content-center" >
		
		<div >
			<p class="h3 ms-5 mb-3">My trainings</p>
			<div >
			<div class="d-flex flex-row">
				<div >
					<div class="a ">
				
    					<table id="a" style="margin:auto;width:auto;">
	    					<tr bgcolor="grey">
	    						<th style="min-width:50px">Name </th>
	    						<th style="min-width:50px">Type</th>
	    						<th style="min-width:50px">Object <button v-on:click="sortNameDown()" >&uarr;</button><button v-on:click="sortNameUp()" >&darr;</button></th>
	    						<th style="min-width:50px">Object type</th>
	    						<th style="min-width:50px">Duration</th>
	    						<th style="min-width:50px">Price  <button v-on:click="sortPriceDescending()" >&uarr;</button><button v-on:click="sortPriceAscending()" >&darr;</button></th>
	    						<th style="min-width:50px">Coach</th>
	    						<th style="min-width:50px">Description</th>
	    						<th style="min-width:50px">Date</th>
	    				
	    					</tr>
	    			
	    					<tr class="data" v-for="sTraining in trainingList">
	    						<td >{{sTraining.name}}</td>
	    						<td >{{sTraining.type}}</td>
	    						<td >{{sTraining.sportObject.objectName}}</td>
	    						<td >{{sTraining.sportObject.objectType}}</td>
	    						<td >{{sTraining.duration}}</td>
	    						<td >{{sTraining.price}}</td>
	    						<td >{{sTraining.trainer.name}}</td>
	    						<td >{{sTraining.description}}</td>
	    						<td >{{sTraining.trainingDate}}</td>
	    				
	    					</tr>
	    				</table>
	    			</div>
	    			<br/>
	    			<br/>
	    			<br/>
	    			<div class="a">
    					<table id="aaaa" style="margin:auto;width:auto;">
	    					<tr bgcolor="grey">
	    						<th style="min-width:50px">Name </th>
	    						<th style="min-width:50px">Type</th>
	    						<th style="min-width:50px">Object <button v-on:click="sortNameDownPersonal()" >&uarr;</button><button v-on:click="sortNameUpPersonal()" >&darr;</button></th>
	    						<th style="min-width:50px">Object type</th>
	    						<th style="min-width:50px">Duration</th>
	    						<th style="min-width:50px">Price  <button v-on:click="sortPriceDescendingPersonal()" >&uarr;</button><button v-on:click="sortPriceAscendingPersonal()" >&darr;</button></th>
	    						<th style="min-width:50px">Coach</th>
	    						<th style="min-width:50px">Description</th>
	    						<th style="min-width:50px">Date</th>
	    						<th style="min-width:50px">&nbsp</th>
	    				
	    					</tr>
	    			
	    					<tr class="data" v-for="sTraining in personalList">
	    						<td >{{sTraining.name}}</td>
	    						<td >{{sTraining.type}}</td>
	    						<td >{{sTraining.sportObject.objectName}}</td>
	    						<td >{{sTraining.sportObject.objectType}}</td>
	    						<td >{{sTraining.duration}}</td>
	    						<td >{{sTraining.price}}</td>
	    						<td >{{sTraining.trainer.name}}</td>
	    						<td >{{sTraining.description}}</td>
	    						<td >{{sTraining.trainingDate}}</td>
	    						<td ><button v-on:click="cancelTraining(sTraining);ruterIdi();">Cancel</button></td>
	    					</tr>
	    				</table>
	    			</div>
	    		
	    			<br/>
	    			<br/>
	    			<br/>
	    			<div class="a">
    					<table id="aa"   style="margin:auto;width:auto;">
	    					<tr bgcolor="grey">
	    						<th style="min-width:50px">Name </th>
	    						<th style="min-width:50px">Type</th>
	    						<th style="min-width:50px">Object <button v-on:click="sortNameDownGroup()" >&uarr;</button><button v-on:click="sortNameUpGroup()" >&darr;</button></th>
	    						<th style="min-width:50px">Object type</th>
	    						<th style="min-width:50px">Duration</th>
	    						<th style="min-width:50px">Price <button v-on:click="sortPriceDescending()" >&uarr;</button><button v-on:click="sortPriceAscending()" >&darr;</button></th>
	    						<th style="min-width:50px">Coach</th>
	    						<th style="min-width:50px">Description</th>
	    						<th style="min-width:50px">Date</th>
	    				
	    					</tr>
	    			
	    					<tr class="data" v-for="sTraining in groupList">
	    						<td >{{sTraining.name}}</td>
	    						<td >{{sTraining.type}}</td>
	    						<td >{{sTraining.sportObject.objectName}}</td>
	    						<td >{{sTraining.sportObject.objectType}}</td>
	    						<td >{{sTraining.duration}}</td>
	    						<td >{{sTraining.price}}</td>
	    						<td >{{sTraining.trainer.name}}</td>
	    						<td >{{sTraining.description}}</td>
	    						<td >{{sTraining.trainingDate}}</td>
	    					</tr>
	    				</table>
	    			</div>
	    			
	    		</div>
	    		<div>
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
	    				
	    				<button class="btn btn-secondary" v-on:click="validateSearch()"  >Search</button>
	    			</div>
	    		</div>
	    		</div>
	    		</div>
	    		<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

			</div>
			</div>
		</div>
		</div>
	
	
	`,
	methods : {
		goBack : function() {
			router.push(`/`);
		},
		cancelTraining : function (training) {
			axios
			.post('customer/cancelTraining', {"name":training.name})
			.then(response => (toast('Training ' + training.name + " has been canceled")))
		},
		ruterIdi : function()
		{
			router.go(0);
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
		
		sortPriceAscendingPersonal : function() {
			this.personalList.sort(this.comparePriceUp);
		},
		
		sortPriceDescendingPersonal : function() {
			this.personalList.sort(this.comparePriceDown);
		},
		sortPriceAscendingGroup : function() {
			this.groupList.sort(this.comparePriceUp);
		},
		
		sortPriceDescendingGroup : function() {
			this.groupList.sort(this.comparePriceDown);
		},
		compareNameUp : function( a, b ) {
			  if ( a.sportObject.objectName < b.sportObject.objectName ){
			    return -1;
			  }
			  if ( a.sportObject.objectName > b.sportObject.objectName ){
			    return 1;
			  }
			  return 0;
			},
			
		compareNameDown : function(a,b) {
			if ( a.sportObject.objectName < b.sportObject.objectName ){
			    return 1;
			 }
			 if ( a.sportObject.objectName > b.sportObject.objectName ){
			    return -1;
			 }
			 return 0;	
		},
		sortNameDown : function() {
			this.trainingList.sort(this.compareNameDown);
		},
		
		sortNameUp : function() {
			this.trainingList.sort(this.compareNameDown)
		},
		
		sortNameUpPersonal : function() {
			this.personalList.sort(this.compareNameUp);
		},
		
		sortNameDownPersonal : function() {
			this.personalList.sort(this.compareNameDown);
		},
		
		sortNameUpGroup : function() {
			this.groupList.sort(this.compareNameUp);
		},
		
		sortNameDownGroup : function() {
			this.groupList.sort(this.compareNameDown);
		},
		
		searchTrainings : function() {
			
			var nameSearch = document.getElementById('search-training-so-name').value;
			var typeSearch = document.getElementById('search-training-so-type').value;
			var priceFromSearch = document.getElementById('search-training-price-from').value;
			var priceToSearch = document.getElementById('search-training-price-to').value;
			var typeeSearch = document.getElementById('search-training-type').value;
			
			if (nameSearch == "") {
				this.searchData.soName = "None";
			}
			
			if (priceFromSearch == "") {
				this.searchData.priceFrom = "None";
			}
			
			if (priceToSearch == "") {
				this.searchData.priceTo = "None";
			}
			
			if (typeSearch == "") {
				this.searchData.soType = "None";
			}
			
			if (typeeSearch == "") {
				this.searchData.type = "None";
			}
			
			
			
			axios
				.post('sportObject/searchTrainersGymTrainings', this.searchData)
				.then(response => this.trainingList = response.data);
				
			axios
				.post('sportObject/searchTrainersPersonalTrainings', this.searchData)
				.then(response => this.personalList = response.data);
				
			axios
				.post('sportObject/searchTrainersGroupTrainings', this.searchData)
				.then(response => this.groupList = response.data);
		},
		
		validateSearch : function () {
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
		logoutUser : function () {
				//this.hideButton("");
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (response.data))
		         router.push("/");
		         router.go(0);
		},
		goToEditProfile : function() {
			router.push(`/editprofile`);
		},
		goToHomePage : function() {
			router.push("/");
		},
		goToMyTrainings : function()
		{
			router.push(`/coachTraining`);
		}
		
		
		
	},
	
	mounted() {
		axios
			.get('customer/mytrainings', this.trainingList)
			.then(response => this.trainingList = response.data);
		axios
			.get('customer/mypersonal', this.personalList)
			.then(response => this.personalList = response.data);
		axios
			.get('customer/mygroup', this.groupList)
			.then(response => this.groupList = response.data);
		}
});