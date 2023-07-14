package hr.asee.android.template.domain.model.common

data class UserCompact(
	val id: Int = 0,
	val login: String = "",
	val role: Role = Role.SEEKER
) {
	companion object {
		val EMPTY = UserCompact()
	}
}