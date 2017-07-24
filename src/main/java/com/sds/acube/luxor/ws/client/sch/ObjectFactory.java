
package com.sds.acube.luxor.ws.client.sch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sds.acube.luxor.ws.client.sch package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Page_QNAME = new QName("http://ws.sch.collaboration.luxor.acube.sds.com/", "page");
    private final static QName _GetPagingListResponse_QNAME = new QName("http://ws.sch.collaboration.luxor.acube.sds.com/", "getPagingListResponse");
    private final static QName _GetPagingList_QNAME = new QName("http://ws.sch.collaboration.luxor.acube.sds.com/", "getPagingList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sds.acube.luxor.ws.client.sch
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Page }
     * 
     */
    public Page createPage() {
        return new Page();
    }

    /**
     * Create an instance of {@link GetPagingList }
     * 
     */
    public GetPagingList createGetPagingList() {
        return new GetPagingList();
    }

    /**
     * Create an instance of {@link GetPagingListResponse }
     * 
     */
    public GetPagingListResponse createGetPagingListResponse() {
        return new GetPagingListResponse();
    }

    /**
     * Create an instance of {@link SearchVO }
     * 
     */
    public SearchVO createSearchVO() {
        return new SearchVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Page }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.sch.collaboration.luxor.acube.sds.com/", name = "page")
    public JAXBElement<Page> createPage(Page value) {
        return new JAXBElement<Page>(_Page_QNAME, Page.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPagingListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.sch.collaboration.luxor.acube.sds.com/", name = "getPagingListResponse")
    public JAXBElement<GetPagingListResponse> createGetPagingListResponse(GetPagingListResponse value) {
        return new JAXBElement<GetPagingListResponse>(_GetPagingListResponse_QNAME, GetPagingListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPagingList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.sch.collaboration.luxor.acube.sds.com/", name = "getPagingList")
    public JAXBElement<GetPagingList> createGetPagingList(GetPagingList value) {
        return new JAXBElement<GetPagingList>(_GetPagingList_QNAME, GetPagingList.class, null, value);
    }

}
