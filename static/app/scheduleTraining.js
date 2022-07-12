Vue.component("scheduleTraining", {
	data: function () {
		return {
			trainingList:null,
			personalList:null,
			groupList:null
			
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
		<div class="add-so-body ">
			<p class="h3 ms-5 mb-3">My trainings</p>
			
	    		<div  class="sport-objects-view">
    			<table id="soTable" style="margin:auto;">
	    			<tr bgcolor="grey">
	    				<th style="min-width:50px">Name </th>
	    				<th style="min-width:50px">Type</th>
	    				<th style="min-width:50px">Object</th>
	    				<th style="min-width:50px">Duration</th>
	    				<th style="min-width:50px">Coach</th>
	    				<th style="min-width:50px">Description</th>
	    				<th style="min-width:50px">Date</th>
	    				<th style="min-width:50px">&nbsp</th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.type}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.duration}}</td>
	    				<td >{{sTraining.trainer.name}}</td>
	    				<td >{{sTraining.description}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    				<td ><button v-on:click="scheduleTraining(sTraining);ruterIdi();">Schedule</button></td>
	    			</tr>
	    		</table>
	    		</div>
	    		
	    		
	    		<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

			</div>
		</div>
	</div>
	
	
	`,
	methods : {
		goBack : function() {
			router.push(`/`);
		},
		scheduleTraining : function (training) {
			axios
			.post('customer/scheduleTraining', {"name": training.name})
			.then(response => response.data)
		},
		ruterIdi : function()
		{
			router.go(0);
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
			.get('customer/scheduleTrainings', this.trainingList)
			.then(response => this.trainingList = response.data);
		}
});