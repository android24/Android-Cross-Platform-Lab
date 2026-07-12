# 课程二教学大纲

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

## 课程名称

Android 跨平台开发实验

## 课程定位

本课程是“跨平台原理”之后的第二门课程。第一门课程关注跨平台技术的底层机制、渲染模型、通信机制和运行时差异；本课程关注如何在真实 Android 工程中落地不同跨平台方案，并完成可比较、可验证、可复盘的工程实践。

课程围绕同一个大型贯穿项目“跨平台智能座舱电子手册与车辆助手”展开。所有技术栈尽量实现同一组核心能力，并在课程末尾从开发效率、UI 一致性、原生能力成本、性能、调试难度、包体积、存量接入和团队成本等维度进行比较。

## 先修要求

学习者应具备以下基础：

- 熟悉 Android Studio、Gradle 和 Android 项目基本结构。
- 掌握 Kotlin 基础语法，理解协程、集合、数据类和扩展函数。
- 理解 Activity、Fragment、生命周期、权限、网络请求和本地存储。
- 了解 HTML、CSS、JavaScript 或 TypeScript 基础。
- 至少了解一种声明式 UI 框架，例如 Jetpack Compose、React 或 Flutter Widget。

## 课程目标

完成本课程后，学习者应能够：

- 使用 Android 原生技术建立功能基准、架构基准和性能基准。
- 使用 Web、PWA、WebView 和 JavaScript Bridge 实现移动端 Hybrid 能力。
- 使用 React Native 实现跨平台业务界面，并接入 Android 原生模块和原生组件。
- 使用 Flutter 实现跨平台业务界面，并通过 Platform Channel 和 Plugin 接入原生能力。
- 使用 Kotlin Multiplatform 共享数据层、Repository 和业务逻辑。
- 理解 Unity、Qt/QML、uni-app、Taro、Weex、Hippy 等方案在 Android 生态中的位置。
- 使用统一指标评估多种跨平台方案，并给出企业级选型建议。

## 贯穿项目

课程统一使用“跨平台智能座舱电子手册与车辆助手”作为贯穿项目。

项目面向智能座舱横屏场景，主要提供车辆电子手册浏览、搜索、收藏、历史、离线访问、车辆状态展示和原生能力调用等功能。它既包含普通 App 的列表、详情、搜索、状态管理和缓存，也包含车机场景里的横屏、多分辨率、多 Display、系统权限、Service 和模拟车辆数据。

## 课程结构

### 第一篇：Android 原生基准

本篇使用 Kotlin 与 Jetpack Compose 建立课程基准工程。

核心内容：

- Kotlin 与 Compose 项目结构
- 页面导航
- 数据层设计
- 状态管理
- 搜索
- 本地缓存
- Android Service
- 性能基准

学习重点：

- 建立可复用的项目功能基准。
- 明确 Android 原生实现的性能、调试和原生能力接入成本。
- 为后续跨平台方案提供对照对象。

建议实验：

- 实验 1：创建 `native-android/` 基准项目。
- 实验 2：实现目录、详情、搜索和收藏。
- 实验 3：实现本地缓存、离线模式和主题切换。
- 实验 4：实现车辆数据 Service 与性能采集入口。

### 第二篇：Web、PWA 与 Hybrid

本篇从移动 Web 开始，逐步进入 WebView、JavaScript Bridge、Cordova、Capacitor 和 Hybrid 工程化。

核心内容：

- 移动 Web 基础
- 响应式布局
- PWA
- WebView
- JavaScript Bridge
- Cordova
- Capacitor
- Hybrid 工程化
- 离线资源
- 白屏和安全治理

学习重点：

- 理解 Web 方案的跨平台效率优势。
- 掌握 WebView 与 Android 原生能力之间的通信边界。
- 理解离线包、白屏治理、桥接安全和版本管理。

建议实验：

- 实验 1：创建 `web-pwa/`，实现可独立运行的移动 Web 版本。
- 实验 2：实现 PWA 缓存、离线访问和安装体验。
- 实验 3：创建 `hybrid-webview/`，使用 WebView 加载本地与远程页面。
- 实验 4：实现 JavaScript Bridge，调用文件选择、相机和通知能力。
- 实验 5：对比 Cordova 与 Capacitor 的工程结构和插件模型。

### 第三篇：React Native

本篇使用 React Native 实现同一业务场景，并接入 Android 原生能力。

核心内容：

- TypeScript 和 React
- RN 组件
- 导航
- 状态管理
- 网络和缓存
- 工程架构
- Kotlin Native Module
- Native Component
- 原生与 RN 混合
- 性能调试

学习重点：

- 理解 RN 的组件模型、状态管理和导航方式。
- 掌握 Kotlin Native Module 与 Native Component 的接入方式。
- 理解 JS 线程、UI 线程、桥接调用和新架构对性能的影响。

建议实验：

- 实验 1：创建 `react-native/`，实现核心页面。
- 实验 2：接入缓存、主题、多语言和搜索。
- 实验 3：使用 Kotlin 编写 Native Module 暴露车辆数据能力。
- 实验 4：实现一个 Android 原生组件并嵌入 RN 页面。
- 实验 5：采集启动、内存和滚动帧率数据。

### 第四篇：Flutter

