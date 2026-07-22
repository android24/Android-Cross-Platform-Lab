# 第1章：与 Cross-Platform Internals 的衔接

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本章用于说明本课程与前置课程 [Cross-Platform Internals](https://android24.github.io/Cross-Platform-Internals/) 的关系。

两个课程共同组成一条“原理到工程”的学习路径：

```text
Cross-Platform Internals
跨平台原理：回答“为什么能跨平台”
        ↓
Android Cross Platform Lab
跨平台实验：回答“工程里怎么落地、怎么评测、怎么选型”
```

## 课程群定位

| 课程 | 定位 | 核心问题 | 学习结果 |
| --- | --- | --- | --- |
| Cross-Platform Internals | 原理课 | 为什么能跨平台 | 理解渲染、运行时、桥接、线程、性能模型 |
| Android Cross Platform Lab | 实验课 | 工程里怎么落地 | 会实现、会评测、会接入原生能力、会做技术选型 |

简单说：

- Internals 讲机制，Lab 讲实现。
- Internals 讲框架为什么这样设计，Lab 验证这些设计在真实项目里的代价。
- Internals 帮你建立统一认知模型，Lab 让你把模型落到工程、性能和团队协作里。

## 学习顺序

建议学习顺序如下：

1. 先学习 [Cross-Platform Internals](https://android24.github.io/Cross-Platform-Internals/)。
2. 用原理课中的渲染、桥接、线程、运行时和性能模型解释不同框架的设计。
3. 再进入本课程，用“跨平台智能座舱电子手册与车辆助手”实现多技术栈版本。
4. 最后从开发效率、UI 一致性、原生能力成本、性能、调试难度、包体积、存量接入和团队成本做比较。

如果你已经熟悉 WebView、React Native、Flutter、Kotlin Multiplatform 等框架的基本原理，也可以直接进入本课程，并在遇到机制问题时回到前置课程查漏补缺。

## 原理到实验的映射

| Cross-Platform Internals 原理主题 | Android Cross Platform Lab 对应实验 |
| --- | --- |
| WebView 渲染与 JavaScript Bridge | Hybrid WebView、离线资源、白屏治理、Bridge 安全治理 |
| React Native 桥接、新架构与线程模型 | RN Native Module、Native Component、原生与 RN 混合、性能调试 |
| Flutter 自绘渲染与 Platform Channel | Flutter Widget、Platform Channel、Flutter Plugin、Flutter Module |
| Kotlin Multiplatform 编译模型与 `expect/actual` | KMP Source Set、共享 Repository、Android Compose UI、iOS 集成 |
| 跨平台性能模型 | 启动速度、内存、帧率、包体积、调用边界评测 |
| 多技术栈共存 | 原生、Hybrid、RN、Flutter、KMP 在存量 Android 工程中的共存边界 |
| 原生能力与权限模型 | 文件选择、相机、通知、Android Service、系统权限和 AIDL |

## 结课到开课的衔接

前置课程的结课作业，可以作为本课程的开课输入。

| Internals 结课方向 | Lab 开课实践 |
| --- | --- |
| 分析 React Native Bridge 或新架构 | 实现 RN Native Module 调用模拟车辆数据 |
| 分析 Flutter Platform Channel | 实现 Flutter 调用文件选择、相机和通知 |
| 分析 WebView 与 JavaScript Bridge 安全边界 | 实现 Hybrid 白屏治理、离线包和 Bridge 权限控制 |
| 分析 KMP 共享逻辑模型 | 设计手册、收藏、历史和车辆状态的共享 Repository |
| 分析跨平台性能来源 | 在 `benchmarks/` 中记录启动、内存、帧率和包体积 |

这样的衔接可以避免“原理课学完就结束”，也避免“实验课只是在写框架 API”。学习者会带着原理问题进入工程实现，再用工程结果反过来验证原理判断。

## 进入本课程前的自测问题

开始课程二之前，可以用以下问题自查：

- WebView、React Native、Flutter 和 Compose 的渲染路径有什么差异？
- JavaScript Bridge、Platform Channel、Native Module 的调用边界分别在哪里？
- 为什么跨平台框架的启动速度、包体积和内存占用会不同？
- 为什么同一个 UI 在不同技术栈里会有不同的布局和调试成本？
- KMP 为什么更适合共享业务逻辑，而不是简单替代所有平台 UI？
- 什么场景适合共享 UI，什么场景更适合只共享数据层和业务逻辑？

## 下一步

完成本章后，进入 [课程二教学大纲](../chapter2/chapter2_1.md)，了解本课程的七篇内容、实验安排和最终评价维度。
