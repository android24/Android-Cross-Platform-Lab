# benchmarks

性能与工程成本评测目录。

本目录用于记录各技术栈实现“跨平台智能座舱电子手册与车辆助手”后的统一评测结果。课程最终比较的不是简单代码量，而是开发效率、UI 一致性、原生能力成本、性能、调试难度、包体积、存量接入和团队成本。

## 记录文件

```text
startup.md
memory.md
fps.md
package-size.md
engineering-cost.md
```

## 统一技术栈

| 技术栈 | 目录 |
| --- | --- |
| Android 原生 | `native-android/` |
| Web/PWA | `web-pwa/` |
| Hybrid WebView | `hybrid-webview/` |
| React Native | `react-native/` |
| Flutter | `flutter/` |
| Kotlin Multiplatform | `kmp/` |
| Compose Multiplatform | `compose-multiplatform/` |
| Unity + Android | `unity-android-demo/` |

## 记录原则

- 使用同一台测试设备或记录设备型号。
- 使用同一份 `shared-assets/` 数据和图片资源。
- 区分 Debug、Release、首次安装、二次启动等状态。
- 记录测试方法，不只记录结果。
- 遇到无法直接比较的指标，应说明原因和替代观察方式。