本篇使用 Flutter 实现同一业务场景，并通过 Android 原生通道扩展能力。

核心内容：

- Dart
- Widget 体系
- 布局
- 导航
- 状态管理
- 网络和数据库
- 工程架构
- Platform Channel
- Flutter Plugin
- Flutter Module
- 混合导航
- 性能优化

学习重点：

- 理解 Flutter 的 Widget、Element、RenderObject 基本分工。
- 掌握 Platform Channel 和 Flutter Plugin 的原生能力接入方式。
- 理解 Flutter Module 接入存量 Android 工程的方式。

建议实验：

- 实验 1：创建 `flutter/`，实现核心页面和响应式布局。
- 实验 2：实现数据库、缓存、多语言和主题切换。
- 实验 3：通过 Platform Channel 接入车辆数据、文件选择和通知。
- 实验 4：将 Flutter Module 接入 Android 原生壳工程。
- 实验 5：使用 Flutter DevTools 分析性能。

### 第五篇：Kotlin Multiplatform

本篇重点关注共享业务逻辑，而不是简单追求“一套 UI 到处运行”。

核心内容：

- Source Set
- expect/actual
- Ktor
- 数据序列化
- 数据库
- Repository
- 共享业务逻辑
- Android Compose UI
- iOS 集成
- Compose Multiplatform
- 存量项目迁移

学习重点：

- 理解 KMP 的定位：共享业务逻辑、数据层、网络层和领域模型。
- 掌握 `expect/actual` 处理平台差异的方法。
- 区分 KMP、Compose Multiplatform 与传统跨平台 UI 框架的差异。

建议实验：

- 实验 1：创建 `kmp/` 共享模块，定义手册、收藏、历史和车辆数据模型。
- 实验 2：使用 Ktor 实现网络访问，使用序列化处理数据。
- 实验 3：抽象 Repository，并在 Android 端接入 Compose UI。
- 实验 4：创建 `compose-multiplatform/`，验证桌面或多平台 UI 能力。
- 实验 5：设计一个存量 Android 项目逐步迁移到 KMP 的方案。

### 第六篇：特色跨平台专题

本篇用于补充更复杂或更垂直的跨平台场景。

核心内容：

- Unity 与 Android 混合
- Qt/QML
- uni-app、Taro、Weex 和 Hippy 概览
- 智能座舱横屏和大屏
- 多 Display
- AIDL
- 系统权限

学习重点：

- 理解游戏引擎、车机大屏、双屏和系统级能力对跨平台方案的额外要求。
- 理解 Android 应用与系统服务、跨进程通信和权限模型之间的关系。
- 能识别“业务跨平台”和“系统能力跨平台”之间的差异。

建议实验：

- 实验 1：创建 `unity-android-demo/`，验证 Unity 与 Android 混合接入。
- 实验 2：实现横屏、大屏和多分辨率适配清单。
- 实验 3：使用 AIDL 或模拟接口设计车辆数据访问边界。
- 实验 4：调研 uni-app、Taro、Weex、Hippy 的工程模型和适用场景。

### 第七篇：性能、架构与选型

本篇汇总所有技术栈的实现结果，形成最终比较。

核心内容：

- 多框架性能测试
- 启动速度
- 内存
- 帧率
- 包体积
- 调用边界
- 工程成本
- 多技术栈共存
- 企业选型
- 毕业项目

学习重点：

- 使用统一方法采集性能数据。
- 区分一次性开发效率和长期维护成本。
- 能根据业务类型、团队背景、存量工程和性能要求给出选型建议。

建议实验：

- 实验 1：设计统一的性能测试脚本和记录表。
- 实验 2：采集不同技术栈的冷启动、热启动、内存和帧率。
- 实验 3：统计包体积、依赖复杂度和原生能力接入成本。
- 实验 4：完成毕业项目答辩和技术选型报告。

## 统一评价维度

课程最终评价不以简单代码量为核心，而从以下维度比较：

| 维度 | 说明 |
| --- | --- |
| 开发效率 | 从创建项目到完成功能所需的时间、代码量和工程配置成本 |
| UI 一致性 | 不同平台、不同分辨率和深色主题下的视觉与交互一致性 |
| 原生能力成本 | 文件、相机、通知、Service、车辆数据等能力接入成本 |
| 性能 | 启动速度、内存、帧率、图片加载和长列表表现 |
| 调试难度 | 日志、断点、热更新、性能工具和问题定位体验 |
| 包体积 | 基础包体积、资源体积、运行时体积和增量成本 |
| 存量接入 | 接入已有 Android 工程的复杂度和风险 |
| 团队成本 | 对 Kotlin、TypeScript、Dart、Web、原生工程能力的要求 |

## 毕业项目要求

毕业项目以“跨平台智能座舱电子手册与车辆助手”为基础，至少完成：

- Android 原生基准版本。
- 任意两个跨平台版本。
- 一份性能测试记录。
- 一份工程成本和企业选型报告。
- 一次面向技术负责人或架构评审场景的答辩。

优秀项目应进一步完成：

- 至少一个原生能力插件或桥接模块。
- 离线资源治理和白屏治理方案。
- 存量 Android 工程混合接入方案。
- 清晰的多技术栈共存边界设计。
