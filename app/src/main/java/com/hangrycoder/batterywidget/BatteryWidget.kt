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
    /*views.setViewPadding(
        R.id.battery_percentage, 0, 0, 500, 0
    )*/

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}