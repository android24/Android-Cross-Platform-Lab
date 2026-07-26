# 第3.1节：Android 原生 P0 实验路径

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节把 `native-android/` 从“目录占位”推进到“可跟做的 P0 实验路径”。P0 的目标不是一次性做完所有功能，而是先得到一个可以和其他跨平台方案比较的最小版本。

## P0 完成定义

`native-android/` 的 P0 版本至少完成：

- 首页/目录页：左侧目录，右侧详情，横屏优先。
- 手册详情页：标题、摘要、正文、图片占位、收藏入口。
- 搜索页：按标题、摘要或正文搜索，支持空结果。
- 车辆状态页：展示模拟电量、续航、胎压、车门、空调、车速。
- 原生能力页：通知、文件选择或相机至少完成一个。
- 性能记录：至少填写一次启动或内存记录。

## 实验 3.1：创建原生基准工程

目标：

- 建立 Kotlin + Jetpack Compose 工程。
- 明确功能模块和后续跨平台对照边界。

建议工程结构：

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

建议依赖方向：

| 模块 | 职责 |
| --- | --- |
| `core/model` | 手册、分类、车辆状态等数据模型 |
| `core/data` | 读取 `shared-assets/` 数据，封装 Repository |
| `feature/manual` | 目录与详情 UI |
| `feature/search` | 搜索状态、搜索结果和空状态 |
| `feature/vehicle` | 模拟车辆数据展示 |
| `feature/nativecapability` | 通知、文件选择、相机等原生能力 |
| `benchmark` | 启动、内存、帧率记录入口或说明 |

任务：

- 创建 Android Studio 工程。
- 使用 Compose 作为 UI 技术。
- 在 README 中记录 Gradle、Kotlin、Compose、Android Gradle Plugin 版本。
- 建立页面导航骨架：手册、搜索、车辆状态、原生能力、设置。

验收：

- 应用可以启动到首页。
- 首页能看到导航入口或主从布局骨架。
- `native-android/README.md` 中有工程版本和启动方式。

思考问题：

- 哪些目录是 Android 原生特有的，哪些目录未来可以迁移到 KMP。
- 如果后续接入 Flutter Module 或 RN 页面，原生壳应该保留哪些职责。

## 实验 3.2：接入共享数据

目标：

- 使用课程统一数据作为原生版本的数据来源。
- 保证后续跨平台版本不是各写各的数据模型。

输入：

- `shared-assets/manual/categories.json`
- `shared-assets/manual/articles.json`
- `shared-assets/vehicle/status-samples.json`
- `shared-assets/i18n/zh.json`
- `shared-assets/i18n/en.json`

建议模型：

```kotlin
data class ManualCategory(
    val id: String,
    val title: LocalizedText,
    val parentId: String?,
    val order: Int
)

data class ManualArticle(
    val id: String,
    val categoryId: String,
    val title: LocalizedText,
    val summary: LocalizedText,
    val body: List<ArticleBlock>,
    val tags: List<String>
)

data class VehicleStatus(
    val speedKph: Int,
    val batteryPercent: Int,
    val rangeKm: Int,
    val tirePressure: TirePressure,
    val doors: DoorStatus,
    val climate: ClimateStatus
)
```

任务：

- 把共享 JSON 放入 Android assets 或通过 Gradle 任务复制到 app 资源目录。
- 建立 `ManualRepository`，提供分类、文章、文章详情和搜索接口。
- 建立 `VehicleRepository` 或模拟数据源，提供车辆状态流。

验收：

- 首页目录来自共享数据。
- 详情页内容来自共享数据。
- 车辆状态至少能展示一组样例数据。
- 数据层不直接依赖 Compose UI。

思考问题：

- Repository 的接口是否可以迁移到 KMP。
- 多语言文本是在数据层选择，还是在 UI 层选择更合适。

## 实验 3.3：实现核心 UI

目标：

