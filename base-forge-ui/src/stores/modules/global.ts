import { defineStore } from 'pinia'


import { DEFAULT_PRIMARY } from '@/config/project'
import piniaPersistConfig from '@/stores/config/persist'
import type { GlobalState } from '@/models/interface/states'

export const useGlobalStore = defineStore({
  id: 'global',
  state: (): GlobalState => ({
    isCollapse: false,
    isDark: false,
    breadcrumb: true,
    primary: DEFAULT_PRIMARY
  }),
  getters: {},
  actions: {
    setCollapseState(state: boolean) {
      this.isCollapse = state
    },
    setBreadcrumbState(state: boolean) {
      this.breadcrumb = state
    },
    setPrimaryState(color: string) {
      this.primary = color
    }
  },
  persist: piniaPersistConfig('global')
})
