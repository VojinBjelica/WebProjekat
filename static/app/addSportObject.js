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
						<input v-model="sportObject.objectName" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Type:</p>
					</td>
					<td>
						<select v-model="sportObject.objectType" class="form-select">
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
						<input v-model="sportObject.location.longitude" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Latitude: </p>
					</td>
					<td>
						<input v-model="sportObject.location.latitude" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Street name and number: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.streetAndNumber" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>City: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.city" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Zip code: </p>
					</td>
					<td>
						<input v-model="sportObject.location.address.zipCode" class="form-control" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Logo url: </p>
					</td>
					<td>
						<input class="form-control" v-model="sportObject.logo" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Offer: </p>
					</td>
					<td>
						<input class="form-control" v-model="sportObject.objectOffer" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<p>Status: </p>
					</td>
					<td>
						<div>
							<div class="form-check form-check-inline" >
								<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioOpen" v-model="sportObject.status" value="True" />
								<label class="form-check-label" for="inlineRadioOpen">Open</label>
							</div>
							<div class="form-check form-check-inline" >
								<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioClosed" v-model="sportObject.status" value="False" />
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
						<input class="form-control" v-model="sportObject.workHour" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-success ms-5" v-on:click="addObject()" >Confirm</button>
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
		}
		
	},
	
	mounted() {}
});