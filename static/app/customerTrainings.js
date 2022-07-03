Vue.component("customerTraining", {
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
	    				<th style="min-width:50px">Object</th>
	    				<th style="min-width:50px">Date</th>
	    				
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingList">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    				
	    			</tr>
	    		</table>
	    		</div>
	    		<br/>
	    		<br/>
	    		<input  type="button" v-on:click="goBack();" class="btn btn-primary" style="margin-top:10px;margin-left:40%" value="Cancel"/>

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