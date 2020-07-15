import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout 布局 */
import Layout from '@/layout'

/**
 * 注意：子菜单仅在路由 children.length > = 1 时出现
 * 详细信息请参见：https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/essentials/router-and-nav.html#%E9%85%8D%E7%BD%AE%E9%A1%B9
 *
 * hidden: true                   // 当设置 true 的时候该路由不会在侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1 (default is false)
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由

 * redirect: 'noRedirect'          //当设置 noRedirect 的时候该路由在面包屑导航中不可被点击

 // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)


 * meta : {
    roles: ['admin','editor']    // 设置该路由进入的权限，支持多个权限叠加
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            //  如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
    activeMenu: '/example/list'  // 高亮
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/login/register'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: 'Dashboard', icon: 'dashboard' }
      },
      {
        path: 'addmember',
        name: 'addmember',
        hidden: true,
        component: () => import('@/views/member/AddMember'), // 添加会员页面
        meta: { title: 'addmember', icon: 'lock' }
      },
      {
        path: 'updatemember',
        name: 'updatemember',
        hidden: true,
        component: () => import('@/views/member/UpdateMember'), // 更新会员页面
        meta: { title: 'updatemember', icon: 'lock' }
      },
      {
        path: 'memberdetail',
        name: 'memberdetail',
        hidden: true,
        component: () => import('@/views/member/components/MemberDetail'), // 会员页面详情
        meta: { title: 'memberdetail', icon: 'lock' }
      }
    ]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'example' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.gitee.io/vue-element-admin-site/zh/guide',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  {
    // 显示在地址栏里的
    path: '/zijiwan',
    component: Layout,
    // 面包屑导航中可被点击，点击后直接跳到对应的路径
    redirect: '/zijiwan',
    // 面包屑导航中不可被点击
    // redirect: 'noRedirect',

    // name没啥用啊？ 被用在<keep-alive>里面
    name: 'ZIJIWAN001',
    // title 显示在侧边栏里面和面包屑里面
    meta: { title: 'ZIJIWANwan', icon: 'example' },
    children: [
      {
        path: 'minio',
        name: 'minio',
        component: () => import('@/views/minio/minio'),
        meta: { title: 'minio', icon: 'table' },
        // activeMenu: '/nested/menu1' 没看出来哪有高亮
      },
      {
        path: 'minio2',
        name: 'minio2',
        component: () => import('@/views/minio/minio2'), // 空白页面
        meta: { title: 'minio2', icon: 'lock' }
      },


    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }


]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
