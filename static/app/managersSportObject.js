Vue.component("managersSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null}
		}
	},
	template: `
		<div>
			<div class="mso-container">
				<img v-bind:src="sportObject.logo" />
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
		</div>
	`,
	mounted() {
		axios
			.get('sportObjects/showManagersSO', this.sportObject)
			.then(response => this.sportObject = response.data);
			
	},
	
	methods: {
		
	}
	
	
});