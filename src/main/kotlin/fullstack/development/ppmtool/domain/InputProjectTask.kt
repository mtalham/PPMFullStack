package fullstack.development.ppmtool.domain

import java.util.Date

class InputProjectTask(
    var summary: String,
    var acceptanceCriteria: String? = null,
    var status: String? = "TO_DO",
    var priority: Int? = 3,
    var dueDate: Date? = null
)