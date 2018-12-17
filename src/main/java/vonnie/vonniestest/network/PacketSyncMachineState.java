package vonnie.vonniestest.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import vonnie.vonniestest.tools.IMachineStateContainer;
import vonnie.vonniestest.vonniestest;

public class PacketSyncMachineState implements IMessage {

    private int energy;
    private int progress; // Store from 0 to 100
    @Override
    public void fromBytes(ByteBuf buf) {
        energy = buf.readInt();
        progress = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(energy);
        buf.writeByte(progress);
    }

    // You need this constructor!
    public PacketSyncMachineState(){
    }

    public PacketSyncMachineState(int energy, int progress) {
        this.energy = energy;
        this.progress = progress;
    }

    public static class Handler implements IMessageHandler<PacketSyncMachineState, IMessage> {
        @Override
        public IMessage onMessage(PacketSyncMachineState message, MessageContext ctx) {
            vonniestest.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }
        private void handle(PacketSyncMachineState message, MessageContext ctx) {
            EntityPlayer player = vonniestest.proxy.getClientPlayer();
            if (player.openContainer instanceof IMachineStateContainer) {
                ((IMachineStateContainer) player.openContainer).sync(message.energy, message.progress);
            }
        }
    }

}
