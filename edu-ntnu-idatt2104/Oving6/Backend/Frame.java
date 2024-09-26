import java.io.*;
import java.nio.ByteBuffer;

public class Frame {

  public static final byte OP_TEXT = 0x1;
  boolean FIN = true;
  boolean RSV1 = false;
  boolean RSV2 = false;
  boolean RSV3 = false;

  int opcode = 0;
  boolean MASK = false;
  long size = 0;
  int mask = 0;
  byte[] payload = null;

  public byte[] transmitFrame() {
    byte first_byte = 0;
    first_byte |= (byte) (FIN ? 128 : 0);
    first_byte |= (byte) (RSV1 ? 64 : 0);
    first_byte |= (byte) (RSV2 ? 32 : 0);
    first_byte |= (byte) (RSV3 ? 16 : 0);
    first_byte |= (byte) (opcode & 15);

    byte second_byte = 0;
    second_byte |= (byte) (MASK ? 128 : 0);

    byte[] size_bytes;
    if (size <= 125) {
      second_byte |= (byte) (size & 127);
      size_bytes = new byte[0];
    } else if (size <= 32767) {
      second_byte |= 126;
      size_bytes = ByteBuffer.allocate(2).putShort((short) size).array();
    } else {
      second_byte |= 127;
      size_bytes = ByteBuffer.allocate(8).putLong(size).array();
    }

    byte[] mask_bytes = new byte[0];
    if (MASK)
      mask_bytes = ByteBuffer.allocate(4).putInt(mask).array();

    int header_length = 2;
    header_length += size_bytes.length;
    header_length += MASK ? Integer.BYTES : 0;

    byte[] header_bytes = new byte[header_length];
    header_bytes[0] = first_byte;
    header_bytes[1] = second_byte;
    System.arraycopy(size_bytes, 0, header_bytes, 2, size_bytes.length);
    System.arraycopy(mask_bytes, 0, header_bytes, 2 + size_bytes.length, mask_bytes.length);

    return header_bytes;
  }

  public void transmit(OutputStream output_stream) throws IOException {
    output_stream.write(transmitFrame());
    if (size > 0)
      output_stream.write(payload);
  }

  public static Frame parseFrame(InputStream input_stream) throws IOException {
    DataInputStream in = new DataInputStream(input_stream);
    Frame frame = new Frame();
    final int first_byte = in.readByte();
    final int second_byte = in.readByte();

    // Handle first byte
    // Checks if the frame is final
    frame.FIN = (first_byte & 128) != 0;
    // Checks if the frame is reserved
    frame.RSV1 = (first_byte & 64) != 0;
    // Checks if the frame is reserved
    frame.RSV2 = (first_byte & 32) != 0;
    // Checks if the frame is reserved
    frame.RSV3 = (first_byte & 16) != 0;
    // Gets the opcode
    frame.opcode = first_byte & 15;

    // Handle second byte
    // Checks if the frame is masked
    frame.MASK = (second_byte & 128) != 0;
    // Gets the size of the frame
    frame.size = second_byte & 127;

    switch (second_byte & 127) {
      // If the size is 127, the next 8 bytes are the size
      case 127 -> frame.size = in.readLong();
      // If the size is 126, the next 2 bytes are the size
      case 126 -> frame.size = in.readShort();
      default -> {
      }
    }

    if (frame.MASK)
      // Gets the mask
      frame.mask = in.readInt();

    // Allocates the payload the size of the frame
    frame.payload = new byte[(int) frame.size];

    long bytes_read = 0;
    while (bytes_read < frame.size)
      bytes_read += in.read(frame.payload, (int) bytes_read, (int) (frame.size - bytes_read));

    return frame;
  }

  public static void mask_payload(Frame frame) {
    // If the frame is not masked, throw an exception
    if (!frame.MASK || frame.mask == 0)
      throw new IllegalArgumentException("This frame has no mask");

    // Create a mask array
    byte[] mask = new byte[4];
    // Set the mask array to the mask of the frame
    // The mask is a 32-bit integer
    // Shift the mask 24 bits to the right and cast it to a byte
    mask[0] = (byte) (frame.mask >> 24);
    // Shift the mask 16 bits to the right and cast it to a byte
    mask[1] = (byte) (frame.mask >> 16);
    // Shift the mask 8 bits to the right and cast it to a byte
    mask[2] = (byte) (frame.mask >> 8);
    // Cast the mask to a byte
    mask[3] = (byte) (frame.mask);

    for (long i = 0; i < frame.size; i++)
      // XOR the payload with the mask with the index of i modulo 4
      frame.payload[(int) i] ^= mask[(int) (i % 4)];

    frame.MASK = false;
  }
}
