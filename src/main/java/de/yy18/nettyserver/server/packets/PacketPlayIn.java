package de.yy18.nettyserver.server.packets;

import lombok.NonNull;


public abstract class PacketPlayIn implements Packet, IPacketPlayIn{

    private final byte[] content;
    private int readPos = 0;

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
        return (short)(((value[0] & 0xFF) << 8) | (value[1] & 0xFF));
    }

    public int readInt() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]};
        increase(4);
        return value[0] << 24 | (value[1] & 0xFF) << 16 | (value[2] & 0xFF) << 8 | (value[3] & 0xFF);
    }

    public long readLong() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]
                , clone[readPos+4], clone[readPos+5], clone[readPos+6], clone[readPos+7]};
        increase(8);
        return (long) value[0] << 56 | (value[1] & 0xFF) << 48 | (value[2] & 0xFF) << 40 | (value[3] & 0xFF) << 32| (value[4] & 0xFF) << 24
                | (value[5] & 0xFF) << 16 | (value[6] & 0xFF) << 8 | (value[7] & 0xFF);
    }

   /* public void readFloat() {
        writeValue(Float.floatToIntBits(f));
    }
   */
    public String readString() {
        final byte[] clone = content.clone();
        final byte[] values = getByteStringArray(clone);
        return new String(values, 0);
    }

    private byte[] getByteStringArray(byte[] bytes) {
        final byte[] current = new byte[bytes.length-readPos];
        for (int i = readPos; i < bytes.length; i++) {
            current[i-readPos] = bytes[i];
        }
        return current;
    }

    private void increase(int i) {
        this.readPos += i;
    }

}
