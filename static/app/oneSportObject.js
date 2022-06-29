Vue.component("oneSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null}
		}
	},
	template: `
		<div class= "home-page">
			<div class="container-fluid">
				<div>
					<p>Name: {{sportObject.objectName}}</p>
				</div>
				<div>
					<p>Type: {{sportObject.objectType}}</p>
				</div>
				<div>
					<p>Status: <span v-if="sportObject.status == true">Open</span><span v-else>Closed</span></p>
				</div>
				<div>
					<p>Location: {{sportObject.location.longitude + ', ' + sportObject.location.latitude + ', ' + sportObject.location.address.streetAndNumber + ', '
	    				+ sportObject.location.address.city + ', ' + sportObject.location.address.zipCode}}</p>
				</div>
				<div>
					<p>Logo: </p><img v-bind:src="sportObject.logo" style="width:200px; height:200px;"></img>
				</div>
				<div>
					<p>Average mark: {{sportObject.avarageMark}}</p>
				</div>
				<div>
					<p style="font-size:30">Dodati raspored treninga kad se implementira!!!!</p>
				</div>
				
			</div>
		</div>
	
	`,
	mounted() {
		axios
			.post('sportObject/showOne', this.sportObject)
			.then(response => this.sportObject = response.data);
		
		axios
			.get('sportObject/addView')
			.then(response => alert(response.data));
	}
	
	
	
	
});