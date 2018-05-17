/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author Albert
 */
public class Variables {
    //Map amb els usuaris que es troben a la sessió de connexió al servidor
    private static final Map<Integer, String> usuarisSessio = new HashMap<>();
    
    public void addUserSession(int id, String token){
        usuarisSessio.put(id,token);
    }
    
    public void removeUserSession(int id, String token){
        usuarisSessio.remove(id,token);
    }
    
    public boolean checkToken (String token){
        return usuarisSessio.containsValue(token); //nombreMap.get(K clave);
    }
    
    public int getUser (String token){
        return getKeyByValue(usuarisSessio , token);
    }
    
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Entry<T, E> entry : map.entrySet()) {
        if (Objects.equals(value, entry.getValue())) {
            return entry.getKey();
        }
    }
    return null;
}
}
