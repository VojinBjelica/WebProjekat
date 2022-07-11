Vue.component("oneSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null},
			searchData: {type:null, priceFrom:null, priceTo:null},
			trainingList:null
		}
	},
	template: `
		<div class= "home-page">
			<div class="container-fluid">
				<div>
					<p>Name: {{sportObject.objectName}}</p>
				</div>
				<div>
					<p>Type: {{sportObject.objectType}}</p>
				</div>
				<div>
					<p>Status: <span v-if="sportObject.status == true">Open</span><span v-else>Closed</span></p>
				</div>
				<div>
					<p>Location: {{sportObject.location.longitude + ', ' + sportObject.location.latitude + ', ' + sportObject.location.address.streetAndNumber + ', '
	    				+ sportObject.location.address.city + ', ' + sportObject.location.address.zipCode}}</p>
				</div>
				<div>
					<p>Logo: </p><img v-bind:src="sportObject.logo" style="width:200px; height:200px;"></img>
				</div>
				<div>
					<p>Average mark: {{sportObject.avarageMark}}</p>
				</div>
				
				<div class="sport-objects-view d-flex flex-row">
    		
				<table id="trTable"  style="margin:auto;">
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
	    				
	    				<button class="btn btn-secondary" v-on:click="validateSearch()" >Search</button>
	    			</div>
	    		</div>
	    		
	    		</div>
			</div>
		</div>
	
	`,
	mounted() {
		axios
			.post('sportObject/showOne', this.sportObject)
			.then(response => this.sportObject = response.data);
		axios
			.post('sportObject/showtable', this.trainingList)
			.then(response => this.trainingList = response.data);
		axios
			.get('sportObject/addView')
			.then(response => alert(response.data));
	},
	methods: {
		
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