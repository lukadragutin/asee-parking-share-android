package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card.GiverRequestCard
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card.SeekerRequestCard
import hr.asee.android.template.data.model.common.service.Request
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User

@Composable
fun RequestList(
    requestList: Set<Request>,
    user : User,
    onForwardClickedRequestsCard: () -> Unit,

    ){
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        requestList.forEach{ request->
            if(user is Seeker){
                SeekerRequestCard(
                    request = request,
                    onForwardClickedRequestsCard = onForwardClickedRequestsCard
                )
            }
            else{
                GiverRequestCard(
                    request = request,
                    onForwardClickedRequestsCard = onForwardClickedRequestsCard
                )
            }
        }
    }
}