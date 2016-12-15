package com.shandrakov.cleanarchitecture.functionals;

/**
 *  The Either type is sometimes used to represent a value which is either correct or an error;
 *  by convention, the Left constructor is used to hold an error value and the Right constructor
 *  is used to hold a correct value (mnemonic: "right" also means "correct").
 */
public class Either<R,L> {

    public static <R> Either right(R right) {
        return new Either(right, null);
    }

    public static <L> Either left(L left) {
        return new Either(null, left);
    }

    private Either(R right, L left) {
        _right = right;
        _left = left;
    }

    public R right() {
        return _right;
    }

    public L left() {
        return _left;
    }

    public boolean isLeft() {
        return _left != null;
    }

    public boolean isRight() {
        return _right != null;
    }

    private final R _right;
    private final L _left;
}
