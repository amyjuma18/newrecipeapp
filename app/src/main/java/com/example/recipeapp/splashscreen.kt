package com.example.recipeapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.models.enticingrecipes

@Composable
fun HomeScreen(onDetailsClick:(id : Long) -> Unit){
//    making a recycler view: Lazycolumn
    Scaffold {
        LazyColumn(contentPadding = it){
            item {
                HomeAppBar()
            }
            items(enticingrecipes){
                RecipeCard(it, onClick = {
                    onDetailsClick(it.id)
                } )
            }
        }
    }
}
@Composable
private fun HomeAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Text("Latest Recipes", style = MaterialTheme.typography.h6)
    }
}





































/*app/src/main/java/com/example/myapplication/MainActivity.kt
@@ -0,0 +1,54 @@
package com.example.myapplication

/*import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(onDetailsClick = {
                            id -> navController.navigate("details/id=$id?name=hi")
                    })
                }
                composable("details/id={id}?name={name}",
                    arguments = listOf(
                        navArgument("id"){
                            type = NavType.LongType
                        },
                        navArgument("name"){
                            type = NavType.StringType
                            nullable = true
                        }),
                ){
                    //inside this composable , I can pick the shared details
                    val arguments = requireNotNull(it.arguments)
                    val id = arguments.getLong("id")
                    val name = arguments.getString("name")

                    DetailScreen(id, name, onNavigateUp = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}


/*app/src/main/java/com/example/myapplication/models/Recipe.kt
@@ -0,0 +1,43 @@
package com.example.myapplication.models;

/*import androidx.annotation.DrawableRes
import com.example.myapplication.R

data class Recipe (
    val id : Long,
    val title : String,
    val ingredients:List<String>,
    @DrawableRes
    val imageResource:Int,
    val method : List<String>
)

val pizza = Recipe (
    1,
    "Pizza",
    listOf("Pizza sauce\n" +
            "2 tbsp olive oil\n" +
            "1/2 cup finely chopped onion\n" +
            "2tbsp garlic\n" +
            "2 large tomato puree(skin peeled)\n" +
            "1/2 tsp black pepper\n" +
            "1tbsp dry Italian herbs( oregano, parsley, thyme)\n" +
            "1tsp chili flacks\n" +
            "1/2 tsp salt\n" +
            "1/2 tsp sugar","2 cups flour\n" +
            "1tsp yeast\n" +
            "1/2 tsp salt\n" +
            "1/2 tsp sugar\n" +
            "2tbsp olive oil\n" +
            "100-120ml water(1/2 -3/4 cup)depending on the type of flour and weather. make a stiff dough"),
    R.drawable.pizza, listOf("To make pizza sauce saute onion and garlic until translucent then add tomato puree and other herbs\n" +
            "cook the sauce until thickens\n" +
            "to make pizza dough mix all ingredients for dough in a bowl\n" +
            "knead until smooth\n" +
            "because it's a quick pizza we won't wait until it rises to double, instead roll the dough according to the size of your pizza pan then set it in the pan\n" +
            "cover the pan and rest for 15 -20 mins in a warm place\n" +
            "then spread the sauce on top of the pizza\n" +
            "add cheese, toppings of your choice then cheese again \n" +
            "bake for 20 mins 250°C(480°F) for a crispy base bake 10 mins on the lower rack and 10 mins on the middle if you have a table top oven and enjoy")
)
val yummyrecipes = listOf(pizza)
45
app/src/main/java/com/example/myapplication/recipecard.kt
@@ -0,0 +1,45 @@
package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.Recipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCard(food:Recipe, onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column {
            Image(
                painterResource(id = food.imageResource),
                contentDescription = "Thumbnail",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(food.title)
            }
        }

    }
}
8
app/src/main/java/com/example/myapplication/ui/theme/Color.kt
@@ -0,0 +1,8 @@
package com.example.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
11
app/src/main/java/com/example/myapplication/ui/theme/Shape.kt
@@ -0,0 +1,11 @@
package com.example.myapplication.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
47
app/src/main/java/com/example/myapplication/ui/theme/Theme.kt
@@ -0,0 +1,47 @@
package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
28
app/src/main/java/com/example/myapplication/ui/theme/Type.kt
@@ -0,0 +1,28 @@
package com.example.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
30
app/src/main/res/drawable-v24/ic_launcher_foreground.xml
@@ -0,0 +1,30 @@
<vector xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:aapt="http://schemas.android.com/aapt"
android:width="108dp"
android:height="108dp"
android:viewportWidth="108"
android:viewportHeight="108">
<path android:pathData="M31,63.928c0,0 6.4,-11 12.1,-13.1c7.2,-2.6 26,-1.4 26,-1.4l38.1,38.1L107,108.928l-32,-1L31,63.928z">
<aapt:attr name="android:fillColor">
<gradient
android:endX="85.84757"
android:endY="92.4963"
android:startX="42.9492"
android:startY="49.59793"
android:type="linear">
<item
android:color="#44000000"
android:offset="0.0" />
<item
android:color="#00000000"
android:offset="1.0" />
</gradient>
</aapt:attr>
</path>
/*<path
android:fillColor="#FFFFFF"
android:fillType="nonZero"
android:pathData="M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z"
android:strokeWidth="1"
android:strokeColor="#00000000" />
</vector>
170
app/src/main/res/drawable/ic_launcher_background.xml
@@ -0,0 +1,170 @@
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
android:width="108dp"
android:height="108dp"
android:viewportWidth="108"
android:viewportHeight="108">
<path
android:fillColor="#3DDC84"
android:pathData="M0,0h108v108h-108z" />
<path
android:fillColor="#00000000"
android:pathData="M9,0L9,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,0L19,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M29,0L29,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M39,0L39,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M49,0L49,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M59,0L59,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M69,0L69,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M79,0L79,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M89,0L89,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M99,0L99,108"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,9L108,9"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,19L108,19"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
/*<path
android:fillColor="#00000000"
android:pathData="M0,29L108,29"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,39L108,39"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,49L108,49"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,59L108,59"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,69L108,69"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,79L108,79"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,89L108,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M0,99L108,99"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,29L89,29"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,39L89,39"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,49L89,49"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,59L89,59"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,69L89,69"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M19,79L89,79"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M29,19L29,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M39,19L39,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M49,19L49,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M59,19L59,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M69,19L69,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
<path
android:fillColor="#00000000"
android:pathData="M79,19L79,89"
android:strokeWidth="0.8"
android:strokeColor="#33FFFFFF" />
</vector>
BIN +177 KB
app/src/main/res/drawable/pizza.jpg
/*Unable to render rich display

5
app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
@@ -0,0 +1,5 @@
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
<background android:drawable="@drawable/ic_launcher_background" />
<foreground android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
5
app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
@@ -0,0 +1,5 @@
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
<background android:drawable="@drawable/ic_launcher_background" />
<foreground android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
6
app/src/main/res/mipmap-anydpi-v33/ic_launcher.xml
@@ -0,0 +1,6 @@
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
<background android:drawable="@drawable/ic_launcher_background" />
<foreground android:drawable="@drawable/ic_launcher_foreground" />
<monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
BIN +1.37 KB
app/src/main/res/mipmap-hdpi/ic_launcher.webp
Binary file not shown.
BIN +2.83 KB
app/src/main/res/mipmap-hdpi/ic_launcher_round.webp
Binary file not shown.
BIN +28.1 KB
app/src/main/res/mipmap-hdpi/mylogo.png
Unable to render rich display

BIN +982 Bytes
app/src/main/res/mipmap-mdpi/ic_launcher.webp
Binary file not shown.
BIN +1.73 KB
app/src/main/res/mipmap-mdpi/ic_launcher_round.webp
Binary file not shown.
BIN +1.86 KB
app/src/main/res/mipmap-xhdpi/ic_launcher.webp
Binary file not shown.
BIN +3.83 KB
app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp
Binary file not shown.
BIN +2.82 KB
app/src/main/res/mipmap-xxhdpi/ic_launcher.webp
Binary file not shown.
BIN +5.78 KB
app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp
Binary file not shown.
BIN +3.75 KB
app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp
Binary file not shown.
BIN +7.6 KB
app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp
Binary file not shown.
10
app/src/main/res/values/colors.xml
@@ -0,0 +1,10 @@
/*<?xml version="1.0" encoding="utf-8"?>
<resources>
<color name="purple_200">#FFBB86FC</color>
<color name="purple_500">#FF6200EE</color>
<color name="purple_700">#FF3700B3</color>
<color name="teal_200">#FF03DAC5</color>
<color name="teal_700">#FF018786</color>
<color name="black">#FF000000</color>
<color name="white">#FFFFFFFF</color>
</resources>
3
app/src/main/res/values/strings.xml
@@ -0,0 +1,3 @@
<resources>
<string name="app_name">Recipe app</string>
</resources>
7
app/src/main/res/values/themes.xml
@@ -0,0 +1,7 @@
<?xml version="1.0" encoding="utf-8"?>
<resources>

<style name="Theme.MyApplication" parent="android:Theme.Material.Light.NoActionBar">
<item name="android:statusBarColor">@color/purple_700</item>
</style>
</resources>
13
app/src/main/res/xml/backup_rules.xml
@@ -0,0 +1,13 @@
<?xml version="1.0" encoding="utf-8"?><!--
Sample backup rules file; uncomment and customize as necessary.
See https://developer.android.com/guide/topics/data/autobackup
for details.
Note: This file is ignored for devices older that API 31
See https://developer.android.com/about/versions/12/backup-restore
-->
<full-backup-content>
<!--
<include domain="sharedpref" path="."/>
<exclude domain="sharedpref" path="device.xml"/>
-->
</full-backup-content>
19
app/src/main/res/xml/data_extraction_rules.xml
@@ -0,0 +1,19 @@
<?xml version="1.0" encoding="utf-8"?><!--
Sample data extraction rules file; uncomment and customize as necessary.
See https://developer.android.com/about/versions/12/backup-restore#xml-changes
for details.
-->
<data-extraction-rules>
<cloud-backup>
<!-- TODO: Use <include> and <exclude> to control what is backed up.
<include .../>
<exclude .../>
-->
</cloud-backup>
<!--
<device-transfer>
<include .../>
<exclude .../>
</device-transfer>
-->
</data-extraction-rules>
17
app/src/test/java/com/example/myapplication/ExampleUnitTest.kt
@@ -0,0 +1,17 @@
package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
10
build.gradle
@@ -0,0 +1,10 @@
buildscript {
    ext {
        compose_ui_version = '1.2.0'
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '7.4.2' apply false
    id 'com.android.library' version '7.4.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.7.0' apply false
}
23
gradle.properties
@@ -0,0 +1,23 @@
# Project-wide Gradle settings.
# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.
# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html
# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. More details, visit
# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects
# org.gradle.parallel=true
# AndroidX package structure to make it clearer which packages are bundled with the
# Android operating system, and which are packaged with your app's APK
# https://developer.android.com/topic/libraries/support-library/androidx-rn
android.useAndroidX=true
# Kotlin code style for this project: "official" or "obsolete":
kotlin.code.style=official
# Enables namespacing of each library's R class so that its R class includes only the
# resources declared in the library itself and none from the library's dependencies,
# thereby reducing the size of the R class for that library
android.nonTransitiveRClass=true
BIN +57.8 KB
gradle/wrapper/gradle-wrapper.jar
Binary file not shown.
6
/*gradle/wrapper/gradle-wrapper.properties
@@ -0,0 +1,6 @@
#Mon Apr 17 10:29:14 EAT 2023
distributionBase=GRADLE_USER_HOME
distributionUrl=https\://services.gradle.org/distributions/gradle-7.5-bin.zip
distributionPath=wrapper/dists
zipStorePath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
185
gradlew
@@ -0,0 +1,185 @@
#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
ls=`ls -ld "$PRG"`
link=`expr "$ls" : '.*-> \(.*\)$'`
if expr "$link" : '/.*' > /dev/null; then
PRG="$link"
else
PRG=`dirname "$PRG"`"/$link"
fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

/*# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
CYGWIN* )
cygwin=true
;;
Darwin* )
darwin=true
;;
MINGW* )
msys=true
;;
NONSTOP* )
nonstop=true
;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar


# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
# IBM's JDK on AIX uses strange locations for the executables
JAVACMD="$JAVA_HOME/jre/sh/java"
else
JAVACMD="$JAVA_HOME/bin/java"
fi
if [ ! -x "$JAVACMD" ] ; then
die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME
Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi
else
JAVACMD="java"
which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

//# Increase the maximum file descriptors if we can.
/*if [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ] ; then
MAX_FD_LIMIT=`ulimit -H -n`//
if [ $? -eq 0 ] ; then
if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then //
MAX_FD="$MAX_FD_LIMIT"
fi
ulimit -n $MAX_FD
if [ $? -ne 0 ] ; then
warn "Could not set maximum file descriptor limit: $MAX_FD"
fi
else
warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
//fi
//fi
