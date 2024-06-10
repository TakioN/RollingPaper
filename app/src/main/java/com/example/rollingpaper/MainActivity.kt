package com.example.rollingpaper

import android.content.ActivityNotFoundException
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.rollingpaper.homePage.homeScreen
import com.example.rollingpaper.homePage.homeScreen_no
import com.example.rollingpaper.makeMemo.makeMemoScreen
import com.example.rollingpaper.ui.theme.RollingPaperTheme
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.ItemContent
import com.kakao.sdk.template.model.ItemInfo
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.Social


class MainActivity : ComponentActivity() {
    private val kakaoAuthViewModel: KakaoAuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RollingPaperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // NavController 초기화
                    val navController = rememberNavController()

//                    // NavHost에 NavController 연결
//                    NavHost(navController, startDestination = "homeScreen") {
//                        composable("homeScreen") {
//                            homeScreen_no(navController, viewModel = kakaoAuthViewModel)
//                        }
//                        composable("makeMemoScreen") {
//                            makeMemoScreen(navController)
//                        }
//                    val isLoggedIn by kakaoAuthViewModel.isLoggedIn.observeAsState(false)
//                    if(isLoggedIn) {
//                        homeScreen(navController)
//                    } else {
//                        homeScreen_no(viewModel = kakaoAuthViewModel)
//                    }

                    val keyHash = Utility.getKeyHash(this)
                    Log.d("Hash", keyHash)
                    kakaoAuthViewModel.checkLoginStatus()
                    val navcontroller= rememberNavController()
                    Graph(navcontroller, kakaoAuthViewModel)

                        // 다른 destination 추가
                    }
                }
            }
        }

    }



