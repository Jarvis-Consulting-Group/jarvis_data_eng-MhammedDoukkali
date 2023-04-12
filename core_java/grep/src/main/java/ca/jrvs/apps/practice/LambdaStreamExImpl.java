package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExImpl implements LambdaStreamEx {
    @Override
    public Stream<String> createStrStream(String... strings) {
     return Arrays.stream(strings);

    }

    @Override
    public Stream<String> toUpperCase(String... strings) {

        return createStrStream(strings).map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {

        return stringStream.filter(str-> !str.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {

        return Arrays.stream(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {

        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        // Need to box the ints into Integers before converting to a list
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {

        return IntStream.range(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {

        return intStream.asDoubleStream().map(num->Math.sqrt(num));
    }

    @Override
    public IntStream getOdd(IntStream intStream) {

        return intStream.filter(num-> num % 2 !=0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {

        return (String s) -> System.out.println(prefix + s + suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {

        Stream.of(messages).forEach(printer);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {

        getOdd(intStream).forEach(num -> printer.accept(Integer.toString(num)));
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {

        return ints.flatMap(arr-> arr.stream().map(num-> num*num));
    }
}
