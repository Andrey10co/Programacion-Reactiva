package dyas.operadores;
// import reactor.core.publisher.Flux;
import io.reactivex.Observable;

public class MapOperatorExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .map(item -> item * 2)
                .subscribe(System.out::println);
    }
}

// public class MapOperatorExample {
//     public static void main(String[] args) {
//         Flux.just(1, 2, 3, 4, 5)
//                 .map(item -> item * 2)
//                 .subscribe(System.out::println);
//     }
// }