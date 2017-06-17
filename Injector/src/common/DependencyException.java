/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Usuario
 */
public class DependencyException extends Exception{
    public DependencyException(Exception e){
        super(e);
    }
    public DependencyException(String message){
        super(message);
    }
}
