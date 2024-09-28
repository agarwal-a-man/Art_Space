package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    ArtSpaceApp()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

@Composable
fun ArtSpaceApp() {
    var currentValue by remember { mutableIntStateOf(1) }
    var nextValue by remember { mutableIntStateOf(0) }

    currentValue = checkNext(currentValue, nextValue)
    nextValue = 0
    val nextClick = {nextValue = 1}
    val preClick = {nextValue = -1}
    when(currentValue){
        1-> {
            ArtSpaceDisplay(imageId =  R.drawable.ar1,
                titleId = R.string.title1,
                artistId = R.string.A1
                , nextClick = nextClick,
                preClick = preClick)
        }
        2->{
            ArtSpaceDisplay(imageId =  R.drawable.ar2,
                titleId = R.string.title2,
                artistId = R.string.A2
                , nextClick = nextClick,
                preClick = preClick)
            }
        3->{
            ArtSpaceDisplay(imageId =  R.drawable.ar3,
                titleId = R.string.title3,
                artistId = R.string.A3
                , nextClick = nextClick,
                preClick = preClick)
           }
    }


}
@Composable
fun ArtSpaceDisplay(titleId : Int, artistId : Int, imageId : Int,nextClick:()->Unit , preClick:()->Unit){
    Column(modifier = Modifier.fillMaxSize()
        .padding(40.dp),
        verticalArrangement = Arrangement.Center) {
        ArtSpaceImage(imageId=imageId)
        ArtSpaceTitleArtist(titleId = titleId, artistId = artistId)
        ArtSpaceButton(nextClick = nextClick, preClick = preClick)
    }
}

@Composable
fun ArtSpaceImage(imageId : Int){
    Image(painter = painterResource(imageId),
        contentDescription = null,
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth()
            .border( 20.dp, White,shape = RoundedCornerShape(15.dp))
            .shadow( 30.dp, shape = RoundedCornerShape(15.dp))
    )
}
@Composable
fun ArtSpaceTitleArtist(titleId : Int, artistId : Int,
                        modifier:Modifier = Modifier ){
    Column(modifier = modifier.fillMaxWidth()
        .padding(top =50.dp)
        .background(
            color = androidx.compose.ui.graphics.Color.LightGray
            , shape = RoundedCornerShape(10.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text= stringResource(id = titleId),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Black)
        Text(text= stringResource(id = artistId),
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
            fontSize = 15.sp,
            color = Black)
    }
}
@Composable
fun ArtSpaceButton(nextClick:()->Unit , preClick:()->Unit){
    Row (modifier = Modifier
        .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom){
        Button(onClick = preClick) {
            Text(text = stringResource(id = R.string.pre))
        }
        Button(onClick = nextClick) {
            Text(text = stringResource(id = R.string.next))
        }

    }

}
fun checkNext(current : Int, next : Int) : Int{
    return if(current==3 && next == 1){
        1
    }
    else if(current == 1 && next == -1){
        3
    }
    else{
        current+next
    }
}