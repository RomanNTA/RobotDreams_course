package cz.robodreams.javadeveloper.project.control.client;
public class ClientProvider {

    public static Client getNewClientInstance(){
        return new ClientImpl();
    }

}
