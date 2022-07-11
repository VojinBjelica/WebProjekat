Vue.component("userview", {
	data: function() {
		return {
			 temp:null,
			 hideFlag:null,
		     list:null,
		     userList:null
		}
	},
	template: `  
    	<div class="home-page" style="background-color:lightgrey; margin-bottom:100px;">
    		<div class="header-wrapper">
    			<h3 style="margin:auto;text-align:center;">User view</h3>
    			
    		</div>
    		<div class="sport-objects-view">
    			<table id="soTable"  style="margin:auto;width:auto">
	    			<tr bgcolor="grey">
	    				<th style="min-width:50px">Name </th>
	    				<th style="min-width:50px">Surname</th>
	    				<th style="min-width:50px">Username </th>
	    				<th style="min-width:50px">Password</th>
	    				<th style="min-width:50px">Date</th>
	    				<th style="min-width:50px">Gender</th>
	    				<th style="min-width:50px">Role </th>
	    				<th style="min-width:50px">&nbsp </th>
	    			</tr>
	    			
	    			<tr class="data" v-for="userr in userList">
	    				<td >{{userr.name}}</td>
	    				<td >{{userr.surname}}</td>
	    				<td >{{userr.username}}</td>
	    				<td >{{userr.password}}</td>
	    				<td >{{userr.dateOfBirth}}</td>
	    				<td >{{userr.gender}}</td>
	    				<td >{{userr.role}}</td>
	    				<td ><button v-on:click="setDeleted(userr);ruterIdi();">Delete</button></td>
	    			</tr>
	    		</table>
	    		<div class="objects-search">
	    			<div class="container">
	    				<p style="margin-top:10px">Search by name: </p>
	    				<input id="searchName" class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			<div class="container">
	    				<p style="margin-top:10px">Search by surname: </p>
	    				<input id="searchSurname" class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			<div class="container">
	    				<p style="margin-top:10px">Search by username: </p>
	    				<input id="searchUsername" class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			<div class="container">
	    				<p style="margin-top:10px"	>Search by role: </p>
	    				<input id="searchRole" class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			
	    			<div class="search-btn-wrapper">
	    				
	    				<button type="submit" class="btn btn-secondary" v-on:click="searchName()">Search</button>
	    			</div>
	    		</div>
	    		
	    		</div>
	    		<a href="#/"><button style="margin-left:45%;margin-top:5px" class="login-btn" id="btn-login" >Start page</button></a>
    			
    	</div>		  
    	`,
    	mounted(){
			
			axios 
				.get('customer/showuserlist', this.userList)
				.then(response => (this.userList = response.data))
				
		
	},
    	methods: {
		ruterIdi : function()
		{
			router.go(0);
		},
		searchName: function() {
			
			var searchNameInput = document.getElementById("searchName").value;
			var table = document.getElementById("soTable");
			var searchSurnameInput = document.getElementById("searchSurname").value;
			var searchUsernameInput = document.getElementById("searchUsername").value;
			var searchRoleInput = document.getElementById("searchRole").value;
			
			
			var tr = table.getElementsByClassName("data");
			for (const s in this.userList) {
					tr[s].style.display = "";
				}
			if (searchNameInput == "" && searchSurnameInput == "" && searchUsernameInput == "" && searchRoleInput == "") {
				for (const s in this.userList) {
					tr[s].style.display = "";
				}
			}	
			
			for (const s in this.userList) {
				if (this.userList[s].name.toLowerCase().indexOf(searchNameInput.toLowerCase()) > -1 && this.userList[s].surname.toLowerCase().indexOf(searchSurnameInput) > -1
				&& (this.userList[s].username.toLowerCase().indexOf(searchUsernameInput.toLowerCase()) > -1 && this.userList[s].role.toString().toLowerCase().indexOf(searchRoleInput.toLowerCase()) > -1) 
				){
					
				}
				else {
					tr[s].style.display = "none";
				}
			}
			
			
		},
		setDeleted : function(training)
		{
			axios 
				.post('customer/setdeleted', {"username": training.username})
				.then(response => (this.userList = response.data))
		}
	
	}
	});