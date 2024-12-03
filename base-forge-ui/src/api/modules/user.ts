import type { ResultData } from '@/models/entity/api'
import http from '@/api'
import type { Userinfo } from 'src/models/entity/user'

export const api = {
  /**
   * 获取用户信息
   * @param userInfoId 用户信息ID
   * @param successCallback 成功回调
   */
  getUserInfo: async (
    userInfoId: string,
    successCallback: (result: ResultData<Userinfo>) => void,
  ): Promise<void> => {
    await http.get<Userinfo>(
      `/user-info/${userInfoId}`,
      undefined,
      successCallback,
      undefined,
      undefined,
    )
  },
}
