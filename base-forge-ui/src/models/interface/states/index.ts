/* GlobalState */
import type { Menu } from '@/models/entity/menu'

export interface GlobalState {
  isCollapse: boolean
  isDark: boolean
  breadcrumb: boolean
  primary: string
}

/* UserState */
export interface UserState {
  userInfo: {
    id: number
    name: string
    username: string | null
    email: string | null
    phone: string | null
    avatar: string | null
    remark: string | null
    roleId: number
    role: string
    roleName: string
    isSuper: number
  }
}

/* AuthState */
export interface AuthState {
  userAuthId: string
  token: string
  expires: number
}
/* MenuState */
export interface MenuState {
  routeName: string
  buttonList: {
    [key: string]: string[]
  }
  menuList: Menu[]
}


/* keepAliveState */
export interface KeepAliveState {
  keepAliveNames: string[]
}
