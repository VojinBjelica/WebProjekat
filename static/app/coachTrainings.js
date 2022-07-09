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
		<div >
			<p class="h3 ms-5 mb-3">My trainings</p>
			<div class="a">
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