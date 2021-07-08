import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class URlShortenerService {

    @Inject
    lateinit var repository: URLKeyMapRepository

    fun retrieve(shortened: String): String? {
        val entity = repository.find("shortenedPath", shortened).firstResult<URLKeyMap>()
        return entity?.originalURL
    }

    fun store(shortened: String, originalUrl: String) {
        repository.persist(URLKeyMap(shortened, originalUrl))
    }
}