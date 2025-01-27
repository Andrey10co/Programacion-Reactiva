package dyas.operadores;

// import io.reactivex.Observable;
import reactor.core.publisher.Flux;

// public class FlatMapOperatorExample {
//     public static void main(String[] args) {
//         Observable.just(1, 2, 3)
//                 .flatMap(item -> Observable.just(item * 10, item * 20))
//                 .subscribe(System.out::println);
//     }
// }


public class FlatMapOperatorExample {
    public static void main(String[] args) {
        Flux.just(1, 2, 3)
                .flatMap(item -> Flux.just(item * 10, item * 20))
                .subscribe(System.out::println);
    }
}