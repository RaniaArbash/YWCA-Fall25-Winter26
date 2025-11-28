package com.example.jan6_project2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jan6_project2.StudentHubViewModel
import com.example.jan6_project2.NotificationClass
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NotificationsScreen(
    viewModel: StudentHubViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val student by viewModel.currentStudent
    val notifications = student?.notificationList ?: emptyList()

    Column(modifier = modifier.fillMaxSize()) {
        // Custom top bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer,
            shadowElevation = 4.dp
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Content
        if (notifications.isEmpty()) {
            EmptyNotificationsView()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(
                    items = notifications,
                    key = { index, _ -> index }
                ) { index, notification ->
                    NotificationItem(
                        notification = notification,
                        onMarkAsRead = { viewModel.markNotificationAsRead(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationItem(
    notification: NotificationClass,
    onMarkAsRead: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = !notification.isRead) { onMarkAsRead() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (notification.isRead) 1.dp else 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Unread indicator
            if (!notification.isRead) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50)
                        )
                        .align(Alignment.Top)
                )
            } else {
                Spacer(modifier = Modifier.width(8.dp))
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (notification.isRead) {
                        FontWeight.Normal
                    } else {
                        FontWeight.SemiBold
                    },
                    color = if (notification.isRead) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    }
                )

                Text(
                    text = formatDate(notification.date),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (notification.isRead) {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    } else {
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    }
                )
            }
        }
    }
}

@Composable
fun EmptyNotificationsView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
            )
            Text(
                text = "No notifications",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    }
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("MMM dd, yyyy 'at' h:mm a", Locale.getDefault())
    return formatter.format(date)
}

// Sample notification data for testing
//fun getSampleNotifications(): List<NotificationClass> {
//    val calendar = Calendar.getInstance()
//
//    return listOf(
//        NotificationClass(
//            message = "Your assignment 'Introduction to Kotlin' is due tomorrow at 11:59 PM.",
//            date = calendar.apply { add(Calendar.HOUR, -1) }.time,
//            isRead = false
//        ),
//        NotificationClass(
//            message = "New grades have been posted for 'Mobile App Development'.",
//            date = calendar.apply { add(Calendar.HOUR, -1) }.time,
//            isRead = false
//        ),
//        NotificationClass(
//            message = "Reminder: Office hours today from 2:00 PM - 4:00 PM in Room 305.",
//            date = calendar.apply { add(Calendar.HOUR, -2) }.time,
//            isRead = true
//        ),
//        NotificationClass(
//            message = "Class cancelled: Android Development lecture on Friday has been rescheduled.",
//            date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
//            isRead = false
//        ),
//        NotificationClass(
//            message = "New course material uploaded: Week 5 - Jetpack Compose Basics.",
//            date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
//            isRead = true
//        ),
//        NotificationClass(
//            message = "Your project submission 'Student Portal App' has been received.",
//            date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
//            isRead = true
//        ),
//        NotificationClass(
//            message = "Upcoming exam: Midterm exam scheduled for December 5th. Review sessions available.",
//            date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
//            isRead = false
//        ),
//        NotificationClass(
//            message = "Group project teams have been assigned. Check your email for details.",
//            date = calendar.apply { add(Calendar.DAY_OF_MONTH, -1) }.time,
//            isRead = true
//        )
//    )
//}
//
//// PREVIEW FUNCTIONS
//@Preview(showBackground = true, name = "Sample Notifications List")
//@Composable
//fun PreviewWithSampleData() {
//    MaterialTheme {
//        LazyColumn(
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            itemsIndexed(
//                items = getSampleNotifications(),
//                key = { index, _ -> index }
//            ) { index, notification ->
//                NotificationItem(
//                    notification = com.example.jan6_project2.NotificationClass(
//                        message = notification.message,
//                        date = notification.date,
//                        isRead = notification.isRead
//                    ),
//                    onMarkAsRead = {}
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, name = "First Sample Notification")
//@Composable
//fun PreviewFirstSampleNotification() {
//    val sampleNotifications = getSampleNotifications()
//    MaterialTheme {
//        NotificationItem(
//            notification = com.example.jan6_project2.NotificationClass(
//                message = sampleNotifications[0].message,
//                date = sampleNotifications[0].date,
//                isRead = sampleNotifications[0].isRead
//            ),
//            onMarkAsRead = {}
//        )
//    }
//}