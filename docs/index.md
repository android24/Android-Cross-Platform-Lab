---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "Android Cross Platform Lab"
  text: "Android 跨平台开发实验"
  tagline: 用一个智能座舱项目，系统比较 Android 原生、Hybrid、React Native、Flutter 与 Kotlin Multiplatform 的工程边界。
  image:
    src: /learning.GIF
    alt: Android 跨平台开发实验
  actions:
    - theme: brand
      text: 开始学习
      link: /chapter2/chapter2_1
    - theme: alt
      text: 查看项目规格
      link: /chapter2/chapter2_2
    - theme: alt
      text: 前置课程
      link: https://android24.github.io/Cross-Platform-Internals/

features:
  - title: 🧭 前后课程联动
    details: 承接 Cross-Platform Internals，从跨平台原理进入 Android 工程实践。
  - title: 💥 统一贯穿项目
    details: 围绕“跨平台智能座舱电子手册与车辆助手”实现目录、搜索、缓存、离线和车辆数据。
  - title: 🎁 多技术栈实战
    details: 覆盖 Android 原生、Web/PWA、Hybrid、React Native、Flutter、KMP 与 Compose Multiplatform。
  - title: 🌐 性能与选型评估
    details: 从启动速度、内存、帧率、包体积、原生能力成本、调试难度和团队成本形成判断。
---
<script setup>
import { VPTeamMembers } from 'vitepress/theme'

const members = [
  {
    avatar: 'https://github.com/android24.png',
    name: 'android24',
    title: '项目负责人',
    links: [
      { icon: 'github', link: 'https://github.com/android24' },
    ]
  }
]
</script>

## 课程路线

课程二承接 [Cross-Platform Internals](https://android24.github.io/Cross-Platform-Internals/) 课程，采用“同一业务、多种技术栈、统一验收标准”的方式组织。学习者会先用 Android 原生建立基准，再分别使用 Web/PWA、Hybrid、React Native、Flutter、Kotlin Multiplatform、Compose Multiplatform 等方案实现同一组业务能力，最终完成性能、工程成本和企业选型比较。

```text
Cross-Platform Internals
跨平台原理：理解渲染、桥接、运行时、线程和性能模型
        ↓
Android Cross Platform Lab
跨平台实验：完成多技术栈实现、原生能力接入、性能评测和企业选型
```

## 课程衔接

如果你还不理解 WebView、JavaScript Bridge、React Native Native Module、Flutter Platform Channel、KMP `expect/actual` 等机制，建议先学习前置课程。完成原理课后，再回到本课程用统一智能座舱项目验证这些机制在真实 Android 工程中的开发成本、性能表现和团队要求。

## 项目能力

贯穿项目覆盖左侧目录、右侧图文说明、全局搜索、收藏和浏览历史、图片加载、本地缓存、离线模式、深色主题、多语言、网络请求、文件选择、相机、通知、Android Service、模拟车辆数据、横屏和多分辨率适配。

<h2 align="center">Team</h2>
<VPTeamMembers size="small" :members />
