<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<template>
  <div class="not-container">
    <img alt="404" class="not-img" src="@/assets/svgs/404.svg" />
    <div class="not-detail">
      <h2>404</h2>
      <h4>抱歉，您访问的页面不存在~🤷‍♂️🤷‍♀️</h4>
      <el-button type="primary" @click="router.back"> 返回上一页 </el-button>
    </div>
  </div>
</template>



<style lang="scss" scoped>
@use '@/styles/components/error';
</style>
