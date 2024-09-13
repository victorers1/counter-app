package com.iteris.counterapp.domain.entities

import com.iteris.counterapp.R

sealed class SocialNetworkEntity(val name: String, val profileUrl: String, val drawableId: Int) {

    data object LinkedIn : SocialNetworkEntity(
        "LinkedIn",
        "https://www.linkedin.com/in/victor-emanuel-809b7a196/",
        R.drawable.ic_launcher_background,
    )

    data object GitHub : SocialNetworkEntity(
        "GitHub",
        "https://github.com/victorers1/",
        R.drawable.ic_launcher_background,
    )

    data object Instagram : SocialNetworkEntity(
        "Instagram",
        "https://www.instagram.com/victorers2/",
        R.drawable.ic_launcher_background,
    )
}
