import { createI18n } from 'vue-i18n'
import en from '@/i18n/locales/en-US.json'
import zh from '@/i18n/locales/zh-CN.json'

const i18n = createI18n({
  locale: 'zh',
  messages: {
    en,
    zh
  }
})

export default i18n
