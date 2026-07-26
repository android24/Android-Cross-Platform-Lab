# 第3.7节：Android Service

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节使用 Android Service 或等价模拟器生成车辆状态数据。它是后续跨平台方案接入原生能力的重要对照点。

## 车辆状态范围

至少包含：

- 电量。
- 续航。
- 胎压。
- 车门状态。
- 空调状态。
- 车速。

数据可以来自 `shared-assets/vehicle/status-samples.json`，也可以由本地模拟器定时生成。

## Service 边界

Service 需要回答：

- 数据如何产生。
- 页面如何订阅。
- 页面退出后是否继续运行。
- 应用后台时如何释放资源。
- 跨平台页面如何调用或订阅这些数据。

## 推荐结构

```text
VehicleService / VehicleSimulator
        ↓
VehicleRepository
        ↓
VehicleUiState
        ↓
VehicleStatusScreen
```

如果 P0 阶段暂不实现真正的 Android Service，也可以先用等价模拟器，但需要在文档中说明和真实 Service 的差异。

## 实验任务

1. 使用样例数据或定时器模拟车辆状态变化。
2. 建立 `VehicleRepository`，向 UI 提供状态流。
3. 实现车辆状态页，展示关键数据。
4. 说明页面退出、应用后台和资源释放策略。
5. 记录后续 Hybrid/RN/Flutter 调用车辆数据的可能方式。

## 验收标准

- 车辆状态可以展示并刷新。
- 数据更新不会阻塞 UI。
- 页面退出或应用后台时资源释放策略明确。
- 跨平台方案需要说明其与原生 Service 的通信方式。

## 对照思考

- Hybrid 应该通过 JavaScript Bridge 订阅车辆数据，还是只请求快照。
- RN Native Module 和 Flutter Platform Channel 如何处理持续数据流。
- AIDL 或系统服务接入时，跨平台层应该暴露多大能力范围。
