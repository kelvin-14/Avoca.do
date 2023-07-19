package com.happymeerkat.avocado

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
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
    fun Context.updateAppWidgetThroughContext() {
        getListItemsJob?.cancel()
        getListItemsJob = listRepository
            .getAllListItems()
            .onEach {
                itemsList = it
                Log.d("WIDGET size got", itemsList.size.toString())
            }.launchIn(CoroutineScope(Dispatchers.IO))

        val component = ComponentName(this,
            ListWidget::class.java)
        with(AppWidgetManager.getInstance(this)) {
            val appWidgetIds = getAppWidgetIds(component)
            appWidgetIds.forEach { appWidgetId ->
                updateAppWidget(
                    context = this@updateAppWidgetThroughContext,
                    appWidgetManager = this,
                    appWidgetId = appWidgetId,
                    itemsList = itemsList
                )
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {


        // There may be multiple widgets active, so update all of them
        context.updateAppWidgetThroughContext()
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        newOptions?.let {
            context.updateAppWidgetThroughContext()
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (context != null) {
            context.updateAppWidgetThroughContext()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    itemsList: List<ListItem>
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.list_widget)
    CoroutineScope(Dispatchers.Main).launch {
        Log.d("WIDGET list item SIZE", itemsList.size.toString())
        setImprovisedAdapter(itemsList, views, context)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}



@RequiresApi(Build.VERSION_CODES.S)
fun setImprovisedAdapter(listItems: List<ListItem>, views: RemoteViews, context: Context) {
    val collection = RemoteViews.RemoteCollectionItems.Builder()

    listItems.forEach {listItem ->
        Log.d("WIDGET list item", listItem.title)
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
