<template>
  <div class="chart-box">
    <ColumnChart :option="option" :resize="true" />
  </div>
</template>

<script lang="ts" setup>
// import ECharts from '@/components/ECharts/index.vue'
import type { ECOption } from '@/config/echart'
import ColumnChart from '@/views/charts/ColumnChart.vue'


const data = {
  columns: getDates(),
  value: ['22', '35', '32', '23', '28', '52', '46']
}

function getDates() {
  const dates = []
  for (let i = 1; i <= 7; i++) {
    const pastDate = new Date()
    pastDate.setDate(pastDate.getDate() - i)
    // 直接格式化为 "MM-dd" 格式
    const formattedDate =
      ('0' + (pastDate.getMonth() + 1)).slice(-2) + '-' + ('0' + pastDate.getDate()).slice(-2)
    dates.unshift(formattedDate)
  }
  return dates
}

const option: ECOption = {
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'none'
    }
  },
  legend: {
    right: '7%',
    top: '3%',
    itemWidth: 15,
    itemHeight: 6,
    align: 'auto',
    icon: 'rect',
    textStyle: {
      color: '#9f9fa6'
    }
  },
  grid: {
    top: '20%',
    left: '10%',
    right: '7%',
    bottom: '20%'
  },
  xAxis: [
    {
      // name: '上一周',
      type: 'category',
      boundaryGap: true,
      axisLine: {
        show: true
      },
      axisLabel: {
        padding: 0,
        fontSize: 12,
        formatter: function (data) {
          return data
        }
      },
      axisTick: {
        show: false
      },
      data: data.columns
    }
  ],
  yAxis: {
    name: '(人数)',
    nameTextStyle: {
      fontSize: 12,
      padding: [0, 30, 0, 0]
    },
    minInterval: 1,
    splitNumber: 5,
    splitLine: {
      show: true
    },
    axisLine: {
      show: false
    },
    axisLabel: {
      show: true,
      padding: 0
    },
    axisTick: {
      show: false
    }
  },
  series: {
    name: '上一周',
    type: 'line',
    symbol: 'circle',
    showSymbol: true,
    smooth: true,
    lineStyle: {
      // width: 1
      // color: data.colors[index],
      // borderColor: data.colors[index]
    },
    itemStyle: {
      // color: data.colors[index],
      borderWidth: 2
    },
    tooltip: {
      show: true
    },
    data: data.value
  }
}
</script>
<style lang="scss" scoped>
.chart-box {
  width: 100%;
  height: 100%;
}
</style>
