# redis session, redis cache

spring boot 기본 connection factory를 사용하지 않고 각자의 connection factory를 사용해 연결하는 예제.

## default RedisConnectionFactory 생성이 반드시 필요하다

Spring data redis autoconfigration을 사용하게 되면 기본적으로 redisClient 객체가 생성되며 해당 객체는 redisConnectionFactory를 사용하게 된다. default redisConnectionFactory bean이 존재하지 않다면 오류가 발생되며 정상동작하지 않기 때문에 기본 redisConnectionFactory를 생성하고 **@Primary** 어노테이션을 붙여 주도록 한다.

## ConfigureRedisAction.NO_OP 고찰

Elastic cache를 사용하려면 아래와 같은 코드를 입력해야한다.

~~~java
@Bean
ConfigureRedisAction configureRedisAction() {
    return ConfigureRedisAction.NO_OP;
}
~~~

해당 코드의 경우 클라이언트 상에서 redis config를 설정할 수 없게 만드는 옵션이다. Elastic cache의 경우 클라이언트에서 redis config를 기본적으로 막아 놓고 있기 때문에 해당 옵션을 사용해야 Elastic cache에 접속할 수 있다.

하지만 세션이 delete 되었을때 redis에서 이벤트를 받아서 나머지 데이터를 삭제해야 하는 기능이 spring session에 설정되어 있는데 해당 config를 사용하여 설정할 수 없으면 redis상에 데이터 가 삭제되지 않아 결국에는 redis를 제기동해야 하는 사태가 발생한다.

아래 링크를 참조하여 redis 설정이 되도록 설정해 보자.

> https://aws.amazon.com/ko/premiumsupport/knowledge-center/elasticache-redis-keyspace-notifications/ 

다른 방법으로 Spring session repository를 변경하는 방법으로 구현할 수 있는데 아래 블로그를 참조하자.

> https://ubot.tistory.com/entry/Spring-session-data-redis-accept

~~~redis
"spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sam"
"spring:session:sessions:expires:bdc76d78-a754-4ba6-a291-aedb96ba10a2"
"spring:session:expirations:1651933860000"
"spring:session:sessions:bdc76d78-a754-4ba6-a291-aedb96ba10a2"
~~~

~~~redis
"spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sam"
"spring:session:expirations:1651933860000"
"spring:session:sessions:bdc76d78-a754-4ba6-a291-aedb96ba10a2"
~~~

~~~redis
"spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sam"
"spring:session:sessions:bdc76d78-a754-4ba6-a291-aedb96ba10a2"
~~~

~~~redis
"spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sam"
~~~

~~~redis
1) "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:sam"
2) "spring:session:expirations:1651935000000"
3) "spring:session:sessions:19c711e9-7a40-4ab9-a3ba-f3e5d80296d1"
4) "spring:session:sessions:expires:19c711e9-7a40-4ab9-a3ba-f3e5d80296d1"
~~~

~~~redis
1) "spring:session:expirations:1651935000000"
2) "spring:session:sessions:19c711e9-7a40-4ab9-a3ba-f3e5d80296d1"
~~~

~~~reids
1) "spring:session:sessions:19c711e9-7a40-4ab9-a3ba-f3e5d80296d1"
~~~
