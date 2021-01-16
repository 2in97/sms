import Vue from 'vue'
import App from '../App'
import Router from 'vue-router'
import studentList from '@/view/studentList'
import HelloWorld from '@/components/HelloWorld'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/index',
      name: 'HelloWorld',
      component: HelloWorld,
    },
    {
      path: '/student/studentList',
      name: 'studentList',
      component: studentList
    },
  ]
})
