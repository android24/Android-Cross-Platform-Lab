# 第3.4节：状态管理

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节讨论原生基准工程中的状态管理。Compose 是声明式 UI，页面应该由状态驱动，而不是在多个组件中分散修改界面。

## 本节产物

完成本节后，页面应该从“组件自己处理数据”变为“状态驱动 UI”：

- 手册页、搜索页、车辆状态页都有独立 UI State。
- 用户操作通过 Event 或函数入口进入 ViewModel。
- 加载、空结果、错误、内容四类状态有明确呈现。
- 收藏、历史、离线模式等业务状态不散落在 Compose 组件中。

## 状态分层

建议把状态分成三类：

| 状态类型 | 示例 | 保存位置 |
| --- | --- | --- |
| 页面 UI 状态 | 加载中、错误、空结果、选中文章 | ViewModel 或页面状态容器 |
| 业务状态 | 收藏、历史、离线模式、语言 | Repository 或持久化存储 |
| 临时交互状态 | 输入框内容、弹窗展开、滚动位置 | Compose remember 或 ViewModel |

## UI State 示例

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

VehicleUiState
  Loading
  Content(status)
  Error(message)
```

Kotlin 中可以使用 `sealed interface` 和 `data class` 表达页面状态：

```kotlin
sealed interface ManualUiState {
    data object Loading : ManualUiState
    data class Content(
        val categories: List<ManualCategory>,
        val selectedArticle: ManualArticle?,
        val favoriteIds: Set<String>
    ) : ManualUiState
    data object Empty : ManualUiState
    data class Error(val message: String) : ManualUiState
}
```

## 事件设计

页面事件可以按业务动作命名：

```text
ManualEvent
  SelectCategory(id)
  SelectArticle(id)
  ToggleFavorite(id)
  Retry

SearchEvent
  QueryChanged(text)
  Submit
  OpenResult(id)
  ClearHistory
```

这样后续迁移到 RN、Flutter 或 KMP 时，也能保持相似的状态语义。

## ViewModel 职责

ViewModel 不是简单的数据中转站，它应该负责把 Repository 数据转换为页面可直接消费的状态。

```text
Repository Flow / suspend function
        ↓
ViewModel 组合、筛选、错误处理
        ↓
UiState
        ↓
Composable 根据状态渲染
```

建议遵守三条边界：

- Composable 不直接读取 JSON、数据库或网络。
- ViewModel 不持有 Android View。
- Repository 不知道页面上有哪些按钮、弹窗或列表。

## 动手路径

1. 为手册页定义 `ManualUiState` 和 `ManualEvent`。
2. 为搜索页定义 `SearchUiState` 和搜索事件。
3. 为车辆状态页定义 `VehicleUiState`。
4. 在 ViewModel 中处理加载、错误和空状态。
5. 让 Composable 只根据 `uiState` 展示不同 UI。

## 实验任务

1. 为目录详情页面建立 `ManualUiState`。
2. 为搜索页面建立 `SearchUiState`。
3. 为车辆状态页面建立 `VehicleUiState`。
4. 把加载、错误、空状态从 UI 组件中抽出来。
5. 记录哪些状态是页面状态，哪些状态需要持久化。

## 阶段挑战

- 搜索输入变化时不要立即阻塞主线程，可使用防抖或后台过滤。
- 收藏状态变化后，目录、详情和收藏入口同步刷新。
- 屏幕旋转后，当前选中文章和搜索词尽量保留。

## 完成后复盘

- 页面状态变化不会导致 UI 明显闪烁。
- 搜索、选中目录、收藏状态有明确数据来源。
- 加载、错误和空状态都有独立展示。
- UI 组件不直接读写底层 JSON 或数据库。

## 对照思考

- Compose 状态提升和 React/RN 的状态管理有什么相似点。
- Flutter 中相同状态应该放在 Widget、Provider、Bloc 还是 Repository。
- KMP 共享业务逻辑时，状态应该共享到什么程度。
