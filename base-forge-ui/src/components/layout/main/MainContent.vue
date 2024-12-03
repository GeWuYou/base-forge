<script lang="ts" setup>
import { computed, h, onBeforeUnmount, ref } from 'vue'
import type { VNode } from 'vue'
import type { RouteLocationNormalizedLoaded } from 'vue-router'
import { useDebounceFn } from '@vueuse/core'
import { useGlobalStore } from '@/stores/modules/global'
import TabComponent from '@/components/layout/tab/TabComponent.vue'
import { useKeepAliveStore } from '@/stores/modules/keep-alive'


const keepAliveStore = useKeepAliveStore()
const keepAliveNames = computed(() => keepAliveStore.keepAliveNames)

// keepAlive缓存 将组件路径fullPath作为key 设置为Component 识别名称
const wrapperMap = new Map()

function createComponentWrapper(
  component: VNode,
  route: RouteLocationNormalizedLoaded,
) {
  if (!component) return
  const wrapperName = route.fullPath
  let wrapper = wrapperMap.get(wrapperName)
  if (!wrapper) {
    wrapper = {
      name: route.fullPath,
      render: () => h(component),
    }
    wrapperMap.set(wrapperName, wrapper)
  }
  return h(wrapper)
}

const globalStore = useGlobalStore()
const isCollapse = computed(() => globalStore.isCollapse)
const breadcrumb = computed(() => globalStore.breadcrumb)

// 监听窗口大小变化，折叠侧边栏, 控制面包屑导航
const screenWidth = ref(0)
const listeningWindow = useDebounceFn(() => {
  screenWidth.value = document.body.clientWidth
  if (!isCollapse.value && screenWidth.value < 1200)
    globalStore.setCollapseState(true)
  if (isCollapse.value && screenWidth.value > 1200)
    globalStore.setCollapseState(false)
  if (breadcrumb.value && screenWidth.value < 768)
    globalStore.setBreadcrumbState(false)
  if (!breadcrumb.value && screenWidth.value > 768)
    globalStore.setBreadcrumbState(true)
}, 100)
window.addEventListener('resize', listeningWindow, false)
onBeforeUnmount(() => {
  window.removeEventListener('resize', listeningWindow)
})
</script>

<template>
  <TabComponent />
  <el-main>
    <router-view v-slot="{ Component, route }">
      <transition appear mode="out-in" name="fade-transform">
        <keep-alive :include="keepAliveNames">
          <component
            :is="createComponentWrapper(Component, route)"
            :key="route.fullPath"
          ></component>
        </keep-alive>
      </transition>
    </router-view>
  </el-main>
</template>

<style lang="scss" scoped>
@use '@/styles/components/main';
</style>
