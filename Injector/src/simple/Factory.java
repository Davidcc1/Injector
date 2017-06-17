/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface Factory {
    Object create(Object... param) throws DependencyException;
}
