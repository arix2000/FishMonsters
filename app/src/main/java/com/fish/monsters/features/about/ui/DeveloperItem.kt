package com.fish.monsters.features.about.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.models.ui.Developer
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12

@Composable
fun DeveloperItem(
    developer: Developer,
    boxShape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp)
    ),
    imageShape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp)
    )
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(DarkPrimaryColorA12, boxShape)
            .fillMaxWidth()
            .height(240.dp)
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(id = developer.photoUrl),
                contentDescription = developer.firstName + " photo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .clip(imageShape)
                    .border(BorderStroke(1.dp, DarkPrimaryColor), shape = imageShape)
                    .size(60.dp),
            )
            Column {
                Text(
                    text = developer.firstName + " " + developer.lastName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = developer.email,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${developer.email}")
                                }
                                context.startActivity(intent)
                            },
                        fontSize = 12.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            color = DarkPrimaryColor
                        )
                    )
                    Text(
                        text = developer.albumNumber.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        style = TextStyle(color = DarkPrimaryColor)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.Call, contentDescription = "Phone Icon",
                    tint = DarkPrimaryColor
                )
                Text(
                    text = developer.phone,
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${developer.phone}")
                            }
                            context.startActivity(intent)
                        },
                    fontSize = 18.sp,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline,
                        color = DarkPrimaryColor
                    )
                )
            }
            Spacer(modifier = Modifier.height(19.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.github_logo),
                    contentDescription = "Github Logo",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Github",
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(developer.githubLink)
                            }
                            context.startActivity(intent)
                        },
                    fontSize = 18.sp,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline,
                        color = DarkPrimaryColor
                    )
                )
            }
            Spacer(modifier = Modifier.height(19.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.linkedin_logo),
                    contentDescription = "Linkedin Logo",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "LinkedIn",
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(developer.linkedinLink)
                            }
                            context.startActivity(intent)
                        },
                    fontSize = 18.sp,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline,
                        color = DarkPrimaryColor
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(19.dp))
}

@Preview
@Composable
fun DeveloperItemPreview() {
    PreviewContainer {
        DeveloperItem(
            developer = Developer(
                firstName = "Janek",
                lastName = "Rembikowski",
                email = "jrembikowski@edu.cdv.pl",
                phone = "+48530036192",
                albumNumber = 27264,
                githubLink = "https://github.com/joohnnyvv",
                linkedinLink = "https://www.linkedin.com/in/jan-rembikowski/",
                photoUrl = R.drawable.janek_avatar
            )
        )
    }
}
