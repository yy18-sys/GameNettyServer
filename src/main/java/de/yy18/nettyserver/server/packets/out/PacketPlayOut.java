package de.yy18.nettyserver.server.packets.out;

import de.yy18.nettyserver.server.packets.Packet;
import de.yy18.nettyserver.server.packets.PacketType;
import lombok.Getter;
import lombok.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class PacketPlayOut implements Packet, IPacketPlayOut {

    private final List<Byte> content = new ArrayList<Byte>();
    @Getter
    private final PacketType packetType;

    public PacketPlayOut(@NonNull final PacketType packetType) {
        this.packetType = packetType;
    }

    public void writeValue(byte[] bytes) {
        content.addAll(getByteList(bytes));
    }
    public void writeValue(boolean b) {
        writeValue(b ? (byte)1 : (byte)0);
    }
    public void writeValue(short s) {
        writeValue(new byte[]{(byte)(s&0xFF), (byte)((s>>8)&0xFF)});
    }
    public void writeValue(int i) {
        writeValue(new byte[]{(byte)(i&0xFF), (byte)((i>>8)&0xFF), (byte)((i>>16)&0xFF), (byte)((i>>24)&0xFF)});
    }
    public void writeValue(long l) {
        writeValue(new byte[]{(byte)(l&0xFF), (byte)((l>>8)&0xFF), (byte)((l>>16)&0xFF), (byte)((l>>24)&0xFF)
                , (byte)((l>>32)&0xFF), (byte)((l>>40)&0xFF), (byte)((l>>48)&0xFF), (byte)((l>>56)&0xFF)});
    }
    public void writeValue(float f) {
        writeValue(Float.floatToRawIntBits(f));
    }
    public void writeValue(@NonNull String s) {
        writeValue(s.length());
        writeValue(s.getBytes(StandardCharsets.US_ASCII));
    }
    public byte[] getContent() {
        final List<Byte> byteList = this.content.stream().toList();
        final byte[] contents = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            contents[i] = byteList.get(i);
        }
        return contents;
    }

    @NonNull
    private List<Byte> getByteList(byte[] bytes) {
        final List<Byte> byteList = new ArrayList<>();
        for (byte b: bytes) {
            byteList.add(b);
        }
        return byteList;
    }


}
