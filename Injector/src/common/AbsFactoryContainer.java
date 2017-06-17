/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Map;

/**
 *
 * @author Usuario
 */
public abstract class AbsFactoryContainer {
    
    public Object[] paramFromFactory( Object[] params, Map cMap){
                Object[] values = new Object[params.length];
        int j = 0;
        for (Object param1 : params) {
            if (cMap.containsKey(param1)) {
                values[j] = cMap.get( param1);
                j++;
            }
        }
        return values;
    }
}
