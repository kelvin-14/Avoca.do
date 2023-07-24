package com.happymeerkat.avocado

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.happymeerkat.avocado.domain.model.ListItem
import com.happymeerkat.avocado.domain.repository.ListRepository
import com.happymeerkat.avocado.notification.WidgetCheckboxBroadcastReceiver
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
        getListItemsJob?.cancel()
        getListItemsJob = listRepository
            .getAllListItems()
            .onEach { itemsList ->
                updateAppWidget(
                    context,
                    appWidgetManager,
                    appWidgetIds,
                    itemsList
                )
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
    views.removeAllViews(R.id.scroll)

    for (appWidgetId in appWidgetIds) {
        CoroutineScope(Dispatchers.Main).launch {
            setImprovisedAdapter(itemsList, views, context)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

@SuppressLint("RemoteViewLayout")
@RequiresApi(Build.VERSION_CODES.S)
fun setImprovisedAdapter(listItems: List<ListItem>, views: RemoteViews, context: Context) {
    val collection = RemoteViews.RemoteCollectionItems.Builder()

    //val flags = PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    val pendingIntent = PendingIntent
        .getBroadcast(
            context,
            0,
            Intent(
                context,
                WidgetCheckboxBroadcastReceiver::class.java
            ),
            PendingIntent.FLAG_MUTABLE
        )

    views.setPendingIntentTemplate(
        R.id.scroll,
        pendingIntent
    )

    listItems.forEach { listItem ->
        val intent = Intent(context, WidgetCheckboxBroadcastReceiver::class.java)
            .setAction(WidgetCheckboxBroadcastReceiver.ACTION_CHECK_BOX_CHANGE)
            .putExtra(
                WidgetCheckboxBroadcastReceiver.ITEM_ID,
                listItem.id
            )

        val listItemRowDetails = if (listItem.completed == true) {
            RemoteViews(context.packageName, R.layout.list_item_row_details_done)
        } else {
            RemoteViews(context.packageName, R.layout.list_item_row_details)
        }

        listItemRowDetails.setTextViewText(R.id.list_item_title, listItem.title)

        val listItemView = RemoteViews(context.packageName, R.layout.list_item)
            .apply {
                setOnCheckedChangeResponse(
                    R.id.list_item_checkbox,
                    RemoteViews.RemoteResponse.fromFillInIntent(intent)
                )
            }

        listItemView.addView(R.id.list_item, listItemRowDetails)
        listItemView.setCompoundButtonChecked(R.id.list_item_checkbox, listItem.completed)


        collection.addItem(
            listItem.id.toLong(),
            listItemView
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