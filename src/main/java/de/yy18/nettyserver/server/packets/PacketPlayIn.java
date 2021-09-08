package de.yy18.nettyserver.server.packets;

import lombok.NonNull;


public abstract class PacketPlayIn extends Packet{

    private final byte[] content;
    private int readPos = 0;

    PacketPlayIn(@NonNull PacketType packetType, final byte[] bytes) {
        super(packetType);
        this.content = bytes;
    }

    public boolean readValue(boolean b) {
        final byte[] clone = content.clone();
        increase(1);
        return clone[readPos] == 1;
    }
    public short readValue(short s) {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1]};
        increase(2);
        return (short)(((value[0] & 0xFF) << 8) | (value[1] & 0xFF));
    }

    public int readValue(int i) {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]};
        increase(4);
        return value[0] << 24 | (value[1] & 0xFF) << 16 | (value[2] & 0xFF) << 8 | (value[3] & 0xFF);
    }
    public long readValue(long l) {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]
                , clone[readPos+4], clone[readPos+5], clone[readPos+6], clone[readPos+7]};
        increase(8);
        return (long) value[0] << 56 | (value[1] & 0xFF) << 48 | (value[2] & 0xFF) << 40 | (value[3] & 0xFF) << 32| (value[4] & 0xFF) << 24
                | (value[5] & 0xFF) << 16 | (value[6] & 0xFF) << 8 | (value[7] & 0xFF);
    }
   /* public void readValue(float f) {
        writeValue(Float.floatToIntBits(f));
    }
    public void readValue(@NonNull String s) {
        writeValue(s.length());
        writeValue(s.getBytes(StandardCharsets.US_ASCII));
    }
*/
    private void increase(int i) {
        this.readPos += i;
    }

    abstract void decodePacket();

}
