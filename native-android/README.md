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

已提供 Android Studio 可打开的 Kotlin + Compose 最小 Demo 工程。当前版本先使用内置样例数据跑通核心交互，后续章节会逐步替换为统一资源读取、Repository、缓存、Service 和真实性能记录。

## 学习者进入本目录后要做什么

本目录不是独立 Demo 的随手堆放区，而是课程的 Android 原生基准样本。学习者应按第 3 章的顺序逐步完成：

1. 先创建可启动的 Kotlin + Compose 工程。
2. 再实现横屏优先的目录和详情主界面。
3. 接入 `shared-assets/` 中的统一手册和车辆数据。
4. 用 ViewModel、UI State 和 Repository 整理状态边界。
5. 增加搜索、收藏或历史、离线模式、车辆状态刷新。
6. 至少接入通知、文件选择或相机中的一个原生能力。
7. 在 `benchmarks/` 中记录一次真实设备或模拟器数据。

完成每一步时，不需要追求一次做完所有功能。更重要的是保留清楚的实现边界，这样后续 Web/PWA、Hybrid、React Native、Flutter 和 KMP 才能拿它做对照。

## 运行 Demo

当前目录已经包含一个 Android Studio 可打开的最小 Demo 工程。

推荐试玩路径：

1. 使用 Android Studio 打开 `native-android/`。
2. 等待 Gradle Sync 完成。
3. 运行 `app` 到模拟器或真机。
4. 也可以打开 `CockpitManualDemoApp.kt`，在 Android Studio Preview 中查看横屏和手机两种预览。

Demo 当前已经包含：

- 横屏优先的目录 + 详情布局。
- 手册目录切换和浏览历史。
- 收藏按钮。
- 全局搜索。
- 中英文切换。
- 深色主题切换。
- 离线模式开关。
- 模拟车辆数据自动刷新。
- 文件选择、通知模拟和分享 Intent 入口。
- 性能记录入口，用于课堂统一操作路径。

当前 Demo 主要文件：

| 文件 | 作用 |
| --- | --- |
| `app/src/main/java/dev/datawhale/crossplatform/nativeandroid/MainActivity.kt` | 应用入口 |
| `app/src/main/java/dev/datawhale/crossplatform/nativeandroid/feature/cockpit/CockpitManualDemoApp.kt` | Compose 交互界面和 Preview |
| `app/src/main/java/dev/datawhale/crossplatform/nativeandroid/core/model/CockpitModels.kt` | 手册、车辆状态、路由等模型 |
| `app/src/main/java/dev/datawhale/crossplatform/nativeandroid/core/data/SampleCockpitData.kt` | 第一版课堂样例数据 |

## 核心实验目标

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

## 最小可运行目标

第一轮实现只需要达到下面的最小闭环：

```text
启动应用
  ↓
看到横屏优先的目录 + 详情布局
  ↓
点击目录切换详情
  ↓
进入搜索页并命中一条本地手册数据
  ↓
进入车辆状态页看到模拟数据刷新
  ↓
触发一个原生能力入口
  ↓
记录一次启动或内存数据
```

这个闭环完成后，Android 原生基准就具备了后续比较价值。深色主题、多语言、完整缓存、更多原生能力和性能专项可以继续迭代。

## 建议记录版本

请在创建工程后补充以下信息：

| 项目 | 当前值 |
| --- | --- |
| Android Gradle Plugin | 8.7.3 |
| Gradle | 使用 Android Studio 或本机 Gradle；Gradle Wrapper 后续补充 |
| Kotlin | 2.0.21 |
| Compose Compiler / Compose BOM | Kotlin Compose Plugin 2.0.21 / Compose BOM 2024.10.01 |
| minSdk | 26 |
| targetSdk | 35 |
| 测试设备或模拟器 | 待填写 |

详细实验说明见：

- `docs/chapter3/chapter3_1.md`：Kotlin 与 Compose 项目
- `docs/chapter3/chapter3_2.md`：页面导航
- `docs/chapter3/chapter3_3.md`：数据层
- `docs/chapter3/chapter3_4.md`：状态管理
- `docs/chapter3/chapter3_5.md`：搜索
- `docs/chapter3/chapter3_6.md`：本地缓存
- `docs/chapter3/chapter3_7.md`：Android Service
- `docs/chapter3/chapter3_8.md`：性能基准

## 对照记录

完成核心版本后，需要记录以下问题，供后续跨平台方案比较：

- 哪些 UI 和状态管理逻辑在原生中最直接。
- 哪些能力未来进入 Hybrid、RN 或 Flutter 后需要桥接。
- 哪些数据层逻辑适合迁移到 KMP。
- 当前性能数据的测试设备、构建类型和测试步骤是什么。
