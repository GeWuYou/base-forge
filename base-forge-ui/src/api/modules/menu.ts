import type { ResultData } from '@/models/entity/api'
import http from '@/api'
import type { MenuList } from '@/models/entity/menu'

export const api = {
  getMenuList: async (
    userAuthId: string,
    successCallback: (result: ResultData<MenuList>) => void,
  ) => {
    await http.get<MenuList>(
      '/menu/list',
      { userAuthId },
      successCallback
    )
  },
}
