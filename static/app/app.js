const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const SportObjectsView = { template: '<sportObjectsView></sportObjectsView>' }
const OneSportObject = { template: '<oneSportObject></oneSportObject>' }
const AddSportObject = { template: '<addSportObject></addSportObject>'}
const EditProfile = { template: '<editprofile></editprofile>'}
const UserView = { template: '<userview></userview>'}
const ManagersSportObject = { template: '<managersSportObject></managersSportObject>' }
const CoachTrainings = { template: '<coachTraining></coachTraining>' }
const CustomerTrainings = { template: '<customerTraining></customerTraining>' }
const EditTraining = { template: '<editTraining></editTraining>' }
const DuesPayment = { template: '<duesPayment></duesPayment>' }
const PromoCodes = { template: '<promoCodes></promoCodes>' }
const ScheduleTraining = { template: '<scheduleTraining></scheduleTraining>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/login', component: Login},
	    { path: '/register', component: Register},
	    { path: '/', component: SportObjectsView},
	    { path: '/oneSportObject', component: OneSportObject },
	    { path: '/addSportObject', component: AddSportObject },
	    { path: '/editprofile', component: EditProfile },
	    { path: '/userview', component: UserView },
	    { path: '/managersSportObject', component: ManagersSportObject },
	    { path: '/coachTraining', component: CoachTrainings },
	    { path: '/customerTraining', component: CustomerTrainings },
	    { path: '/editTraining', component: EditTraining },
	    { path: '/duesPayment', component: DuesPayment },
	    { path: '/promoCodes', component: PromoCodes },
	    { path: '/scheduleTraining', component: ScheduleTraining }
	  ]
});

var app = new Vue({
	router,
	el: '#sportCenter'
});

