/**
 * ProcessImageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tjee;

public class ProcessImageServiceLocator extends org.apache.axis.client.Service implements tjee.ProcessImageService {

    public ProcessImageServiceLocator() {
    }


    public ProcessImageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProcessImageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProcessImage
    private java.lang.String ProcessImage_address = Config.server_wsdl;

    public java.lang.String getProcessImageAddress() {
        return ProcessImage_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProcessImageWSDDServiceName = "ProcessImage";

    public java.lang.String getProcessImageWSDDServiceName() {
        return ProcessImageWSDDServiceName;
    }

    public void setProcessImageWSDDServiceName(java.lang.String name) {
        ProcessImageWSDDServiceName = name;
    }

    public tjee.ProcessImage getProcessImage() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProcessImage_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProcessImage(endpoint);
    }

    public tjee.ProcessImage getProcessImage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            tjee.ProcessImageSoapBindingStub _stub = new tjee.ProcessImageSoapBindingStub(portAddress, this);
            _stub.setPortName(getProcessImageWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProcessImageEndpointAddress(java.lang.String address) {
        ProcessImage_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (tjee.ProcessImage.class.isAssignableFrom(serviceEndpointInterface)) {
                tjee.ProcessImageSoapBindingStub _stub = new tjee.ProcessImageSoapBindingStub(new java.net.URL(ProcessImage_address), this);
                _stub.setPortName(getProcessImageWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProcessImage".equals(inputPortName)) {
            return getProcessImage();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tjee", "ProcessImageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tjee", "ProcessImage"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProcessImage".equals(portName)) {
            setProcessImageEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
