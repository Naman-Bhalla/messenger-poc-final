package com.scaler.messengerpocfinal.configs;

import com.scaler.messengerpocfinal.controllers.MessageReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter privateListenerAdapter,
                                                                       MessageListenerAdapter groupListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(privateListenerAdapter, new PatternTopic("privateChat"));
        container.addMessageListener(groupListenerAdapter, new PatternTopic("groupChat"));
        return container;
    }

    @Bean
    MessageListenerAdapter privateListenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receivePrivateMessage");
    }

    @Bean
    MessageListenerAdapter groupListenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveGroupMessage");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Using Jackson2JsonRedisSerializer for values
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }

}
