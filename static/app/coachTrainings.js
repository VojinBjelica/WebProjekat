Vue.component("coachTraining", {
	data: function () {
		return {
			trainingList:null,
			personalList:null,
			groupList:null
			
		}
	},
	template: `
		<div class="add-so d-flex justify-content-center" >
		<div class="add-so-body ">
			<p class="h3 ms-5 mb-3">My trainings</p>
			<div class="a">
    			<table id="a" style="margin:auto;width:auto;">
	    			<tr bgcolor="grey">
	    				<th style="min-width:50px">Name </th>
	    				<th style="min-width:50px">Type</th>
	    				<th style="min-width:50px">Object</th>
	    				<th style="min-width:50px">Duration</th>
	    				<th style="min-width:50px">Coach</th>
	    				<th style="min-width:50px">Description</th>
	    				<th style="min-width:50px">Date</th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.type}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.duration}}</td>
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
	    				<th style="min-width:50px">Object</th>
	    				<th style="min-width:50px">Duration</th>
	    				<th style="min-width:50px">Coach</th>
	    				<th style="min-width:50px">Description</th>
	    				<th style="min-width:50px">Date</th>
	    				<th style="min-width:50px">&nbsp</th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in personalList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.type}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.duration}}</td>
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
	    				<th style="min-width:50px">Object</th>
	    				<th style="min-width:50px">Duration</th>
	    				<th style="min-width:50px">Coach</th>
	    				<th style="min-width:50px">Description</th>
	    				<th style="min-width:50px">Date</th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in groupList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.type}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.duration}}</td>
	    				<td >{{sTraining.trainer.name}}</td>
	    				<td >{{sTraining.description}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    			</tr>
	    		</table>
	    		</div>
	    		<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

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