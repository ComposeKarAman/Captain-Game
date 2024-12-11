package com.example.captaingame

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.captaingame.ui.theme.CaptainGameTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptainGame(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CaptainGame(modifier : Modifier) {

    val treasureFound = remember { mutableIntStateOf(0) }
    val stormEncountered = remember { mutableIntStateOf(0) }
    val direction = remember { mutableStateOf("North") }
    val hpRemaining = remember { mutableIntStateOf(100) }
    val noChange = remember { mutableStateOf(false ) }
    val context = LocalContext.current

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally)
    {
        Text("Captain Game", Modifier.padding(50.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Text(text = "Treasure Found: ${treasureFound.intValue}")
        Text("Direction: ${direction.value}")
        Text("Storm Encountered: ${stormEncountered.intValue}")
        Text("HP Remaining: ${hpRemaining.intValue}")
        Button({
            direction.value = "East"
            if (Random().nextBoolean()) {
                treasureFound.intValue++
                Toast.makeText(context, "You Were Lucky", Toast.LENGTH_SHORT).show()
            }else{
                stormEncountered.intValue++
                Toast.makeText(context, "You Were unlucky", Toast.LENGTH_SHORT).show()
                hpRemaining.value -= 10
        }
    })
        {
            Text("Move to East")
        }

        Button({
            direction.value = "North"
            if (Random().nextBoolean()) {
                treasureFound.intValue++
                Toast.makeText(context, "You Were Lucky", Toast.LENGTH_SHORT).show()
            }else{
                stormEncountered.intValue++
                Toast.makeText(context, "You Were unlucky", Toast.LENGTH_SHORT).show()
                hpRemaining.value -= 10
        }
    })
        {
            Text("Move North")
        }

        Button({
            direction.value = "West"
            if (Random().nextBoolean()) {
                treasureFound.intValue++
                Toast.makeText(context, "You Were Lucky", Toast.LENGTH_SHORT).show()
            }else{
                stormEncountered.intValue++
                Toast.makeText(context, "You Were unlucky", Toast.LENGTH_SHORT).show()
                hpRemaining.value -= 10
            }
    })
        {
            Text("Move West")
        }

        Button({
            direction.value = "South"
            if (Random().nextBoolean()) {
                treasureFound.intValue++
                Toast.makeText(context, "You Were Lucky", Toast.LENGTH_SHORT).show()
            }else{
                stormEncountered.intValue++
                Toast.makeText(context, "You Were unlucky", Toast.LENGTH_SHORT).show()
                hpRemaining.value -= 10
        }
    })
        {
            Text("Move South")
        }
        Spacer(modifier.height((16.dp)))
        if (hpRemaining.intValue <= 0){
            noChange.value = true
            hpRemaining.intValue = 0
            Text("Game Over")
            Spacer(Modifier.padding(6.dp))
            Button({
                treasureFound.intValue = 0
                stormEncountered.intValue = 0
                direction.value = "North"
                hpRemaining.intValue = 100
            })
            {
                Text("RETRY")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptainGamePreview() {
    CaptainGameTheme {
        CaptainGame(Modifier)
    }
}
