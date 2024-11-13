import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import { ElConfigProvider } from 'element-plus'
import '@/styles/main.scss'

import pinia from '@/stores'
import i18n from '@/i18n'


const app = createApp(App)
app.component(<string>ElConfigProvider.name, ElConfigProvider)
app.use(pinia)
app.use(router)
app.use(i18n)
app.mount('#app')
