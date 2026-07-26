@file:OptIn(ExperimentalMaterial3Api::class)

package dev.datawhale.crossplatform.nativeandroid.feature.cockpit

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.datawhale.crossplatform.nativeandroid.core.data.SampleCockpitData
import dev.datawhale.crossplatform.nativeandroid.core.model.AppLanguage
import dev.datawhale.crossplatform.nativeandroid.core.model.DemoRoute
import dev.datawhale.crossplatform.nativeandroid.core.model.ManualArticle
import dev.datawhale.crossplatform.nativeandroid.core.model.ManualCategory
import dev.datawhale.crossplatform.nativeandroid.core.model.VehicleStatus
import kotlinx.coroutines.delay

@Composable
fun CockpitManualDemoApp() {
    var language by remember { mutableStateOf(AppLanguage.Zh) }
    var darkTheme by remember { mutableStateOf(false) }
    var offlineMode by remember { mutableStateOf(false) }
    var route by remember { mutableStateOf(DemoRoute.Manual) }
    var selectedCategoryId by remember { mutableStateOf(SampleCockpitData.categories.first().id) }
    var selectedArticleId by remember { mutableStateOf(SampleCockpitData.articles.first().id) }
    var query by remember { mutableStateOf("") }
    var favoriteIds by remember { mutableStateOf(setOf<String>()) }
    var historyIds by remember { mutableStateOf(listOf<String>()) }
    var chosenFile by remember { mutableStateOf<Uri?>(null) }
    var benchmarkStart by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }
    var manualSwitchCount by remember { mutableIntStateOf(0) }
    var vehicleIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1600)
            vehicleIndex = (vehicleIndex + 1) % SampleCockpitData.vehicleStatuses.size
        }
    }

    val selectedArticle = SampleCockpitData.articles
        .firstOrNull { it.id == selectedArticleId }
        ?: SampleCockpitData.articles.first()

    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints {
                val compact = maxWidth < 760.dp
                Column(modifier = Modifier.fillMaxSize()) {
                    DemoTopBar(
                        language = language,
                        darkTheme = darkTheme,
                        offlineMode = offlineMode,
                        onToggleLanguage = {
                            language = if (language == AppLanguage.Zh) AppLanguage.En else AppLanguage.Zh
                        },
                        onToggleTheme = { darkTheme = !darkTheme },
                        onToggleOffline = { offlineMode = !offlineMode }
                    )
                    if (compact) {
                        CompactNavigation(
                            route = route,
                            language = language,
                            onSelect = { route = it }
                        )
                    }
                    Row(modifier = Modifier.fillMaxSize()) {
                        if (!compact) {
                            RouteRail(
                                route = route,
                                language = language,
                                onSelect = { route = it }
                            )
                        }
                        DemoContent(
                            compact = compact,
                            route = route,
                            language = language,
                            offlineMode = offlineMode,
                            selectedCategoryId = selectedCategoryId,
                            selectedArticle = selectedArticle,
                            query = query,
                            favoriteIds = favoriteIds,
                            historyIds = historyIds,
                            chosenFile = chosenFile,
                            vehicleStatus = SampleCockpitData.vehicleStatuses[vehicleIndex],
                            benchmarkStart = benchmarkStart,
                            manualSwitchCount = manualSwitchCount,
                            onSelectCategory = { categoryId ->
                                selectedCategoryId = categoryId
                                SampleCockpitData.articles.firstOrNull { it.categoryId == categoryId }?.let {
                                    selectedArticleId = it.id
                                    historyIds = (listOf(it.id) + historyIds).distinct().take(6)
                                    manualSwitchCount += 1
                                }
                            },
                            onSelectArticle = { article ->
                                selectedArticleId = article.id
                                selectedCategoryId = article.categoryId
                                historyIds = (listOf(article.id) + historyIds).distinct().take(6)
                                manualSwitchCount += 1
                                route = DemoRoute.Manual
                            },
                            onQueryChange = { query = it },
                            onToggleFavorite = { articleId ->
                                favoriteIds = if (articleId in favoriteIds) {
                                    favoriteIds - articleId
                                } else {
                                    favoriteIds + articleId
                                }
                            },
                            onFileChosen = { chosenFile = it },
                            onResetBench = {
                                benchmarkStart = SystemClock.elapsedRealtime()
                                manualSwitchCount = 0
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Cockpit Manual Demo - Landscape",
    showBackground = true,
    widthDp = 1280,
    heightDp = 720
)
@Composable
private fun CockpitManualDemoLandscapePreview() {
    CockpitManualDemoApp()
}

@Preview(
    name = "Cockpit Manual Demo - Phone",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun CockpitManualDemoPhonePreview() {
    CockpitManualDemoApp()
}

@Composable
private fun DemoTopBar(
    language: AppLanguage,
    darkTheme: Boolean,
    offlineMode: Boolean,
    onToggleLanguage: () -> Unit,
    onToggleTheme: () -> Unit,
    onToggleOffline: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = if (language == AppLanguage.Zh) "跨平台智能座舱电子手册" else "Cross-platform Cockpit Manual",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (language == AppLanguage.Zh) "Android 原生基准 Demo" else "Android native baseline demo",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(
                onClick = onToggleLanguage,
                label = { Text(if (language == AppLanguage.Zh) "EN" else "中文") }
            )
            AssistChip(
                onClick = onToggleTheme,
                label = { Text(if (darkTheme) "Light" else "Dark") }
            )
            AssistChip(
                onClick = onToggleOffline,
                label = { Text(if (offlineMode) "Offline" else "Online") }
            )
        }
    }
}

@Composable
private fun RouteRail(
    route: DemoRoute,
    language: AppLanguage,
    onSelect: (DemoRoute) -> Unit
) {
    Column(
        modifier = Modifier
            .width(168.dp)
            .fillMaxHeight()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DemoRoute.entries.forEach { item ->
            FilterChip(
                selected = item == route,
                onClick = { onSelect(item) },
                label = { Text(item.title(language)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CompactNavigation(
    route: DemoRoute,
    language: AppLanguage,
    onSelect: (DemoRoute) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DemoRoute.entries.forEach { item ->
            FilterChip(
                selected = item == route,
                onClick = { onSelect(item) },
                label = { Text(item.title(language)) }
            )
        }
    }
}

@Composable
private fun DemoContent(
    compact: Boolean,
    route: DemoRoute,
    language: AppLanguage,
    offlineMode: Boolean,
    selectedCategoryId: String,
    selectedArticle: ManualArticle,
    query: String,
    favoriteIds: Set<String>,
    historyIds: List<String>,
    chosenFile: Uri?,
    vehicleStatus: VehicleStatus,
    benchmarkStart: Long,
    manualSwitchCount: Int,
    onSelectCategory: (String) -> Unit,
    onSelectArticle: (ManualArticle) -> Unit,
    onQueryChange: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onFileChosen: (Uri?) -> Unit,
    onResetBench: () -> Unit
) {
    when (route) {
        DemoRoute.Manual -> ManualScreen(
            compact = compact,
            language = language,
            categories = SampleCockpitData.categories,
            articles = SampleCockpitData.articles,
            selectedCategoryId = selectedCategoryId,
            selectedArticle = selectedArticle,
            favoriteIds = favoriteIds,
            historyIds = historyIds,
            onSelectCategory = onSelectCategory,
            onToggleFavorite = onToggleFavorite
        )

        DemoRoute.Search -> SearchScreen(
            language = language,
            query = query,
            favoriteIds = favoriteIds,
            onQueryChange = onQueryChange,
            onSelectArticle = onSelectArticle
        )

        DemoRoute.Vehicle -> VehicleScreen(
            language = language,
            vehicleStatus = vehicleStatus
        )

        DemoRoute.NativeCapability -> NativeCapabilityScreen(
            language = language,
            chosenFile = chosenFile,
            onFileChosen = onFileChosen
        )

        DemoRoute.Benchmarks -> BenchmarkScreen(
            language = language,
            offlineMode = offlineMode,
            benchmarkStart = benchmarkStart,
            manualSwitchCount = manualSwitchCount,
            onResetBench = onResetBench
        )

        DemoRoute.Settings -> SettingsScreen(
            language = language,
            favoriteIds = favoriteIds,
            historyIds = historyIds
        )
    }
}

@Composable
private fun ManualScreen(
    compact: Boolean,
    language: AppLanguage,
    categories: List<ManualCategory>,
    articles: List<ManualArticle>,
    selectedCategoryId: String,
    selectedArticle: ManualArticle,
    favoriteIds: Set<String>,
    historyIds: List<String>,
    onSelectCategory: (String) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    if (compact) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                DirectoryPane(
                    language = language,
                    categories = categories,
                    selectedCategoryId = selectedCategoryId,
                    onSelectCategory = onSelectCategory
                )
            }
            item {
                ArticleDetailPane(
                    language = language,
                    article = selectedArticle,
                    favorite = selectedArticle.id in favoriteIds,
                    onToggleFavorite = onToggleFavorite
                )
            }
            item {
                HistoryPane(language = language, historyIds = historyIds, articles = articles)
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DirectoryPane(
                language = language,
                categories = categories,
                selectedCategoryId = selectedCategoryId,
                onSelectCategory = onSelectCategory,
                modifier = Modifier
                    .width(260.dp)
                    .fillMaxHeight()
            )
            ArticleDetailPane(
                language = language,
                article = selectedArticle,
                favorite = selectedArticle.id in favoriteIds,
                onToggleFavorite = onToggleFavorite,
                modifier = Modifier.weight(1f)
            )
            HistoryPane(
                language = language,
                historyIds = historyIds,
                articles = articles,
                modifier = Modifier.width(220.dp)
            )
        }
    }
}

@Composable
private fun DirectoryPane(
    language: AppLanguage,
    categories: List<ManualCategory>,
    selectedCategoryId: String,
    onSelectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionTitle(if (language == AppLanguage.Zh) "目录" else "Directory")
        categories.sortedBy { it.order }.forEach { category ->
            val selected = category.id == selectedCategoryId
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectCategory(category.id) },
                shape = RoundedCornerShape(8.dp),
                color = if (selected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surface
                },
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Text(
                    text = category.title.value(language),
                    modifier = Modifier.padding(14.dp),
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
private fun ArticleDetailPane(
    language: AppLanguage,
    article: ManualArticle,
    favorite: Boolean,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = article.title.value(language),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = article.summary.value(language),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(onClick = { onToggleFavorite(article.id) }) {
                    Text(if (favorite) "★" else "☆")
                }
            }
        }
        items(article.body) { block ->
            Text(
                text = block.value(language),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            ImagePlaceholder(language = language)
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                article.tags.take(4).forEach { tag ->
                    AssistChip(onClick = {}, label = { Text(tag) })
                }
            }
        }
    }
}

@Composable
private fun HistoryPane(
    language: AppLanguage,
    historyIds: List<String>,
    articles: List<ManualArticle>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionTitle(if (language == AppLanguage.Zh) "浏览历史" else "History")
        if (historyIds.isEmpty()) {
            EmptyHint(if (language == AppLanguage.Zh) "点击目录后会出现浏览记录" else "Open articles to build history")
        } else {
            historyIds.forEach { id ->
                val article = articles.firstOrNull { it.id == id }
                if (article != null) {
                    Text(
                        text = article.title.value(language),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
                            .padding(10.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchScreen(
    language: AppLanguage,
    query: String,
    favoriteIds: Set<String>,
    onQueryChange: (String) -> Unit,
    onSelectArticle: (ManualArticle) -> Unit
) {
    val results = remember(query) {
        SampleCockpitData.articles.filter { it.matches(query) }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionTitle(if (language == AppLanguage.Zh) "全局搜索" else "Global Search")
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text(if (language == AppLanguage.Zh) "输入关键词：胎压、空调、charging" else "Try tire, climate, charging") }
            )
        }
        if (query.isBlank()) {
            item {
                EmptyHint(if (language == AppLanguage.Zh) "输入关键词后查看搜索结果" else "Type a keyword to search the manual")
            }
        } else if (results.isEmpty()) {
            item {
                EmptyHint(if (language == AppLanguage.Zh) "没有匹配结果" else "No matching results")
            }
        } else {
            items(results) { article ->
                Card(
                    onClick = { onSelectArticle(article) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = article.title.value(language),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(if (article.id in favoriteIds) "★" else "")
                        }
                        Text(article.summary.value(language))
                        Text(
                            text = article.tags.joinToString(prefix = "#", separator = " #"),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun VehicleScreen(
    language: AppLanguage,
    vehicleStatus: VehicleStatus
) {
    val lowTire = vehicleStatus.tirePressure.minValue < 2.3
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionTitle(if (language == AppLanguage.Zh) "模拟车辆数据" else "Simulated Vehicle Data")
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetricCard("Speed", "${vehicleStatus.speedKph} km/h", Modifier.weight(1f))
                MetricCard("Battery", "${vehicleStatus.batteryPercent}%", Modifier.weight(1f))
                MetricCard("Range", "${vehicleStatus.rangeKm} km", Modifier.weight(1f))
            }
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.Zh) "胎压与车身状态" else "Tire and Body Status",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text("FL ${vehicleStatus.tirePressure.frontLeft} / FR ${vehicleStatus.tirePressure.frontRight}")
                    Text("RL ${vehicleStatus.tirePressure.rearLeft} / RR ${vehicleStatus.tirePressure.rearRight}")
                    Text(if (vehicleStatus.doorsClosed) "Doors closed" else "Door warning")
                    Text("Climate ${if (vehicleStatus.climateEnabled) "on" else "off"} · ${vehicleStatus.temperatureCelsius}°C")
                    if (lowTire) {
                        Text(
                            text = if (language == AppLanguage.Zh) "提醒：胎压偏低，请检查车辆状态。" else "Alert: low tire pressure detected.",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        item {
            EmptyHint(if (language == AppLanguage.Zh) "数据每 1.6 秒刷新一次，用于模拟 Service 或车辆数据流。" else "Data refreshes every 1.6s to simulate a service-backed vehicle stream.")
        }
    }
}

@Composable
private fun NativeCapabilityScreen(
    language: AppLanguage,
    chosenFile: Uri?,
    onFileChosen: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val filePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        onFileChosen(uri)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionTitle(if (language == AppLanguage.Zh) "原生能力入口" else "Native Capability Entry")
        }
        item {
            CapabilityCard(
                title = if (language == AppLanguage.Zh) "文件选择" else "File Picker",
                description = if (language == AppLanguage.Zh) {
                    "调用 Android 系统文件选择器，并回显所选 URI。"
                } else {
                    "Open the Android system file picker and display the selected URI."
                },
                action = if (language == AppLanguage.Zh) "选择文件" else "Pick file",
                onClick = { filePicker.launch(arrayOf("*/*")) }
            )
        }
        item {
            Text(
                text = chosenFile?.toString()
                    ?: if (language == AppLanguage.Zh) "尚未选择文件" else "No file selected",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        item {
            CapabilityCard(
                title = if (language == AppLanguage.Zh) "本地通知" else "Local Notification",
                description = if (language == AppLanguage.Zh) {
                    "课堂第一版先用 Toast 模拟通知入口，后续替换为 NotificationManager。"
                } else {
                    "The first lab uses Toast as the notification entry, then replaces it with NotificationManager."
                },
                action = if (language == AppLanguage.Zh) "发送提醒" else "Send alert",
                onClick = {
                    Toast.makeText(context, "胎压偏低，请检查车辆状态", Toast.LENGTH_SHORT).show()
                }
            )
        }
        item {
            CapabilityCard(
                title = if (language == AppLanguage.Zh) "分享当前手册" else "Share Manual",
                description = if (language == AppLanguage.Zh) {
                    "通过 Android 分享面板观察原生 Intent 调用边界。"
                } else {
                    "Use the Android share sheet to observe native Intent boundaries."
                },
                action = if (language == AppLanguage.Zh) "打开分享" else "Open share",
                onClick = {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Android native baseline demo")
                    }
                    context.startActivity(Intent.createChooser(sendIntent, "Share demo"))
                }
            )
        }
    }
}

@Composable
private fun BenchmarkScreen(
    language: AppLanguage,
    offlineMode: Boolean,
    benchmarkStart: Long,
    manualSwitchCount: Int,
    onResetBench: () -> Unit
) {
    val elapsedSeconds = (SystemClock.elapsedRealtime() - benchmarkStart) / 1000
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionTitle(if (language == AppLanguage.Zh) "性能记录入口" else "Benchmark Entry")
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetricCard("Session", "${elapsedSeconds}s", Modifier.weight(1f))
                MetricCard("Switches", manualSwitchCount.toString(), Modifier.weight(1f))
                MetricCard("Mode", if (offlineMode) "Offline" else "Online", Modifier.weight(1f))
            }
        }
        item {
            Text(
                text = if (language == AppLanguage.Zh) {
                    "课堂中可以用本页统一操作路径：启动应用、切换目录、搜索关键词、进入车辆页观察刷新，再把数据写入 benchmarks/。"
                } else {
                    "Use this page to standardize actions: launch, switch manuals, search, observe vehicle refresh, then record data in benchmarks/."
                }
            )
        }
        item {
            Button(onClick = onResetBench) {
                Text(if (language == AppLanguage.Zh) "重新开始手动记录" else "Restart manual record")
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    language: AppLanguage,
    favoriteIds: Set<String>,
    historyIds: List<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionTitle(if (language == AppLanguage.Zh) "设置与状态快照" else "Settings Snapshot")
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(if (language == AppLanguage.Zh) "离线模式、语言、主题在顶部切换" else "Offline, language, and theme are toggled in the top bar")
                Switch(checked = favoriteIds.isNotEmpty(), onCheckedChange = {}, enabled = false)
            }
        }
        item {
            Text(if (language == AppLanguage.Zh) "收藏数量：${favoriteIds.size}" else "Favorites: ${favoriteIds.size}")
            Text(if (language == AppLanguage.Zh) "历史数量：${historyIds.size}" else "History: ${historyIds.size}")
        }
    }
}

@Composable
private fun CapabilityCard(
    title: String,
    description: String,
    action: String,
    onClick: () -> Unit
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(description)
            }
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedButton(onClick = onClick) {
                Text(action)
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ImagePlaceholder(language: AppLanguage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (language == AppLanguage.Zh) "图片占位：cockpit-overview.png" else "Image placeholder: cockpit-overview.png",
            fontWeight = FontWeight.Bold
        )
        Text(if (language == AppLanguage.Zh) "第 3.6 节可替换为真实图片缓存" else "Replace with real image cache in section 3.6")
    }
}

@Composable
private fun EmptyHint(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
            .padding(14.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
