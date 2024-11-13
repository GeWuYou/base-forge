<script lang="ts" setup>
import { LOGIN_URL } from '@/config/project'
import router from '@/router'

import { ElMessage, ElMessageBox } from 'element-plus'
import { computed } from 'vue'
import { useUserStore } from '@/stores/modules/user'
import i18n from '@/i18n'

const userStore = useUserStore()
const avatar = computed(() => userStore.userInfo.avatar)
const t = i18n.global.t
const logout = () => {
  ElMessageBox.confirm(t('logout.message.content'), t('logout.message.title'), {
    confirmButtonText: t('confirm'),
    cancelButtonText: t('cancel'),
    type: 'warning',
  }).then(() => {
    userStore.setTokenWithExpires('', 0)
    router.replace(LOGIN_URL)
    ElMessage.success(t('logout.message.success'))
  })
}
</script>

<template>
  <el-dropdown trigger="click">
    <span class="avatar">
      <img
        :alt="$t('avatar')"
        :src="
          avatar
            ? avatar
            : 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
        "
      />
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>
          <span
            ><i class="iconfont icon-user"></i
            >{{ $t('personalInformation') }}</span
          >
        </el-dropdown-item>
        <el-dropdown-item>
          <span
            ><i class="iconfont icon-xiugai"></i
            >{{ $t('changePassword') }}</span
          >
        </el-dropdown-item>
        <el-dropdown-item divided @click="logout">
          <span
            ><i class="iconfont icon-tuichu"></i>{{ $t('logout.value') }}</span
          >
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style lang="scss" scoped>
.avatar {
  width: 40px;
  height: 40px;
  overflow: hidden;
  cursor: pointer;
  border-radius: 50%;

  img {
    width: 100%;
    height: 100%;
  }
}
</style>
