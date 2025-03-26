import com.matin.happychat.ChatMessage
import com.matin.happychat.ChatServiceGrpcKt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class ChatGrpcService : ChatServiceGrpcKt.ChatServiceCoroutineImplBase() {

    override fun chatStream(requests: Flow<ChatMessage>): Flow<ChatMessage> {
        return flow {
            requests.collect { request ->
                println("From client: ${request.message}")  // Log the message received
                emit(
                    ChatMessage.newBuilder()
                        .setMessage("Echo: ${request.message}")  // Echo message back to the client
                        .build()
                )
            }
        }
    }
}
