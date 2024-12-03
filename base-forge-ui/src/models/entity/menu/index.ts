import type { MetaProps } from '@/models/interface/props'

export interface Menu {
  id: number
  name: string
  path: string
  component: string | (() => Promise<unknown>)
  redirect?: string
  parentId: number
  sort: number
  meta: MetaProps
  createdAt: string
  children?: Menu[]
}
export interface MenuList{
  menus: Menu[]
}
