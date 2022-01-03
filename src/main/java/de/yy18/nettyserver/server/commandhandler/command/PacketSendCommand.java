package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.packets.PacketPlayOutHandler;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import de.yy18.nettyserver.server.user.User;
import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.ConsoleWriter;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class PacketSendCommand implements Command{

    @Override
    public void executeCommand(@NotNull @NonNull String[] strings) {
        if(strings.length >= 3) {
            try {
                final String stringUuid = strings[1];
                final UUID uuid = UUID.fromString(stringUuid);
                final User user = UserManager.getINSTANCE().getUserByUUID(uuid);
                if(user == null) {
                    System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                            +" ServerInfo] User not found");
                    return;
                }
                final short packetTypeNumber = Short.parseShort(strings[2]);
                ConsoleWriter.write(String.valueOf(packetTypeNumber));
                if(packetTypeNumber % 2 != 0) return;
                for (PacketType packetType : PacketType.values()) {
                    if(packetTypeNumber == packetType.getType()) {
                        final Constructor<?> constructor = packetType.getAClass().getConstructors()[0];
                        try {
                            final int parameterCount = constructor.getParameterCount();
                            final List<Object> objectList = new ArrayList<>();
                            final Class<?>[] aClass = constructor.getParameterTypes();
                            ConsoleWriter.write(Arrays.toString(aClass));
                            for (int i = 0; i < parameterCount; i++) {
                                ConsoleWriter.write("test7");
                                ConsoleWriter.write(Arrays.toString(aClass[i].getConstructors()));
                                objectList.add(aClass[i].getConstructors()[0].newInstance(strings[3+i]));
                            }
                            ConsoleWriter.write("test7");
                            final Object[] paramObject = objectList.toArray(new Object[0]);
                            ConsoleWriter.write("tes8t");
                            final PacketPlayOut packet = (PacketPlayOut) constructor.newInstance(paramObject
                                    , user.getSocket().getRemoteSocketAddress());
                            ConsoleWriter.write("test9");
                            PacketPlayOutHandler.sendPacket(packet, user.getSocket());
                            ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                                    +" ServerInfo] Packet sended to client "+uuid);
                        } catch (Exception e) {
                            ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                                    +" ServerInfo] Parameter are not valid");
                            return;
                        }
                    }
                }
                ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                        +" ServerInfo] Packet not found");
            } catch (Exception exception) {
                ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                        +" ServerInfo] Error by handling the packet" +
                        " use: send [uuid] [packetID] [attributes]");
            }
        } else {
            ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                    +" ServerInfo] Command: send [uuid] [packetID] [attributes]");
        }
    }

}
