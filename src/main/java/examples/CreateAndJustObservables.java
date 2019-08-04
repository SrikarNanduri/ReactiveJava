package examples;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Srikar on Aug, 2019
 */
public class CreateAndJustObservables {

    public static void main(String[] args){
        // Create is one of the Observable factories to create observables.

        // Source without lambda expressions
        Observable<String> sourceWithoutLambda = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    emitter.onNext("first");
                    emitter.onNext("second");
                    emitter.onNext("third");
                    emitter.onComplete();
                } catch (Exception e){
                    emitter.onError(e);
                }
            }
        });

        // Source with lambda expressions
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("first");
                emitter.onNext("second");
                emitter.onNext("third");
                emitter.onComplete();
            } catch (Exception e){
                emitter.onError(e);
            }
        });

        // Observable.just is a pretty straightforward operator. It wraps you input as an observable and returns them as items.
        Observable<String> source2 = Observable.just("first", "second", "third");


        // Observer without lambda expressions
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("OnNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("OnComplete is called");
            }
        };

        System.out.println("--------------------------------Source without Lambda expressions---------------------------------------");
        sourceWithoutLambda.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("OnComplete is called"));

        System.out.println("--------------------------------Source1 emissions---------------------------------------");
        source.subscribe(observer);
        System.out.println("--------------------------------Source2 emissions---------------------------------------");
        source2.subscribe(observer);

        System.out.println("--------------------------------Using Lambda expressions for observer to reduce code---------------------------------------");
        source.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("OnComplete is called"));


    }

}
