<script lang="ts" setup>
import { computed } from 'vue'
import { Avatar, FullScreen } from '@element-plus/icons-vue'
import ThemeSetting from '@/components/layout/header/right/ThemeSetting.vue'
import { useUserStore } from '@/stores/modules/user'
// import { useUserStore } from '@/store/modules/user'

const userStore = useUserStore()
const username = computed(() => userStore.userInfo.username)
</script>
<template>
  <div class="tool-bar-rt">
    <FullScreen class="tb-item" />
    <ThemeSetting class="tb-item" />
    <div class="tb-item username">{{ username }}</div>
    <Avatar />
  </div>
</template>

<style lang="scss" scoped>
.tool-bar-rt {
  display: flex;
  align-items: center;
  justify-content: center;
  padding-right: 25px;

  .tb-item {
    margin-right: 14px;
  }

  .username {
    font-size: 15px;
    color: var(--el-header-text-color);
  }
}
</style>
