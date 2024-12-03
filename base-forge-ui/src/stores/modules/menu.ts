import { defineStore } from 'pinia'

import { getAllBreadcrumbList, getFlatMenuList, getShowMenuList } from '@/utils'
import { ElMessage } from 'element-plus'
import { api } from '@/api/modules/menu'
import type { MenuState } from '@/models/interface/states'

export const useMenuStore = defineStore({
  id: 'menu',
  state: (): MenuState => ({
    menuList: [],
    buttonList: {},
    routeName: '',
  }),
  getters: {
    // 菜单权限列表 ==> 这里的菜单没有经过任何处理
    authMenuListGet: state => state.menuList,
    // 菜单权限列表 ==> 扁平化之后的一维数组菜单，主要用来添加动态路由
    flatMenuListGet: state => getFlatMenuList(state.menuList),
    // 菜单权限列表 ==> 左侧菜单栏渲染，需要剔除 isEnable === 0
    showMenuListGet: state => getShowMenuList(state.menuList),
    // 递归处理后的所有面包屑导航列表
    breadcrumbListGet: state => getAllBreadcrumbList(state.menuList),
  },
  actions: {
    async getMenuList(userAuthId: string) {
      try {
        await api.getMenuList(userAuthId, result => {
          const menus = result.data.menus
          if (menus.length === 0) {
            ElMessage.error('角色菜单列表为空，联系管理员')
            throw new Error('角色菜单列表为空，联系管理员')
          }
          this.menuList = menus
        })
        return true
      } catch (error) {
        console.log(error)
        return false
      }
    },
  },
})
