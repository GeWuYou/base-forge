<script lang="ts" setup>
import mittBus from '@/utils/mitt-bus'

const openDrawer = () => {
  mittBus.emit('openThemeDrawer')
}
</script>
<template>
  <div class="theme-setting">
    <i class="icon-yifu toolbar-icon icon iconfont" @click="openDrawer"></i>
  </div>
</template>

<style lang="scss" scoped>
.toolbar-icon:hover {
  color: var(--el-color-primary);
  cursor: pointer;
}
</style>
