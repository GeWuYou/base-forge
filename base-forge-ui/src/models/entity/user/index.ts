export interface Userinfo {
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
export interface User {
  id: number
  name: string
  username: string | null
  deptId: number
  email: string | null
  phone: string | null
  avatar: string | null
  remark: string | null
  roleId: number
  role: string
  roleName: string
}
