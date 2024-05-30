package mx.com.lgonzalez.pruebatecnica.utils.extensions

import android.util.Log

fun String.initials() : String{
    Log.e("prb", "initials: $this", )

    val text = this.trim()
    if (text.isEmpty()){
        return ""
    }

    val words = text.split(" ")
    val firstWord =  words.first()
    val firstChar =  firstWord.firstOrNull()

    if (firstChar == null ||!firstChar.isLetter())
        return ""

    val firstCharUpperCase = firstChar.uppercase()

    if (words.size == 1)
        return firstCharUpperCase

    val secondWord = words.getOrNull(1)

    val secondChar = secondWord?.firstOrNull()
    val secondCharUpperCase = secondChar?.uppercase() ?: ""

    return "${firstCharUpperCase}${secondCharUpperCase}"
}