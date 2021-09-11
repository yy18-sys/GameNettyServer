package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.user.User;
import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;

import java.text.ParseException;
import java.util.Arrays;
import java.util.UUID;

public final class UserManagerCommand implements Command{

    @Override
    public void executeCommand(@NonNull String[] strings) {
        switch (strings.length) {
            case 1:
                getInfo();
                break;
            case 2:
                switch (strings[1]) {
                    case "list":
                        try {
                            System.out.println("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] User list:");
                            System.out.println(UserManager.getINSTANCE().getAllUserConsole());
                        } catch (ParseException ignored) {

                        }
                        break;
                    default:
                        getInfo();
                        break;
                }
                break;

            case 3:
                switch (strings[1]) {
                    case "remove":
                        try {
                            final String stringUuid = strings[2];
                            final UUID uuid = UUID.fromString(stringUuid);
                            final User user = UserManager.getINSTANCE().getUserByUUID(uuid);
                            if(user == null) {
                                System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                                        +" ServerInfo] User not found");
                                return;
                            } else {
                                UserManager.getINSTANCE().closeConnection(user);
                                System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                                        +" ServerInfo] User {[uuid]: "+user.getUuid()
                                        +", [name]: "+user.getUserName()+"} got disconnected");
                            }
                        } catch (Exception exception) {
                            System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                                    +" ServerInfo] No valid UUID");
                        }
                        break;
                    default:
                        getInfo();
                        break;
                }
                break;
        }
    }

    private void getInfo() {
        System.out.println("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] Command info:");
        System.out.println("                      usermanager info - shows usermanager info");
        System.out.println("                      usermanager list - shows all connected users");
        System.out.println("                      usermanager remove [uuid] - removes a user");
    }


}
