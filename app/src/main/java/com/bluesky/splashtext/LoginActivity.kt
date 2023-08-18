package com.bluesky.splashtext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    fun MessageCard(msg: Message) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(all = 8.dp),
            tonalElevation = 5.dp
        ) {
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.girl),
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
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }


        }
    }


    @Preview
    @Composable
    fun PreviewMessageCard() {
        SplashTestTheme {
            MessageCard(msg = Message("Jetpack Compose 博物馆", body = "我们开始更新啦"))

        }
    }
}

data class Message(var author: String, var body: String)
