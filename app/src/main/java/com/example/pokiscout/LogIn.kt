package com.example.pokiscout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(navController: NavController) {
    var textFieldState by remember { mutableStateOf("") }
    var textFieldState2 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA652BB))
    )
    Scaffold(
        modifier = Modifier.fillMaxSize()

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFA652BB))
        )
        {
            item {
                Image(
                    painter = painterResource(id = R.drawable.maserballogo),
                    contentDescription = "Masterball",
                    modifier = Modifier
                        .offset(x=100.dp,y=200.dp)

                )
                Text(
                    text= "Username*",
                    modifier = Modifier
                        .offset(x=25.dp,y=340.dp)
                        .zIndex(1f)

                )
                TextField(
                    value = textFieldState,
                    onValueChange = { textFieldState = it },
                    label = { Text("USERNAME") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .offset(y = 340.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFFFFFFF),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text= "Password*",
                    modifier = Modifier
                        .offset(x=25.dp,y=350.dp)
                        .zIndex(1f)

                )

                TextField(
                    value = textFieldState2,
                    onValueChange = { textFieldState2 = it },
                    label = { Text("PASSWORD") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .offset(y = 350.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFFFFFFF),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )

                )
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
                    modifier=Modifier
                        .padding(16.dp)
                        .offset(x=140.dp,y = 350.dp)


                ) {
                    Text(text = "Log in",
                   color = Color.Black,
                    modifier=Modifier)
                }
            }
        }
    }
}
