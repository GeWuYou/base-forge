import { defineStore } from 'pinia'
import type { KeepAliveState } from '@/models/interface/states'


export const useKeepAliveStore = defineStore({
  id: 'ym-keepAlive',
  state: (): KeepAliveState => ({
    keepAliveNames: []
  }),
  getters: {},
  actions: {
    async addKeepAlive(name: string) {
      if (this.keepAliveNames.includes(name)) return
      this.keepAliveNames.push(name)
    },
    async removeKeepAlive(name: string) {
      this.keepAliveNames = this.keepAliveNames.filter((item) => item !== name)
    },
    async setKeepAliveNames(names: string[] = []) {
      this.keepAliveNames = names
    }
  }
})
