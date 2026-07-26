# 第3.2节：创建原生基准工程

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节目标是创建 `native-android/` 原生基准工程，并先搭好模块边界。此处不追求一次性完成所有业务，而是先让应用能启动、能导航、能承载后续页面。

## 实验目标

- 建立 Kotlin + Jetpack Compose 工程。
- 明确功能模块和后续跨平台对照边界。
- 记录 Gradle、Kotlin、Compose、Android Gradle Plugin 等基础版本。

## 建议工程结构

```text
native-android/
  app/
    src/main/java/.../
      App.kt
      MainActivity.kt
      core/
        data/
        model/
        ui/
      feature/
        manual/
        search/
        vehicle/
        nativecapability/
        settings/
      benchmark/
```

## 模块职责

| 模块 | 职责 |
| --- | --- |
| `core/model` | 手册、分类、车辆状态等数据模型 |
| `core/data` | 读取 `shared-assets/` 数据，封装 Repository |
| `core/ui` | 主题、通用组件、布局断点 |
| `feature/manual` | 目录与详情 UI |
| `feature/search` | 搜索状态、搜索结果和空状态 |
| `feature/vehicle` | 模拟车辆数据展示 |
| `feature/nativecapability` | 通知、文件选择、相机等原生能力 |
| `feature/settings` | 主题、语言、离线模式入口 |
| `benchmark` | 启动、内存、帧率记录入口或说明 |

## 任务步骤

1. 创建 Android Studio 工程。
2. 使用 Kotlin 和 Jetpack Compose。
3. 建立主导航骨架：手册、搜索、车辆状态、原生能力、设置。
4. 在 `native-android/README.md` 中记录工程版本和启动方式。
5. 预留 `core/data` 和 `core/model`，为下一节接入共享数据做准备。

## 验收标准

- 应用可以启动到首页。
- 首页能看到导航入口或主从布局骨架。
- `native-android/README.md` 中有工程版本、依赖版本和运行方式。
- 页面结构没有把业务数据、UI 状态和原生能力调用全部写在 `MainActivity` 中。

## 思考问题

- 哪些目录是 Android 原生特有的，哪些目录未来可以迁移到 KMP。
- 如果后续接入 Flutter Module 或 RN 页面，原生壳应该保留哪些职责。
- 原生基准工程应该先做“多模块”，还是先用单 app 模块保证学习门槛更低。

下一节进入 [第3.3节：接入共享数据](./chapter3_3.md)。
