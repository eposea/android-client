package xyz.savvamirzoyan.eposea.ui

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import xyz.savvamirzoyan.eposea.BuildConfig
import xyz.savvamirzoyan.eposea.data.mapper.CourseCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.InstitutionCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.InstitutionInfoCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.repository.CoursesRepository
import xyz.savvamirzoyan.eposea.data.repository.InstitutionInfoRepository
import xyz.savvamirzoyan.eposea.data.repository.InstitutionRepository
import xyz.savvamirzoyan.eposea.data.source.cloud.CourseCloudSource
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionCloudSource
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionInfoCloudSource
import xyz.savvamirzoyan.eposea.domain.interactor.CoursesInteractor
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInfoInteractor
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInteractor
import xyz.savvamirzoyan.eposea.domain.mapper.CourseDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.InstitutionDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.InstitutionInfoDataToDomainMapper
import xyz.savvamirzoyan.eposea.ui.mapper.CourseDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionInfoDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionInfoToolbarDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoursesViewModel
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionInfoViewModel
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionsViewModel

private const val BASE_URL = BuildConfig.SERVER_URL

class App : Application() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    lateinit var institutionsViewModel: InstitutionsViewModel
        private set

    lateinit var institutionInfoViewModel: InstitutionInfoViewModel
        private set

    lateinit var coursesViewModel: CoursesViewModel
        private set

    @OptIn(ExperimentalSerializationApi::class)
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
        val institutionInfoCloudSource = retrofit.create(InstitutionInfoCloudSource::class.java)
        val courseCloudSource = retrofit.create(CourseCloudSource::class.java)

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

        // Repository
        val institutionRepository = InstitutionRepository.Base(institutionCloudSource, institutionCloudToDataMapper)
        val institutionInfoRepository = InstitutionInfoRepository.Base(
            institutionInfoCloudSource, institutionInfoCloudToDataMapper
        )
        val coursesRepository = CoursesRepository.Base(courseCloudSource, courseCloudToDataMapper)

        // Interactors
        val institutionInteractor = InstitutionInteractor.Base(institutionRepository, institutionDataToDomainMapper)
        val institutionInfoInteractor = InstitutionInfoInteractor.Base(
            institutionInfoRepository, institutionInfoDataToDomainMapper
        )
        val coursesInteractor = CoursesInteractor.Base(coursesRepository, courseDataToDomainMapper)

        // ViewModels
        institutionsViewModel = InstitutionsViewModel(institutionInteractor, institutionDomainToWithCoursesUiMapper)
        institutionInfoViewModel = InstitutionInfoViewModel(
            institutionInfoInteractor,
            institutionInfoToolbarDomainToUiMapper,
            institutionInfoDomainToUiMapper,
            resourceManager
        )
        coursesViewModel = CoursesViewModel(coursesInteractor, courseDomainToUiMapper)
    }
}