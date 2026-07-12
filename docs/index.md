---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "Android Cross Platform Lab"
  text: "Android 跨平台开发实验"
  tagline: 用同一个智能座舱项目比较原生、Web、Hybrid、React Native、Flutter、KMP 与 Compose Multiplatform。
  image:
    src: /learning.GIF
    alt: Android 跨平台开发实验
  actions:
    - theme: brand
      text: 开始学习
      link: /chapter2/chapter2_1

features:
  - title: 💥 工程实践
    details: 使用统一贯穿项目比较多种 Android 跨平台方案
  - title: 🎁 开源课程
    details: 教程和代码源文件全部托管在GitHub
  - title: 🌐 选型对比
    details: 从性能、调试、包体积、原生能力和团队成本形成判断
---
<script setup>
import { VPTeamMembers } from 'vitepress/theme'

const members = [
  {
    avatar: 'https://www.github.com/Sm1les.png',
    name: '待补充',
    title: '项目负责人',
    links: [
      { icon: 'github', link: 'https://github.com/android24' },
    ]
  },
  {
    avatar: 'https://www.github.com/Sm1les.png',
    name: '待补充',
    title: '核心贡献者',
    links: [
      { icon: 'github', link: 'https://github.com/android24' },
    ]
  }
]
</script>


<h2 align="center">Team</h2>
<VPTeamMembers size="small" :members />
