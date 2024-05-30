package mx.com.lgonzalez.pruebatecnica.domain.usecases

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface GetLocationUseCase {
    operator fun invoke() : Flow<Location?>
}