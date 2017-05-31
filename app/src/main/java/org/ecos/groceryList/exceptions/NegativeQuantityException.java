package org.ecos.groceryList.exceptions;

public class NegativeQuantityException extends Throwable {
    public NegativeQuantityException(String message) {
        super(message);
    }
}
