package com.example.walap.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri


fun resourceUri(context: Context,resourceId: Int): Uri = with(context.resources) {
    Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(getResourcePackageName(resourceId))
        .appendPath(getResourceTypeName(resourceId))
        .appendPath(getResourceEntryName(resourceId))
        .build()
}
