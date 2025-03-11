package compose.popkter.robotface

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun isLandScape(): Boolean