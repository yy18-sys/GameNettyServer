package de.yy18.nettyserver.server.packets;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class PacketPlayOut implements Packet, IPacketPlayOut {

    private final List<Byte> content = new ArrayList<Byte>();

    public void writeValue(byte[] bytes) {
        content.addAll(getByteList(bytes));
    }
    public void writeValue(boolean b) {
        writeValue(b ? (byte)1 : (byte)0);
    }
    public void writeValue(short s) {
        writeValue(new byte[]{(byte)((s>>8)&0xFF),(byte)(s&0xFF)});
    }
    public void writeValue(int i) {
        writeValue(new byte[]{(byte)((i>>24)&0xFF),(byte)((i>>16)&0xFF),(byte)((i>>8)&0xFF),(byte)(i&0xFF)});
    }
    public void writeValue(long l) {
        writeValue(new byte[]{(byte)((l>>56)&0xFF),(byte)((l>>48)&0xFF),(byte)((l>>40)&0xFF),(byte)((l>>32)&0xFF)
                ,(byte)((l>>24)&0xFF),(byte)((l>>16)&0xFF),(byte)((l>>8)&0xFF),(byte)(l&0xFF)});
    }
    public void writeValue(float f) {
        writeValue(Float.floatToIntBits(f));
    }
    public void writeValue(@NonNull String s) {
        writeValue(s.length());
        writeValue(s.getBytes(StandardCharsets.US_ASCII));
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
