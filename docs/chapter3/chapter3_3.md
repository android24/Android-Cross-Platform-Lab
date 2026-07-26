# 第3.3节：数据层

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节建立 Android 原生基准的数据层。数据层要尽量使用课程统一的 `shared-assets/` 数据，避免后续每个技术栈各写一套数据，导致比较失真。

## 本节产物

完成本节后，原生工程应该不再依赖写死在 UI 中的假数据：

- 手册目录和文章详情来自 `shared-assets/manual/`。
- 车辆状态样例来自 `shared-assets/vehicle/status-samples.json`。
- UI 层通过 Repository 获取数据，而不是直接解析 JSON。
- 数据模型命名和字段语义可以被后续 Web、RN、Flutter、KMP 对照复用。

## 输入数据

- `shared-assets/manual/categories.json`
- `shared-assets/manual/articles.json`
- `shared-assets/vehicle/status-samples.json`
- `shared-assets/i18n/zh.json`
- `shared-assets/i18n/en.json`

## 核心模型

建议至少建立以下模型：

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

## Repository 接口草图

先把调用关系设计清楚，再决定 JSON 解析、缓存和数据库的具体实现。

```kotlin
interface ManualRepository {
    suspend fun getCategories(): List<ManualCategory>
    suspend fun getArticles(): List<ManualArticle>
    suspend fun getArticle(articleId: String): ManualArticle?
    suspend fun search(query: String): List<ManualArticle>
}

interface VehicleRepository {
    fun observeVehicleStatus(): Flow<VehicleStatus>
}

interface UserRepository {
    fun observeFavoriteIds(): Flow<Set<String>>
    suspend fun toggleFavorite(articleId: String)
    suspend fun recordHistory(articleId: String)
}
```

接口不应该带 Compose 类型。这样后续迁移到 KMP 时，可以优先移动模型、Repository 接口和业务规则，再处理平台文件系统、数据库和资源读取差异。

## Repository 设计

| Repository | 职责 |
| --- | --- |
| `ManualRepository` | 分类、文章列表、文章详情、本地搜索 |
| `UserRepository` | 收藏、历史、最近搜索词 |
| `VehicleRepository` | 车辆状态样例、定时模拟或状态流 |
| `SettingsRepository` | 主题、语言、离线模式 |

Repository 不应直接依赖 Compose UI。UI 层只接收状态和触发事件，数据读取、解析、缓存和搜索逻辑放在数据层。

## 数据接入路径

```text
shared-assets/manual/*.json
        ↓
Android assets 或 Gradle 复制任务
        ↓
JsonDataSource
        ↓
ManualRepository
        ↓
ViewModel
        ↓
Compose UI
```

第一版实现可以直接读取 assets 中的 JSON。后续如果加入网络请求、Room 或 KMP，共享数据仍然保持同一套语义。

## 动手路径

1. 把 `shared-assets/manual/` 和 `shared-assets/vehicle/` 中的 JSON 复制到 Android `assets/`。
2. 建立 `ManualCategory`、`ManualArticle`、`ArticleBlock`、`VehicleStatus` 等模型。
3. 实现 `JsonManualDataSource`，只负责读取和解析 JSON。
4. 实现 `DefaultManualRepository`，向页面提供分类、文章、详情和搜索接口。
5. 实现 `SampleVehicleRepository`，先从样例数据中返回车辆状态。
6. 把 3.1 中的假数据替换成 Repository 数据。

## 实验任务

1. 把共享 JSON 放入 Android assets，或通过 Gradle 任务复制到 app 资源目录。
2. 建立手册、文章、车辆状态和本地化文本模型。
3. 实现 `ManualRepository`，提供分类、文章、详情和搜索接口。
4. 实现 `VehicleRepository`，先读取样例数据。
5. 为收藏、历史和离线模式预留数据接口。

## 阶段挑战

- 保证首页目录顺序由 `order` 字段决定，而不是 JSON 文件顺序。
- 手册详情缺失时返回空状态，不让页面崩溃。
- 给 Repository 增加一条简单单元测试：搜索“胎压”可以命中对应文章。

## 完成后复盘

- 首页目录来自共享数据。
- 详情页内容来自共享数据。
- 车辆状态至少能展示一组样例数据。
- 数据层不直接依赖 Compose UI。
- 后续跨平台版本可以复用同一份 JSON 数据。

## 对照思考

- 哪些 Repository 接口适合迁移到 KMP。
- 多语言文本应该在数据层选择，还是在 UI 层选择。
- Web、RN、Flutter 是否能复用同样的数据模型语义。
