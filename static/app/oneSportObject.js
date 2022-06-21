Vue.component("oneSportObject", {
	data: function() {
		return {
			sportObject: {objectName:null, objectType:null, objectOffer:null, status:null, location:null, logo:null, avarageMark:null, workHour:null}
		}
	},
	template: `
		<div class= "home-page">
			<div>
				<div>
					<p>Name: </p>
					<p>{{sportObject.objectName}}</p>
				</div>
			</div>
		</div>
	
	`,
	mounted() {
		axios
			.post('sportObject/showOne', this.sportObject)
			.then(response => this.sportObject = response.data);
	}
	
	
	
	
});