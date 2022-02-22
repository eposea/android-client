package xyz.savvamirzoyan.eposea.ui

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import xyz.savvamirzoyan.eposea.BuildConfig
import xyz.savvamirzoyan.eposea.data.mapper.*
import xyz.savvamirzoyan.eposea.data.repository.AuthRepository
import xyz.savvamirzoyan.eposea.data.repository.CoursesRepository
import xyz.savvamirzoyan.eposea.data.repository.InstitutionInfoRepository
import xyz.savvamirzoyan.eposea.data.repository.InstitutionRepository
import xyz.savvamirzoyan.eposea.data.source.cloud.AuthService
import xyz.savvamirzoyan.eposea.data.source.cloud.CourseCloudSource
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionCloudSource
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionInfoCloudSource
import xyz.savvamirzoyan.eposea.domain.interactor.*
import xyz.savvamirzoyan.eposea.domain.mapper.*
import xyz.savvamirzoyan.eposea.ui.mapper.*
import xyz.savvamirzoyan.eposea.ui.viewmodel.*

private const val BASE_URL = BuildConfig.SERVER_URL
private const val DATASTORE_NAME = "datastore"

class App : Application() {

    private val json = Json { ignoreUnknownKeys = true }

    lateinit var splashViewModel: SplashViewModel
        private set

    lateinit var registerViewModel: RegisterViewModel
        private set

    lateinit var institutionsViewModel: InstitutionsViewModel
        private set

    lateinit var institutionInfoViewModel: InstitutionInfoViewModel
        private set

    lateinit var coursesViewModel: CoursesViewModel
        private set

    @OptIn(ExperimentalSerializationApi::class, kotlinx.coroutines.ObsoleteCoroutinesApi::class)
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

        // Sources and services
        val institutionCloudSource = retrofit.create(InstitutionCloudSource::class.java)
        val institutionInfoCloudSource = retrofit.create(InstitutionInfoCloudSource::class.java)
        val courseCloudSource = retrofit.create(CourseCloudSource::class.java)
        val registrationService = retrofit.create(AuthService::class.java)

        // Mappers
        val courseCloudToDataMapper = CourseCloudToDataMapper.Base()
        val institutionCloudToDataMapper = InstitutionCloudToDataMapper.Base(courseCloudToDataMapper)
        val courseDataToDomainMapper = CourseDataToDomainMapper.Base()
        val institutionDataToDomainMapper = InstitutionDataToDomainMapper.Base()
        val institutionDomainToWithCoursesUiMapper = InstitutionDomainToUiMapper.Base(resourceManager)
        val institutionInfoCloudToDataMapper = InstitutionInfoCloudToDataMapper.Base()
        val institutionInfoDataToDomainMapper = InstitutionInfoDataToDomainMapper.Base()
        val institutionInfoToolbarDomainToUiMapper = InstitutionInfoToolbarDomainToUiMapper.Base(resourceManager)
        val institutionInfoDomainToUiMapper = InstitutionInfoDomainToUiMapper.Base(resourceManager)
        val courseDomainToUiMapper = CourseDomainToUiMapper.Base(resourceManager)
        val editTextStatusDomainToUiMapper = EditTextStatusDomainToUiMapper.Base(resourceManager)
        val registrationCloudToDataMapper = RegistrationCloudToDataMapper.Base()
        val registrationConfirmCloudToDataMapper = RegistrationConfirmCloudToDataMapper.Base()
        val registrationDataToDomainMapper = RegistrationDataToDomainMapper.Base()
        val registrationConfirmDataToDomainMapper = RegistrationConfirmDataToDomainMapper.Base()

        // Repository
        val institutionRepository = InstitutionRepository.Base(institutionCloudSource, institutionCloudToDataMapper)
        val institutionInfoRepository = InstitutionInfoRepository.Base(
            institutionInfoCloudSource, institutionInfoCloudToDataMapper
        )
        val coursesRepository = CoursesRepository.Base(courseCloudSource, courseCloudToDataMapper)
        val authRepository = AuthRepository.Base(
            registrationService,
            registrationCloudToDataMapper,
            registrationConfirmCloudToDataMapper
        )

        // Interactors
        val institutionInteractor = InstitutionInteractor.Base(institutionRepository, institutionDataToDomainMapper)
        val institutionInfoInteractor = InstitutionInfoInteractor.Base(
            institutionInfoRepository, institutionInfoDataToDomainMapper
        )
        val coursesInteractor = CoursesInteractor.Base(coursesRepository, courseDataToDomainMapper)
        val registerInteractor = RegisterInteractor.Base(
            authRepository,
            registrationDataToDomainMapper,
            registrationConfirmDataToDomainMapper
        )
        val splashInteractor = SplashInteractor.Base(authRepository)

        // ViewModels
        institutionsViewModel = InstitutionsViewModel(institutionInteractor, institutionDomainToWithCoursesUiMapper)
        institutionInfoViewModel = InstitutionInfoViewModel(
            institutionInfoInteractor,
            institutionInfoToolbarDomainToUiMapper,
            institutionInfoDomainToUiMapper,
            resourceManager
        )
        coursesViewModel = CoursesViewModel(coursesInteractor, courseDomainToUiMapper)
        splashViewModel = SplashViewModel(splashInteractor)
        registerViewModel = RegisterViewModel(registerInteractor, editTextStatusDomainToUiMapper, resourceManager)
    }

    val Context.dataStore by preferencesDataStore(DATASTORE_NAME)
}