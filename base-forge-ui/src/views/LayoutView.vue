<script lang="ts" setup>
import SideBar from '@/components/layout/aside/SideBar.vue'
import ToolBarLeft from '@/components/layout/header/ToolBarLeft.vue'
import ToolBarRight from '@/components/layout/header/ToolBarRight.vue'
import MainContent from '@/components/layout/main/MainContent.vue'
</script>

<template>
  <el-container class="layout">
    <!--侧栏-->
    <SideBar />
    <el-container>
      <el-header>
        <ToolBarLeft />
        <ToolBarRight />
      </el-header>
      <MainContent />
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped></style>
