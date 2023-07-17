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


