package dk.dd.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamProcessor
{
    @Value("${kafka.topic.input}")
    private String inputTopic;

    @Autowired
    private OddNumberProcessor oddPro;

    @Autowired
    private EvenNumberProcessor evenPro;

    // define streams configuration
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties)
    {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
        return new KafkaStreamsConfiguration(config);
    }

    @Bean
    public KStream<String, Long> kStream(StreamsBuilder kStreamBuilder)
    {
        // create a stream out of the input topic
        KStream<String, Long> stream = kStreamBuilder.stream(inputTopic);
        
        // create predicates to be used for splitting into two branches
        Predicate<String, Long> isDivisibleBy3 = (k, v) -> (v % 3) == 0;
        Predicate<String, Long> isDivisibleBy5 = (k, v) -> (v % 5) == 0;
        KStream<String, Long>[] branches = stream.branch(isDivisibleBy3, isDivisibleBy5);

        // different branches are processed differently and sent to different output topics
        branches[0].mapValues(v -> v * 2).to("doubled-output");
        branches[1].mapValues(v -> v * v).to("squared-output");

        // create a new stream merging the branches
        Arrays.stream(branches)
                .forEach(branch -> branch.to("new-stream"));
        return stream;
    }
}
