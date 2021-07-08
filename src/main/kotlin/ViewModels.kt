import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.jboss.resteasy.reactive.RestForm

// BsonCreator and BsonProperty are required to prevent decoding error
@MongoEntity(collection = "url_key_maps")
data class URLKeyMap @BsonCreator constructor(
    @BsonProperty("shortenedPath")
    val shortenedPath: String,
    @BsonProperty("originalURL")
    val originalURL: String,
) : ReactivePanacheMongoEntity()

data class URLWrapper(
    val url: String
)