import org.eclipse.microprofile.config.inject.ConfigProperty
import java.net.URI
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
class URLShortenerController constructor(
    private val randomStringGenerator: RandomStringGenerator
){
    @Inject
    lateinit var service: URLShortenerService

    @ConfigProperty(name = "SERVER_BASE_URL")
    lateinit var serverlessBaseURL : String

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ping")
    fun ping(): String {
        return "{ \"message\": \"Hello from quarkus\"}"
    }

    /**
     * Shorten the given url
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/shorten")
    fun shorten(wrapper: URLWrapper): URLWrapper {
        val randomString = randomStringGenerator.generate()
        val shortenedURL = "$serverlessBaseURL/$randomString"
        val withHttp = wrapper.url.prependIfNotExist("https://", "http")
        service.store(randomString, withHttp)
        return URLWrapper(shortenedURL)
    }

    /**
     * Redirect user to the original url
     */
    @GET
    @Path("/{url}")
    fun expand(@PathParam("url") url: String): Response {
        val expandedURL = service.retrieve(url) ?: "$serverlessBaseURL/not-found"
        val uri = URI.create(expandedURL)
        return Response.temporaryRedirect(uri).build()
    }

}
