<script lang="ts" setup>

import VistorChart from '@/components/content/dashboard/VistorChart.vue'
import CarChart from '@/components/content/dashboard/CarChart.vue'
</script>

<template>
  <div class="dashboard">
    <el-row :gutter="12">
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-1">
          <div class="card-info">
            <span class="card-title">人员出入</span>
            <h2 class="card-text">20</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangke"></i>
          </div>
        </div>
      </el-col>
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-2">
          <div class="card-info">
            <span class="card-title">外来人员</span>
            <h2 class="card-text">2</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangke"></i>
          </div>
        </div>
      </el-col>
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-3">
          <div class="card-info">
            <span class="card-title">本周人员</span>
            <h2 class="card-text">77</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangke"></i>
          </div>
        </div>
      </el-col>
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-4">
          <div class="card-info">
            <span class="card-title">车辆出入</span>
            <h2 class="card-text">10</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangche"></i>
          </div>
        </div>
      </el-col>
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-5">
          <div class="card-info">
            <span class="card-title">外来车辆</span>
            <h2 class="card-text">1</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangche"></i>
          </div>
        </div>
      </el-col>
      <el-col :md="8" :sm="12" :xs="24">
        <div class="gather-card mb12 bg-6">
          <div class="card-info">
            <span class="card-title">本周车辆</span>
            <h2 class="card-text">66</h2>
          </div>
          <div class="card-icon">
            <i class="iconfont icon-fangche"></i>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="12">
      <el-col :md="12" :sm="24" :xs="24">
        <div class="chart-card mb12">
          <div class="chart-title mb10">访问人数</div>
          <VistorChart />
        </div>
      </el-col>
      <el-col :md="12" :sm="24" :xs="24">
        <div class="chart-card mb12">
          <div class="chart-title mb10">访车数据</div>
          <CarChart />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
@use '@/styles/views/dashboard';
</style>
