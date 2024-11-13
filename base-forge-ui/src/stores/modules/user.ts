import { defineStore } from 'pinia'
import piniaPersistConfig from '@/stores/config/persist'
import type { UserState } from '@/models/interface/states'
import { api } from '@/api/modules/user'

export const useUserStore = defineStore({
  id: 'user',
  state: (): UserState => ({
    userInfo: {
      id: 0,
      name: '',
      username: null,
      email: null,
      phone: null,
      avatar: null,
      remark: null,
      roleId: 0,
      role: '',
      roleName: '',
      isSuper: 0,
    },
  }),
  getters: {},
  actions: {
    async getUserInfo(userInfoId: string) {
      await api.getUserInfo(userInfoId, result => {
        this.userInfo = result.data
      })
    },
  },
  persist: piniaPersistConfig('user'),
})
