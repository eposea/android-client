//package xyz.savvamirzoyan.eposea.data.repository
//
//import junit.framework.TestCase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ObsoleteCoroutinesApi
//import xyz.savvamirzoyan.eposea.data.mapper.LoginCloudToDataMapperTest
//import xyz.savvamirzoyan.eposea.data.mapper.RegistrationCloudToDataMapperTest
//import xyz.savvamirzoyan.eposea.data.mapper.RegistrationConfirmCloudToDataMapperTest
//import xyz.savvamirzoyan.eposea.data.repository.source.cloud.AuthServiceTest
//
//@OptIn(ObsoleteCoroutinesApi::class)
//class AuthRepositoryTest : TestCase() {
//
//    private val authService = AuthServiceTest()
//    private val loginCloudToDataMapper = LoginCloudToDataMapperTest()
//    private val registrationCloudToDataMapper = RegistrationCloudToDataMapperTest()
//    private val registrationConfirmCloudToDataMapper = RegistrationConfirmCloudToDataMapperTest()
//    private val tokenDataStoreRepository = TokenDataStoreRepositoryTest()
//    private val repository = AuthRepository.Base(
//        authService, loginCloudToDataMapper, registrationCloudToDataMapper,
//        registrationConfirmCloudToDataMapper, tokenDataStoreRepository,
//        CoroutineScope(Dispatchers.Main)
//    )
//
//    fun testSendRegisterCredentials() {
//
//        val input = Pair("email@email.com","password123")
//
//        assertEquals(repository.sendRegisterCredentials(input.first, input.second))
//    }
//
//    fun testSendConfirmationCode() {}
//
//    fun testCheckToken() {}
//
//    fun testLogin() {}
//
//    fun testIsEmailValid() {}
//
//    fun testIsPasswordValid() {}
//}