Vue.component("promoCodes", {
	data: function () {
		    return {
			
		      code:{promoCodeName:null,fromDate:null,toDate:null,numberOfUsing:null,discount:null}
		   
		    }
	},
	template: ` 
<div style="background-color:gray;position: fixed;
  left:40%;
  top:15%;
  width:25%;
  height:70%;
  text-align:center;">
  <br/>
  <br/>
<h2 class="h2">Promo Codes</h2>
<form name="myForm">
<br/>

<table style="margin-left:auto;margin-right:auto;" >
<tr>
<td>
<p>Name:</p>
</td>
<td>
<input id="name" v-model="code.promoCodeName" class="form-control" type="text" />
</td>
</tr>
<tr>
<td>
<p>From:</p>
</td>
<td>
<input id="name" v-model="code.fromDate" class="form-control" type="date" />
</td>
</tr>
<tr>
<td>
<p>To:</p>
</td>
<td>
<input id="surname" v-model="code.toDate" class="form-control"   type="date" />
</td>
</tr>
<tr>
<td>
<p>Using Times:</p>
</td>
<td>
<input id="username" v-model="code.numberOfUsing" class="form-control"  type="text" />
</td>
</tr>
<tr>
<td>
<p>Discount(%):</p>
</td>
<td>
<input class="form-control" v-model="code.discount" type="text" />
</td>
</tr>


<tr>
<td>
<a href="#/" ><input type="button"  class="btn btn-success" v-on:click="addPromoCode();" value="Make code"/></a>
</td>
<td>
<a href="#/" ><input  type="button"  class="btn btn-success"  value="Cancel"/></>
</td>
</tr>
</table>
</form>
</div> `
, 
	methods : {
		addPromoCode : function () {
				axios  
		          .post('customer/addPromoCode',this.code)
		          .then(response => alert(response.data))
		          }
		
		
	}
	
,mounted(){
	
	}
})