package dev.datawhale.crossplatform.nativeandroid.core.model

data class LocalizedText(
    val zh: String,
    val en: String
) {
    fun value(language: AppLanguage): String = when (language) {
        AppLanguage.Zh -> zh
        AppLanguage.En -> en
    }
}

enum class AppLanguage {
    Zh,
    En
}

data class ManualCategory(
    val id: String,
    val title: LocalizedText,
    val order: Int
)

data class ManualArticle(
    val id: String,
    val categoryId: String,
    val title: LocalizedText,
    val summary: LocalizedText,
    val body: List<LocalizedText>,
    val tags: List<String>
) {
    fun matches(query: String): Boolean {
        val normalized = query.trim().lowercase()
        if (normalized.isEmpty()) return false

        val text = buildString {
            append(title.zh).append(' ')
            append(title.en).append(' ')
            append(summary.zh).append(' ')
            append(summary.en).append(' ')
            append(tags.joinToString(" "))
            body.forEach { block ->
                append(' ').append(block.zh).append(' ').append(block.en)
            }
        }.lowercase()

        return text.contains(normalized)
    }
}

data class VehicleStatus(
    val speedKph: Int,
    val batteryPercent: Int,
    val rangeKm: Int,
    val tirePressure: TirePressure,
    val doorsClosed: Boolean,
    val climateEnabled: Boolean,
    val temperatureCelsius: Int
)

data class TirePressure(
    val frontLeft: Double,
    val frontRight: Double,
    val rearLeft: Double,
    val rearRight: Double
) {
    val minValue: Double
        get() = minOf(frontLeft, frontRight, rearLeft, rearRight)
}

enum class DemoRoute(
    val zhTitle: String,
    val enTitle: String
) {
    Manual("手册", "Manual"),
    Search("搜索", "Search"),
    Vehicle("车辆", "Vehicle"),
    NativeCapability("原生能力", "Native"),
    Benchmarks("性能", "Benchmarks"),
    Settings("设置", "Settings");

    fun title(language: AppLanguage): String = when (language) {
        AppLanguage.Zh -> zhTitle
        AppLanguage.En -> enTitle
    }
}
