Vue.component("managersSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null},
			coaches : null,
			viewers : null,
			trainingsSO : null,
			training: {name:null, type:null, sportObject:null, duration:null, trainer:null, description:null, picture:null, trainingDate:null, id:null, deleted:null},
			allCoaches: null
		}
	},
	template: `
		<div  >
			<div class="mso-container " style="width:70%">
				<img v-bind:src="sportObject.logo" style="width:20%"/>
				<div style="display:flex;flex-direction:column;">
					<p class="mso-name  h3" >{{sportObject.objectName}}</p>
					<div class="d-flex ">
						<p style="font-size:20px;margin-left:10px">{{sportObject.objectType}}</p>
						<p style="font-size:20px;margin-left:20px" >Status: <span class="badge rounded-pill bg-success" v-if="sportObject.status == true">Open</span><span class="badge rounded-pill bg-danger" v-else>Closed</span></p>
					</div>
					<div>
						<p style="font-size:20px;margin-left:10px">Rating: <span class="badge rounded-pill bg-primary">{{sportObject.avarageMark}}</span></p>
					</div>
					<div>
						<p style="font-size:20px;margin-left:10px">Offer: {{sportObject.objectOffer}}</p>
					</div>
				</div>
				<div style="margin-left:50px; margin-top:45px" class="d-flex flex-column">
					<p style="font-size:20px;margin-left:10px">Location: {{sportObject.location.longitude + ', ' + sportObject.location.latitude + ', ' + sportObject.location.address.streetAndNumber + ', '
	    				+ sportObject.location.address.city + ', ' + sportObject.location.address.zipCode}}</p>
					<p style="font-size:20px;margin-left:10px">Work hour: {{sportObject.workHour}}</p>
				</div>
			</div>
			<br/>
			
			<div class="d-flex justify-content-between">
				<div style="width:40%">
					<div class="a" >
						<p>Trainers:</p>
    					<table id="aaa"  style="width:100%;">
	    					<tr bgcolor="grey">
	    						<th style="min-width:50px">Name </th>
	    						<th style="min-width:50px">Surname</th>
	    						<th style="min-width:50px">Username</th>
	    						<th style="min-width:50px">Birth date</th>
	    						<th style="min-width:50px">gender</th>
	    				
	    					</tr>
	    			
	    					<tr class="data" v-for="sCoaches in coaches">
	    						<td >{{sCoaches.name}}</td>
	    						<td >{{sCoaches.surname}}</td>
	    						<td >{{sCoaches.username}}</td>
	    						<td >{{sCoaches.dateOfBirth}}</td>
	    						<td >{{sCoaches.gender}}</td>
	    					</tr>
	    				</table>
	    			</div>
	    			<br/>
			
					<div class="a">
						<p>Customers who visited {{sportObject.objectName}}:</p>
    					<table id="aa"  style="width:100%;">
	    					<tr bgcolor="grey">
	    						<th style="min-width:50px">Name </th>
	    						<th style="min-width:50px">Surname</th>
	    						<th style="min-width:50px">Username</th>
	    						<th style="min-width:50px">Birth date</th>
	    						<th style="min-width:50px">gender</th>
	    				
	    					</tr>
	    			
	    					<tr class="data" v-for="sViewers in viewers">
	    						<td >{{sViewers.name}}</td>
	    						<td >{{sViewers.surname}}</td>
	    						<td >{{sViewers.username}}</td>
	    						<td >{{sViewers.dateOfBirth}}</td>
	    						<td >{{sViewers.gender}}</td>
	    					</tr>
	    				</table>
	    			</div>
	    	
	    			
	    	
	    		</div>
	    		<div  style="width:50%">
	    			<button v-on:click="toggleAddTraining()" >Add a new training</button>
	    			<div id="add-training-input" class="invisible">
	    			<table>
						<tr>
							<td>
								<p>Name: </p>
							</td>
							<td>
								<input id="add-training-name" v-model="training.name" class="form-control" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								<p>Type:</p>
							</td>
							<td>
								<select id="add-training-type" v-model="training.type"  class="form-select">
									<option value="Personal" >Personal training</option>
									<option value="Group" >Group training</option>
									<option value="Gym" >Gym</option>
								</select>
							</td>
						</tr>
				
						<tr>
							<td>
								<p>Image url: </p>
							</td>
							<td>
								<input id="add-training-img" v-model="training.picture" class="form-control"  type="text" />
							</td>
						</tr>
				
						<tr>
							<td>
								<p>Description: </p>
							</td>
							<td>
								<input class="form-control" id="add-training-description" v-model="training.description" type="text" />
							</td>
						</tr>
				
						
						<tr>
							<td>
								<p>Duration (in minutes): </p>
							</td>
							<td>
								<input class="form-control" id="add-training-duration" v-model="training.duration"  type="text" />
							</td>
						</tr>
						
						<tr>
							<td>
								<p>Date: </p>
							</td>
							<td>
								<input id="add-training-date" v-model="training.trainingDate" class="form-control"  type="date" />
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
							<td>
								<button class="btn btn-success ms-5" v-on:click="validateAdding()" >Confirm</button>
							</td>
							<td >
								<button  class="btn btn-danger ms-5" v-on:click="cancelAdding()" >Cancel</button>
							</td>
						</tr>
					</table>
					</div>
	    		</div>
	    	</div>
	    	
	    	<div>
	    		<p>All trainings in {{sportObject.objectName}}:</p>
	    		<table >
	    			<tr bgcolor="grey">
	    				<th style="min-width:100px">Name </th>
	    				<th style="min-width:100px">Type</th>
	    				<th style="min-width:100px">Object</th>
	    				<th style="min-width:100px">Duration</th>
	    				<th style="min-width:100px">Coach</th>
	    				<th style="min-width:100px; max-width:200px">Description</th>
	    				<th style="min-width:100px">Date</th>
	    				<th style="min-width:100px"></th>
	    			</tr>
	    			
	    			<tr class="data" v-for="sTraining in trainingsSO">
	    				<td >{{sTraining.name}}</td>
	    				<td >{{sTraining.type}}</td>
	    				<td >{{sTraining.sportObject.objectName}}</td>
	    				<td >{{sTraining.duration}}</td>
	    				<td >{{sTraining.trainer.name}} {{sTraining.trainer.surname}}</td>
	    				<td >{{sTraining.description}}</td>
	    				<td >{{sTraining.trainingDate}}</td>
	    				<td style="text-align:center" ><button class="btn btn-primary" v-on:click="showSelectedTraining(sTraining)" >Edit</button></td>
	    			</tr>
	    		</table>
	    	</div>
	    	
	    	
		</div>
		
	`,
	mounted() {
		axios
			.get('sportObjects/showManagersSO', this.sportObject)
			.then(response => this.sportObject = response.data);
			
		axios
			.get('customer/showObjectCoaches', this.coaches)
			.then(response => this.coaches = response.data);
		axios
			.get('customer/getViewers', this.viewers)
			.then(response => this.viewers = response.data);
		axios
			.get('customers/getAllTrainers', this.allCoaches)
			.then(response => this.allCoaches = response.data);
		axios
			.get('sportObject/getTrainingsForSO')
			.then(response => this.trainingsSO = response.data);
			
	},
	
	methods: {
		
		
		toggleAddTraining : function() {
			var input = document.getElementById('add-training-input');
			
			if (input.classList.contains('invisible')) {
				input.classList.remove('invisible');
			} else {
				input.classList.add('invisible');
			}
		},
		
		showSelectedTraining : function(sTraining) {
			axios
				.post('sportObject/editShow', sTraining)
				.then(response => response.data);
				
			router.push('/editTraining');
			router.go(0);
		},
		
		validateAdding: function() {
			var name = document.getElementById('add-training-name').value;
			var type = document.getElementById('add-training-type').value;
			var img = document.getElementById('add-training-img').value;
			var date = document.getElementById('add-training-date').value;
			
			
			if (name == "") {
				alert("You must enter a name.");
			} else if (type == "") {
				alert("You must enter a type.");
			} else if (img == "") {
				alert("You must enter an image url.");
			} else if (date == "") {
				alert("You must select a date.");
			} else {
				alert("Usao u validate adding");
				this.addTraining();
			}
			
		},
		
		
		
		addTraining : function() {
			this.training.sportObject = this.sportObject;
			this.training.id = 76; //ZA SAD DOK NE IZBACIMO ID
			this.training.deleted = 0;
			
			if (document.getElementById('add-training-duration').value == "") {
				this.training.duration = 0;
			}
			
			if (document.getElementById('add-training-description').value == "") {
				this.training.description = "None";
			}
			
			
			/*alert("Dodao trening: " + this.training.name);
			alert("Trajanje " + this.training.duration);
			alert("Tip " + this.training.type);
			alert("Sportski objekat " + this.training.sportObject.objectName);
			alert("Ime trenera " + this.training.trainer.name);
			alert("Description " + this.training.description);
			alert("Slika " + this.training.picture);
			alert("Date " + this.trainingDate);
			alert("ID " + this.training.id);
			alert("Deleted: " + this.training.deleted);*/
			
			axios
				.post('customers/addTraining', this.training)
				.then(response => alert(response.data));
				
			router.go(0);
		},
		
		cancelAdding: function() {
			var input = document.getElementById('add-training-input');
			input.classList.add('invisible');
		}
		
	}
	
	
});