package mx.com.lgonzalez.pruebatecnica.domain.usecases

interface GetInitialsUseCase {

    operator fun invoke(text: String): String
}