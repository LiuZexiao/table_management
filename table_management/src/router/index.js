import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  base: '/information/',  //添加的地方
  mode:"history", // 去除路由链接中的 '#'
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../components/login')
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('../components/index'),
      meta: {requiresAuth: true},   // 是否需要登陆验证
      children: [
        {
          path: 'major',
          name: 'major',
          component: () => import('../components/major'),
          meta: {requiresAuth: true}
        },
        {
          path: 'updatePassword',
          name: 'updatePassword',
          component: () => import('../user/updatePassword'),
          meta: {requiresAuth: true}
        },
        {
          path: 'userInfo',
          name: 'userInfo',
          component: () => import('../user/userInfo'),
          meta: {requiresAuth: true}
        },
        {
          path: 'account',
          name: 'account',
          component: () => import('../page/account'),
          meta: {requiresAuth: true}
        },
        {
          path: 'addSkill',
          name: 'addSkill',
          component: () => import('../page/addSkill'),
          meta: {requiresAuth: true}
        },
        {
          path: 'addResearch',
          name: 'addResearch',
          component: () => import('../page/addResearch'),
          meta: {requiresAuth: true}
        },
        {
          path: 'manageSkill',
          name: 'manageSkill',
          component: () => import('../page/manageSkill'),
          meta: {requiresAuth: true}
        },
        {
          path: 'manageResearch',
          name: 'manageResearch',
          component: () => import('../page/manageResearch'),
          meta: {requiresAuth: true}
        },
        { // 设置默认显示的路由
          path: '/',
          redirect: 'major',
          meta: {requiresAuth: true}
        }
        
      ]
    }
    
  ]
})
