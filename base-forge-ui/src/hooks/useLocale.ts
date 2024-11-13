import { ref } from 'vue'

// 引入英文语言包
import enUs from 'element-plus/es/locale/lang/en'
// 引入中文语言包
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { useI18n } from 'vue-i18n'

/**
 * 当前语言包
 */
const elementPlusLocale = ref(zhCn)

export function useLocale() {
  const { locale } = useI18n()
  /**
   * 切换语言
   * @param lang 语言标识符
   */
  const switchLanguage = (lang: string) => {
    switch (lang) {
      case 'zh':
        elementPlusLocale.value = zhCn
        locale.value = 'zh'
        break
      case 'en':
        elementPlusLocale.value = enUs
        locale.value = 'en'
        break
      default:
        elementPlusLocale.value = zhCn
        locale.value = 'zh'
        break
    }
    return elementPlusLocale.value
  }

  return {
    locale: elementPlusLocale,
    switchLanguage
  }
}

