import java.util.*
import javax.enterprise.context.ApplicationScoped
import kotlin.random.Random

// singleton
@ApplicationScoped
object RandomStringGenerator {
    private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun generate(): String = (0..10)
        .map { Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}