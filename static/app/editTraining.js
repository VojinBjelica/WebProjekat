Vue.component("editTraining", {
	data: function() {
		return {
			allTrainings:null,
			allCoaches:null,
			training: {name:null, type:null, sportObject:null, duration:null, trainer:null, description:null, picture:null, trainingDate:null, id:null, deleted:null}
		}
	},
	template: `
		<div>
			<form name="myTrainingForm">
				<table  >
					<tr>
						<td>
							<p>Name:</p>
						</td>
						<td>
							<input id="nameTr" v-model="training.name" class="form-control"   type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Type:</p>
						</td>
						<td>
							<select id="typeTr" v-model="training.type"  class="form-select" >
								<option value="Personal" >Personal training</option>
								<option value="Group" >Group training</option>
								<option value="Gym" >Gym</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<p>Duration:</p>
						</td>
						<td>
							<input id="durationTr" v-model="training.duration" class="form-control"  type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Description:</p>
						</td>
						<td>
							<input id="descriptionTr" v-model="training.description" class="form-control"   type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Picture:</p>
						</td>
						<td>
							<input id="pictureTr" v-model="training.picture" class="form-control" type="password" />
						</td>
					</tr>
					
					<tr>
						<td>
							<p>Date:</p>
						</td>
						<td>
							<input id="dateTr" v-model="training.trainingDate" class="form-control" type="date" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Select a coach:</p>
						</td>
						<td>
							<select class="form-select" v-model="training.trainer" >
								<option v-for="co in allCoaches"  v-bind:value="co" >{{co.name}} {{co.surname}}</option>
							</select>
						</td>
					</tr>
					<tr>
						<td >
							<input  type="button"  class="btn btn-success" v-on:click="editTraining()" value="Confirm"/>
						</td>
						<td>
							<input type="button" class="btn btn-danger" v-on:click="goBack()" value="Cancel"/>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	`,
	methods: {
		
		editTraining : function() {
			axios
				.post('sportObject/editTraining', this.training)
				.then(response => response.data);
			router.push('/managersSportObject');
		},
		
		goBack : function() {
			router.push('/managersSportObject');
		}
		
	}
	,mounted(){
		axios
			.get('customers/getAllTrainers', this.allCoaches)
			.then(response => this.allCoaches = response.data);
			
		axios
			.post('sportObject/showEditableTraining',this.training)
			.then(response => this.training = response.data);
		
	}
});