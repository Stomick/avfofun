package evfor.`fun`.skvader.utils.socket

import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.OkHttpClient
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit

class ChatSocket {

    private var chat: Socket? = null

    fun provideSocketChat(url: String ): Socket {
        if (chat != null) {
            return chat as Socket
        }
        val options = IO.Options()
        options.transports = arrayOf("polling")
        options.reconnectionDelay = 60000
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

        IO.setDefaultOkHttpCallFactory(okHttpClient)
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
        try {
            chat = IO.socket(url)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        chat!!.connect()
        return chat as Socket
    }
}
