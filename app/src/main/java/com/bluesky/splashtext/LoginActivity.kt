package com.bluesky.splashtext

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bluesky.splashtext.ui.theme.SplashTestTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //todo:SplashScreen 5:
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    @Composable
    fun Conversation(message: List<Message>) {
        LazyColumn {
            items(message) {
                MessageCard(msg = it)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        SplashTestTheme {
            Conversation(message = MsgData.messages)
        }
    }

    @Composable
    fun MessageCard(msg: Message) {

        var isExpanded by remember { mutableStateOf(false) }

        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 5.dp,
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(all = 8.dp)
                .clickable { isExpanded = !isExpanded }

        ) {
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.girl_robot_avatar),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            1.5.dp,
                            MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = msg.body,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        modifier = Modifier.animateContentSize()
                    )
                }
            }


        }
    }


    @Preview(name = "light mode")
    @Preview(
        name = "dark mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewMessageCard() {
        SplashTestTheme {
            MessageCard(msg = Message("Jetpack Compose 博物馆", body = "我们开始更新啦"))

        }
    }
}

data class Message(var author: String, var body: String)
