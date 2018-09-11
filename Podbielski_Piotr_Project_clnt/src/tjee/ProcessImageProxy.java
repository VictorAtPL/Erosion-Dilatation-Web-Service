package tjee;

public class ProcessImageProxy implements tjee.ProcessImage {
  private String _endpoint = null;
  private tjee.ProcessImage processImage = null;
  
  public ProcessImageProxy() {
    _initProcessImageProxy();
  }
  
  public ProcessImageProxy(String endpoint) {
    _endpoint = endpoint;
    _initProcessImageProxy();
  }
  
  private void _initProcessImageProxy() {
    try {
      processImage = (new tjee.ProcessImageServiceLocator()).getProcessImage();
      if (processImage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)processImage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)processImage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (processImage != null)
      ((javax.xml.rpc.Stub)processImage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public tjee.ProcessImage getProcessImage() {
    if (processImage == null)
      _initProcessImageProxy();
    return processImage;
  }
  
  public byte[] filter(byte[] inputBytes, java.lang.String filter, java.lang.String ext) throws java.rmi.RemoteException{
    if (processImage == null)
      _initProcessImageProxy();
    return processImage.filter(inputBytes, filter, ext);
  }
  
  
}