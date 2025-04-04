import router from '@/router/index'

import { type RouteRecordRaw } from 'vue-router'
import { useMenuStore } from '@/stores/modules/menu'

// 引入 views 文件夹下所有 vue 文件
const modules = import.meta.glob('@/views/**/*.vue')

export const initDynamicRouter = async () => {
  const menuStore = useMenuStore()
  try {
    // 添加动态路由  flatMenuListGet 递归将菜单全部平铺
    menuStore.flatMenuListGet.forEach(item => {
      if (item.children && item.children.length > 0) {
        item.redirect = item.children[0].path
      }
      if (item.children) {
        delete item.children
      }
      if (item.component && typeof item.component == 'string') {
        item.component = modules['/src/views' + item.component + '.vue']
      }
      // 添加路由
      router.addRoute('layout', item as unknown as RouteRecordRaw)
    })
  } catch (error) {
    return Promise.reject(error)
  }
}
