<script lang="ts" setup>
import { useTheme } from '@/hooks/useTheme'
import { useGlobalStore } from '@/stores/modules/global'


const { switchDark } = useTheme()
const globalStore = useGlobalStore()
</script>

<template>
  <el-switch v-model="globalStore.isDark" inline-prompt @change="switchDark">
    <template #active-action>
      <i class="icon iconfont icon-yueliang"></i>
    </template>
    <template #inactive-action>
      <i class="icon iconfont icon-taiyang"></i>
    </template>
  </el-switch>
</template>

<style lang="scss" scoped>

</style>
