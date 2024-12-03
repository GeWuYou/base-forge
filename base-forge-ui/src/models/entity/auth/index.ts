// 后台登录返回结果
export interface AdminLoginResult {
  userInfoId: string
  userAuthId: string
  token: string
  expires: number
}
// 后台登录参数
export interface AdminLogin {
  username: string
  password: string
  rememberMe: boolean
}
