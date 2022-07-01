package com.example.lzzll.socketserver;

import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SocketServerDemoApplicationTests {

	@Test
	void contextLoads() {
		GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-945d777056f94cbfa6c5d098c54c90d4");
		goEasy.publish("test_application", "Hello, GoEasy!");

	}

}
