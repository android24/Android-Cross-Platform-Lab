# shared-assets

跨技术栈共享资源目录。

本目录存放所有实现共同使用的数据、图片、国际化文本和车辆状态样例，避免不同技术栈各自编造数据导致最终比较口径不一致。

## 目录说明

```text
manual/
  categories.json
  articles.json
images/
i18n/
  zh.json
  en.json
vehicle/
  status-samples.json
```

## 使用原则

- 各技术栈应优先读取本目录中的数据样例。
- 如果某个技术栈需要转换格式，应记录转换脚本或转换说明。
- 性能测试时应尽量使用同一组图片、文章和车辆状态样例。
