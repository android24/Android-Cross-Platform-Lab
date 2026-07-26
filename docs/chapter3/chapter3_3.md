# 第3.3节：接入共享数据

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节目标是让原生版本使用课程统一数据，而不是单独写一套只服务于 Android 原生的假数据。这样后续 Web、Hybrid、RN、Flutter、KMP 才能围绕同一业务口径进行比较。

## 输入数据

- `shared-assets/manual/categories.json`
- `shared-assets/manual/articles.json`
- `shared-assets/vehicle/status-samples.json`
- `shared-assets/i18n/zh.json`
- `shared-assets/i18n/en.json`

## 建议模型

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

## Repository 边界

建议至少建立两个 Repository：

| Repository | 职责 |
| --- | --- |
| `ManualRepository` | 提供分类、文章列表、文章详情和本地搜索 |
| `VehicleRepository` | 提供车辆状态样例、定时模拟或状态流 |

Repository 不应直接依赖 Compose UI。UI 层只关心状态和事件，数据读取、解析、搜索和模拟逻辑放在数据层。

## 任务步骤

1. 把共享 JSON 放入 Android assets，或通过 Gradle 任务复制到 app 资源目录。
2. 建立 `LocalizedText`、`ManualCategory`、`ManualArticle`、`VehicleStatus` 等模型。
3. 实现 `ManualRepository`，提供分类、文章、文章详情和搜索接口。
4. 实现 `VehicleRepository` 或模拟数据源，提供车辆状态数据。
5. 写一段最小验证逻辑，确认首页目录和车辆状态来自共享数据。

## 验收标准

- 首页目录来自 `shared-assets/manual/categories.json`。
- 详情页内容来自 `shared-assets/manual/articles.json`。
- 车辆状态至少能展示一组 `status-samples.json` 数据。
- 数据层不直接依赖 Compose UI。
- 后续跨平台版本可以复用同一份 JSON 数据。

## 思考问题

- Repository 的接口是否可以迁移到 KMP。
- 多语言文本是在数据层选择，还是在 UI 层选择更合适。
- 本地搜索是每次遍历 JSON，还是预先建立索引更合适。

下一节进入 [第3.4节：实现核心 UI](./chapter3_4.md)。
