package com.example.reservationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//@EnableBinding(Sink.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationServerApplication.class, args);
    }

    //启动之后直接运行 runner  bean 创建直接注入reservationRepository
    @Bean
    ApplicationRunner sampleData(ReservationRepository reservationRepository) {
        return arg -> {
            Stream.of("wangliang", "baidu", "jinln", "java")
                    .forEach(name -> reservationRepository.save(new Reservation(name)));
            reservationRepository.findAll().forEach(System.out::println);
            //System.out::println 直接把方法注入进去用::   参数是一个对象
        };
    }
}

@Component
class ReservationConsumer{
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationConsumer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @StreamListener(Sink.INPUT)
    public void write(String name){
        reservationRepository.save(new Reservation(name));
    }
}


/**
 * controller 读取message  热部署
 * RefreshScope  哪些变化才初始化
 */
@RestController
@RefreshScope
class MessageController {
    private final String value;

    public MessageController(@Value("${message}") String value) {
        this.value = value;
    }

    @RequestMapping("/message")
    public String read() {
        return value;
    }
}


/**
 * DAO  //不用写controller 直接暴露出来
 * http://127.0.0.1:8080/reservations
 * <p>
 * http://127.0.0.1:8080/reservations/search/by-name?name=java
 */
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @RestResource(path = "by-name")
    Optional<Reservation> findByReservationName(@Param("name") String name);

    List<Reservation> findById(@Param("id") String name);
}

/**
 * entity  实体表 jpa数据表
 */
@Entity
class Reservation {
    @Id
    @GeneratedValue  //自增长
    private Long id;
    private String reservationName;

    public Reservation() {
    }

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationName='" + reservationName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }
}

