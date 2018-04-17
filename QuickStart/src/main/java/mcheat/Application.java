package mcheat;

import mcheat.fork.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author wangjy
 * @date 2018/4/17
 */
public class Application {

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        ForkJoinSumCalculator calculator = new ForkJoinSumCalculator(numbers);
        Long result = new ForkJoinPool().invoke(calculator);
        System.out.println(result);
    }
}
