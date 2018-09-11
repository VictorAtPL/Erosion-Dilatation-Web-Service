/**
 * ProcessImage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tjee;

public interface ProcessImage extends java.rmi.Remote {
    public byte[] filter(byte[] inputBytes, java.lang.String filter, java.lang.String ext) throws java.rmi.RemoteException;
}
