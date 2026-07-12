import { defineConfig } from 'vitepress'
// https://vitepress.dev/reference/site-config

// 1. 获取环境变量并判断
// 如果环境变量 EDGEONE 等于 '1'，说明在 EdgeOne 环境，使用根路径 '/'
// 否则默认是 GitHub Pages 环境，使用仓库子路径 '/Android-Cross-Platform-Lab/'
const isEdgeOne = process.env.EDGEONE === '1'
const baseConfig = isEdgeOne ? '/' : '/Android-Cross-Platform-Lab/'

export default defineConfig({
  lang: 'zh-CN',
  title: "Android Cross Platform Lab",
  description: "面向 Android 开发者的跨平台开发实验课程，使用统一智能座舱项目比较 Hybrid、React Native、Flutter、KMP 等方案。",
  base: baseConfig,
  head: [
    ['meta', { name: 'keywords', content: 'Android,跨平台,Hybrid,React Native,Flutter,Kotlin Multiplatform,Compose Multiplatform,Datawhale' }],
    ['meta', { property: 'og:title', content: 'Android Cross Platform Lab' }],
    ['meta', { property: 'og:description', content: '用一个智能座舱项目，系统比较 Android 原生、Hybrid、React Native、Flutter 与 Kotlin Multiplatform 的工程边界。' }],
    ['meta', { property: 'og:type', content: 'website' }]
  ],
  markdown: {
    math: true
  },
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    logo: '/datawhale-logo.png',
    nav: [
      { text: '课程大纲', link: '/chapter2/chapter2_1' },
      { text: '项目规格', link: '/chapter2/chapter2_2' },
      { text: 'GitHub', link: 'https://github.com/android24/Android-Cross-Platform-Lab' },
      { text: 'PDF版本下载', link: 'https://github.com/android24/Android-Cross-Platform-Lab/releases' },
    ],
    search: {
      provider: 'local',
      options: {
        translations: {
          button: {
            buttonText: '搜索文档',
            buttonAriaLabel: '搜索文档'
          },
          modal: {
            noResultsText: '无法找到相关结果',
            resetButtonTitle: '清除查询条件',
            footer: {
              selectText: '选择',
              navigateText: '切换'
            }
          }
        }
      }
    },
    sidebar: [
      {
        items: [
          { text: '第1章：跨平台原理前置说明', link: '/chapter1/' },
          { text: '第2章：Android 跨平台开发实验', 
            items: [
              { text: '第2.1节：课程二教学大纲', link: '/chapter2/chapter2_1' },
              { text: '第2.2节：贯穿项目需求规格说明', link: '/chapter2/chapter2_2' }
            ]
           }
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/android24/Android-Cross-Platform-Lab' }
    ],

    editLink: {
      pattern: 'https://github.com/android24/Android-Cross-Platform-Lab/blob/main/docs/:path'
    },

    footer: {
      message: 'Android Cross Platform Lab | 基于 Datawhale 开源教程模板建设',
      copyright: '本作品采用 <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/" target="_blank">知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议（CC BY-NC-SA 4.0）</a> 进行许可'
    }
  }
})
