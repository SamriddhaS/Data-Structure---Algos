package android_questions.landmark_final_round

interface UserRepository {
    suspend fun fetchAllUsers(forceRefresh: Boolean):Result
}