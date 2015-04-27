package peopletraackr;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println("Starting...");
		long start_time = System.nanoTime();
		String file = "coding-test-data.txt";

		Worker w = new Worker(file);
		ForkJoinPool forkJoinPool = new ForkJoinPool();


		Integer nl = forkJoinPool.invoke(w);
		System.out.println("Read : "+nl + " lines");
		long end_time = System.nanoTime();
		double diff = ((double)(end_time - start_time))/1000000000.0;
		System.out.println("Time: "+diff + " seconds");
	}

}