- 完成 P0 中最重要的可视化体验。
- 为后续跨平台 UI 版本提供视觉和交互基准。

页面要求：

| 页面 | P0 要求 |
| --- | --- |
| 首页/目录页 | 横屏左右分栏，左侧目录，右侧默认选中第一篇文章 |
| 手册详情页 | 展示标题、摘要、正文段落、图片占位和收藏按钮 |
| 搜索页 | 输入关键词，展示结果列表，点击进入详情 |
| 车辆状态页 | 展示电量、续航、胎压、车门、空调和车速 |
| 原生能力页 | 至少提供一个能力入口 |

建议状态设计：

```text
ManualUiState
  Loading
  Content(categories, selectedArticle, favoriteIds)
  Empty
  Error(message)

SearchUiState
  query
  results
  recentQueries
  isSearching
```

验收：

- 横屏下目录和详情不重叠。
- 小屏下可以退化为单列或抽屉。
- 搜索空结果有明确提示。
- 车辆状态刷新不阻塞 UI。

思考问题：

- Compose 中状态提升的边界在哪里。
- 哪些 UI 状态未来在 RN/Flutter 中也需要保持同样语义。

## 实验 3.4：接入原生能力与性能记录

目标：

- 接入至少一个 Android 原生能力。
- 形成第一份可复查的性能记录。

原生能力任选一项：

| 能力 | 最小要求 | 对照意义 |
| --- | --- | --- |
| 通知 | 请求权限，发送一条车辆提醒通知 | 后续对比 Hybrid Bridge、RN Module、Flutter Channel |
| 文件选择 | 打开系统文件选择器，展示文件名和 MIME 类型 | 对比系统 Intent 与跨平台封装成本 |
| 相机 | 调用相机或相册，展示图片预览 | 对比权限、生命周期和结果回调 |

性能记录至少完成一项：

| 指标 | 记录位置 |
| --- | --- |
| 冷启动时间 | `benchmarks/startup.md` |
| 首页稳定内存 | `benchmarks/memory.md` |
| 详情页滚动帧率 | `benchmarks/fps.md` |
| Debug/Release 包体积 | `benchmarks/package-size.md` |

任务：

- 在原生能力页中完成一个能力闭环。
- 记录设备型号、系统版本、构建类型和测试步骤。
- 在 `benchmarks/native-android-sample.md` 中复制一份记录模板，并替换为真实数据。

验收：

- 权限拒绝或用户取消时应用不崩溃。
- 性能记录包含测试环境、测试步骤、结果和观察。
- 能指出本次数据是否可与后续跨平台版本直接比较。

思考问题：

- 原生能力中哪些代码后续适合封装为 Bridge/Module/Channel。
- 性能记录中哪些差异可能来自实现方式，而不是框架本身。

## 阶段验收清单

| 项目 | 必须完成 | 说明 |
| --- | --- | --- |
| 可启动应用 | 是 | 启动后进入手册或主导航页面 |
| 共享数据读取 | 是 | 不手写一套只属于原生版本的数据 |
| 目录详情体验 | 是 | 横屏主从布局优先 |
| 搜索 | 是 | 本地搜索即可 |
| 车辆状态 | 是 | 样例数据或定时模拟均可 |
| 原生能力 | 是 | 通知、文件选择、相机任选一项 |
| 性能记录 | 是 | 至少一项，并说明测试方法 |
| 收藏历史 | 否 | P1 阶段补齐 |
| 离线和图片缓存 | 否 | P1 阶段补齐 |
| 多语言和主题 | 否 | P1 阶段补齐 |

## 输出物

完成本节后，应提交以下内容：

```text
native-android/
  README.md
  app/...

benchmarks/
  native-android-sample.md
  startup.md 或 memory.md
```

同时在最终对比报告中记录：

```text
技术栈：Android 原生
实现范围：P0
完成时间：
主要依赖：
原生能力接入方式：
最困难的问题：
后续跨平台对照点：
```
