package net.castava.api.services;

public interface IServiceProvider {

    default String getName() {
        return getClass().getSimpleName();
    }

}