import com.matin.happychat.ChatMessage
import com.matin.happychat.ChatServiceGrpcKt
import com.matin.happychat.service.ChatService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class ChatGrpcService(private val chatService: ChatService) : ChatServiceGrpcKt.ChatServiceCoroutineImplBase() {

    override fun chatStream(requests: Flow<ChatMessage>): Flow<ChatMessage> {
        return flow {
            requests.collect { request ->
                println("From client: ${request.message}")
                chatService.saveMessage(request)
                emit(
                    ChatMessage.newBuilder()
                        .setMessage("Echo: ${request.message}")
                        .build()
                )
            }
        }
    }
}
