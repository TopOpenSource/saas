package com.sdstc.oauth2.integration;

/**
 * 
 * @author cheng
 *
 */
public class IntegrationAuthenticationContext {

    private static ThreadLocal<IntegrationAuthentication> holder = new ThreadLocal<>();

    public static void set(IntegrationAuthentication integrationAuthentication){
        holder.set(integrationAuthentication);
    }

    public static IntegrationAuthentication get(){
        return holder.get();
    }

    public static void clear(){
        holder.remove();
    }
}
