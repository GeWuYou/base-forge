import { defineStore } from 'pinia'
import piniaPersistConfig from '@/stores/config/persist'
import type { AuthState } from '@/models/interface/states'

export const useAuthStore = defineStore({
  id: 'auth',
  state: (): AuthState => ({
    userAuthId: '',
    token: '',
    expires: 0,
  }),
  getters: {},
  actions: {
    // Set Token
   async setTokenWithExpires(userAuthId:string,token: string, expires: number) {
      this.userAuthId = userAuthId
      this.token = token
      this.expires = expires
    },
  },
  persist: piniaPersistConfig('auth'),
})
