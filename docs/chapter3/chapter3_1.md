# 第3.1节：Kotlin 与 Compose 项目

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节建立 Android 原生基准工程。技术栈选择 Kotlin 与 Jetpack Compose，因为后续 RN、Flutter、KMP 和 Compose Multiplatform 都会围绕声明式 UI、状态驱动和原生能力边界展开比较。

## 本节产物

完成本节后，学习者应该拿到一个可以启动的 Android 原生基准壳工程：

- `native-android/` 中存在 Kotlin + Compose Android 工程。
- 首页能展示“智能座舱电子手册与车辆助手”的基本布局。
- 工程中预留 `core/`、`feature/` 和 `benchmark/` 分层目录。
- `native-android/README.md` 中记录当前使用的关键版本。

本仓库已经提供一个最小 Demo 起点：

- [`native-android/`](https://github.com/android24/Android-Cross-Platform-Lab/tree/main/native-android)
- [`MainActivity.kt`](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/native-android/app/src/main/java/dev/datawhale/crossplatform/nativeandroid/MainActivity.kt)
- [`CockpitManualDemoApp.kt`](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/native-android/app/src/main/java/dev/datawhale/crossplatform/nativeandroid/feature/cockpit/CockpitManualDemoApp.kt)
- [`CockpitModels.kt`](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/native-android/app/src/main/java/dev/datawhale/crossplatform/nativeandroid/core/model/CockpitModels.kt)
- [`SampleCockpitData.kt`](https://github.com/android24/Android-Cross-Platform-Lab/blob/main/native-android/app/src/main/java/dev/datawhale/crossplatform/nativeandroid/core/data/SampleCockpitData.kt)

课堂中可以先运行这个 Demo，再逐步把样例数据替换为 Repository、缓存和真实性能记录。

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

## 最小页面骨架

原生基准首页不需要一开始就做完整视觉效果，但要先具备后续实验会不断扩展的主从结构。

```kotlin
@Composable
fun CockpitManualApp() {
    AppTheme {
        ManualHomeScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ManualHomeScreen(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ManualDirectoryPane(
            modifier = Modifier
                .width(280.dp)
                .fillMaxHeight()
        )
        ManualDetailPane(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}
```

这段代码的目的不是追求一次完成界面，而是先固定课程的“原生基准形态”：左侧目录、右侧详情、状态驱动、后续可替换数据。

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

## 动手路径

1. 使用 Android Studio 创建 Empty Activity 项目，并启用 Jetpack Compose。
2. 把项目根目录放在 `native-android/` 下，避免和后续 `react-native/`、`flutter/` 混在一起。
3. 创建 `core/model`、`core/data`、`core/ui` 三个基础包。
4. 创建 `feature/manual`，放置 `ManualHomeScreen`、`ManualDirectoryPane` 和 `ManualDetailPane`。
5. 在首页中先使用静态假数据展示目录和详情。
6. 在 `native-android/README.md` 中记录 Gradle、Kotlin、Compose、Android Gradle Plugin 和 minSdk。

## 第一版假数据

本节可以先不用接入 JSON，直接使用最小假数据让 UI 跑起来。

```kotlin
data class ManualMenuItem(
    val id: String,
    val title: String,
    val summary: String
)

val previewManualItems = listOf(
    ManualMenuItem("quick-start", "快速开始", "了解车辆基础操作"),
    ManualMenuItem("climate", "空调与座椅", "调节温度、风量和座椅功能"),
    ManualMenuItem("charging", "充电与能耗", "查看充电状态和续航信息")
)
```

这里的假数据后续会被 `shared-assets/manual/categories.json` 和 `shared-assets/manual/articles.json` 替换。先让 UI 和布局成型，再接入真实数据，学习曲线会更平滑。

当前 Demo 已经在 `SampleCockpitData.kt` 中提供了第一版样例数据，包含手册分类、文章、搜索标签和车辆状态样本。第 3.3 节会把这些静态样例替换为统一资源读取和 Repository。

## 实验任务

1. 创建 Android 工程，并确认应用可以启动。
2. 启用 Jetpack Compose。
3. 创建应用首页，先放置主导航入口或左右分栏骨架。
4. 在 `native-android/README.md` 中记录 Gradle、Kotlin、Compose、Android Gradle Plugin 版本。
5. 保留 `core/data`、`core/model` 和 `core/ui` 目录，为后续章节接入数据和状态做准备。

## 阶段挑战

- 给左侧目录增加选中态。
- 在右侧详情区域展示标题、摘要和三行说明文字。
- 使用 `WindowSizeClass` 或自定义宽度判断，让小屏从左右分栏退化为上下布局。

## 完成后复盘

- 应用可以启动到首页。
- 首页能看到 Android 原生基准的主入口。
- 工程结构能区分 UI、数据、模型和原生能力。
- `MainActivity` 不承担全部业务逻辑。

## 对照思考

- Compose 的声明式 UI 和 React Native、Flutter Widget 有哪些相似点。
- 原生工程中哪些职责应保留在 Android 壳中。
- 哪些模型和 Repository 未来可能迁移到 KMP。
