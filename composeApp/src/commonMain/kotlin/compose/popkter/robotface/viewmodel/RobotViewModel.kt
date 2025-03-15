package compose.popkter.robotface.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import compose.popkter.robotface.status.Ordinary
import compose.popkter.robotface.status.RobotStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RobotViewModel : ViewModel() {

    private val mRobotStatusFlow = MutableStateFlow<RobotStatus>(Ordinary)
    val robotStatus = mRobotStatusFlow.asStateFlow()

    fun updateStatus(robotStatus: RobotStatus) {
        viewModelScope.launch {
            mRobotStatusFlow.emit(robotStatus)
        }
    }
}