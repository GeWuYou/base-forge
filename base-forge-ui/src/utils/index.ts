import type { Department } from '@/models/entity/api'
import type { Menu } from '@/models/entity/menu'


/**
 * @description 使用递归扁平化菜单，方便添加动态路由
 * @param  menuList 菜单列表
 * @returns 扁平化后的菜单列表
 */
export function getFlatMenuList(menuList: Menu[]): Menu[] {
  const newMenuList: Menu[] = JSON.parse(JSON.stringify(menuList))
  return newMenuList.flatMap((item) => [
    item,
    ...(item.children ? getFlatMenuList(item.children) : [])
  ])
}

/**
 * @description 使用递归过滤出需要渲染在左侧菜单的列表 (需剔除 isEnable == false 的菜单)
 * @param  menuList 菜单列表
 * @returns 展示的菜单列表
 * */
export function getShowMenuList(menuList: Menu[]) {
  const newMenuList: Menu[] = JSON.parse(JSON.stringify(menuList))
  return newMenuList.filter((item) => {
    // 如果item.children存在且不为null或undefined，则递归处理
    if (item.children?.length) {
      item.children = getShowMenuList(item.children)
    }
    return item.meta.isEnable
  })
}

/**
 * @description 使用递归找出所有面包屑存储到 pinia/vuex 中
 * @param menuList 菜单列表
 * @param  parent 父级菜单
 * @param  result 处理后的结果
 * @returns 处理后的结果
 */
export function getAllBreadcrumbList(
  menuList: Menu[],
  parent: Menu[] = [],
  result: { [key: string]: Menu[] } = {}
) {
  for (const item of menuList) {
    result[item.path] = [...parent, item] // 本路径：父级对象+自己  父级递归
    if (item.children) getAllBreadcrumbList(item.children, result[item.path], result)
  }
  return result
}

export interface MenuOption {
  label: string
  value: number
  children: MenuOption[]
}

/**
 * @description 递归生成菜单树操作选项
 * @param menuList 菜单列表
 * @returns 菜单树操作选项列表
 */
export function getTreeMenuOptions(menuList: Menu[]): MenuOption[] {
  return menuList.map((item) => {
    return {
      label: item.meta.title,
      value: item.id,
      children: item.children ? getTreeMenuOptions(item.children) : []
    }
  })
}

export interface MenuTree {
  id: number
  label: string
  children?: MenuTree[]
}

/**
 * @description 生成菜单树
 * @param menuList 菜单列表
 * @returns 菜单树
 */
export function getTreeMenu(menuList: Menu[]): MenuTree[] {
  return menuList.map((item) => {
    const treeItem: MenuTree = {
      id: item.id,
      label: item.meta.title
    }

    if (item.children && item.children.length > 0) {
      treeItem.children = getTreeMenu(item.children)
    }

    return treeItem
  })
}

/**
 * 根据节点ID查找节点
 * @param tree 树
 * @param id 节点ID
 * @returns 节点
 */
export function findNodeById(tree: Department[], id: number): Department | undefined {
  for (const node of tree) {
    if (node.id === id) {
      return node
    }
    if (node.children && node.children.length > 0) {
      const foundNode = findNodeById(node.children, id)
      if (foundNode) {
        return foundNode
      }
    }
  }
  return undefined
}
