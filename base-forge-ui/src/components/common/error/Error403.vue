<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<template>
  <div class="not-container">
    <img alt="403" class="not-img" src="@/assets/svgs/403.svg" />
    <div class="not-detail">
      <h2>403</h2>
      <h4>抱歉，您无权访问该页面~🙅‍♂️🙅‍♀️</h4>
      <el-button type="primary" @click="router.back"> 返回上一页 </el-button>
    </div>
  </div>
</template>



<style lang="scss" scoped>
@use '@/styles/components/error';
</style>
