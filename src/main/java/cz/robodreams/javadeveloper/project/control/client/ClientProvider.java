package cz.robodreams.javadeveloper.project.control.client;
public class ClientProvider {

    public static Client getNewClientInstance(){
        // change this according your needs
        return new ClientImpl();
    }

}
