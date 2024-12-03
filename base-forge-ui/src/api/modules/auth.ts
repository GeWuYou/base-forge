import type { ResultData } from '@/models/entity/api'
import http from '@/api'
import type { AdminLoginResult, AdminLogin } from 'src/models/entity/auth'

export const api = {
  /**
   * 登录
   * @param account 登录信息
   * @param successCallback 登录成功回调
   */
  login: async (
    account: AdminLogin,
    successCallback: (
      result: ResultData<AdminLoginResult>,
    ) => void,
  ) => {
    await http.post<AdminLoginResult>(
      '/auth/login',
      successCallback,
      undefined,
      undefined,
      account,
    )
  },
}
