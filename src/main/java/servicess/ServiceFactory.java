package servicess;

import servicess.custom.impl.Book_Service_impl;
import servicess.custom.impl.Memeber_Servic_impl;
import servicess.custom.impl.Staff_Servic_impl;
import utill.ServiceType;

public class ServiceFactory {
    public static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance == null ? instance = new ServiceFactory() : instance;
    }

    public <T extends Super_Service> T getServiceType(ServiceType serviceType){
        switch (serviceType) {
            case AddBooks: return (T) new Book_Service_impl();
            case AddMemeber: return (T) new Memeber_Servic_impl();
            case AddStaff: return (T) new Staff_Servic_impl();
        }
        return null;
    }
}
