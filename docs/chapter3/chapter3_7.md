# 第3.7节：Android Service

> [!CAUTION]
> ⚠️ Alpha内测版本警告：此为早期内部构建版本，课程文档、实验工程和评测口径仍在持续完善，欢迎大家提Issue反馈问题或建议。

或者

> [!WARNING]
> 🧪 Beta公测版本提示：教程主体完成后，将进入细节优化、实验验证和反馈修订阶段，欢迎大家提Issue反馈问题或建议。

本节使用 Android Service 或等价模拟器生成车辆状态数据。它是后续跨平台方案接入原生能力的重要对照点。

## 本节产物

完成本节后，应用应能展示一组会变化的模拟车辆数据：

- 车辆状态页展示电量、续航、胎压、车门、空调、车速。
- 数据可以来自 Android Service，也可以来自等价的本地模拟器。
- 页面通过 Repository 订阅状态，不直接依赖 Service。
- 文档中说明如果换成 Hybrid、RN、Flutter，应如何暴露这组能力。

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

如果核心版本暂不实现真正的 Android Service，也可以先用等价模拟器，但需要在文档中说明和真实 Service 的差异。

## 最小模拟器

如果本节先不引入真实 Service，可以用 Flow 模拟周期性车辆状态。

```kotlin
class VehicleSimulator(
    private val samples: List<VehicleStatus>
) {
    fun observe(): Flow<VehicleStatus> = flow {
        var index = 0
        while (true) {
            emit(samples[index % samples.size])
            index += 1
            delay(1000)
        }
    }
}
```

这种写法适合作为课堂第一版：学习者能立即看到数据刷新，也能清楚地区分“车辆数据生产者”和“页面展示者”。后续再把 `VehicleSimulator` 替换为真实 `Service`、绑定服务、广播、AIDL 或系统接口。

## Service 版本关注点

| 问题 | 说明 |
| --- | --- |
| 启动方式 | 前台 Service、绑定 Service 或应用内模拟器 |
| 订阅方式 | Flow、回调、Broadcast、Binder 或 AIDL |
| 生命周期 | 页面退出、应用后台、进程被杀后的行为 |
| 权限 | 是否涉及系统权限、后台运行限制 |
| 跨平台暴露 | Bridge、Native Module、Platform Channel 或共享 Repository |

## 动手路径

1. 先从 `shared-assets/vehicle/status-samples.json` 读取样例。
2. 实现 `VehicleSimulator` 或 `VehicleService`。
3. `VehicleRepository` 对 UI 暴露 `Flow<VehicleStatus>`。
4. 车辆状态页订阅 UI State 并展示数据。
5. 让数据每 1 秒或 2 秒刷新一次。
6. 记录页面退出和应用后台时的资源释放策略。

## 实验任务

1. 使用样例数据或定时器模拟车辆状态变化。
2. 建立 `VehicleRepository`，向 UI 提供状态流。
3. 实现车辆状态页，展示关键数据。
4. 说明页面退出、应用后台和资源释放策略。
5. 记录后续 Hybrid/RN/Flutter 调用车辆数据的可能方式。

## 阶段挑战

- 当胎压低于阈值时，在页面中显示提醒状态。
- 暂停页面后停止 UI 层刷新，返回页面后继续展示最新状态。
- 设计一个“跨平台只读车辆状态接口”，说明哪些字段允许暴露给 Web/RN/Flutter。

## 完成后复盘

- 车辆状态可以展示并刷新。
- 数据更新不会阻塞 UI。
- 页面退出或应用后台时资源释放策略明确。
- 跨平台方案需要说明其与原生 Service 的通信方式。

## 对照思考

- Hybrid 应该通过 JavaScript Bridge 订阅车辆数据，还是只请求快照。
- RN Native Module 和 Flutter Platform Channel 如何处理持续数据流。
- AIDL 或系统服务接入时，跨平台层应该暴露多大能力范围。
