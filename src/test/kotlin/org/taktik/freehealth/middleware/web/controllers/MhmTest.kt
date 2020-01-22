package org.taktik.freehealth.middleware.web.controllers

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse

import java.io.File
import java.time.Instant

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MhmTest : EhealthTest() {
        @LocalServerPort
        private val port: Int = 0

        @Autowired
        private val restTemplate: TestRestTemplate? = null


        private fun assertErrors(scenario: String, error: String, results: MemberDataResponse?) {
            println(scenario + "\n====================")
        }

        private fun assertErrors(scenario: String, error: String, results: List<MemberDataResponse?>) {
            println(scenario + "\n====================")
            results.forEachIndexed { index, it ->
            }
        }

        private fun assertErrors(scenario: String, errors: List<String>, results: List<MemberDataResponse?>) {
            println(scenario + "\n====================")
            results.forEachIndexed { index, it ->
            }
        }

        @Before
        fun setUp() {
            try {
                System.setProperty("mycarenet.license.password", this.javaClass.getResourceAsStream("/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText()) } catch (e: NullPointerException) {
                System.setProperty("mycarenet.license.password", File("src/test/resources/org/taktik/freehealth/middleware/mycarenet.license").reader(Charsets.UTF_8).readText())
            }
        }

        @Test
        fun getGeneralInsurability() {
            val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
            val genIns = this.restTemplate.exchange("http://localhost:$port/mda/${"74010414733"}?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpName=$name1&hcpQuality=${"doctor"}",
                                                    HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
            Assertions.assertThat(genIns != null)
        }

    @Test
    fun sendSubscription(){
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val sendSubscriptionResponse = this.restTemplate.exchange("http://localhost:$port/mhm/send/{patientSsin}/verbose", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
    }

}
