package com.hangrycoder.batterywidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import android.view.View
import android.widget.LinearLayout
import android.widget.RemoteViews
import androidx.annotation.IdRes


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

    batteryRemaining.apply {
        views.setVisibility(R.id.percentage_05, this in 1..5)
        views.setVisibility(R.id.percentage_10, this in 6..10)
        views.setVisibility(R.id.percentage_15, this in 11..15)
        views.setVisibility(R.id.percentage_20, this in 16..20)
        views.setVisibility(R.id.percentage_25, this in 21..25)
        views.setVisibility(R.id.percentage_30, this in 26..30)
        views.setVisibility(R.id.percentage_35, this in 31..35)
        views.setVisibility(R.id.percentage_40, this in 36..40)
        views.setVisibility(R.id.percentage_45, this in 41..45)
        views.setVisibility(R.id.percentage_50, this in 46..50)
        views.setVisibility(R.id.percentage_55, this in 51..55)
        views.setVisibility(R.id.percentage_60, this in 56..60)
        views.setVisibility(R.id.percentage_65, this in 61..65)
        views.setVisibility(R.id.percentage_70, this in 66..70)
        views.setVisibility(R.id.percentage_75, this in 71..75)
        views.setVisibility(R.id.percentage_80, this in 76..80)
        views.setVisibility(R.id.percentage_85, this in 81..85)
        views.setVisibility(R.id.percentage_90, this in 86..90)
        views.setVisibility(R.id.percentage_95, this in 91..95)
        views.setVisibility(R.id.percentage_97, this in 96..97)
        views.setVisibility(R.id.percentage_99, this in 98..99)
        views.setVisibility(R.id.percentage_100, this == 100)
    }

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun RemoteViews.setVisibility(@IdRes viewId: Int, show: Boolean) {
    this.setViewVisibility(
        viewId, if (show) View.VISIBLE else View.INVISIBLE
    )

}