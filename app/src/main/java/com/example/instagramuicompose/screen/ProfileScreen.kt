package com.example.instagramuicompose.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramuicompose.R
import com.example.instagramuicompose.data.StoryHighlight
import com.example.instagramuicompose.data.TabItem

/**
 * Created by Dhruv Limbachiya on 03-09-2021.
 */

@ExperimentalFoundationApi
@Composable
fun ProfileScreen() {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            modifier = Modifier.padding(12.dp),
            instagramHandleName = "dhruv__limbachiya"
        )
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        ProfileDescription(
            displayName = "Dhruv Limbachiya",
            description = "Sagittarius \uD83D\uDC7B\n" +
                    "Android App Developer \uD83D\uDC69\u200D\uD83D\uDCBB\n" +
                    "Modern Monk \uD83D\uDC7C\n" +
                    "Fitness Enthusiat \uD83D\uDCAA",
            url = "https://github.com/DHRUV-LIMBACHIYA",
            followedBy = listOf("danbilzerian", "katrinakaif"),
            otherCount = 54
        )
        Spacer(modifier = Modifier.height(20.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        HighlightSection(
            highLights = listOf(
                StoryHighlight(R.drawable.highlight_1, "Thoughts"),
                StoryHighlight(R.drawable.highlight_2, "Favorite Shot"),
                StoryHighlight(R.drawable.highlight_3, "Fitness"),
                StoryHighlight(R.drawable.highlight_4, "Birthday"),
                StoryHighlight(R.drawable.highlight_5, "Snapchat"),
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostsTabView(
            tabItems = listOf(
                TabItem(tabIcon = painterResource(id = R.drawable.ic_grid)),
                TabItem(tabIcon = painterResource(id = R.drawable.ic_reels)),
                TabItem(tabIcon = painterResource(id = R.drawable.ic_igtv)),
                TabItem(tabIcon = painterResource(id = R.drawable.profile)),
            ),modifier = Modifier.fillMaxWidth()
        ) {
            selectedIndex = it
        }

        when (selectedIndex) {
            0 -> PostSection(posts =  listOf(
                    painterResource(id = R.drawable.img_1),
                    painterResource(id = R.drawable.img_2),
                    painterResource(id = R.drawable.img_3),
                    painterResource(id = R.drawable.img_4),
                    painterResource(id = R.drawable.img_5),
                    painterResource(id = R.drawable.img_6),
                    painterResource(id = R.drawable.img_7),
                    painterResource(id = R.drawable.img_8),
                    painterResource(id = R.drawable.img_9),
                ),modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    instagramHandleName: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Arrow",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = instagramHandleName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis // Show Ellipsis(...) when instagramHandleName is too long.
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Notifications",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "Dot Menu",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

/**
 * Composable for displaying profile section data like profile image,statistics & bio.
 */
@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundImage(imageResourceId = R.drawable.img_2, modifier = Modifier.weight(3f))
            Spacer(modifier = Modifier.width(10.dp))
            StatisticsSection(modifier = Modifier.weight(7f))
        }
    }
}

/**
 * Composable for displaying RoundImage for profile image & highlights.
 */
@Composable
fun RoundImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageResourceId: Int
) {
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            // matchHeightConstraintsFirst = true :  Calculate the height value of the image and make width with equal of height value.
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape)

    )
}

/**
 * Composable for displaying user statistics like posts,follower & following.
 */
@Composable
fun StatisticsSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileStatistics(number = "162", textToDisplay = "Posts")
        ProfileStatistics(number = "459", textToDisplay = "Followers")
        ProfileStatistics(number = "408", textToDisplay = "Following")
    }
}

/**
 * Re-usable composable for displaying profile statistics.
 */
@Composable
fun ProfileStatistics(
    modifier: Modifier = Modifier,
    number: String,
    textToDisplay: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = number,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = textToDisplay,
            fontSize = 16.sp
        )
    }
}


/**
 * Composable for user bio.
 */
@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        val letterSpacing = 0.5.sp  // The amount of space to add between each letter
        val lineHeight = 20.sp // Line height for the Paragraph

        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(
            text = description,
            lineHeight = lineHeight
        )

        Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        if (followedBy.isNotEmpty()) {
            // Style for followed person name text and others text
            val boldStyle = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buildAnnotatedString {
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle) // Push the bold style in the stack. Followed text will have this pushed style.
                        append(name)
                        pop() // Pop the current bold style.
                        // Append "," after name if it is not the last element in the list.
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    append(" and ")
                    pushStyle(boldStyle) // Push the bold style again.
                    append("$otherCount others")
                }
            )
        }
    }
}

/**
 * Composable for displaying all action buttons.
 */
@Composable
fun ButtonSection(
    modifier: Modifier = Modifier
) {
    val minWidth = 110.dp
    val height = 35.dp
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ActionButton(
            buttonText = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            buttonText = "Message", modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            buttonText = "Email", modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown, modifier = Modifier.height(height)
        )
    }
}

/**
 * Composable for Action Button like Following,Message & Email.
 */
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    buttonText: String? = null,
    icon: ImageVector? = null
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        buttonText?.let {
            Text(
                text = buttonText,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
            )
        }

        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
            )
        }
    }
}

/**
 * Composable for displaying list of story highlights.
 */
@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highLights: List<StoryHighlight>
) {
    LazyRow(
        modifier = modifier,
    ) {
        items(highLights.size) {
            HighLightItem(highlight = highLights[it])
        }
    }
}

/**
 * Composable for creating individual story highlight with image & text.
 */
@Composable
fun HighLightItem(
    modifier: Modifier = Modifier,
    highlight: StoryHighlight
) {
    Column(
        modifier = modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundImage(imageResourceId = highlight.imageRes, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = highlight.highlightName,
            fontSize = 14.sp,
        )
    }
}

/**
 * Composable for displaying TabView.
 */
@Composable
fun PostsTabView(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>,
    onItemSelect: (selectedIndex: Int) -> Unit
) {
    val inactiveColor = Color(0xFF777777)

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        tabItems.forEachIndexed { index, tabItem ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index // Update the selected index.
                    onItemSelect(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
            ) {
                Icon(
                    painter = tabItem.tabIcon,
                    contentDescription = tabItem.contentDescription,
                    tint = if (selectedIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}


/**
 * Composable for displaying Post Section.
 */
@ExperimentalFoundationApi
@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    posts: List<Painter>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f) // Square size.
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}