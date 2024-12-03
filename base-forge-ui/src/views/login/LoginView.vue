<script lang="ts" setup>
import { ref } from 'vue'

import ThemeModeSwitch from '@/components/common/switch/ThemeModeSwitch.vue'
import LoginForm from '@/views/login/form/LoginForm.vue'

const loginRef = ref<InstanceType<typeof LoginForm>>()

const handleSubmitClick = () => {
  loginRef.value?.loginAction()
}

const handleResetClick = () => {
  loginRef.value?.resetAction()
}
</script>

<template>
  <div class="login-container flx-center">
    <div class="login-box">
      <ThemeModeSwitch class="switch-dark" />
      <div class="login-left">
        <img
          alt="login"
          class="login-left-img"
          src="../../assets/svgs/login-left.svg"
        />
      </div>
      <div class="login-form">
        <div class="login-logo">
          <img alt="logo" class="login-icon" src="../../assets/svgs/logo.svg" />
          <h2 class="logo-text">YM-Admin</h2>
        </div>
        <LoginForm ref="loginRef"></LoginForm>
        <div class="login-btns">
          <el-button class="login-btn" size="large" @click="handleResetClick">
            重置
          </el-button>
          <el-button
            class="login-btn"
            size="large"
            type="primary"
            @click="handleSubmitClick"
          >
            立即登录
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use '@/styles/views/login';
</style>
