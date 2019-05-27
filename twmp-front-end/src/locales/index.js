import Vue from 'vue'
import VueI18n from 'vue-i18n'
import { read } from '@/utils/storage'
import elementEnLocale from 'element-ui/lib/locale/lang/en'
import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'
// import elementEsLocale from 'element-ui/lib/locale/lang/es'
import enLocale from './en'
import zhLocale from './zh-CN'
// import esLocale from './es'
Vue.use(VueI18n)

const locales = {
  // es: {
  //   ...esLocale,
  //   ...elementEsLocale
  // },
  en: {
    ...enLocale,
    ...elementEnLocale
  },
  "zh-CN": {
    ...zhLocale,
    ...elementZhLocale
  }
}

Vue.use(VueI18n)

Vue.config.lang = read('language') || 'zh-CN'
Vue.config.fallbackLang = 'zh-CN'

// set locales
Object.keys(locales).forEach(lang => {
  Vue.locale(lang, locales[lang])
})