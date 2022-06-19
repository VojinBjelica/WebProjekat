Vue.component("sportObjectsView", {
	data: function() {
		return {
			sportObjectList: null,
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null}
		}
	},
	template: ` 
    	<div style="background-color:gray; margin-bottom:100px;">
    	
    		<h3 style="margin:auto">Sport object view</h3>
    		<table id="soTable" border="1" style="margin:auto">
	    		<tr bgcolor="lightgrey">
	    			<th style="min-width:50px">Name</th>
	    			<th style="min-width:50px">Type</th>
	    			<th style="min-width:50px">Location</th>
	    			<th style="min-width:50px">Logo</th>
	    			<th style="min-width:50px">Work hours</th>
	    			<th style="min-width:50px">Offer</th>
	    			<th style="min-width:50px">Average mark</th>
	    			<th style="min-width:50px">Status</th>
	    		</tr>
	    			
	    		<tr class="data" v-for="sObject in sportObjectList">
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
	    	<div>
	    		<p>Search by name: </p>
	    		<input id="searchName"  type="text" placeholder="Search...">
	    		<button type="submit" v-on:click="searchName()">Search</button>
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
    	</div>		  
    	`,
	mounted(){
		alert("Radi");
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
		showList: function() {
			
			axios 
				.get('sportObjects/show', this.sportObjectList)
				.then(response => alert(response.data))
		},
		
		searchName: function() {
			
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchTypeInput = document.getElementById("searchType").value;
			var searchLocationInput = document.getElementById("searchLocation").value;
			var searchGradeInput = document.getElementById("searchGrade").value;
			
			
			
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
				&& this.sportObjectList[s].location.address.streetAndNumber.toString().toLowerCase().indexOf(searchLocationInput.toLowerCase()) > -1 && this.sportObjectList[s].avarageMark.toString().indexOf(searchGradeInput.toString()) > -1) {}
				else {
					tr[s].style.display = "none";
				
					
					
					
					
					
					
				}
			}
			
		}
	}
	
});