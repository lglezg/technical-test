package mx.com.lgonzalez.pruebatecnica.domain.usecases

import android.util.Log

class GetInitialsUseCaseImpl : GetInitialsUseCase {
    override fun invoke(text: String): String {
       if (text.isEmpty()){
           return ""
       }

        val words = text.split(" ")
        val firstWord =  words.first()

        val firstChar =  firstWord.first()
        if (!firstChar.isLetter())
            return ""

        val firstCharUpperCase = firstChar.uppercase()

        if (words.size == 1)
            return firstCharUpperCase

        val secondWord = words[1]
        Log.e("PRB", "invoke: $secondWord", )
        val secondCharUpperCase =  secondWord.first().uppercase()

        return "${firstCharUpperCase}${secondCharUpperCase}"


    }
}