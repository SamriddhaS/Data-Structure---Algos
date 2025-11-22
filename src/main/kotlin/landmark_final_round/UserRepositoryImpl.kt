package android_questions.landmark_final_round

sealed class Result{
    class Success(allUsers:List<User>): Result()
    class Error(e: Exception): Result()
}

class UserRepositoryImpl : UserRepository {

    val roomDbManager  = RoomDbManager()
    val remoteNeworkManager  = RemoteNeworkManager()

    override suspend fun fetchAllUsers(forceRefresh: Boolean): Result {
        try {
            if (forceRefresh){
                val allUsers = remoteNeworkManager.getAllUsers()
                roomDbManager.addUsers(allUsers)
            }
            val users = roomDbManager.getAllUsers()
            return Result.Success(users)
        }catch (e: Exception){
            return Result.Error(e)
        }
    }
}