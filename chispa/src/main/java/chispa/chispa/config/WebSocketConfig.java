package chispa.chispa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * Configuration class to enable and configure WebSocket message broker.
 * <p>
 * This class sets up STOMP endpoints and message broker settings for real-time communication.
 * </p>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configure message broker options.
     * <p>
     * Enables a simple in-memory message broker and sets application destination prefixes.
     * </p>
     *
     * @param config the message broker registry to configure
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Enable simple broker for destinations prefixed with /topic
        config.setApplicationDestinationPrefixes("/app"); // Prefix for messages bound for @MessageMapping methods
    }

    /**
     * Register STOMP endpoints for WebSocket connections.
     * <p>
     * This defines the endpoint clients use to connect to the WebSocket server.
     * SockJS fallback is enabled for browsers that don’t support WebSocket.
     * </p>
     *
     * @param registry the registry to add STOMP endpoints to
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")                 // Register endpoint at /ws
                .setAllowedOriginPatterns("*")      // Allow all origins for WebSocket connections
                .withSockJS();                      // Enable SockJS fallback options
    }

    /**
     * Configure WebSocket transport settings such as message size and time limits.
     *
     * @param registration the registration to customize WebSocket transport options
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(1024 * 1024);    // Set max message size to 1MB
        registration.setSendTimeLimit(20 * 1000);         // Set send time limit to 20 seconds
        registration.setSendBufferSizeLimit(1024 * 1024); // Set send buffer size to 1MB
    }
}
