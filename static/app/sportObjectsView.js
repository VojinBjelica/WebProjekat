Vue.component("sportObjectsView", {
	data: function() {
		return {
			sportObjectList: null
		}
	},
	template: ` 
    	<div style="background-color:gray; margin-bottom:100px">
    	
    		<h3>Prikaz sportskih objekata</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Name</th>
	    			<th>Type</th>
	    			<th>Offer</th>
	    			<th>Work hours</th>
	    			<th>Average mark</th>
	    		</tr>
	    			
	    		<tr v-for="sObject in sportObjectList">
	    			<td>{{sObject.objectName}}</td>
	    			<td>{{sObject.objectType}}</td>
	    			<td>{{sObject.objectOffer}}</td>
	    			<td>{{sObject.workHour}}</td>
	    			<td>{{sObject.avarageMark}}</td>
	    		</tr>
	    	</table>
    	</div>		  
    	`,
	mounted(){
		alert("Radi");
		axios
			.get('sportObjects/read', this.sportObjectList)
			.then(response => (this.sportObjectList = response.data));
	}
	
});