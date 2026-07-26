# 第3.4节：状态管理

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节讨论原生基准工程中的状态管理。Compose 是声明式 UI，页面应该由状态驱动，而不是在多个组件中分散修改界面。

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

## 实验任务

1. 为目录详情页面建立 `ManualUiState`。
2. 为搜索页面建立 `SearchUiState`。
3. 为车辆状态页面建立 `VehicleUiState`。
4. 把加载、错误、空状态从 UI 组件中抽出来。
5. 记录哪些状态是页面状态，哪些状态需要持久化。

## 验收标准

- 页面状态变化不会导致 UI 明显闪烁。
- 搜索、选中目录、收藏状态有明确数据来源。
- 加载、错误和空状态都有独立展示。
- UI 组件不直接读写底层 JSON 或数据库。

## 对照思考

- Compose 状态提升和 React/RN 的状态管理有什么相似点。
- Flutter 中相同状态应该放在 Widget、Provider、Bloc 还是 Repository。
- KMP 共享业务逻辑时，状态应该共享到什么程度。
