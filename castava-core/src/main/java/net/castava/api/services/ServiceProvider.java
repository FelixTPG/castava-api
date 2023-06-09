package net.castava.api.services;

import lombok.experimental.UtilityClass;

import java.util.WeakHashMap;

@UtilityClass
public final class ServiceProvider {

    private static final WeakHashMap<Class<? extends IServiceProvider>, IServiceProvider> services = new WeakHashMap<>();

    public static <T extends IServiceProvider> void registerService(Class<T> service, T impl) {
        services.put(service, impl);
    }

    public static <T extends IServiceProvider> T getService(Class<T> clazz) {
        return (T) services.get(clazz);
    }

}
