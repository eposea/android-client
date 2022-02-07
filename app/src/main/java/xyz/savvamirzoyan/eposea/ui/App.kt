package xyz.savvamirzoyan.eposea.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import xyz.savvamirzoyan.eposea.data.mapper.CourseCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.InstitutionCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.repository.InstitutionRepository
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionCloudSource
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInteractor
import xyz.savvamirzoyan.eposea.domain.mapper.CourseDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.InstitutionDataToDomainMapper
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionDomainToWithCoursesUiMapper
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionViewModel
import xyz.savvamirzoyan.eposea.ui.viewmodel.ViewModelsFactory

private const val BASE_URL = "https://example.com/"

@ExperimentalSerializationApi
class App : Application() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private lateinit var institutionViewModel: InstitutionViewModel

    private val factory by lazy {
        ViewModelsFactory(institutionViewModel)
    }

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder().build()
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        // Other
        val resourceManager = ResourceManager.Base(applicationContext)

        // Sources
        val institutionCloudSource = retrofit.create(InstitutionCloudSource::class.java)

        // Mappers
        val courseCloudToDataMapper = CourseCloudToDataMapper.Base()
        val institutionCloudToDataMapper = InstitutionCloudToDataMapper.Base(courseCloudToDataMapper)
        val courseDataToDomainMapper = CourseDataToDomainMapper.Base()
        val institutionDataToDomainMapper = InstitutionDataToDomainMapper.Base(courseDataToDomainMapper)
        val institutionDomainToWithCoursesUiMapper = InstitutionDomainToWithCoursesUiMapper.Base(resourceManager)

        // Repository
        val institutionRepository = InstitutionRepository.Base(institutionCloudSource, institutionCloudToDataMapper)

        // Interactors
        val institutionInteractor = InstitutionInteractor.Base(institutionRepository, institutionDataToDomainMapper)

        // ViewModels
        institutionViewModel = InstitutionViewModel(institutionInteractor, institutionDomainToWithCoursesUiMapper)
    }

    fun <VM : CoreViewModel> viewModel(model: Class<VM>, owner: ViewModelStoreOwner): VM =
        ViewModelProvider(owner, factory)[model]
}