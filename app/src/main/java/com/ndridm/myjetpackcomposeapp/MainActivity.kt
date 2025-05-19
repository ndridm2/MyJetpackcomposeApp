package com.ndridm.myjetpackcomposeapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndridm.myjetpackcomposeapp.ui.theme.MyJetpackcomposeAppTheme

val sampelName = listOf(
    "JetPack Compose",
    "Java",
    "Kotlin",
    "Dart",
    "Python",
    "C",
    "Golang",
    "Rust",
    "PHP",
    "Javascript",
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyJetpackcomposeAppTheme {
                HelloJetpackComposeApp()
            }
        }
    }
}

@Composable
fun HelloJetpackComposeApp(){
    GreetingList(sampelName)
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HelloJetpackComposeAppPreview(){
    MyJetpackcomposeAppTheme {
        HelloJetpackComposeApp()
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {

        LazyColumn {
            items (names) { name ->
                Greeting(name)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text("No programming language :(")
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = "Logo Jetpack Compose",
                modifier = Modifier.size(animatedSizeDp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    )
                Text(
                    text = "Welcome to Jetpack Compose!",
                    style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic
                    )
                )
            }
            IconButton(onClick = {isExpanded = !isExpanded}) {
                Icon(imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview(){
    MyJetpackcomposeAppTheme {
        Greeting("Jetpack Compose")
    }
}