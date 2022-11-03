package com.hangrycoder.batterywidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import android.widget.LinearLayout
import android.widget.RemoteViews


class BatteryWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.battery_widget)

    val batteryManager = context.getSystemService(BATTERY_SERVICE) as BatteryManager
    val batteryRemaining = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

    views.setTextViewText(R.id.battery_value, "$batteryRemaining%")
    views.setImageViewResource(R.id.battery_icon, R.drawable.ic_phone)

    var batteryPercentageId = R.id.percentage_05

    when (batteryRemaining) {
        in 1..5 -> R.id.percentage_05
        in 6..10 -> R.id.percentage_10
        in 11..15 -> R.id.percentage_15
        in 16..20 -> R.id.percentage_20
        in 21..25 -> R.id.percentage_25
        in 26..30 -> R.id.percentage_30
        in 31..35 -> R.id.percentage_35
        in 36..40 -> R.id.percentage_40
        in 41..45 -> R.id.percentage_45
        in 46..50 -> R.id.percentage_50
        in 51..55 -> R.id.percentage_55
        in 56..60 -> R.id.percentage_60
        in 61..65 -> R.id.percentage_65
        in 66..70 -> R.id.percentage_70
        in 71..75 -> R.id.percentage_75
        in 76..80 -> R.id.percentage_80
        in 81..85 -> R.id.percentage_85
        in 86..90 -> R.id.percentage_90
        in 91..95 -> R.id.percentage_95
        in 96..97 -> R.id.percentage_97
        in 98..99 -> R.id.percentage_99
        in 96..100 -> R.id.percentage_100
    }


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}