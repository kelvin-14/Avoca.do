package com.happymeerkat.avocado

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class ListWidget : AppWidgetProvider() {
    @Inject lateinit var listRepository: ListRepository
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        Log.d("WIDGET initial update", "a")
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, listRepository)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@RequiresApi(Build.VERSION_CODES.S)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    listRepository: ListRepository
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.list_widget)

    CoroutineScope(Dispatchers.IO).launch {
        val listItemsFlow = listOf<ListItem>(
            ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),
            ListItem(id = 3, title = "some item 3", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),
            ListItem(id = 3, title = "some item 3", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),
            ListItem(id = 3, title = "some item 3", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),
            ListItem(id = 3, title = "some item 3", completed = false),ListItem(id = 1, title = "some item 1", completed = false),
            ListItem(id = 2, title = "some item 2", completed = false),
        )
        CoroutineScope(Dispatchers.Main).launch {
            setImprovisedAdapter(listItemsFlow, views, context)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun setImprovisedAdapter(listItems: List<ListItem>, views: RemoteViews, context: Context) {
    val collection = RemoteViews.RemoteCollectionItems.Builder()

    listItems.forEach {listItem ->
        val itemView = RemoteViews(context.packageName, R.layout.other)
        itemView.setTextViewText(R.id.textView, listItem.title)
        collection.addItem(
            listItem.id.toLong(),
            itemView
        )
    }

    collection
        .setViewTypeCount(listItems.size)

    views.setRemoteAdapter(R.id.scroll, collection.build())
}
