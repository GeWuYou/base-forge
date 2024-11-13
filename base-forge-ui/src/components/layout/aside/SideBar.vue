<script lang="ts" setup>
import { Location, Menu as IconMenu, Setting, User } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router'
import { useGlobalStore } from '@/stores/modules/global'
import { computed } from 'vue'

const route = useRoute()
const globalStore = useGlobalStore()
const isCollapse = computed(() => globalStore.isCollapse)
const activeMenu = computed(() => route.path)
</script>

<template>

  <el-aside>
    <div :style="{ width: isCollapse ? '65px' : '210px' }" class="aside">
      <router-link class="logo" to="/">
        <img :alt="$t('logo')" class="logo-img" src="../../../assets/svgs/logo.svg" />
        <span v-show="!isCollapse" class="logo-text">{{ $t('logoText') }}</span>
      </router-link>
<!--      <el-scrollbar>-->
<!--        <el-menu-->
<!--          :router="false"-->
<!--          :default-active="activeMenu"-->
<!--          :collapse="isCollapse"-->
<!--          :unique-opened="true"-->
<!--          :collapse-transition="false"-->
<!--        >-->
<!--          <SubMenu :menu-list="menuList" />-->
<!--        </el-menu>-->
<!--      </el-scrollbar>-->
      <el-scrollbar>
        <el-menu
          :collapse="isCollapse"
          :collapse-transition="false"
          :default-active="activeMenu"
          :router="false"
          :unique-opened="true"
        >
          <el-sub-menu index="1" >
            <template #title>
              <el-icon>
                <location />
              </el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group>
              <template #title><span>Group One</span></template>
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title><span>item four</span></template>
              <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="/menus">
            <el-icon>
              <icon-menu />
            </el-icon>
            <template #title>{{ $t('menu.menuManagement') }}</template>
          </el-menu-item>
          <el-menu-item index="/users">
            <el-icon>
              <User/>
            </el-icon>
            <template #title>{{ $t('menu.userManagement') }}</template>
          </el-menu-item>
          <el-menu-item index="/resources">
            <el-icon>
              <setting />
            </el-icon>
            <template #title>{{ $t('menu.resourceManagement') }}</template>
          </el-menu-item>
          <el-sub-menu index="/roles">
            <template #title>
              <el-icon>
                <location />
              </el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group>
              <template #title><span>Group One</span></template>
              <el-menu-item index="5-1">item one</el-menu-item>
              <el-menu-item index="5-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="5-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="5-4">
              <template #title><span>item four</span></template>
              <el-menu-item index="5-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </div>
  </el-aside>
</template>

<style lang="scss" scoped>
@use '@/styles/components/side-bar';
//.logo-container {
//  display: flex;
//  justify-content: center;
//  align-items: center;
//  cursor: pointer; /* 鼠标悬停时显示为手型 */
//  text-decoration: none; /* 去掉下划线 */
//  color: inherit; /* 继承父级的字体颜色 */
//  height: 60px;
//  padding: 10px;
//  .logo {
//    width: 40px;
//    height: auto;
//    margin-right: 10px;
//  }
//
//  .logo-text {
//    font-size: 14px;
//    font-weight: bold;
//  }
//}
//.el-aside {
//  display: flex;
//  flex-direction: column;
//  height: 100vh;
//  width: auto;
//  .el-menu {
//    width: 200px;
//    &.el-menu--collapse {
//      width: 60px;
//    }
//    flex: 1;
//  }
//
//}

</style>
