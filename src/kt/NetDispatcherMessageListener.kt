package kt

class NetDispatcherMessageListener : INetDispatchMessageListener {

    override fun dispatchMessage(message: Any) {
        println("NetDispatcherMessageListener ----> dispatchMessage")
    }
}