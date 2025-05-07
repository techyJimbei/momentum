@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.momentum_app.view.onboardingscreen

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.momentum_app.MainActivity
import com.example.momentum_app.R
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun OnboardingScreen(navController: NavHostController, context: MainActivity) {
    val animations = listOf(
        R.raw.todo_list,
        R.raw.share_media,
        R.raw.connecting_people
    )

    val titles = listOf(
        "Stay on Track",
        "Celebrate Progress",
        "Share & Support"
    )

    val descriptions = listOf(
        "Create tasks, set goals, and keep your daily progress in check—all in one place.",
        "Share your achievements—big or small—and inspire others as you grow.",
        "Meet like-minded people, build meaningful connections, and grow together."
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { animations.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { currentPage ->

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(animations[currentPage])
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(500.dp)
                )

                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.Dark)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = descriptions[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.Dark_Grey)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PageIndicator(
                    pageCount = animations.size,
                    currentPage = currentPage,
                    modifier = Modifier.padding(60.dp)
                )
            }

        }
        ButtonSection(
            pagerState = pagerState,
            navController = navController,
            context = context
        )

    }

}



@Composable
fun ButtonSection(pagerState: PagerState, navController: NavHostController, context: MainActivity) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        if (pagerState.currentPage != 2) {
            Text(
                text = "Prev",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable {
                        scope.launch {
                            val prevPage = pagerState.currentPage - 1
                            if(prevPage >= 0){
                                pagerState.scrollToPage(prevPage)
                            }
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = "Next",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.scrollToPage(nextPage)
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        } else {
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate("SignUpPage")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1A78D6),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "SIGN UP",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier =  Modifier.padding(16.dp))

            Text(
                text = "SIGN IN",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clickable {
                        navController.navigate("SignInPage")
                    },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray

            )
        }
    }
}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            IndicatorSingleDot(isSelected = index == currentPage)
        }
    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {
    val width by animateDpAsState(targetValue = if (isSelected) 35.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(10.dp)
            .clip(CircleShape)
            .width(width)
            .background(if (isSelected) Color.DarkGray else Color.LightGray)
    )
}



