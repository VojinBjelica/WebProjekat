Vue.component("customerTraining", {
	data: function () {
		return {
			trainingList:null,
			personalList:null,
			groupList:null,
			
		}
	},
	template: `
		<div class="add-so d-flex justify-content-center" >
		<div >
			<p class="h3 ms-5 mb-3">My trainings</p>
			<div class="a">
    			<table id="a" style="margin:auto;width:auto;">
	    			<tr bgcolor="grey">
	    				<th style="min-width:150px">Name </th>
	    				<th style="min-width:150px">Object</th>
	    				<th style="min-width:150px">Object type</th>
	    				<th style="min-width:150px">Date</th>
	    				<th style="min-width:150px">Price</th>
	    				<th style="min-width:150px">Training type</th>
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.sportObject.objectType}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    				<td >{{sTraining.price}}</td>
	    				<td >{{sTraining.type}}</td>
	    			</tr>
	    		</table>
	    	</div>
	    	<br/>
	    	<br/>
	    	<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

		</div>
		
		
		<div style="margin-left:20px">
	    			<div class="container">
	    				<p style="margin-top:10px">Search by name: </p>
	    				<input id="search-training-name"  class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by sport object name: </p>
	    				<input id="search-training-so-name"  class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by sport object type: </p>
	    				<select id="search-training-so-type"  class="form-select">
	    					<option value="None" selected > </option>
	    					<option value="Gym">Gym</option>
	    					<option value="Pool">Pool</option>
	    					<option value="SportCenter">SportCenter</option>
	    				</select>
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by date: </p>
	    				<input id="search-training-name"  class="form-control"  type="text" placeholder="Search...">
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Price from: </p>
	    				<input id="search-training-price-from"  class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Price to: </p>
	    				<input id="search-training-price-to"  class="form-control"  type="text" placeholder="Search...">
	    				
	    			</div>
	    			
	    			<div class="container">
	    				<p style="margin-top:10px">Search by type: </p>
	    				<select id="search-training-type"  class="form-select">
	    					<option value="None" selected > </option>
	    					<option value="Personal">Personal trainings</option>
	    					<option value="Group">Group trainings</option>
	    					<option value="Gym">Gym</option>
	    				</select>
	    			</div>
	    			<div class="search-btn-wrapper">
	    				
	    				<button class="btn btn-secondary" >Search</button>
	    			</div>
	    		
		</div>
		
		</div>
	
	
	`,
	methods : {
		
		goBack : function() {
			router.push(`/`);
		}
		},
	mounted() {
		axios
			.get('customer/showcustomertrainings', this.trainingList)
			.then(response => this.trainingList = response.data);
		}
		});