const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const SportObjectsView = { template: '<sportObjectsView></sportObjectsView>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/login', component: Login},
	    { path: '/register', component: Register},
	    { path: '/', component: SportObjectsView}
	  ]
});

var app = new Vue({
	router,
	el: '#sportCenter'
});

