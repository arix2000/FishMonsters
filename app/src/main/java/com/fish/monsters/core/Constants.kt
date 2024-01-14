package com.fish.monsters.core

import com.fish.monsters.R
import com.fish.monsters.common.models.ui.Developer
import com.google.maps.android.compose.MapUiSettings

object SettingsUris {
    const val rateUs = "https://play.google.com/store/apps/details?id=com.shams.futurecity"
    const val reportProblem = "https://github.com/arix2000/FishMonsters/issues/new"
    const val support = "https://patronite.pl/RatujRyby"
}

object Developers {
    private val Arek = Developer(
        firstName = "Arek",
        lastName = "Mądry",
        email = "amadry@edu.cdv.pl",
        phone = "+48530304270",
        albumNumber = 27308,
        githubLink = "https://github.com/arix2000",
        linkedinLink = "https://www.linkedin.com/in/arkadiusz-madry/",
        photoUrl = R.drawable.arek_avatar
    )
    private val Janek = Developer(
        firstName = "Janek",
        lastName = "Rembikowski",
        email = "jrembikowski@edu.cdv.pl",
        phone = "+48530036192",
        albumNumber = 27264,
        githubLink = "https://github.com/joohnnyvv",
        linkedinLink = "https://www.linkedin.com/in/jan-rembikowski/",
        photoUrl = R.drawable.janek_avatar
    )
    private val Diana = Developer(
        firstName = "Diana",
        lastName = "Lehmann",
        email = "dlehmann@edu.cdv.pl",
        phone = "+48570281772",
        albumNumber = 27181,
        githubLink = "https://github.com/lunore",
        linkedinLink = "https://www.linkedin.com/in/dianalehmann/",
        photoUrl = R.drawable.diana_avatar
    )
    private val Maciej = Developer(
        firstName = "Maciej",
        lastName = "Kiełducki",
        email = "mkielducki@edu.cdv.pl",
        phone = "+48605385025",
        albumNumber = 26863,
        githubLink = "https://github.com/maciekkielducki",
        linkedinLink = "https://www.linkedin.com/in/maciej-kielducki/",
        photoUrl = R.drawable.maciej_avatar
    )

    val allDevelopers = listOf(Arek, Janek, Diana, Maciej)
}

object MapDefaults {
    val DEFAULT_MAP_UI_SETTINGS = MapUiSettings(
        compassEnabled = true,
        indoorLevelPickerEnabled = true,
        mapToolbarEnabled = false,
        myLocationButtonEnabled = false,
        rotationGesturesEnabled = false,
        scrollGesturesEnabled = false,
        scrollGesturesEnabledDuringRotateOrZoom = false,
        tiltGesturesEnabled = false,
        zoomControlsEnabled = false,
        zoomGesturesEnabled = false,
    )
}