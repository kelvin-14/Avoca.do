package com.happymeerkat.avocado

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class ListWidget : AppWidgetProvider() {
    @Inject lateinit var listRepository: ListRepository
    var getListItemsJob: Job? = null
    var itemsList: List<ListItem> = emptyList()
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        Log.d("WIDGET initial update", "a")
        getListItemsJob?.cancel()
        getListItemsJob = listRepository
            .getAllListItems()
            .onEach {itemsList ->
                updateAppWidget(
                    context,
                    appWidgetManager,
                    appWidgetIds,
                    itemsList
                )
                Log.d("WIDGET size got", itemsList.size.toString())
            }.launchIn(CoroutineScope(Dispatchers.IO))


    }
}

@RequiresApi(Build.VERSION_CODES.S)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetIds: IntArray,
    itemsList: List<ListItem>
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.list_widget)

    for (appWidgetId in appWidgetIds) {
        CoroutineScope(Dispatchers.Main).launch {
            setImprovisedAdapter(itemsList, views, context)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun setImprovisedAdapter(listItems: List<ListItem>, views: RemoteViews, context: Context) {
    val collection = RemoteViews.RemoteCollectionItems.Builder()

    listItems.forEach {listItem ->
        val itemView = RemoteViews(context.packageName, R.layout.list_item)
        itemView.setTextViewText(R.id.item_title, listItem.title)
        collection.addItem(
            listItem.id.toLong(),
            itemView
        )
    }

    collection
        .setViewTypeCount(listItems.size)

    views.setRemoteAdapter(R.id.scroll, collection.build())
}


@RequiresApi(Build.VERSION_CODES.S)
fun Context.updateAppWidgetThroughContext(itemsList: List<ListItem>) {
    val component = ComponentName(this,
        ListWidget::class.java)
    with(AppWidgetManager.getInstance(this)) {
        val appWidgetIds = getAppWidgetIds(component)
        updateAppWidget(
            context = this@updateAppWidgetThroughContext,
            appWidgetManager = this,
            appWidgetIds = appWidgetIds,
            itemsList = itemsList
        )
    }
}