# Android 原生基准实验总览

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本页是 Android 原生基准的实验总览。第 3 章已经按照课程最初规划拆成以下小节：

| 小节 | 内容 |
| --- | --- |
| [第3.1节：Kotlin 与 Compose 项目](./chapter3_1.md) | 创建 `native-android/` 工程骨架 |
| [第3.2节：页面导航](./chapter3_2.md) | 建立首页、详情、搜索、车辆状态、原生能力等页面流转 |
| [第3.3节：数据层](./chapter3_3.md) | 接入共享数据，建立 Repository |
| [第3.4节：状态管理](./chapter3_4.md) | 设计 UI State、事件和加载/错误/空状态 |
| [第3.5节：搜索](./chapter3_5.md) | 实现本地搜索、搜索结果和空状态 |
| [第3.6节：本地缓存](./chapter3_6.md) | 实现收藏、历史、离线模式和图片缓存策略 |
| [第3.7节：Android Service](./chapter3_7.md) | 模拟车辆数据和原生 Service 边界 |
| [第3.8节：性能基准](./chapter3_8.md) | 记录启动、内存、帧率、包体积和工程成本 |

建议按 3.1 到 3.8 顺序完成。完成后，`native-android/` 就具备原生基准能力，可以作为后续 Web/PWA、Hybrid、React Native、Flutter 和 KMP 的对照对象。
