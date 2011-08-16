
package org.geworkbench.components.genspace.server.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geworkbench.components.genspace.server.stubs package. 
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

    private final static QName _CreateNetworkResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "createNetworkResponse");
    private final static QName _GetProfilesByNetwork_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getProfilesByNetwork");
    private final static QName _UpdateNetworkVisibilityResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "updateNetworkVisibilityResponse");
    private final static QName _JoinNetwork_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "joinNetwork");
    private final static QName _GetAllNetworksResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getAllNetworksResponse");
    private final static QName _UpdateNetworkVisibility_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "updateNetworkVisibility");
    private final static QName _RejectNetworkRequest_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "rejectNetworkRequest");
    private final static QName _JoinNetworkResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "joinNetworkResponse");
    private final static QName _GetNumberOfNetworkRequests_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getNumberOfNetworkRequests");
    private final static QName _CreateNetwork_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "createNetwork");
    private final static QName _LeaveNetworkResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "leaveNetworkResponse");
    private final static QName _GetNumberOfNetworkRequestsResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getNumberOfNetworkRequestsResponse");
    private final static QName _LeaveNetwork_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "leaveNetwork");
    private final static QName _AcceptNetworkRequest_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "acceptNetworkRequest");
    private final static QName _GetMyNetworks_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getMyNetworks");
    private final static QName _GetNetworkRequests_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getNetworkRequests");
    private final static QName _GetNetworkRequestsResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getNetworkRequestsResponse");
    private final static QName _GetMyNetworksResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getMyNetworksResponse");
    private final static QName _RejectNetworkRequestResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "rejectNetworkRequestResponse");
    private final static QName _User_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "user");
    private final static QName _AcceptNetworkRequestResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "acceptNetworkRequestResponse");
    private final static QName _GetProfilesByNetworkResponse_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getProfilesByNetworkResponse");
    private final static QName _GetAllNetworks_QNAME = new QName("http://server.genspace.components.geworkbench.org/", "getAllNetworks");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geworkbench.components.genspace.server.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JoinNetwork }
     * 
     */
    public JoinNetwork createJoinNetwork() {
        return new JoinNetwork();
    }

    /**
     * Create an instance of {@link GetMyNetworksResponse }
     * 
     */
    public GetMyNetworksResponse createGetMyNetworksResponse() {
        return new GetMyNetworksResponse();
    }

    /**
     * Create an instance of {@link UpdateNetworkVisibilityResponse }
     * 
     */
    public UpdateNetworkVisibilityResponse createUpdateNetworkVisibilityResponse() {
        return new UpdateNetworkVisibilityResponse();
    }

    /**
     * Create an instance of {@link JoinNetworkResponse }
     * 
     */
    public JoinNetworkResponse createJoinNetworkResponse() {
        return new JoinNetworkResponse();
    }

    /**
     * Create an instance of {@link GetNumberOfNetworkRequests }
     * 
     */
    public GetNumberOfNetworkRequests createGetNumberOfNetworkRequests() {
        return new GetNumberOfNetworkRequests();
    }

    /**
     * Create an instance of {@link AcceptNetworkRequestResponse }
     * 
     */
    public AcceptNetworkRequestResponse createAcceptNetworkRequestResponse() {
        return new AcceptNetworkRequestResponse();
    }

    /**
     * Create an instance of {@link RejectNetworkRequest }
     * 
     */
    public RejectNetworkRequest createRejectNetworkRequest() {
        return new RejectNetworkRequest();
    }

    /**
     * Create an instance of {@link UserNetwork }
     * 
     */
    public UserNetwork createUserNetwork() {
        return new UserNetwork();
    }

    /**
     * Create an instance of {@link Network }
     * 
     */
    public Network createNetwork() {
        return new Network();
    }

    /**
     * Create an instance of {@link GetAllNetworksResponse }
     * 
     */
    public GetAllNetworksResponse createGetAllNetworksResponse() {
        return new GetAllNetworksResponse();
    }

    /**
     * Create an instance of {@link RejectNetworkRequestResponse }
     * 
     */
    public RejectNetworkRequestResponse createRejectNetworkRequestResponse() {
        return new RejectNetworkRequestResponse();
    }

    /**
     * Create an instance of {@link CreateNetworkResponse }
     * 
     */
    public CreateNetworkResponse createCreateNetworkResponse() {
        return new CreateNetworkResponse();
    }

    /**
     * Create an instance of {@link LeaveNetwork }
     * 
     */
    public LeaveNetwork createLeaveNetwork() {
        return new LeaveNetwork();
    }

    /**
     * Create an instance of {@link GetMyNetworks }
     * 
     */
    public GetMyNetworks createGetMyNetworks() {
        return new GetMyNetworks();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetNetworkRequestsResponse }
     * 
     */
    public GetNetworkRequestsResponse createGetNetworkRequestsResponse() {
        return new GetNetworkRequestsResponse();
    }

    /**
     * Create an instance of {@link CreateNetwork }
     * 
     */
    public CreateNetwork createCreateNetwork() {
        return new CreateNetwork();
    }

    /**
     * Create an instance of {@link GetNumberOfNetworkRequestsResponse }
     * 
     */
    public GetNumberOfNetworkRequestsResponse createGetNumberOfNetworkRequestsResponse() {
        return new GetNumberOfNetworkRequestsResponse();
    }

    /**
     * Create an instance of {@link GetProfilesByNetwork }
     * 
     */
    public GetProfilesByNetwork createGetProfilesByNetwork() {
        return new GetProfilesByNetwork();
    }

    /**
     * Create an instance of {@link UpdateNetworkVisibility }
     * 
     */
    public UpdateNetworkVisibility createUpdateNetworkVisibility() {
        return new UpdateNetworkVisibility();
    }

    /**
     * Create an instance of {@link AcceptNetworkRequest }
     * 
     */
    public AcceptNetworkRequest createAcceptNetworkRequest() {
        return new AcceptNetworkRequest();
    }

    /**
     * Create an instance of {@link LeaveNetworkResponse }
     * 
     */
    public LeaveNetworkResponse createLeaveNetworkResponse() {
        return new LeaveNetworkResponse();
    }

    /**
     * Create an instance of {@link GetProfilesByNetworkResponse }
     * 
     */
    public GetProfilesByNetworkResponse createGetProfilesByNetworkResponse() {
        return new GetProfilesByNetworkResponse();
    }

    /**
     * Create an instance of {@link GetNetworkRequests }
     * 
     */
    public GetNetworkRequests createGetNetworkRequests() {
        return new GetNetworkRequests();
    }

    /**
     * Create an instance of {@link GetAllNetworks }
     * 
     */
    public GetAllNetworks createGetAllNetworks() {
        return new GetAllNetworks();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNetworkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "createNetworkResponse")
    public JAXBElement<CreateNetworkResponse> createCreateNetworkResponse(CreateNetworkResponse value) {
        return new JAXBElement<CreateNetworkResponse>(_CreateNetworkResponse_QNAME, CreateNetworkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfilesByNetwork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getProfilesByNetwork")
    public JAXBElement<GetProfilesByNetwork> createGetProfilesByNetwork(GetProfilesByNetwork value) {
        return new JAXBElement<GetProfilesByNetwork>(_GetProfilesByNetwork_QNAME, GetProfilesByNetwork.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateNetworkVisibilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "updateNetworkVisibilityResponse")
    public JAXBElement<UpdateNetworkVisibilityResponse> createUpdateNetworkVisibilityResponse(UpdateNetworkVisibilityResponse value) {
        return new JAXBElement<UpdateNetworkVisibilityResponse>(_UpdateNetworkVisibilityResponse_QNAME, UpdateNetworkVisibilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinNetwork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "joinNetwork")
    public JAXBElement<JoinNetwork> createJoinNetwork(JoinNetwork value) {
        return new JAXBElement<JoinNetwork>(_JoinNetwork_QNAME, JoinNetwork.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNetworksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getAllNetworksResponse")
    public JAXBElement<GetAllNetworksResponse> createGetAllNetworksResponse(GetAllNetworksResponse value) {
        return new JAXBElement<GetAllNetworksResponse>(_GetAllNetworksResponse_QNAME, GetAllNetworksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateNetworkVisibility }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "updateNetworkVisibility")
    public JAXBElement<UpdateNetworkVisibility> createUpdateNetworkVisibility(UpdateNetworkVisibility value) {
        return new JAXBElement<UpdateNetworkVisibility>(_UpdateNetworkVisibility_QNAME, UpdateNetworkVisibility.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RejectNetworkRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "rejectNetworkRequest")
    public JAXBElement<RejectNetworkRequest> createRejectNetworkRequest(RejectNetworkRequest value) {
        return new JAXBElement<RejectNetworkRequest>(_RejectNetworkRequest_QNAME, RejectNetworkRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinNetworkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "joinNetworkResponse")
    public JAXBElement<JoinNetworkResponse> createJoinNetworkResponse(JoinNetworkResponse value) {
        return new JAXBElement<JoinNetworkResponse>(_JoinNetworkResponse_QNAME, JoinNetworkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfNetworkRequests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getNumberOfNetworkRequests")
    public JAXBElement<GetNumberOfNetworkRequests> createGetNumberOfNetworkRequests(GetNumberOfNetworkRequests value) {
        return new JAXBElement<GetNumberOfNetworkRequests>(_GetNumberOfNetworkRequests_QNAME, GetNumberOfNetworkRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNetwork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "createNetwork")
    public JAXBElement<CreateNetwork> createCreateNetwork(CreateNetwork value) {
        return new JAXBElement<CreateNetwork>(_CreateNetwork_QNAME, CreateNetwork.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveNetworkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "leaveNetworkResponse")
    public JAXBElement<LeaveNetworkResponse> createLeaveNetworkResponse(LeaveNetworkResponse value) {
        return new JAXBElement<LeaveNetworkResponse>(_LeaveNetworkResponse_QNAME, LeaveNetworkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfNetworkRequestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getNumberOfNetworkRequestsResponse")
    public JAXBElement<GetNumberOfNetworkRequestsResponse> createGetNumberOfNetworkRequestsResponse(GetNumberOfNetworkRequestsResponse value) {
        return new JAXBElement<GetNumberOfNetworkRequestsResponse>(_GetNumberOfNetworkRequestsResponse_QNAME, GetNumberOfNetworkRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveNetwork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "leaveNetwork")
    public JAXBElement<LeaveNetwork> createLeaveNetwork(LeaveNetwork value) {
        return new JAXBElement<LeaveNetwork>(_LeaveNetwork_QNAME, LeaveNetwork.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptNetworkRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "acceptNetworkRequest")
    public JAXBElement<AcceptNetworkRequest> createAcceptNetworkRequest(AcceptNetworkRequest value) {
        return new JAXBElement<AcceptNetworkRequest>(_AcceptNetworkRequest_QNAME, AcceptNetworkRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyNetworks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getMyNetworks")
    public JAXBElement<GetMyNetworks> createGetMyNetworks(GetMyNetworks value) {
        return new JAXBElement<GetMyNetworks>(_GetMyNetworks_QNAME, GetMyNetworks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetworkRequests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getNetworkRequests")
    public JAXBElement<GetNetworkRequests> createGetNetworkRequests(GetNetworkRequests value) {
        return new JAXBElement<GetNetworkRequests>(_GetNetworkRequests_QNAME, GetNetworkRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetworkRequestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getNetworkRequestsResponse")
    public JAXBElement<GetNetworkRequestsResponse> createGetNetworkRequestsResponse(GetNetworkRequestsResponse value) {
        return new JAXBElement<GetNetworkRequestsResponse>(_GetNetworkRequestsResponse_QNAME, GetNetworkRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyNetworksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getMyNetworksResponse")
    public JAXBElement<GetMyNetworksResponse> createGetMyNetworksResponse(GetMyNetworksResponse value) {
        return new JAXBElement<GetMyNetworksResponse>(_GetMyNetworksResponse_QNAME, GetMyNetworksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RejectNetworkRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "rejectNetworkRequestResponse")
    public JAXBElement<RejectNetworkRequestResponse> createRejectNetworkRequestResponse(RejectNetworkRequestResponse value) {
        return new JAXBElement<RejectNetworkRequestResponse>(_RejectNetworkRequestResponse_QNAME, RejectNetworkRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptNetworkRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "acceptNetworkRequestResponse")
    public JAXBElement<AcceptNetworkRequestResponse> createAcceptNetworkRequestResponse(AcceptNetworkRequestResponse value) {
        return new JAXBElement<AcceptNetworkRequestResponse>(_AcceptNetworkRequestResponse_QNAME, AcceptNetworkRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfilesByNetworkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getProfilesByNetworkResponse")
    public JAXBElement<GetProfilesByNetworkResponse> createGetProfilesByNetworkResponse(GetProfilesByNetworkResponse value) {
        return new JAXBElement<GetProfilesByNetworkResponse>(_GetProfilesByNetworkResponse_QNAME, GetProfilesByNetworkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNetworks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.genspace.components.geworkbench.org/", name = "getAllNetworks")
    public JAXBElement<GetAllNetworks> createGetAllNetworks(GetAllNetworks value) {
        return new JAXBElement<GetAllNetworks>(_GetAllNetworks_QNAME, GetAllNetworks.class, null, value);
    }

}
