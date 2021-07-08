fun String.prependIfNotExist(intent: String, keyword: String): String {
    return if (!this.contains(keyword)) this.prependIndent(intent) else this
}