Vue.component("sportObjectsView", {
	data: function() {
		return {
			sportObjectList: null,
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null},
			hideFlag:null
		}
	},
	template: `  
    	<div class="home-page" style="background-color:gray; margin-bottom:100px;">
    		<div class="header-wrapper">
    			<h3 style="margin:auto">Sport object view</h3>
    			<button v-on:click="goToLogin()" class="login-btn" id="btn-login" >Login</button>
    			<button v-on:click="goToRegister();" class="register-btn" id="btn-register">Register</button>
    			<button v-on:click="logoutUser();" id="btn-logout" style="margin-right:5%; width:80px">Log out</button>
    		</div>
    		<div class="sport-objects-view">
    			<table id="soTable" border="1" style="margin:auto">
	    			<tr bgcolor="lightgrey">
	    				<th style="min-width:50px">Name <button v-on:click="sortNameFunction()" >&uarr;</button> <button v-on:click="sortNameDown()" >&darr;</button></th>
	    				<th style="min-width:50px">Type</th>
	    				<th style="min-width:50px">Location <button v-on:click="sortLocationFunction()" >&uarr;</button> <button v-on:click="sortLocationDown()" >&darr;</button></th>
	    				<th style="min-width:50px">Logo</th>
	    				<th style="min-width:50px">Work hours</th>
	    				<th style="min-width:50px">Offer</th>
	    				<th style="min-width:50px">Average mark <button v-on:click="sortAvgMarkFunction()" >&uarr;</button> <button v-on:click="sortAvgMarkDown()" >&darr;</button></th>
	    				<th style="min-width:50px">Status</th>
	    			</tr>
	    			
	    			<tr class="data" v-for="sObject in sportObjectList" v-on:click="showSelectedObject(sObject)">
	    				<td >{{sObject.objectName}}</td>
	    				<td >{{sObject.objectType}}</td>
	    				<td >{{sObject.location.longitude + ', ' + sObject.location.latitude + ', ' + sObject.location.address.streetAndNumber + ', '
	    				+ sObject.location.address.city + ', ' + sObject.location.address.zipCode}}</td>
	    				<td ><img v-bind:src="sObject.logo" style="width:100px; height:100px;"></img></td>
	    				<td >{{sObject.workHour}}</td>
	    				<td >{{sObject.objectOffer}}</td>
	    				<td >{{sObject.avarageMark}}</td>
	    				<td ><span v-if="sObject.status == true">Open</span><span v-else>Closed</span></td>
	    			</tr>
	    		</table>
	    		<div class="objects-search">
	    			<div>
	    				<p>Search by name: </p>
	    				<input id="searchName"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			<div>
	    				<p>Search by type: </p>
	    				<select id="searchType">
	    					<option></option>
	    					<option value="Gym">Gym</option>
	    					<option value="Pool">Pool</option>
	    					<option value="SportCenter">Sport center</option>
	    				</select>
	    			</div>
	    			<div>
	    				<p>Search by location: </p>
	    				<input id="searchLocation"  type="text" placeholder="Search...">
	    			</div>
	    			<div>
	    				<p>Search by average grade: </p>
	    				<input id="searchGrade"  type="text" placeholder="Search...">
	    			</div>
	    			<div style="margin-top:10px;">
	    				<input type="checkbox" id="checkbox-ignore" name="ignore-closed" value="checked">
	    				<label for="ignore-closed">Show open only</label>
	    			</div>
	    			<div class="search-btn-wrapper">
	    				
	    				<button type="submit" v-on:click="searchName()">Search</button>
	    			</div>
	    		</div>
	    	</div>
    	</div>		  
    	`,
	mounted(){
		document.getElementById("btn-logout").disabled = true;
		this.hideCheck();
		axios
			.get('sportObjects/read', this.sportObjectList)
			.then(response => (this.sportObjectList = response.data));
		
			
	},
	methods: {
		searchByName: function() {
			alert(this.sportObject.objectName);
			axios 
				.post('sportObjects/search', this.sportObject, this.sportObjectList)
				.then(response => alert(response.data))
		},
		logoutUser : function () {
				this.hideButton("");
				axios  
		          .get('customer/logout',this.user)
		          .then(response => (response.data))
		},
		showList: function() {
			
			axios 
				.get('sportObjects/show', this.sportObjectList)
				.then(response => alert(response.data))
		},
		hideButton: function(check) {
			this.hideFlag = check;
			
			if (this.hideFlag == "logged") {
				document.getElementById("btn-login").disabled= true;
				document.getElementById("btn-register").disabled = true;
				document.getElementById("btn-logout").disabled = false;
			} else if (this.hideFlag=""){
				document.getElementById("btn-logout").disabled = true;
			}
			else {
				document.getElementById("btn-logout").disabled = true;
				document.getElementById("btn-login").disabled= false;
				document.getElementById("btn-register").disabled = false;
			}
		},
		
		hideCheck: function() {
			
			axios 
				.post('sportObjects/hide', this.hideFlag)
				.then(response =>  this.hideButton(response.data))
		},
		
		searchName: function() {
			
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
			
			
		},
		
		compareName : function( a, b ) {
			  if ( a.objectName < b.objectName ){
			    return -1;
			  }
			  if ( a.objectName > b.objectName ){
			    return 1;
			  }
			  return 0;
			},
			
		compareNameDown : function(a,b) {
			if ( a.objectName < b.objectName ){
			    return 1;
			 }
			 if ( a.objectName > b.objectName ){
			    return -1;
			 }
			 return 0;	
		},
		
		sortNameDown : function() {
			this.sportObjectList.sort(this.compareNameDown);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		sortNameFunction : function () {
			this.sportObjectList.sort(this.compareName);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		goToLogin : function() {
			router.push(`/login`);
		},
		
		goToRegister : function() {
			router.push(`/register`);
		},
		
		sortAvgMarkFunction : function() {
			this.sportObjectList.sort(this.compareNum);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		compareNum : function(a,b) {
			return a.avarageMark - b.avarageMark;
		},
		
		compareNumDown : function(a,b) {
			return b.avarageMark - a.avarageMark;
		},
		
		sortAvgMarkDown : function() {
			this.sportObjectList.sort(this.compareNumDown);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		compareLocation : function( a, b ) {
			if ( a.location.address.city < b.location.address.city){
			  return -1;
			}
			if ( a.location.address.city > b.location.address.city ){
			  return 1;
			}
			  return 0;
		},
			
		sortLocationFunction : function () {
			this.sportObjectList.sort(this.compareLocation);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		compareLocationDown : function( a, b ) {
			if ( a.location.address.city < b.location.address.city){
			  return 1;
			}
			if ( a.location.address.city > b.location.address.city ){
			  return -1;
			}
			  return 0;
		},
		sortLocationDown : function () {
			this.sportObjectList.sort(this.compareLocationDown);
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			var checkboxVal = document.getElementById('checkbox-ignore').checked;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchTypeInput == "" && searchLocationInput == "" && searchGradeInput == "") {
				for (const s in this.sportObjectList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.sportObjectList) {
				if (this.sportObjectList[s].objectName.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.sportObjectList[s].objectType.toString().indexOf(searchTypeInput) > -1
				&& (this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 || this.sportObjectList[s].location.address.city.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1  )
				&& this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {
					if (checkboxVal==true && this.sportObjectList[s].status==false) {
						tr[s].style.display = "none";
					}
				}
				else {
					tr[s].style.display = "none";
				}
			}
		},
		
		//Ne koristim jos
		showSelectedObject : function (sObject) {
			axios
				.post('sportObject/getOne', sObject)
				.then(response => response.data)
				
			router.go(`/oneSportObject`);
		}
		
	}
	
});