package org.beru.dreammer.web.config;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.service.dto.MessageDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import java.time.*;

@Component
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:3000").setAllowedOriginPatterns("*");
        registry.addEndpoint("/chat").setAllowedOriginPatterns("*").setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                try {
                    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
                            StompHeaderAccessor.class);
                    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                        create(accessor);
                    }
                    if (StompCommand.SEND.equals(accessor.getCommand()) && accessor.getDestination().contains("send")) {
                        User user = create(accessor);
                        byte[] originalPayload = (byte[]) message.getPayload();
                        ObjectMapper objectMapper = new ObjectMapper();
                        MessageDto messageDto = (MessageDto) objectMapper.readValue(new String(originalPayload), MessageDto.class);
                        messageDto.setCreatedBy(user.getUsername());
                        messageDto.setCreatedAt(LocalDateTime.now());
                        message = MessageBuilder.createMessage(messageDto, accessor.toMessageHeaders());
                    }
                    return message;
                } catch (Exception e) {
                    throw new RestRequestEntityExceptionHandler(e.getMessage());
                }
            }
        });
    }

    private User create(StompHeaderAccessor accessor) {
        String token = accessor.getFirstNativeHeader("Authorization").split(" ")[1].trim();
        System.out.println(token);
        if (!jwtUtil.isValid(token)) {
            throw new RestRequestEntityExceptionHandler("This user is not authorized");
        }
        String username = jwtUtil.getSub(token);
        User user = (User) userDetailsService.loadUserByUsername(username);
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities());
        accessor.setUser(authenticationToken);
        return user;
    }

}
