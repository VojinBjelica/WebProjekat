Vue.component("addSportObject", {
	data: function () {
		return {
			availableManagersList:null,
			selectedManager:null,
			sportObjectList:null,
			listOfUsers:null,
			user:{name:null,surname:null,username:null,password:null,gender:null,dateOfBirth:null,role:null},
			isListEmpty:null,
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
				workHour:null,
				deleted:null}
		}
	},
	template: `
		<div class="add-so d-flex justify-content-center" >
		<div class="add-so-body ">
			<p class="h3 ms-5 mb-3">Add new sport object</p>
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
				<tr v-if="isListEmpty == false">
					<td>
						<p>Select a manager: </p>
					</td>
					<td>
						<select id="add-so-manager"  v-model="selectedManager" class="form-select">
							<option v-for="man in availableManagersList" v-bind:value="man" >{{man.name}} {{man.surname}}</option>
							
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<button id="add-so-conf-btn" class="btn btn-success ms-5" v-on:click="validateAdding()" >Confirm</button>
					</td>
					<td >
						<button v-on:click="goBack()" class="btn btn-danger ms-5">Cancel</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="mt-5 ms-5" v-if="isListEmpty == true">
			
			
			<form name="myManaForm">
				<table style="margin-left:auto;margin-right:auto;" >
					<tr>
						<td>
							<p>Name:</p>
						</td>
						<td>
							<input id="nameMana" class="form-control" v-model="user.name"  type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Surname:</p>
						</td>
						<td>
							<input id="surnameMana" class="form-control" v-model="user.surname" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Username:</p>
						</td>
						<td>
							<input id="usernameMana" class="form-control" v-model="user.username"  type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Password:</p>
						</td>
						<td>
							<input id="passwordMana" v-model="user.password" class="form-control" type="password" />
						</td>
					</tr>
					<tr>
						<td>
							<p>Gender:</p>
						</td>
						<td>
							<select id="genderMana" v-model="user.gender" class="form-select" >
								<option value="Male" >Male</option>
								<option value="Female" >Female</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<p>Date:</p>
						</td>
						<td>
							<input id="dateMana" v-model="user.dateOfBirth" class="form-control" type="date" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input  type="button"  class="btn btn-success" v-on:click="validateText()" value="Register a new manager"/>
						</td>
					</tr>
				</table>
			</form>
			
		</div>
		</div>
	
	
	`,
	methods : {
		addObject : function () {
			this.sportObject.deleted = 0;
			this.sportObject.avarageMark = 0;
			axios
				.post('sportObjects/addSOManager', this.selectedManager)
				.then(response => response.data);
			axios
				.post('sportObjects/add', this.sportObject)
				.then(response => { response.data});
				
			router.push(`/`);
		},
		
		addManagerlessObject : function () {
			this.sportObject.avarageMark = 0;
			axios
				.post('sportObjects/add', this.sportObject)
				.then(response => { response.data});
				
			router.push(`/`);
		},
		
		isEmpty : function (lista) {
			
			if (lista == undefined || lista.length < 1) {
				this.isListEmpty = true;
			} else {
				this.isListEmpty = false;
			}
			
			
			var btn = document.getElementById("add-so-conf-btn");
			if (this.isListEmpty == true) {
				btn.classList.add('invisible');
			} else {
				btn.classList.remove('invisible');
			}
			
			
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
			var managerUname = document.getElementById('add-so-manager').value;
			
			var patternName = /^[A-Za-z0-9 ]+$/;
			var patternStreet = /^[A-Za-z ]+ [0-9a-zA-Z]+$/;
			var patternCity = /^[a-zA-Z ]+$/;
			var patternHours = /^([01]?[0-9]|2[0-3])(:[0-5][0-9])?-([01]?[0-9]|2[0-3])(:[0-5][0-9])?$/;
			//var patternHours = /^(([01]?[0-9]|2[0-3])-([01]?[0-9]|2[0-3]))|(([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9])$/;
			
			if (name == "") {
				alert("You must enter a name.");
			} else if (!patternName.test(name)) {
				alert("Name is in incorrect format (must contain only letters, numbers and spaces).");
			} else if (type == ""){
				alert("You must select a type.");
			} else if (longitude == "") {
				alert ("You must enter a longitude.");
			} else if (latitude == "") {
				alert ("You must enter a latitude.");
			} else if (street == "") {
				alert ("You must enter a street name and number");
			} else if (!patternStreet.test(street)) {
				alert ("Street name and number in incorrect format.")
			} else if (city == "") {
				alert ("You must enter a city.");
			} else if (!patternCity.test(city)) {
				alert ("City is in incorrent format (must contain only letters and spaces).");
			} 
			
			else if (zip == "") {
				alert ("You must enter a zip code.");
			} else if (logo == "") {
				alert ("You must enter a logo.");
			} else if (offer == "") {
				alert ("You must enter an offer.");
			} else if (statusOpen.checked == false && statusClosed.checked == false) {
				alert ("You must select a status.");
			} else if (workHour == "") {
				alert ("You must enter work hours.");
			} else if (!patternHours.test(workHour)) {
				alert ("Work hour is in incorrenct format (Correct example: 7-21 or 7:00-21:00)")
			} 
			 else if (managerUname == "") {
				alert ("You must select a manager or create a new one");
			}
			
			 else {
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
		},
		
		validateManagerlessAdding : function () {
			
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
			
			var patternName = /^[A-Za-z0-9 ]+$/;
			var patternStreet = /^[A-Za-z]+ [0-9a-zA-Z]+$/;
			var patternCity = /^[a-zA-Z ]+$/;
			var patternHours = /^([01]?[0-9]|2[0-3])(:[0-5][0-9])?-([01]?[0-9]|2[0-3])(:[0-5][0-9])?$/;
			//var patternHours = /^(([01]?[0-9]|2[0-3])-([01]?[0-9]|2[0-3]))|(([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9])$/;
			
			if (name == "") {
				alert("You must enter a name.");
			} else if (!patternName.test(name)) {
				alert("Name is in incorrect format (must contain only letters, numbers and spaces).");
			} else if (type == ""){
				alert("You must select a type.");
			} else if (longitude == "") {
				alert ("You must enter a longitude.");
			} else if (latitude == "") {
				alert ("You must enter a latitude.");
			} else if (street == "") {
				alert ("You must enter a street name and number");
			} else if (!patternStreet.test(street)) {
				alert ("Street name and number in incorrect format.")
			} else if (city == "") {
				alert ("You must enter a city.");
			} else if (!patternCity.test(city)) {
				alert ("City is in incorrent format (must contain only letters and spaces).");
			} 
			
			else if (zip == "") {
				alert ("You must enter a zip code.");
			} else if (logo == "") {
				alert ("You must enter a logo.");
			} else if (offer == "") {
				alert ("You must enter an offer.");
			} else if (statusOpen.checked == false && statusClosed.checked == false) {
				alert ("You must select a status.");
			} else if (workHour == "") {
				alert ("You must enter work hours.");
			} else if (!patternHours.test(workHour)) {
				alert ("Work hour is in incorrenct format (Correct example: 7-21 or 7:00-21:00)")
			} 
			 
			
			 else {
				var nameFlag = true;
				for (const i in this.sportObjectList) {
					if (this.sportObjectList[i].objectName.trim().toLowerCase() == name.trim().toLowerCase()) {
						nameFlag = false;
					}
				}
				if (nameFlag == true) {
					this.addNewManager();
					
				} else {
					alert("That name is already in use.");
				}
			}
		},
		
		validateText : function() {
			
			let x = document.getElementById('nameMana').value;
			let y = document.getElementById('surnameMana').value;
			let z = document.getElementById('usernameMana').value;
			let w = document.getElementById('passwordMana').value;
			let q = document.getElementById('genderMana').value;
			let p = document.getElementById('dateMana').value;

			if (x == "") {
				alert("Fill name input.");
			} else if (y == "") {
				alert("Fill surname input.");
			} else if (z == "") {
				alert("Fill username input.");
			} else if (w == "") {
				alert("Fill password input.");
			} else if (q == "") {
				alert("Select a gender.");
			} else if(p == "") {
				alert("Select a date.");	
			} else {
				this.duplicateUsername();
			}
		},
		
		duplicateUsername: function() {
			let z = document.getElementById('usernameMana').value;
			let doubleFlag = false;
			let regexxx = new RegExp('[A-Z]*[a-z]*[1-9]*');

			for (const s in this.listOfUsers) {
				if(this.listOfUsers[s].username == z)
				{
					doubleFlag = true;
				}
			}
			if (doubleFlag == true) {
				alert("Username is already taken.");
			} else if(!regexxx.test(z)) {
				alert("Invalid username.");
			} else {
				
				this.validateManagerlessAdding();
			}
		},
		
		addNewManager : function () {
			this.user.role = "Manager";
			axios  
		    	.post('sportObject/addManager',this.user)
		        .then(response => response.data)
		        
		  	this.addManagerlessObject();
		}
		
		
	},
	
	mounted() {
		axios
			.get('sportObjects/read', this.sportObjectList)
			.then(response => (this.sportObjectList = response.data));
			
		axios
			.get('sportObjects/availableManagers', this.availableManagersList)
			.then(response => {(this.availableManagersList = response.data),
			this.isEmpty(response.data);
			});
			
		axios
			.get('customer/listinit', this.listOfUsers)
			.then(response => (this.listOfUsers = response.data));
		
		/*this.isEmpty(this.availableManagersList);   //ne ucita odmah sa ovim
		alert(this.availableManagersList.length);*/

	},
	beforeUpdate() {
		//this.isEmpty(); //ovo se pozove vise puta al valjda je ok
		//alert(this.availableManagersList.length);
	}
});