package com.iteris.counterapp.core.extensions

import com.iteris.counterapp.core.formatters.MMddYYYYatHHmm
import java.util.Date

fun Date.toDateAndHour(): String = MMddYYYYatHHmm.format(this)
