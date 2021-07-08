import io.quarkus.mongodb.panache.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class URLKeyMapRepository: PanacheMongoRepository<URLKeyMap> {

}