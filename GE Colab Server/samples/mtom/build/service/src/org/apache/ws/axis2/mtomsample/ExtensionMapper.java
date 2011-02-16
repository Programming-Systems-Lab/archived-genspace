
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:19:26 CET)
 */

        
            package org.apache.ws.axis2.mtomsample;
        
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.w3.org/2005/05/xmlmime".equals(namespaceURI) &&
                  "contentType_type0".equals(typeName)){
                   
                            return  org.w3.www._2005._05.xmlmime.ContentType_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ws.apache.org/axis2/mtomsample/".equals(namespaceURI) &&
                  "AttachmentType".equals(typeName)){
                   
                            return  org.apache.ws.axis2.mtomsample.AttachmentType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.w3.org/2005/05/xmlmime".equals(namespaceURI) &&
                  "base64Binary".equals(typeName)){
                   
                            return  org.w3.www._2005._05.xmlmime.Base64Binary.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    