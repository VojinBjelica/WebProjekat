const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const SportObjectsView = { template: '<sportObjectsView></sportObjectsView>' }
const OneSportObject = { template: '<oneSportObject></oneSportObject>' }
const AddSportObject = { template: '<addSportObject></addSportObject>'}
const EditProfile = { template: '<editprofile></editprofile>'}
const UserView = { template: '<userview></userview>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/login', component: Login},
	    { path: '/register', component: Register},
	    { path: '/', component: SportObjectsView},
	    { path: '/oneSportObject', component: OneSportObject },
	    { path: '/addSportObject', component: AddSportObject },
	    { path: '/editprofile', component: EditProfile },
	    { path: '/userview', component: UserView }
	    
	  ]
});

var app = new Vue({
	router,
	el: '#sportCenter'
});

