package dev.datawhale.crossplatform.nativeandroid.core.data

import dev.datawhale.crossplatform.nativeandroid.core.model.LocalizedText
import dev.datawhale.crossplatform.nativeandroid.core.model.ManualArticle
import dev.datawhale.crossplatform.nativeandroid.core.model.ManualCategory
import dev.datawhale.crossplatform.nativeandroid.core.model.TirePressure
import dev.datawhale.crossplatform.nativeandroid.core.model.VehicleStatus

object SampleCockpitData {
    val categories = listOf(
        ManualCategory(
            id = "quick-start",
            title = LocalizedText("快速开始", "Quick Start"),
            order = 10
        ),
        ManualCategory(
            id = "climate",
            title = LocalizedText("空调与座椅", "Climate and Seats"),
            order = 20
        ),
        ManualCategory(
            id = "charging",
            title = LocalizedText("充电与能耗", "Charging and Energy"),
            order = 30
        ),
        ManualCategory(
            id = "driver-assist",
            title = LocalizedText("驾驶辅助", "Driver Assistance"),
            order = 40
        )
    )

    val articles = listOf(
        ManualArticle(
            id = "quick-start-cockpit",
            categoryId = "quick-start",
            title = LocalizedText("认识智能座舱", "Explore the Smart Cockpit"),
            summary = LocalizedText(
                "了解中控屏、仪表区、快捷入口和常用车辆状态。",
                "Learn about the center display, cluster area, shortcuts, and common vehicle status."
            ),
            body = listOf(
                LocalizedText(
                    "智能座舱首页提供车辆状态、常用控制、电子手册和提醒入口。",
                    "The smart cockpit home screen provides vehicle status, common controls, manual access, and alerts."
                ),
                LocalizedText(
                    "本 Demo 先把目录、详情、搜索和车辆数据跑通，后续章节再逐步替换为真实数据层和缓存。",
                    "This demo first connects manual browsing, search, and vehicle data, then later chapters replace the sample data with real repositories and cache."
                )
            ),
            tags = listOf("cockpit", "manual", "home")
        ),
        ManualArticle(
            id = "climate-auto-mode",
            categoryId = "climate",
            title = LocalizedText("自动空调模式", "Automatic Climate Mode"),
            summary = LocalizedText(
                "了解如何开启并调节自动空调。",
                "Learn how to enable and adjust automatic climate control."
            ),
            body = listOf(
                LocalizedText(
                    "点击中控屏底部的空调入口，进入空调控制面板。",
                    "Tap the climate entry at the bottom of the center display."
                ),
                LocalizedText(
                    "开启自动模式后，系统会根据车内温度自动调节风量和出风方向。",
                    "After automatic mode is enabled, the system adjusts fan speed and airflow direction based on cabin temperature."
                )
            ),
            tags = listOf("climate", "seat", "auto")
        ),
        ManualArticle(
            id = "charging-range",
            categoryId = "charging",
            title = LocalizedText("查看续航与充电", "Check Range and Charging"),
            summary = LocalizedText(
                "查看当前电量、预计续航和充电提醒。",
                "Check battery level, estimated range, and charging reminders."
            ),
            body = listOf(
                LocalizedText(
                    "车辆状态页会展示电量、续航和充电相关提醒，帮助驾驶者判断是否需要补能。",
                    "The vehicle page displays battery, range, and charging reminders to help drivers decide when to recharge."
                ),
                LocalizedText(
                    "后续跨平台版本应使用同一份车辆状态字段，便于比较桥接和刷新成本。",
                    "Later cross-platform versions should use the same vehicle status fields to compare bridge and refresh costs."
                )
            ),
            tags = listOf("charging", "battery", "range")
        ),
        ManualArticle(
            id = "driver-assist-warning",
            categoryId = "driver-assist",
            title = LocalizedText("驾驶辅助提醒", "Driver Assistance Alerts"),
            summary = LocalizedText(
                "理解车道、车门和胎压提醒在页面中的展示方式。",
                "Understand how lane, door, and tire pressure alerts are displayed."
            ),
            body = listOf(
                LocalizedText(
                    "当车辆数据出现异常时，页面应显示明确提醒，而不是只改变颜色。",
                    "When vehicle data becomes abnormal, the page should show a clear alert instead of relying only on color."
                ),
                LocalizedText(
                    "跨平台页面接入此能力时，要说明是读取快照、订阅流，还是通过平台通道回调。",
                    "When cross-platform pages connect to this capability, clarify whether they read snapshots, subscribe to streams, or use platform channel callbacks."
                )
            ),
            tags = listOf("assist", "tire", "door", "warning")
        )
    )

    val vehicleStatuses = listOf(
        VehicleStatus(
            speedKph = 0,
            batteryPercent = 82,
            rangeKm = 430,
            tirePressure = TirePressure(2.5, 2.5, 2.4, 2.4),
            doorsClosed = true,
            climateEnabled = true,
            temperatureCelsius = 23
        ),
        VehicleStatus(
            speedKph = 32,
            batteryPercent = 81,
            rangeKm = 426,
            tirePressure = TirePressure(2.5, 2.4, 2.4, 2.4),
            doorsClosed = true,
            climateEnabled = true,
            temperatureCelsius = 22
        ),
        VehicleStatus(
            speedKph = 58,
            batteryPercent = 78,
            rangeKm = 398,
            tirePressure = TirePressure(2.2, 2.4, 2.3, 2.4),
            doorsClosed = true,
            climateEnabled = true,
            temperatureCelsius = 22
        )
    )
}
