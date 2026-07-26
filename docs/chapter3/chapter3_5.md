# 第3.5节：搜索

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

搜索是贯穿项目的核心能力之一。它既能检验数据层设计，也能检验状态管理、列表性能、本地缓存和离线能力。

## 本节产物

完成本节后，应用应具备一条完整搜索链路：

- 用户输入关键词后可以搜索本地手册数据。
- 搜索结果展示标题、摘要、分类和命中信息。
- 点击结果后能打开或更新手册详情。
- 空结果、加载中和错误状态有明确 UI。
- 最近搜索词或搜索历史至少预留接口。

## 核心搜索范围

| 能力 | 要求 |
| --- | --- |
| 关键词输入 | 支持输入标题、摘要或正文关键词 |
| 本地搜索 | 从共享手册数据中搜索 |
| 搜索结果 | 展示标题、摘要、所属分类 |
| 空状态 | 无结果时给出明确提示 |
| 点击跳转 | 点击结果后进入或更新详情页 |

## 搜索增强方向

- 最近搜索词。
- 搜索历史清空。
- 关键词高亮。
- 本地索引缓存。
- 拼音、英文别名或同义词。
- 离线状态下搜索已缓存内容。

## 搜索边界

核心版本可以先使用简单遍历：

```text
query -> articles.filter(title/summary/body/tags)
```

当数据量变大时，再考虑建立本地索引。课程阶段重点不是追求复杂搜索引擎，而是让不同技术栈用同一份数据完成同一组搜索行为。

## 最小搜索规则

第一版搜索可以采用统一的大小写归一化和字段遍历：

```kotlin
fun ManualArticle.matches(query: String): Boolean {
    val normalized = query.trim().lowercase()
    if (normalized.isEmpty()) return false

    val searchableText = buildString {
        append(title.zh).append(' ')
        append(title.en).append(' ')
        append(summary.zh).append(' ')
        append(summary.en).append(' ')
        append(tags.joinToString(" "))
        body.forEach { block -> append(' ').append(block.plainText()) }
    }.lowercase()

    return searchableText.contains(normalized)
}
```

这里的重点是统一行为：后续 Web、RN、Flutter 可以先复刻同一套简单规则，再逐步比较索引、缓存和性能差异。

## 搜索体验状态

| 状态 | 页面表现 |
| --- | --- |
| 未输入 | 展示最近搜索词或推荐关键词 |
| 输入中 | 保持输入框响应，结果可延迟更新 |
| 有结果 | 展示结果列表，点击后进入详情 |
| 无结果 | 展示空状态，不清空用户输入 |
| 数据异常 | 展示错误提示和重试入口 |

## 动手路径

1. 在 `ManualRepository` 中实现 `search(query)`。
2. 在 `SearchViewModel` 中维护 `query`、`results`、`recentQueries` 和 `isSearching`。
3. UI 输入框只发送事件，不直接过滤数据。
4. 搜索结果列表复用文章摘要组件。
5. 点击结果后复用详情页展示逻辑。
6. 搜索完成后记录最近搜索词，后续接入缓存。

## 实验任务

1. 在 `ManualRepository` 中增加搜索接口。
2. 建立 `SearchUiState`，管理 query、results、recentQueries 和 isSearching。
3. 实现搜索输入框、搜索结果列表和空状态。
4. 点击结果后进入详情页或更新右侧详情区域。
5. 记录搜索实现方式，供后续 Web/RN/Flutter 对照。

## 阶段挑战

- 输入“胎压”“空调”“charging”等关键词时能命中不同语言或标签字段。
- 搜索结果中显示所属分类，帮助用户理解结果来源。
- 在 100 条以上文章数据下观察列表滚动是否仍然流畅。

## 完成后复盘

- 搜索结果与详情页数据一致。
- 空结果有明确提示。
- 搜索不会阻塞 UI。
- 断网状态下至少能搜索本地已加载数据。

## 对照思考

- Web/PWA 是否更适合做本地全文搜索。
- RN/Flutter 中长列表搜索结果的滚动性能如何记录。
- 搜索索引是否适合放入 KMP 共享逻辑。
