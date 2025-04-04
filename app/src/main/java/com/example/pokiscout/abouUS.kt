package com.example.pokiscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import androidx.navigation.NavController


@Composable

fun aboutUS(navController: NavController) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(



        topBar = {
            Image(
                painter = painterResource(id=R.drawable.pokiaboutus),
                contentDescription= "AboutUs_PokiBall",
                modifier=Modifier
                    .size(35.dp)
                    .offset(9.dp, y = 150.dp)
                    .zIndex(1f)
            )
            Image(
                painter = painterResource(id=R.drawable.pokifinder),
                contentDescription= "TOP_ICON",
                modifier=Modifier
                    .size(100.dp)
                    .offset(155.dp, y = 45.dp)
                    .zIndex(1f)
            )
            Image(
                painter = painterResource(id=R.drawable.pokiaboutus),
                contentDescription= "Bottom_right",
                modifier=Modifier
                    .size(35.dp)
                    .offset(375.dp, y = 720.dp)
                    .zIndex(1f)
            )
            Image(
                painter = painterResource(id=R.drawable.pokiballaboutus2),
                contentDescription= "TOP_BoTTOM_right",
                modifier=Modifier
                    .size(35.dp)
                    .offset(x=370.dp, y = 405.dp)
                    .zIndex(1f)
            )
            Image(
                painter = painterResource(id=R.drawable.pokiballaboutus2),
                contentDescription= "AboutUs_PokiBall",
                modifier=Modifier
                    .size(35.dp)
                    .offset(x=7.dp, y = 505.dp)
                    .zIndex(1f)
            )
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp)
                .offset(x = 5.dp, y = 150.dp)
                    .background(Color(0xFFFF0000))
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(10.dp))




            ) {

                Text(
                    text = "Poki Scout is a developed application" +
                            "by a 21 year old \n" +
                            "who wanted to make \n" +
                            "finding Pokemon easier for players around the world.\n" +
                            "Here you will be able to gather\n" +
                            "fast location,types and much more so that you will\n" +
                            "be able to go straight back into playing!\n" +
                            "We allow any race/ethicnity on this application and do not discriminate.\n" +
                            "Thank you for using Poki Scout and may you CATCH THEM ALL!\n",


                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp ,
                        color = Color.White

                    )
                )
                Spacer(modifier = Modifier.height(80.dp))

            }
            Box(
                //rounded shape for box
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp)
                    .padding(start = 6.dp, top = 500.dp,)
                    .background(Color(0xFF560BE5))
                    .clip(RoundedCornerShape(10.dp))
                    .padding(16.dp)


            ) {
                Text(
                    modifier = Modifier
                    ,
                    text = "We believe in equality for all our users. \n\n" +
                            "We treat employees, customers, and partners with fairness and dignity. \n\n" +
                            "We encourage creativity and continuous improvement.\n\n" +
                            "We prioritize customer satisfaction and experience.\n",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White

                    )
                )
            }
        },


        bottomBar = {

            Image(
                painter = painterResource(id = R.drawable.pokeball1),
                contentDescription = "PokeBall1",
                modifier = Modifier
                    .size(35.dp)
                    .offset(x = 20.dp, y = -10.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.instagramlogo),
                contentDescription = "InstaGramLogo",
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 380.dp, y = -8.dp)
                    .clickable {
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.facebooklogo),
                contentDescription = "FaceBookLogo",
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 350.dp, y = -8.dp)
                    .clickable {
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.linkedin),
                contentDescription = "FaceBookLogo",
                modifier = Modifier
                    .size(25.dp)
                    .offset(x = 320.dp, y = -10.dp)
                    .clickable {
                    }

            )

        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
                .verticalScroll(rememberScrollState())
        ) {
        Button(
            onClick = {navController.popBackStack()},
            colors = ButtonDefaults.buttonColors(Color(0xFFFF0000)),
            modifier = Modifier
                .padding(16.dp)
                .offset( y = 360.dp)
        ) {
            Text("EXIT")
        }

        }
    }
}


