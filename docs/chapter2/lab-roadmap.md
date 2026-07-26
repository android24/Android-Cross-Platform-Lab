# 实验路线图

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本页把“跨平台智能座舱电子手册与车辆助手”拆成一组可以逐步完成的实验任务。它的目标是让课程从“知道要学什么”变成“知道这一周要做什么、交什么、怎么验收”。

## 总体节奏

```text
阶段 0：原理热身
阶段 1：Android 原生基准
阶段 2：Web/PWA 与 Hybrid 对照
阶段 3：React Native 与 Flutter 对照
阶段 4：KMP 共享业务逻辑
阶段 5：特色跨平台专题
阶段 6：性能评测与企业选型
```

## 阶段 0：原理热身

目标：

- 从 Cross-Platform Internals 迁移到本课程。
- 明确不同跨平台方案的渲染路径、桥接边界和性能风险。

输入：

- [Cross-Platform Internals](https://android24.github.io/Cross-Platform-Internals/)
- [第1章：与 Cross-Platform Internals 的衔接](../chapter1/index.md)

任务：

- 选择 WebView、React Native、Flutter 或 KMP 中的一个方向。
- 画出它的渲染路径或调用链。
- 写出它在本课程项目中最可能暴露的两个工程风险。

验收：

- 能解释“为什么这个方案适合或不适合智能座舱电子手册”。
- 能说清楚至少一个原生能力调用边界。

思考问题：

- 这个框架的性能瓶颈更可能出现在启动、渲染、桥接还是资源加载？
- 如果要接入相机、通知、Service，调用路径会经过哪些层？

## 阶段 1：Android 原生基准

目标：

- 建立所有跨平台方案的功能和性能对照基准。
- 完成 P0 能力的原生版本。

输入：

- `shared-assets/manual/categories.json`
- `shared-assets/manual/articles.json`
- `shared-assets/vehicle/status-samples.json`
- [第3.1节：原生基准目标与验收口径](../chapter3/chapter3_1.md)
- [第3.2节：创建原生基准工程](../chapter3/chapter3_2.md)

任务：

- 创建 `native-android/` 工程。
- 实现目录页、详情页、搜索页和车辆状态页。
- 实现一个原生能力入口，例如通知或文件选择。
- 记录一次基础启动和内存数据。

验收：

- 首页能展示左侧目录和右侧详情。
- 搜索可以命中本地手册数据。
- 车辆状态可以展示并刷新。
- `benchmarks/startup.md` 或 `benchmarks/memory.md` 中有一次记录。

思考问题：

- 原生实现中哪些部分后续可以复用为跨平台方案的基准？
- 哪些能力在原生 Android 中成本很低，但到跨平台中会变复杂？

## 阶段 2：Web/PWA 与 Hybrid 对照

目标：

- 对比 Web 独立运行和 WebView Hybrid 运行时的能力边界。
- 验证离线资源、白屏治理和 JavaScript Bridge 的工程成本。

任务：

- 在 `web-pwa/` 中实现目录、详情、搜索和离线缓存。
- 在 `hybrid-webview/` 中加载同一套 Web 页面。
- 通过 JavaScript Bridge 调用一个 Android 原生能力。
- 记录 Web/PWA 与 Hybrid 的启动和白屏表现。

验收：

- Web/PWA 可独立访问核心手册内容。
- Hybrid WebView 可以加载本地资源。
- Bridge 调用有成功、失败和权限不可用的处理。
- 能说明 Web/PWA 与 Hybrid 的调试差异。

思考问题：

- 离线包更新失败时，如何避免用户看到白屏？
- Bridge 暴露能力时，如何控制调用来源和参数安全？

## 阶段 3：React Native 与 Flutter 对照

目标：

- 使用两类主流跨平台 UI 框架实现同一业务。
- 比较组件模型、状态管理、原生能力接入和性能调试体验。

任务：

- 在 `react-native/` 中实现核心页面和一个 Kotlin Native Module。
- 在 `flutter/` 中实现核心页面和一个 Platform Channel。
- 两个版本都接入车辆状态数据。
- 分别记录启动、内存和滚动帧率。

验收：

- RN 与 Flutter 均完成 P0 能力。
- 至少各接入一个原生能力。
- 能解释 Native Module 和 Platform Channel 的调用差异。
- `benchmarks/fps.md` 中有一次滚动或数据刷新记录。

思考问题：

- RN 与 Flutter 在 UI 一致性上谁更容易控制？
- 哪个方案的原生能力接入更容易被团队长期维护？

## 阶段 4：KMP 共享业务逻辑

目标：

- 理解 KMP 在本课程中的定位：共享业务逻辑，而不是强行替代所有 UI。
- 把手册、收藏、历史、车辆状态抽象为可复用的数据层。

任务：

- 在 `kmp/` 中定义共享数据模型。
- 实现 Repository 接口。
- 设计 `expect/actual` 处理平台差异。
- 在 Android Compose UI 或其他端验证共享逻辑。

验收：

- 数据模型能覆盖手册、收藏、历史和车辆状态。
- Repository 可以被 Android 侧调用。
- 能说明哪些逻辑适合共享，哪些逻辑应该留在平台侧。

思考问题：

- KMP 与 Flutter/RN 的“跨平台”目标有什么不同？
- 存量 Android 工程中，先迁移 UI 还是先迁移业务逻辑更稳妥？

## 阶段 5：特色跨平台专题

目标：

- 扩展智能座舱、大屏、多 Display、Unity、Qt/QML 等特殊场景。
- 理解“业务跨平台”和“系统能力跨平台”的差异。

任务：

- 在 `unity-android-demo/` 中验证 Unity 与 Android 混合接入。
- 设计横屏、大屏、多 Display 的适配规则。
- 设计一个车辆数据访问边界，例如 AIDL 或模拟服务。
- 调研 uni-app、Taro、Weex、Hippy 的适用场景。

验收：

- 能说明 Unity/Qt 与 RN/Flutter/WebView 的定位差异。
- 能画出车辆数据从系统侧到 UI 侧的访问边界。
- 能识别哪些场景不适合普通跨平台 UI 框架。

思考问题：

- 智能座舱中，横屏和多 Display 会如何改变架构设计？
- 系统权限、AIDL 和 Service 对跨平台框架意味着什么？

## 阶段 6：性能评测与企业选型

目标：

- 形成跨技术栈的最终比较。
- 从实验结果推导企业选型建议。

任务：

- 填写 `benchmarks/startup.md`。
- 填写 `benchmarks/memory.md`。
- 填写 `benchmarks/fps.md`。
- 填写 `benchmarks/package-size.md`。
- 填写 `benchmarks/engineering-cost.md`。
- 完成一份最终选型报告。

验收：

- 至少比较 Android 原生和两个跨平台方案。
- 每个方案都有实现范围、性能记录和工程成本记录。
- 能给出“适合什么业务、不适合什么业务、团队需要什么能力”的结论。

思考问题：

- 哪些性能差异来自框架本身，哪些来自实现质量？
- 企业选型时，短期开发效率和长期维护成本如何取舍？

## 最终交付物

| 交付物 | 说明 |
| --- | --- |
| 原生基准版本 | `native-android/` 中的 Android 原生实现 |
| 至少两个跨平台版本 | Web/PWA、Hybrid、RN、Flutter、KMP、Compose Multiplatform 中任选 |
| 共享资源使用说明 | 说明如何使用 `shared-assets/` |
| 性能记录 | 启动、内存、帧率、包体积至少覆盖核心技术栈 |
| 工程成本记录 | 原生能力、调试、存量接入和团队要求 |
| 选型报告 | 面向技术负责人或架构评审场景 |

## 评价方式

| 维度 | 权重建议 |
| --- | --- |
| 功能完成度 | 30% |
| 原生能力接入 | 15% |
| 性能记录与分析 | 20% |
| 工程结构与可维护性 | 15% |
| 技术选型结论 | 20% |
