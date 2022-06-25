Vue.component("addSportObject", {
	data: function () {
		return {
			sportObjectList:null,
			sportObject: {
				objectName:null, 
				objectType:null, 
				objectOffer:null, 
				status:null, 
				location: {
					longitude:null,
					latitude:null,
					address: {
						streetAndNumber:null,
						city:null,
						zipCode:null
					}
				}, 
				logo:null, 
				avarageMark:null, 
				workHour:null}
		}
	},
	template: `
		<div class="add-so d-flex justify-content-center" >
		<div class="add-so-body ">
			<p class="h3 ms-5 mb-5">Add new sport object</p>
			<table>
				<tr>
					<td>
						<p>Name: </p>
					</td>
					<td>
						<input v-model="sportObject.objectName" id="add-so-name" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Type:</p>
					</td>
					<td>
						<select v-model="sportObject.objectType" id="add-so-type" class="form-select">
							<option value="Gym" >Gym</option>
							<option value="Pool" >Pool</option>
							<option value="SportCenter" >Sport center</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<p>Longitude: </p>
					</td>
					<td>
						<input v-model="sportObject.location.longitude" id="add-so-longitude" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Latitude: </p>
					</td>
					<td>
						<input v-model="sportObject.location.latitude" id="add-so-latitude" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Street name and number: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.streetAndNumber" id="add-so-street" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>City: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.city" id="add-so-city" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Zip code: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.zipCode" id="add-so-zip" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Logo url: </p>
					</td>
					<td>
						<input class="form-control" v-model="sportObject.logo" id="add-so-logo" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Offer: </p>
					</td>
					<td>
						<input class="form-control" v-model="sportObject.objectOffer" id="add-so-offer" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Status: </p>
					</td>
					<td>
						<div>
							<div class="form-check form-check-inline" >
								<input checked  class="form-check-input " type="radio"  name="inlineRadioOptions" id="inlineRadioOpen" v-model="sportObject.status" value="True" >
								<label class="form-check-label" for="inlineRadioOpen">Open</label>
							</div>
							<div class="form-check form-check-inline" >
								<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioClosed" v-model="sportObject.status" value="False" >
								<label class="form-check-label" for="inlineRadioClosed">Closed</label>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<p>Work hours: </p>
					</td>
					<td>
						<input class="form-control" id="add-so-work-hour" v-model="sportObject.workHour" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-success ms-5" v-on:click="validateAdding()" >Confirm</button>
					</td>
					<td >
						<button v-on:click="goBack()" class="btn btn-danger ms-5">Cancel</button>
					</td>
				</tr>
					
				
			</table>
		</div>
		</div>
	
	
	`,
	methods : {
		addObject : function () {
			this.sportObject.avarageMark = 0;
			axios
				.post('sportObjects/add', this.sportObject)
				.then(response => alert(response.data))
				
			router.push(`/`);
		},
		
		goBack : function () {
			router.push(`/`);
		},
		
		validateAdding : function () {
			var name = document.getElementById('add-so-name').value;
			var type = document.getElementById('add-so-type').value;
			var longitude = document.getElementById('add-so-longitude').value;
			var latitude = document.getElementById('add-so-latitude').value;
			var street = document.getElementById('add-so-street').value;
			var city = document.getElementById('add-so-city').value;
			var zip = document.getElementById('add-so-zip').value;
			var logo = document.getElementById('add-so-logo').value;
			var offer = document.getElementById('add-so-offer').value;
			var workHour = document.getElementById('add-so-work-hour').value;
			var statusOpen = document.getElementById('inlineRadioOpen');
			var statusClosed = document.getElementById('inlineRadioClosed');
			
			if (name == "") {
				alert("You must enter a name.");
			} else if (type == ""){
				alert("You must select a type.");
			} else if (longitude == "") {
				alert ("You must enter a longitude.");
			} else if (latitude == "") {
				alert ("You must enter a latitude.");
			} else if (street == "") {
				alert ("You must enter a street name and number");
			} else if (city == "") {
				alert ("You must enter a city.");
			} else if (zip == "") {
				alert ("You must enter a zip code.");
			} else if (logo == "") {
				alert ("You must enter a logo.");
			} else if (offer == "") {
				alert ("You must enter an offer.");
			} else if (statusOpen.checked == false && statusClosed.checked == false) {
				alert ("You must select a status.");
			} else if (workHour == "") {
				alert ("You must enter work hours.");
			} else {
				var nameFlag = true;
				for (const i in this.sportObjectList) {
					if (this.sportObjectList[i].objectName.trim().toLowerCase() == name.trim().toLowerCase()) {
						nameFlag = false;
					}
				}
				if (nameFlag == true) {
					this.addObject();
				} else {
					alert("That name is already in use.");
				}
			}

		}
		
	},
	
	mounted() {
		axios
			.get('sportObjects/read', this.sportObjectList)
			.then(response => (this.sportObjectList = response.data));
	}
});