package hr.asee.android.template.data.model.common.service

class Request(
    val id: Int = generateId()

) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleRequest1 = Request(
    id = 2210
)

val exampleRequest2 = Request(
    id = 2211
)

val exampleRequest3 = Request(
    id = 2212
)

val exampleRequest4 = Request(
    id = 2213
)

val exampleRequest5 = Request(
    id = 221457
)



