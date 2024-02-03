package com.example.lzzll.aspose;

import com.example.lzzll.aspose.common.constant.ThreadNotSafeVariable;
import com.example.lzzll.aspose.common.constant.ThreadSafeVariable;
import com.example.lzzll.aspose.service.RetryableService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AsposeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
@Slf4j
public class AsposeApplicationTests {

	@Autowired
	private RetryableService retryableService;

	@Test
	public void testRetry() throws Exception {
		for (int i = 0; i < 3; i++) {
			String s = retryableService.testRetry();
			System.out.println(s);
		}
	}

	@Test
	public void testPrivateData() throws Exception {
		for (int i = 0; i < 3; i++) {
			ThreadSafeVariable instance = ThreadSafeVariable.getInstance();
			int data = instance.getGlobalVariable();
			System.out.println(data);
			instance.setGlobalVariable(data+2);
			System.out.println(instance.getGlobalVariable());
		}
	}

	@Test
	public void testMultiThread() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					ThreadSafeVariable instance = ThreadSafeVariable.getInstance();
					int data = instance.getGlobalVariable();
					System.out.println(Thread.currentThread().getId()+":"+data);
					instance.setGlobalVariable(data+2);
					System.out.println(Thread.currentThread().getId()+":"+instance.getGlobalVariable());
				}
			});
		}
	}

	@Test
	public void testMultiThreadNotSafe() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Integer> list = Lists.newArrayList();
		for (int i = 0; i < 3; i++) {
			// 不带返回值
//			executorService.submit(()->{
//				test();
//			});
			// 带返回值
			Future<Integer> futures = executorService.submit(this::test);
			if (futures.isDone()){
				list.add(futures.get());
			}
		}
		list.forEach(System.out::println);
	}

	public Integer test(){
		ThreadNotSafeVariable variable = new ThreadNotSafeVariable();
		int data = variable.getGlobalVariable();
		System.out.println(Thread.currentThread().getId()+":"+data);
		variable.setGlobalVariable(2);
		if (Thread.currentThread().getId() == 34){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getId()+":"+variable.getGlobalVariable());
		variable.setGlobalVariable(5);
		System.out.println(Thread.currentThread().getId()+":"+variable.getGlobalVariable());
		return variable.getGlobalVariable();
	}

}
