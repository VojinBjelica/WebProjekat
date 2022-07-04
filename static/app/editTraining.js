Vue.component("editTraining", {
	data: function() {
		return {
			allTrainings:null,
			training: {name:null, type:null, sportObject:null, duration:null, trainer:null, description:null, picture:null, trainingDate:null, id:null, deleted:null}
		}
	},
	template: `
		<div>
			<p>Edit training</p>
		</div>
	`,
	methods: {}
	,mounted(){}
});