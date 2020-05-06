import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Welcome from '../components/Welcome.vue'
import Register from '../components/Register.vue'
import HelloWorld from '../components/HelloWorld.vue'
import MemberDetail from '../views/member/components/MemberDetail.vue'
import AddMember from '../views/member/AddMember.vue'
import UpdateMember from '../views/member/UpdateMember.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/welcome',
    name: 'Welcome',
    component: Welcome
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/helloWorld',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path: '/memberDetail',
    name: 'MemberDetail',
    component: MemberDetail
  },
  {
    path: '/addMember',
    name: 'AddMember',
    component: AddMember
  },
  {
    path: '/updateMember',
    name: 'UpdateMember',
    component: UpdateMember
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
