package com.demo.letstrack.shared

import android.text.format.DateFormat
import com.demo.letstrack.view.LetsTrackApplication
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {

    companion object{

        internal fun getQuestionFormatDateTime(dateTime: Long): String {
            val date = Date(dateTime)
            val localDateFormat = SimpleDateFormat(getSystemDateFormat(), Locale.getDefault())
            val timeZone = Calendar.getInstance().timeZone.id
            val localFormatDate = Date(date.time + TimeZone.getTimeZone(timeZone).getOffset(date.time))
            return localDateFormat.format(localFormatDate)
        }

        private fun getSystemDateFormat(): String {
            val dateFormat: Format = DateFormat.getDateFormat(LetsTrackApplication.instance.applicationContext)
            return (dateFormat as SimpleDateFormat).toLocalizedPattern()
        }


    }
}