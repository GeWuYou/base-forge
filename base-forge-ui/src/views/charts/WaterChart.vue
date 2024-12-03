<script lang="ts" setup>

import type { ECOption } from '@/config/echart'
import EChart from '@/components/common/chart/EChart.vue'

const option = {
  series: [
    {
      type: 'liquidFill',
      data: [0.6, 0.5, 0.4, 0.3]
    }
  ]
} as ECOption
</script>

<template>
  <div class="card chart-box">
    <EChart :option="option" />
  </div>
</template>



<style lang="scss" scoped>
@use '@/styles/views/charts/water-chart';
</style>
