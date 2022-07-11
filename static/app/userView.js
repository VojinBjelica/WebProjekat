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
    		
    		<div class="header-wrapper">
    			<h3 style="margin:auto;text-align:center;">User view</h3>
    			
    		</div>
    		<div class="sport-objects-view">
    			<table id="soTable"  style="margin:auto;width:auto">
	    			<tr bgcolor="grey">
	    				<th style="min-width:50px">Name<button v-on:click="sortNameFunction()" >&uarr;</button> <button v-on:click="sortNameDown()" >&darr;</button> </th>
	    				<th style="min-width:50px">Surname<button v-on:click="sortSurnameFunction()" >&uarr;</button> <button v-on:click="sortSurnameDown()" >&darr;</button></th>
	    				<th style="min-width:50px">Username<button v-on:click="sortUsernameFunction()" >&uarr;</button> <button v-on:click="sortUsernameDown()" >&darr;</button> </th>
	    				<th style="min-width:50px">Password</th>
	    				<th style="min-width:50px">Date</th>
	    				<th style="min-width:50px">Gender</th>
	    				<th style="min-width:50px">Role </th>
	    				<th style="min-width:50px">Points<button v-on:click="sortPoints()" >&uarr;</button> <button v-on:click="sortPointsDown()" >&darr;</button> </th>
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
	    				<td >{{userr.collectedPoints}}</td>
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
		compareName : function( a, b ) {
			  if ( a.name < b.name ){
			    return -1;
			  }
			  if ( a.name > b.name ){
			    return 1;
			  }
			  return 0;
			},
			
		compareNameDown : function(a,b) {
			if ( a.name < b.name ){
			    return 1;
			 }
			 if ( a.name > b.name ){
			    return -1;
			 }
			 return 0;	
		},
		compareSurname : function( a, b ) {
			  if ( a.surname < b.surname ){
			    return -1;
			  }
			  if ( a.surname > b.surname ){
			    return 1;
			  }
			  return 0;
			},
			
		compareSurnameDown : function(a,b) {
			if ( a.surname < b.surname ){
			    return 1;
			 }
			 if ( a.surname > b.surname ){
			    return -1;
			 }
			 return 0;	
		},
		compareUsername : function( a, b ) {
			  if ( a.username < b.username ){
			    return -1;
			  }
			  if ( a.username > b.username ){
			    return 1;
			  }
			  return 0;
			},
			
		compareUsernameDown : function(a,b) {
			if ( a.username < b.username ){
			    return 1;
			 }
			 if ( a.username > b.username ){
			    return -1;
			 }
			 return 0;	
		},
		
		
		sortNameDown : function() {
			this.userList.sort(this.compareNameDown);
			this.searchName();
		},
		
		sortNameFunction : function () {
			this.userList.sort(this.compareName);
			this.searchName();
		},
		sortSurnameDown : function() {
			this.userList.sort(this.compareSurnameDown);
			this.searchName();
		},
		
		sortSurnameFunction : function () {
			this.userList.sort(this.compareName);
			this.searchName();
		},
		sortUsernameDown : function() {
			this.userList.sort(this.compareUsernameDown);
			this.searchName();
		},
		
		sortUsernameFunction : function () {
			this.userList.sort(this.compareUsername);
			this.searchName();
		},
		compareNum : function(a,b) {
			return a.collectedPoints - b.collectedPoints;
		},
		
		compareNumDown : function(a,b) {
			return b.collectedPoints - a.collectedPoints;
		},
		sortPoints : function() {
			this.userList.sort(this.compareNum);
			this.searchName();
		},
		sortPointsDown : function() {
			this.userList.sort(this.compareNumDown);
			this.searchName();
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
		},
		

		setDeleted : function(training)
		{
			axios 
				.post('customer/setdeleted', {"username": training.username})
				.then(response => (this.userList = response.data))
		}
	
	}
	});