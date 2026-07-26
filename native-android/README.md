# native-android

Android 原生基准工程目录。

本目录用于实现“跨平台智能座舱电子手册与车辆助手”的 Android 原生版本，作为后续 Web/PWA、Hybrid、React Native、Flutter、KMP 和 Compose Multiplatform 的功能、性能与工程复杂度对照基准。

## 目标范围

- Kotlin 与 Jetpack Compose 项目结构
- 左侧目录与右侧图文详情
- 页面导航、状态管理、搜索、收藏和历史
- 本地缓存、离线模式、深色主题和多语言
- 文件选择、相机、通知和 Android Service
- 模拟车辆数据与性能基准入口

## 当前状态

框架占位，后续补充 Android Studio 工程。当前目录先提供 P0 实验路径、工程边界和验收口径，避免后续各技术栈实现时各自发挥，导致最终无法比较。

## P0 实验目标

原生基准版本至少完成：

| 能力 | 要求 |
| --- | --- |
| 首页/目录页 | 横屏下左侧目录、右侧详情 |
| 手册详情页 | 标题、摘要、正文、图片占位、收藏入口 |
| 搜索页 | 按标题、摘要或正文搜索，支持空结果 |
| 车辆状态页 | 展示电量、续航、胎压、车门、空调和车速 |
| 原生能力页 | 通知、文件选择或相机至少完成一个 |
| 性能记录 | 至少记录启动时间或首页稳定内存 |

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

## 推荐实现顺序

1. 创建 Kotlin + Jetpack Compose 工程。
2. 接入 `shared-assets/manual/categories.json` 和 `shared-assets/manual/articles.json`。
3. 实现目录、详情和搜索。
4. 接入 `shared-assets/vehicle/status-samples.json`，完成车辆状态页。
5. 接入通知、文件选择或相机中的一个原生能力。
6. 在 `benchmarks/` 中补充一次启动或内存记录。

详细实验说明见：

- `docs/chapter3/chapter3_1.md`：原生基准目标与验收口径
- `docs/chapter3/chapter3_2.md`：创建原生基准工程
- `docs/chapter3/chapter3_3.md`：接入共享数据
- `docs/chapter3/chapter3_4.md`：实现核心 UI
- `docs/chapter3/chapter3_5.md`：原生能力与性能记录

## 对照记录

完成 P0 后，需要记录以下问题，供后续跨平台方案比较：

- 哪些 UI 和状态管理逻辑在原生中最直接。
- 哪些能力未来进入 Hybrid、RN 或 Flutter 后需要桥接。
- 哪些数据层逻辑适合迁移到 KMP。
- 当前性能数据的测试设备、构建类型和测试步骤是什么。
