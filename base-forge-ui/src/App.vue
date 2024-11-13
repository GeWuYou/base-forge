<script lang="ts" setup>
import { RouterView } from 'vue-router'
import { ElConfigProvider } from 'element-plus'
import { useLocale } from '@/hooks/useLocale'
import { useTheme } from '@/hooks/useTheme'

// 初始化主题
const { initTheme } = useTheme()
initTheme()

// 初始化语言
const { locale } = useLocale()
</script>

<template>
  <el-config-provider :locale="locale">
    <RouterView />
  </el-config-provider>
</template>

<style lang="scss" scoped>

</style>
