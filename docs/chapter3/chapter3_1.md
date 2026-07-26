# 第3.1节：Kotlin 与 Compose 项目

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节建立 Android 原生基准工程。技术栈选择 Kotlin 与 Jetpack Compose，因为后续 RN、Flutter、KMP 和 Compose Multiplatform 都会围绕声明式 UI、状态驱动和原生能力边界展开比较。

## 学习目标

- 创建 `native-android/` Android Studio 工程。
- 使用 Kotlin 和 Jetpack Compose 搭建首页骨架。
- 明确原生基准工程的模块边界。
- 为后续页面导航、数据层和状态管理预留结构。

## 建议工程结构

```text
native-android/
  app/
    src/main/java/.../
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
| `core/data` | 读取共享数据，封装 Repository |
| `core/ui` | 主题、通用组件、布局断点 |
| `feature/manual` | 目录与详情页面 |
| `feature/search` | 搜索页面 |
| `feature/vehicle` | 车辆状态页面 |
| `feature/nativecapability` | 通知、文件选择、相机等原生能力 |
| `feature/settings` | 主题、语言、离线模式入口 |
| `benchmark` | 性能记录入口或说明 |

## 实验任务

1. 创建 Android 工程，并确认应用可以启动。
2. 启用 Jetpack Compose。
3. 创建应用首页，先放置主导航入口或左右分栏骨架。
4. 在 `native-android/README.md` 中记录 Gradle、Kotlin、Compose、Android Gradle Plugin 版本。
5. 保留 `core/data`、`core/model` 和 `core/ui` 目录，为后续章节接入数据和状态做准备。

## 验收标准

- 应用可以启动到首页。
- 首页能看到 Android 原生基准的主入口。
- 工程结构能区分 UI、数据、模型和原生能力。
- `MainActivity` 不承担全部业务逻辑。

## 对照思考

- Compose 的声明式 UI 和 React Native、Flutter Widget 有哪些相似点。
- 原生工程中哪些职责应保留在 Android 壳中。
- 哪些模型和 Repository 未来可能迁移到 KMP。
