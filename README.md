<h1 align="center"> Android Cross Platform Lab（⚠️ Alpha内测版） </h1>

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本项目是 Android 跨平台开发实验课程仓库。课程二承接前置课程 [Cross-Platform Internals](https://android24.github.io/Cross-Platform-Internals/)，不再只讲框架 API，而是通过同一个大型贯穿项目，比较 Android 原生、Web/PWA、Hybrid、React Native、Flutter、Kotlin Multiplatform、Compose Multiplatform 等方案在真实工程中的能力边界与成本差异。

两个课程共同组成一条“原理到工程”的学习路径：

```text
Cross-Platform Internals
跨平台原理：回答“为什么能跨平台”
        ↓
Android Cross Platform Lab
跨平台实验：回答“工程里怎么落地、怎么评测、怎么选型”
```

课程二使用大型贯穿项目：

> 跨平台智能座舱电子手册与车辆助手

项目包含左侧目录、右侧图文说明、全局搜索、收藏和浏览历史、图片加载、本地缓存、离线模式、深色主题、多语言、网络请求、文件选择、相机、通知、Android Service、模拟车辆数据、横屏和多分辨率适配。

## 项目受众

本项目面向以下学习者和开发者：

- 已完成“跨平台原理”学习，希望进入工程实践阶段的同学。
- 具备 Android、Kotlin、Web 或任意一种声明式 UI 框架基础的移动端开发者。
- 需要比较 React Native、Flutter、KMP、Hybrid 等技术方案的技术负责人或学习小组。
- 希望通过同一业务场景理解开发效率、UI 一致性、原生能力成本、性能、调试难度、包体积、存量接入和团队成本的学习者。

## 在线阅读
https://android24.github.io/Android-Cross-Platform-Lab/

前置课程：

- [Cross-Platform Internals 在线阅读](https://android24.github.io/Cross-Platform-Internals/)
- [Cross-Platform Internals GitHub 仓库](https://github.com/android24/Cross-Platform-Internals)

## 目录
课程二最终比较的不是简单代码量，而是开发效率、UI 一致性、原生能力成本、性能、调试难度、包体积、存量接入和团队成本。

|  章节名   | 简介 | 状态 |
|  ----  | ---- | ---- |
| [第1章 与 Cross-Platform Internals 的衔接](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/docs/chapter1) | 说明前置课程、当前课程和实验项目之间的映射关系 | ✅ |
| [第2章 Android 跨平台开发实验](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/docs/chapter2) | 使用智能座舱电子手册与车辆助手项目贯穿多技术栈实践 | 🚧 |
| [第2.1节 课程二教学大纲](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/docs/chapter2/chapter2_1.md) | 课程目标、七篇内容、实验建议与评价维度 | ✅ |
| [第2.2节 贯穿项目需求规格说明](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/docs/chapter2/chapter2_2.md) | 统一业务需求、页面清单、数据模型与验收标准 | ✅ |

课程二工程目录规划：

| 目录 | 说明 | 状态 |
|  ----  | ---- | ---- |
| `native-android/` | Android 原生基准工程 | 🚧 |
| `web-pwa/` | 移动 Web 与 PWA 实验工程 | 🚧 |
| `hybrid-webview/` | Android WebView 与 Hybrid 实验工程 | 🚧 |
| `react-native/` | React Native 实验工程 | 🚧 |
| `flutter/` | Flutter 实验工程 | 🚧 |
| `kmp/` | Kotlin Multiplatform 共享业务逻辑工程 | 🚧 |
| `compose-multiplatform/` | Compose Multiplatform 实验工程 | 🚧 |
| `unity-android-demo/` | Unity 与 Android 混合实验工程 | 🚧 |
| `shared-assets/` | 多技术栈共享数据、图片和国际化资源 | ✅ |
| `benchmarks/` | 启动、内存、帧率、包体积和工程成本记录模板 | ✅ |

## 贡献者名单

| 姓名 | 职责 | 简介 |
| :----| :---- | :---- |
| 待补充 | 项目负责人 | 负责课程规划、内容组织和实验设计 |
| 待补充 | 核心贡献者 | 负责章节编写、代码实现和评测验证 |

*注：表头可自定义，但必须在名单中标明项目负责人*

## 参与贡献

- 如果你发现了一些问题，可以提Issue进行反馈，如果提完没有人回复你可以联系[保姆团队](https://github.com/datawhalechina/DOPMC/blob/main/OP.md)的同学进行反馈跟进~
- 如果你想参与贡献本项目，可以提Pull Request，如果提完没有人回复你可以联系[保姆团队](https://github.com/datawhalechina/DOPMC/blob/main/OP.md)的同学进行反馈跟进~
- 如果你对 Datawhale 很感兴趣并想要发起一个新的项目，请按照[Datawhale开源项目指南](https://github.com/datawhalechina/DOPMC/blob/main/GUIDE.md)进行操作即可~

## 关注我们

<div align=center>
<p>扫描下方二维码关注公众号：Datawhale</p>
<img src="https://raw.githubusercontent.com/datawhalechina/pumpkin-book/master/res/qrcode.jpeg" width = "180" height = "180">
</div>

## LICENSE

<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="知识共享许可协议" style="border-width:0" src="https://img.shields.io/badge/license-CC%20BY--NC--SA%204.0-lightgrey" /></a><br />本作品采用<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议</a>进行许可。

*注：默认使用CC 4.0协议，也可根据自身项目情况选用其他协议*
