package de.yy18.nettyserver.server.packets.in;

import de.yy18.nettyserver.server.packets.Packet;

import java.util.Arrays;

public abstract class PacketPlayIn implements Packet, IPacketPlayIn{

    protected final byte[] content;
    protected int readPos = 0;

    PacketPlayIn(final byte[] bytes) {
        this.content = bytes;
    }

    public boolean readBoolean() {
        final byte[] clone = content.clone();
        increase(1);
        return clone[readPos] == 1;
    }

    public short readShort() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1]};
        increase(2);
        return (short)((value[0] & 0xFF | (value[1] & 0xFF) << 8));
    }

    public int readInt() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]};
        increase(4);
        return value[0] & 0xFF | (value[1] & 0xFF) << 8 | (value[2] & 0xFF) << 16 | value[3] << 24;
    }

    public long readLong() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]
                , clone[readPos+4], clone[readPos+5], clone[readPos+6], clone[readPos+7]};
        increase(8);
        return (long) value[0] << 56 | (value[1] & 0xFF) << 48 | (value[2] & 0xFF) << 40 | (value[3] & 0xFF) << 32| (value[4] & 0xFF) << 24
                | (value[5] & 0xFF) << 16 | (value[6] & 0xFF) << 8 | (value[7] & 0xFF);
    }

   /*
    public void readFloat() {
        writeValue(Float.floatToIntBits(f));
    }
   */
    public String readString(int length) {
        final byte[] clone = content.clone();
        final byte[] values = Arrays.copyOfRange(clone, readPos , readPos+length);
        return new String(values, 0);
    }

    private void increase(int i) {
        this.readPos += i;
    }

}
