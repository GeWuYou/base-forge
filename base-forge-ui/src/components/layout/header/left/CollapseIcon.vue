<script lang="ts" setup>
import { computed } from 'vue'
import { useGlobalStore } from '@/stores/modules/global'

const globalStore = useGlobalStore()
const isCollapse = computed(() => globalStore.isCollapse)

const changeCollapse = () => {
  globalStore.setCollapseState(!isCollapse.value)
}
</script>

<template>
  <div class="collapse-icon" @click="changeCollapse">
    <i
      :class="isCollapse ? 'icon-zhankai' : 'icon-shouqi'"
      class="toolbar-icon icon iconfont"
    ></i>
  </div>
</template>

<style lang="scss" scoped>
.collapse-icon {
  margin-right: 20px;
  cursor: pointer;
}
</style>
