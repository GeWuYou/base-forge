<script lang="ts" setup>
import CollapseIcon from './components/CollapseIcon.vue'
import BreadCrumb from './components/BreadCrumb.vue'
import { useGlobalStore } from '@/stores/modules/global'


const globalStore = useGlobalStore()
</script>

<template>
  <div class="tool-bar-lf">
    <CollapseIcon />
    <BreadCrumb v-show="globalStore.breadcrumb" />
  </div>
</template>

<style lang="scss" scoped>
.tool-bar-lf {
  display: flex;
  align-items: center;
}
</style>
