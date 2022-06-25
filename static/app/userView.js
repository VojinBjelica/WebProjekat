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
	    			</tr>
	    			
	    			<tr class="data" v-for="userr in userList">
	    				<td >{{userr.name}}</td>
	    				<td >{{userr.surname}}</td>
	    				<td >{{userr.username}}</td>
	    				<td >{{userr.password}}</td>
	    				<td >{{userr.dateOfBirth}}</td>
	    				<td >{{userr.gender}}</td>
	    				<td >{{userr.role}}</td>
	    			</tr>
	    		</table>
	    		
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
	
	}
	});