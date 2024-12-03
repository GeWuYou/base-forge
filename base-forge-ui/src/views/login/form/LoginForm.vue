<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElMessage, type FormInstance } from 'element-plus'
import { useRouter } from 'vue-router'
import { HOME_URL } from '@/config/project'
import { useUserStore } from '@/stores/modules/user'
import { useTabsStore } from '@/stores/modules/tabs'
import { useAuthStore } from '@/stores/modules/auth'

import { api } from '@/api/modules/auth'
import type { AdminLogin } from 'src/models/entity/auth'
import { useMenuStore } from '@/stores/modules/menu'
import { initDynamicRouter } from '@/router/dynamicRouter'

const router = useRouter()

const userStore = useUserStore()
const tabsStore = useTabsStore()
const authStore = useAuthStore()
const menuStore = useMenuStore()
const account: AdminLogin = reactive({
  username: '',
  password: '',
  rememberMe: false, // 后端代码控制token过期时间 默认24h
})

const rules = {
  name: [
    { required: true, message: '请输入账号名称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,}$/,
      message: '密码必须是6位以上的字母或数字',
      trigger: 'blur',
    },
  ],
}

const loginFormRef = ref<FormInstance>()

const loginAction = () => {
  loginFormRef.value?.validate(async valid => {
    if (valid) {
      await api.login(account, async result => {
        const data = result.data
        await Promise.all([
          // 设置token
          authStore.setTokenWithExpires(data.userAuthId,data.token, data.expires),
          // 清空标签页
          tabsStore.setTabs([]),
          // 获取用户信息
          userStore.getUserInfo(data.userInfoId),
          Promise.all([
            // 获取菜单列表
            menuStore.getMenuList(data.userInfoId),
            // 加载动态路由
            initDynamicRouter(),
          ]),
        ])
        // 跳转主页
        await router.push(HOME_URL)
        ElMessage.success(result.msg)
      })
    }
  })
}

const resetAction = () => {
  loginFormRef.value?.resetFields()
}

// setup 将方法暴露出去
defineExpose({
  loginAction,
  resetAction,
})
</script>

<template>
  <el-form ref="loginFormRef" :model="account" :rules="rules">
    <el-form-item prop="username">
      <el-input v-model="account.username" placeholder="用户名">
        <template #prefix><i class="iconfont icon-user"></i></template>
      </el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        v-model="account.password"
        placeholder="密码"
        show-password
        type="password"
      >
        <template #prefix><i class="iconfont icon-lock"></i></template>
      </el-input>
    </el-form-item>
    <el-form-item prop="expires7d">
      <el-checkbox v-model="account.rememberMe" class="expires7d">
        记住我
      </el-checkbox>
    </el-form-item>
  </el-form>
</template>

<style lang="scss" scoped></style>
