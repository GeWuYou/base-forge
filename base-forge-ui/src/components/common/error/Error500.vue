<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<template>
  <div class="not-container">
    <img alt="500" class="not-img" src="@/assets/svgs/500.svg" />
    <div class="not-detail">
      <h2>500</h2>
      <h4>抱歉，您的网络不见了~🤦‍♂️🤦‍♀️</h4>
      <el-button type="primary" @click="router.back"> 返回上一页 </el-button>
    </div>
  </div>
</template>



<style lang="scss" scoped>
@use '@/styles/components/error';
</style>
